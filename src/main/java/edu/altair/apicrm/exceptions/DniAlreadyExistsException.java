package edu.altair.apicrm.exceptions;

public class DniAlreadyExistsException extends RuntimeException {
    public DniAlreadyExistsException(String mensaje) {
        super(mensaje);
    }
}

