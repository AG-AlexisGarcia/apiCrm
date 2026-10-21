package edu.altair.apicrm.dtos;

import java.time.LocalDate;

public record FaltaRequestDto(
    Long idAlumno,
    LocalDate fecha,
    String tramoHorario,
    Boolean justificada,
    String motivo
) {}