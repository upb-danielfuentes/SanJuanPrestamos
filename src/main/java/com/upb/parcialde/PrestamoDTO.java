package com.upb.parcialde;
import java.util.Date;

public class PrestamoDTO {
    private EstudianteDTO estudiante;
    private ComputadoraDTO computadora;
    private Date fechaPrestamo;

    public PrestamoDTO(EstudianteDTO estudiante, ComputadoraDTO computadora) {
        this.estudiante = estudiante;
        this.computadora = computadora;
        this.fechaPrestamo = new Date();
    }

    public EstudianteDTO getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteDTO estudiante) {
        this.estudiante = estudiante;
    }

    public ComputadoraDTO getComputadora() {
        return computadora;
    }

    public void setPortatil(ComputadoraDTO computadora) {
        this.computadora = computadora;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaPrestamo(String nuevaFechaPrestamo) {
    }
}
