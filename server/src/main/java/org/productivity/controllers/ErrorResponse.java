package org.productivity.controllers;

import org.productivity.domain.Result;
import org.productivity.domain.ResultType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorResponse {
    
    private final LocalDateTime timestamp = LocalDateTime.now();
    
    private final String message;

    public String getTimestamp() {
        // custom formatter (excluded small fraction of changes like second and millisecond) to prevent timestamp error while testing
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return timestamp.format(formatter);
    }

    public ErrorResponse(String message) {
            this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static ResponseEntity<ErrorResponse> build(String message) {
        return new ResponseEntity<>(new ErrorResponse(message),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity<Object> build(Result<T> result) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (result.getType() == null || result.getType() == ResultType.INVALID) {
            status = HttpStatus.BAD_REQUEST;
        } else if (result.getType() == ResultType.NOT_FOUND) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(result.getMessages(), status);
    }
}
