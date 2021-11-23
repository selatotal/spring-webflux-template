package br.com.selat.template.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InternalErrorException extends ResponseStatusException {

    public InternalErrorException(){
        super(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public InternalErrorException(String message){
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public InternalErrorException(Throwable exception){
        super(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
    }

    public InternalErrorException(String message, Throwable exception){
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, exception);
    }
}
