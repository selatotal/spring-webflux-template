package br.com.selat.template.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ServiceValidationException extends ResponseStatusException {

    public ServiceValidationException(){
        super(HttpStatus.BAD_REQUEST);
    }

    public ServiceValidationException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }

    public ServiceValidationException(String message, Throwable exception){
        super(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
    }

}
