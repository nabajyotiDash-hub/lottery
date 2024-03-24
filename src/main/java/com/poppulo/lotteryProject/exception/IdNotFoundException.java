package com.poppulo.lotteryProject.exception;

/**
 * Exception Class
 * Thrown when a requested id is not present in Database
 * When encountered, application should return HttpStatus.NOT_FOUND
 */
public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String message){
        super(message);
    }

    public IdNotFoundException(){
        super();
    }
}

