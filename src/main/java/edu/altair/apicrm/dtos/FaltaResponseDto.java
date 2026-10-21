package edu.altair.apicrm.dtos;

import java.time.LocalDate;

public record FaltaResponseDto(
    Long idFalta,
    Long idAlumno,
    LocalDate fecha,
    String tramoHorario,
    Boolean justificada,
    String motivo
) {}