package com.company.showtime.exceptions;

/**
 * This is a custom Exception made by me for error handling.
 */
public class CustomException extends Exception{

    /**
     * Constructs a new CustomException with the specified error message.
     * @param message the error message for the exception
     */
    public CustomException(String message){
        super(message);
    }
    /**
     * Constructs a new CustomException with the specified error message and cause.
     * @param message the error message for the exception
     * @param cause the cause of the exception, which may be null
     */
    public CustomException(String message, Throwable cause){
        // In case of implementation specific errors - Specific errors we wish to catch
        super(message, cause);
    }
}

