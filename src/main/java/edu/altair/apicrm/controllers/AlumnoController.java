package edu.altair.apicrm.controllers;

import edu.altair.apicrm.dtos.AlumnoRequestDto;
import edu.altair.apicrm.dtos.AlumnoResponseDto;
import edu.altair.apicrm.services.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    // 1. READ ALL - GET /api/alumnos
    @GetMapping
    public ResponseEntity<List<AlumnoResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(alumnoService.obtenerTodos()); // HTTP 200 OK
    }

    // 2. READ BY ID - GET /api/alumnos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(alumnoService.obtenerPorId(id)); // HTTP 200 OK
    }

    // 3. CREATE - POST /api/alumnos
    @PostMapping
    public ResponseEntity<AlumnoResponseDto> crear(@RequestBody AlumnoRequestDto requestDto) {
        AlumnoResponseDto creado = alumnoService.crear(requestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED) // HTTP 201 Created
                .body(creado);
    }

    // 4. UPDATE - PUT /api/alumnos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<AlumnoResponseDto> actualizar(
            @PathVariable Long id,
            @RequestBody AlumnoRequestDto requestDto) {
        AlumnoResponseDto actualizado = alumnoService.actualizar(id, requestDto);
        return ResponseEntity.ok(actualizado); // HTTP 200 OK
    }

    // 5. DELETE - DELETE /api/alumnos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        alumnoService.eliminar(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}

