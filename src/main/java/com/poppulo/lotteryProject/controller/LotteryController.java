package com.poppulo.lotteryProject.controller;

import com.poppulo.lotteryProject.dto.LotteryDto;
import com.poppulo.lotteryProject.model.Ticket;
import com.poppulo.lotteryProject.service.LotteryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lottery")
public class LotteryController {

    @Autowired
    private LotteryService lotteryService;

    /**
     * @param lotteryDto
     * @return ResponseEntity<?>
     *
     * Creates a new Ticket with given number of lines
     */
    @Operation(summary = "Create A Ticket")
    @PostMapping("/ticket")
    public ResponseEntity<?> generateTicket(@Valid @RequestBody LotteryDto lotteryDto) {
        return new ResponseEntity<>(lotteryService.generateTicket(lotteryDto.getNumberOfLines()), HttpStatus.CREATED);
    }

    /**
     * @return ResponseEntity<List<Ticket>>
     *
     * gets all tickets
     */
    @Operation(summary = "Get All Tickets")
    @GetMapping("/ticket")
    public ResponseEntity<List<Ticket>> getTickets() {
        return ResponseEntity.ok(lotteryService.getTickets());
    }

    /**
     * @param id
     * @return ResponseEntity<Ticket>
     *
     * get the ticket for a given id
     */
    @Operation(summary = "Get A Ticket From Id")
    @GetMapping("/ticket/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
        return ResponseEntity.ok(lotteryService.getTicket(id));
    }

    /**
     * @param id
     * @param lotteryDto
     * @return ResponseEntity<Ticket>
     *
     * amend the ticket with 'n' additional lines
     */
    @Operation(summary = "Amend A Ticket")
    @PutMapping("/ticket/{id}")
    public ResponseEntity<Ticket> amendTicket(@PathVariable Long id, @Valid @RequestBody LotteryDto lotteryDto) {
        return new ResponseEntity<>(lotteryService.amendTicket(id, lotteryDto.getNumberOfLines()), HttpStatus.ACCEPTED);
    }

    /**
     * @param id
     * @return ResponseEntity<Ticket>
     *
     * update the status of a ticket
     */
    @Operation(summary = "Update Status Of A Ticket")
    @PutMapping("/status/{id}")
    public ResponseEntity<Ticket> checkTicketStatus(@PathVariable Long id) {
        return ResponseEntity.ok(lotteryService.checkTicketStatus(id));
    }
}
