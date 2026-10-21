package edu.altair.apicrm.exceptions;

public class AlumnoNotFoundException extends RuntimeException {
    public AlumnoNotFoundException(String mensaje) {
        super(mensaje);
    }
}

