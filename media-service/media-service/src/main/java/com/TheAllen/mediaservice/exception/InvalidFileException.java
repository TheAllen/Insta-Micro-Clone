package com.TheAllen.mediaservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFileException extends RuntimeException {

    public InvalidFileException(String error) {
        super(error);
    }

    public InvalidFileException(String error, Throwable cause) {
        super(error, cause);
    }
}
