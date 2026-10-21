package edu.altair.apicrm.services.impl;

import edu.altair.apicrm.dtos.AlumnoRequestDto;
import edu.altair.apicrm.dtos.AlumnoResponseDto;
import edu.altair.apicrm.entities.Alumno;
import edu.altair.apicrm.exceptions.AlumnoNotFoundException;
import edu.altair.apicrm.exceptions.DniAlreadyExistsException;
import edu.altair.apicrm.repositories.AlumnoRepository;
import edu.altair.apicrm.services.AlumnoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    // Inyección de dependencias por constructor
    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlumnoResponseDto> obtenerTodos() {
        return alumnoRepository.findAll()
                .stream()
                .map(this::mapToResponseDto)
                .toList(); // Método toList() optimizado en Java 25
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoResponseDto obtenerPorId(Long id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new AlumnoNotFoundException("Alumno no encontrado con el ID: " + id));
        return mapToResponseDto(alumno);
    }

    @Override
    @Transactional
    public AlumnoResponseDto crear(AlumnoRequestDto dto) {
        // Validación de regla de negocio: DNI único
        if (alumnoRepository.existsByDni(dto.dni())) {
            throw new DniAlreadyExistsException("Ya existe un alumno registrado con el DNI: " + dto.dni());
        }

        Alumno nuevoAlumno = new Alumno(
                null,
                dto.dni(),
                dto.nombre(),
                dto.apellidos(),
                dto.fechaNacimiento(),
                dto.telefonoUno(),
                dto.telefonoDos(),
                dto.emailUno(),
                dto.emailDos()
        );

        Alumno guardado = alumnoRepository.save(nuevoAlumno);
        return mapToResponseDto(guardado);
    }

    @Override
    @Transactional
    public AlumnoResponseDto actualizar(Long id, AlumnoRequestDto dto) {
        Alumno alumnoExistente = alumnoRepository.findById(id)
                .orElseThrow(() -> new AlumnoNotFoundException("Alumno no encontrado con el ID: " + id));

        // Si se modifica el DNI, comprobar que el nuevo DNI no pertenezca a otro alumno
        if (!alumnoExistente.getDni().equalsIgnoreCase(dto.dni()) && alumnoRepository.existsByDni(dto.dni())) {
            throw new DniAlreadyExistsException("El DNI " + dto.dni() + " ya está en uso por otro alumno.");
        }

        alumnoExistente.setDni(dto.dni());
        alumnoExistente.setNombre(dto.nombre());
        alumnoExistente.setApellidos(dto.apellidos());
        alumnoExistente.setFechaNacimiento(dto.fechaNacimiento());
        alumnoExistente.setTelefonoUno(dto.telefonoUno());
        alumnoExistente.setTelefonoDos(dto.telefonoDos());
        alumnoExistente.setEmailUno(dto.emailUno());
        alumnoExistente.setEmailDos(dto.emailDos());

        Alumno actualizado = alumnoRepository.save(alumnoExistente);
        return mapToResponseDto(actualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!alumnoRepository.existsById(id)) {
            throw new AlumnoNotFoundException("Imposible eliminar. Alumno no encontrado con el ID: " + id);
        }
        alumnoRepository.deleteById(id);
    }

    // Método auxiliar de mapeo de Entidad a DTO
    private AlumnoResponseDto mapToResponseDto(Alumno alumno) {
        return new AlumnoResponseDto(
                alumno.getIdAlumno(),
                alumno.getDni(),
                alumno.getNombre(),
                alumno.getApellidos(),
                alumno.getFechaNacimiento(),
                alumno.getTelefonoUno(),
                alumno.getTelefonoDos(),
                alumno.getEmailUno(),
                alumno.getEmailDos()
        );
    }
}

