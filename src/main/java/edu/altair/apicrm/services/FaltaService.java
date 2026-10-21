package edu.altair.apicrm.services;

import edu.altair.apicrm.dtos.FaltaRequestDto;
import edu.altair.apicrm.dtos.FaltaResponseDto;

import java.util.List;

public interface FaltaService {
    List<FaltaResponseDto> obtenerTodas();
    List<FaltaResponseDto> obtenerPorAlumno(Long idAlumno);
    FaltaResponseDto obtenerPorId(Long id);
    FaltaResponseDto crear(FaltaRequestDto requestDto);
    FaltaResponseDto actualizar(Long id, FaltaRequestDto requestDto);
    void eliminar(Long id);
}