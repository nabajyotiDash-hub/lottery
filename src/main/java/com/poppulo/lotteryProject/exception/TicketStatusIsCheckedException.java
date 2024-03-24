package com.poppulo.lotteryProject.exception;

/**
 * Exception Class
 * Thrown when UPDATE operation is tried on a checked ticket.
 * When encountered, application should return HttpStatus.FORBIDDEN
 */
public class TicketStatusIsCheckedException extends RuntimeException{

    public TicketStatusIsCheckedException(String message){
        super(message);
    }
}
