package com.poppulo.lotteryProject.service;

import com.poppulo.lotteryProject.exception.IdNotFoundException;
import com.poppulo.lotteryProject.exception.TicketStatusIsCheckedException;
import com.poppulo.lotteryProject.model.Line;
import com.poppulo.lotteryProject.model.Ticket;
import com.poppulo.lotteryProject.repository.LotteryLineRepository;
import com.poppulo.lotteryProject.repository.LotteryTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * service class implementation for Lottery end points
 */
@Service
@Scope("singleton")
public class LotteryServiceImpl implements LotteryService{
    @Autowired
    private LotteryTicketRepository ticketRepository;

    @Autowired
    private LotteryLineRepository lineRepository;


    private static final Logger logger = LoggerFactory.getLogger(LotteryServiceImpl.class);

    /**
     * @param numberOfLines (Long)
     * @return Ticket
     *
     * creates a new ticket
     */
    @Override
    @Transactional
    public Ticket generateTicket(int numberOfLines) {
        Ticket ticket = new Ticket();
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < numberOfLines; i++) {
            Line line = new Line();
            lines.add(line);
        }
        ticket.setLines(lines);

        logger.info("Creating a Ticket with {} lines ", numberOfLines);
        return ticketRepository.save(ticket);
    }

    /**
     * @param ticketId (Long)
     * @return ticket  (Ticket)
     *
     * Updates the status of the ticket.
     * If the status is already checked throws TicketStatusIsCheckedException
     */
    @Override
    @Transactional
    public Ticket checkTicketStatus(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IdNotFoundException("Ticket not found with id: " + ticketId));
        if (ticket.isChecked()) {
            throw new TicketStatusIsCheckedException("Ticket status has already been checked For TicketId "+ ticketId +
                    " . Can not be performed again");
        }
        List<Line> lines = ticket.getLines();
        for (Line line : lines) {
            line.setResult(calculateResult(line));
        }
        ticket.setChecked(true);
        logger.info("Updating the status of Ticket with TicketId {} ", ticketId);
        ticketRepository.save(ticket);
        return ticket;
    }

    /**
     * @param ticketId (Long)
     * @return Ticket
     *
     * Get the ticket with mentioned ticketId
     */
    @Override
    public Ticket getTicket(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new IdNotFoundException("Ticket not found with id: " + ticketId));
    }

    /**
     * @return List<Ticket>
     *
     * Get all tickets
     */
    @Override
    public List<Ticket> getTickets() {
        List<Ticket> tickets = new ArrayList<>();
        ticketRepository.findAll().forEach(tickets::add);
        return tickets;
    }

    /**
     * @param line (Line)
     * @return result (int)
     *
     * if the sum of the values on a line is 2, the result for that line is 10.
     * Else if they are all the same, the result is 5.
     * Else if both 2nd and 3rd numbers are different from the 1st, the result is 1.
     * Else the result is 0.
     */
    int calculateResult(Line line) {
        int sum = line.getNumber1() + line.getNumber2() + line.getNumber3();
        if (sum == 2) {
            return 10;
        } else if (line.getNumber1() == line.getNumber2() && line.getNumber2() == line.getNumber3()) {
            return 5;
        } else if (line.getNumber1() != line.getNumber2() && line.getNumber1() != line.getNumber3()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * @param ticketId (Long)
     * @param additionalLines (int)
     * @return ticket (Ticket)
     *
     * amends 'n' lines to a ticket
     * if the status of the ticket is already checked then throws TicketStatusIsCheckedException
     */
    @Override
    @Transactional
    public Ticket amendTicket(Long ticketId, int additionalLines) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new IdNotFoundException("Ticket not found with id: " + ticketId));
        if (ticket.isChecked()) {
            throw new TicketStatusIsCheckedException("Ticket status has already been checked For TicketId "+ ticketId +
                    " . Lines Can not be amended anymore");
        }
        List<Line> lines = ticket.getLines();
        for (int i = 0; i < additionalLines; i++) {
            lines.add(new Line());
        }
        ticket.setLines(lines);
        logger.info("Amending {} lines to ticket with ticketId {} ", additionalLines ,ticketId);
        return ticketRepository.save(ticket);
    }
}
