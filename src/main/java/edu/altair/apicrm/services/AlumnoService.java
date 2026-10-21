package edu.altair.apicrm.services;

import edu.altair.apicrm.dtos.AlumnoRequestDto;
import edu.altair.apicrm.dtos.AlumnoResponseDto;
import java.util.List;

public interface AlumnoService {
    List<AlumnoResponseDto> obtenerTodos();
    AlumnoResponseDto obtenerPorId(Long id);
    AlumnoResponseDto crear(AlumnoRequestDto requestDto);
    AlumnoResponseDto actualizar(Long id, AlumnoRequestDto requestDto);
    void eliminar(Long id);
}

