package com.hctec.candidates.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleInterviewIdException(InterviewIdException ex, WebRequest request){
        InterviewIdExceptionResponse exceptionResponse = new InterviewIdExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public final ResponseEntity<Object> handleInterviewNotFoundException(InterviewNotFoundException ex, WebRequest request){
        InterviewNotFoundExceptionResponse exceptionResponse = new InterviewNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}