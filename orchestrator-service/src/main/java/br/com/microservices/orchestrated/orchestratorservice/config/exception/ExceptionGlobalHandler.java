package br.com.microservices.orchestrated.orchestratorservice.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ExceptionGlobalHandler {
    @ExceptionHandler(ValidationExeption.class)
    public ResponseEntity<?> handlerValidationException(ValidationExeption validationExeption){
          var details = new ExceptionDetails(HttpStatus.BAD_REQUEST.value(), validationExeption.getMessage());
            return  new ResponseEntity<>(details,HttpStatus.BAD_REQUEST);
    }
}
