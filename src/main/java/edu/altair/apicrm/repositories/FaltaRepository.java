package edu.altair.apicrm.repositories;

import edu.altair.apicrm.entities.Falta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FaltaRepository extends JpaRepository<Falta, Long> {

    // Método derivado: busca todas las faltas asociadas a un alumno concreto
    List<Falta> findByIdAlumno(Long idAlumno);

    // Método derivado: comprueba si ya existe una falta para ese alumno en ese día y tramo
    boolean existsByIdAlumnoAndFechaAndTramoHorario(Long idAlumno, LocalDate fecha, String tramoHorario);
}