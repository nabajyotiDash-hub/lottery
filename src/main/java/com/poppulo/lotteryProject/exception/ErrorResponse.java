package com.poppulo.lotteryProject.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

/**
 * Response object to be returned in case of any error/exception
 */
@Component
@JsonIgnoreProperties(value = { "mockitoInterceptor", "mockHandler", "invocationContainer" })
public class ErrorResponse {
    String message;
    String details;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
