package edu.altair.apicrm.exceptions;

public class FaltaNotFoundException extends RuntimeException {
    public FaltaNotFoundException(String mensaje) {
        super(mensaje);
    }
}