package com.poppulo.lotteryProject.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;

/**
 *  Exception handler across all controllers
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    ErrorResponse errorResponse;

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * handles ConstraintViolationException and sends 400 status code
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        logger.error("ConstraintViolationException : \n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        errorResponse.setMessage("Validation failed");
        errorResponse.setDetails(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    /**
     * handles IdNotFoundException and sends 404 status code
     */
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(IdNotFoundException e) {
        logger.error("IdNotFoundException : \n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        errorResponse.setMessage("Element Not Found");
        errorResponse.setDetails(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }


    /**
     * handles TicketStatusIsCheckedException and sends 403 status code
     */
    @ExceptionHandler(TicketStatusIsCheckedException.class)
    public ResponseEntity<ErrorResponse> handleTicketStatusIsCheckedException(TicketStatusIsCheckedException e) {
        logger.error("TicketStatusIsCheckedException : \n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        errorResponse.setMessage("Ticket status already Checked");
        errorResponse.setDetails(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(errorResponse);
    }

    /**
     * handles any other unmentioned Exceptions with response code 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnmentionedExceptions(Exception e){
        errorResponse.setMessage("Something Went Wrong. Please try Again..");
        errorResponse.setDetails(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * handles Invalid requests and sends 400 status code
     */
    @ExceptionHandler({HttpMessageNotReadableException.class,MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleInvalidReq(Exception e) {
        logger.error("InvalidRequestException : \n" + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        errorResponse.setMessage("Invalid request");
        errorResponse.setDetails(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }


}
