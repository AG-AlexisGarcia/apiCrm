package edu.altair.apicrm.controllers;

import edu.altair.apicrm.dtos.FaltaRequestDto;
import edu.altair.apicrm.dtos.FaltaResponseDto;
import edu.altair.apicrm.services.FaltaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faltas")
public class FaltaController {

    private final FaltaService faltaService;

    // Inyección de dependencias por constructor
    public FaltaController(FaltaService faltaService) {
        this.faltaService = faltaService;
    }

    // 1. GET /api/faltas (listar todas)
    @GetMapping
    public ResponseEntity<List<FaltaResponseDto>> obtenerTodas() {
        return ResponseEntity.ok(faltaService.obtenerTodas());
    }

    // 2. GET /api/faltas/alumno/{idAlumno} (filtrar faltas por alumno)
    @GetMapping("/alumno/{idAlumno}")
    public ResponseEntity<List<FaltaResponseDto>> obtenerPorAlumno(@PathVariable Long idAlumno) {
        return ResponseEntity.ok(faltaService.obtenerPorAlumno(idAlumno));
    }

    // 3. GET /api/faltas/{id} (detalle de una falta por ID)
    @GetMapping("/{id}")
    public ResponseEntity<FaltaResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(faltaService.obtenerPorId(id));
    }

    // 4. POST /api/faltas (crear una falta)
    @PostMapping
    public ResponseEntity<FaltaResponseDto> crear(@RequestBody FaltaRequestDto requestDto) {
        FaltaResponseDto creado = faltaService.crear(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // 5. PUT /api/faltas/{id} (actualizar o justificar una falta)
    @PutMapping("/{id}")
    public ResponseEntity<FaltaResponseDto> actualizar(
            @PathVariable Long id,
            @RequestBody FaltaRequestDto requestDto) {
        FaltaResponseDto actualizado = faltaService.actualizar(id, requestDto);
        return ResponseEntity.ok(actualizado);
    }

    // 6. DELETE /api/faltas/{id} (eliminar una falta)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        faltaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}