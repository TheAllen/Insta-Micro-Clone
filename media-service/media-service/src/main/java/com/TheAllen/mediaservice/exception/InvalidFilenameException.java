package com.TheAllen.mediaservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFilenameException extends RuntimeException {

    public InvalidFilenameException(String error) {
        super(error);
    }

    public InvalidFilenameException(String error, Throwable cause) {
        super(error, cause);
    }
}
