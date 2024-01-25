package org.example.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class ErrorHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ValidationException.class})
    public ErrorResponse handleValidationException(RuntimeException e){
        return new ErrorResponse(400, "BAD_REQUEST", e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityDoesNotExistException.class})
    public ErrorResponse handleEntityDoesNotExistException(RuntimeException e){
        return new ErrorResponse(404, "NOT_FOUND", e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({EntityAlreadyExistsException.class})
    public ErrorResponse handleEntityAlreadyExistsException(RuntimeException e){
        return new ErrorResponse(409, "CONFLICT", e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({WrongPasswordException.class})
    public ErrorResponse handleWrongPassword(RuntimeException e){
        return new ErrorResponse(401, "UNAUTHORIZED", e.getMessage());
    }
}
