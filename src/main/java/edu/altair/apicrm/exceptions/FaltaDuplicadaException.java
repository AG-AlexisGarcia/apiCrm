package edu.altair.apicrm.exceptions;

public class FaltaDuplicadaException extends RuntimeException {
    public FaltaDuplicadaException(String mensaje) {
        super(mensaje);
    }
}