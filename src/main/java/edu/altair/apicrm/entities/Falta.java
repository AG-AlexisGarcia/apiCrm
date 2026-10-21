package edu.altair.apicrm.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "faltas", schema = "crm")
public class Falta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_falta")
    private Long idFalta;

    @Column(name = "id_alumno", nullable = false)
    private Long idAlumno;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "tramo_horario", nullable = false, length = 20)
    private String tramoHorario;

    @Column(name = "justificada", nullable = false)
    private Boolean justificada;

    @Column(name = "motivo", length = 255)
    private String motivo;

    // Constructor vacío (obligatorio para Hibernate/JPA)
    public Falta() {
    }

    // Constructor con todos los campos
    public Falta(Long idFalta, Long idAlumno, LocalDate fecha, String tramoHorario, Boolean justificada, String motivo) {
        this.idFalta = idFalta;
        this.idAlumno = idAlumno;
        this.fecha = fecha;
        this.tramoHorario = tramoHorario;
        this.justificada = justificada;
        this.motivo = motivo;
    }

    // Getters y Setters
    public Long getIdFalta() { return idFalta; }
    public void setIdFalta(Long idFalta) { this.idFalta = idFalta; }

    public Long getIdAlumno() { return idAlumno; }
    public void setIdAlumno(Long idAlumno) { this.idAlumno = idAlumno; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getTramoHorario() { return tramoHorario; }
    public void setTramoHorario(String tramoHorario) { this.tramoHorario = tramoHorario; }

    public Boolean getJustificada() { return justificada; }
    public void setJustificada(Boolean justificada) { this.justificada = justificada; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}