package edu.altair.apicrm.dtos;
import java.time.LocalDate;
public record AlumnoResponseDto(
        Long idAlumno,
        String dni,
        String nombre,
        String apellidos,
        LocalDate fechaNacimiento,
        String telefonoUno,
        String telefonoDos,
        String emailUno,
        String emailDos
) {}

