package edu.altair.apicrm.services.impl;

import edu.altair.apicrm.dtos.FaltaRequestDto;
import edu.altair.apicrm.dtos.FaltaResponseDto;
import edu.altair.apicrm.entities.Falta;
import edu.altair.apicrm.exceptions.FaltaDuplicadaException;
import edu.altair.apicrm.exceptions.FaltaNotFoundException;
import edu.altair.apicrm.repositories.FaltaRepository;
import edu.altair.apicrm.services.FaltaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FaltaServiceImpl implements FaltaService {

    private final FaltaRepository faltaRepository;

    // Inyección de dependencias por constructor
    public FaltaServiceImpl(FaltaRepository faltaRepository) {
        this.faltaRepository = faltaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FaltaResponseDto> obtenerTodas() {
        return faltaRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FaltaResponseDto> obtenerPorAlumno(Long idAlumno) {
        return faltaRepository.findByIdAlumno(idAlumno)
                .stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public FaltaResponseDto obtenerPorId(Long id) {
        Falta falta = faltaRepository.findById(id)
                .orElseThrow(() -> new FaltaNotFoundException("Falta no encontrada con el ID: " + id));
        return mapToResponseDto(falta);
    }

    @Override
    @Transactional
    public FaltaResponseDto crear(FaltaRequestDto dto) {
        // Validación de regla de negocio: no duplicar falta en misma fecha y tramo
        if (faltaRepository.existsByIdAlumnoAndFechaAndTramoHorario(dto.idAlumno(), dto.fecha(), dto.tramoHorario())) {
            throw new FaltaDuplicadaException("El alumno ya tiene una falta registrada en la fecha " + dto.fecha() + " y tramo " + dto.tramoHorario());
        }

        Falta nuevaFalta = new Falta(
                null,
                dto.idAlumno(),
                dto.fecha(),
                dto.tramoHorario(),
                dto.justificada() != null ? dto.justificada() : false,
                dto.motivo()
        );

        Falta guardada = faltaRepository.save(nuevaFalta);
        return mapToResponseDto(guardada);
    }

    @Override
    @Transactional
    public FaltaResponseDto actualizar(Long id, FaltaRequestDto dto) {
        Falta faltaExistente = faltaRepository.findById(id)
                .orElseThrow(() -> new FaltaNotFoundException("Falta no encontrada con el ID: " + id));

        faltaExistente.setIdAlumno(dto.idAlumno());
        faltaExistente.setFecha(dto.fecha());
        faltaExistente.setTramoHorario(dto.tramoHorario());
        faltaExistente.setJustificada(dto.justificada());
        faltaExistente.setMotivo(dto.motivo());

        Falta actualizada = faltaRepository.save(faltaExistente);
        return mapToResponseDto(actualizada);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!faltaRepository.existsById(id)) {
            throw new FaltaNotFoundException("Imposible eliminar. Falta no encontrada con el ID: " + id);
        }
        faltaRepository.deleteById(id);
    }

    // Método auxiliar de mapeo de Entidad a DTO
    private FaltaResponseDto mapToResponseDto(Falta falta) {
        return new FaltaResponseDto(
                falta.getIdFalta(),
                falta.getIdAlumno(),
                falta.getFecha(),
                falta.getTramoHorario(),
                falta.getJustificada(),
                falta.getMotivo()
        );
    }
}
