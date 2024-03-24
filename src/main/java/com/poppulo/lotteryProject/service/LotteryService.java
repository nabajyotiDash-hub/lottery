package com.poppulo.lotteryProject.service;

import com.poppulo.lotteryProject.model.Ticket;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service class for Lottery end points
 */
@Service
@Scope("singleton")
public interface LotteryService {
    Ticket generateTicket(int numberOfLines);

    Ticket checkTicketStatus(Long ticketId);

    Ticket getTicket(Long ticketId);

    List<Ticket> getTickets();

    Ticket amendTicket(Long ticketId, int additionalLines);
}
