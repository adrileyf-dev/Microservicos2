package br.com.microservices.orchestrated.paymentservice.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationExeption  extends RuntimeException{
    public ValidationExeption(String message){
        super(message);
    }

}
