package edu.altair.apicrm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {


    // Manejo de Error 404 - Recurso no encontrado
    @ExceptionHandler(AlumnoNotFoundException.class)
    public ProblemDetail manejarAlumnoNoEncontrado(AlumnoNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problem.setTitle("Alumno No Encontrado");
        problem.setType(URI.create("https://api.crm.altair.edu/errors/not-found"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // Manejo de Error 409 - Conflicto de duplicidad de DNI
    @ExceptionHandler(DniAlreadyExistsException.class)
    public ProblemDetail manejarDniDuplicado(DniAlreadyExistsException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
        problem.setTitle("Conflicto de Datos");
        problem.setType(URI.create("https://api.crm.altair.edu/errors/conflict"));
        problem.setProperty("timestamp", Instant.now());
        return problem;

        
    }
    // Manejo de Error 404 - Falta no encontrada
    @ExceptionHandler(FaltaNotFoundException.class)
    public ProblemDetail manejarFaltaNoEncontrada(FaltaNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problem.setTitle("Falta No Encontrada");
        problem.setType(URI.create("https://api.crm.altair.edu/errors/not-found"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    // Manejo de Error 409 - Conflicto por falta duplicada
    @ExceptionHandler(FaltaDuplicadaException.class)
    public ProblemDetail manejarFaltaDuplicada(FaltaDuplicadaException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
        problem.setTitle("Conflicto de Falta");
        problem.setType(URI.create("https://api.crm.altair.edu/errors/conflict"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }
}

