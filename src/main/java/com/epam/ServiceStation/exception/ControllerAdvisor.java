package com.epam.ServiceStation.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RegistrationExpiredException.class)
    public ResponseEntity<Object> handleRegistrationExpire(
            RegistrationExpiredException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Vehicle Registration Expired");

        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    }
    //@ResponseStatus(HttpStatus.BAD_REQUEST)

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InsuranceIssueException.class)
    public ResponseEntity<Object> handleInsuranceIssue(
            InsuranceIssueException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Vehicle Insurance Expired");

        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(PoliceVerificatioFailException.class)
    public ResponseEntity<Object> handleInsuranceIssue(
            PoliceVerificatioFailException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Police BGV Failed");

        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    }
}
