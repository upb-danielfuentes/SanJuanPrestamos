package com.upb.parcialde;
import java.util.Date;

public class PrestamoDTO {
    private EstudianteDTO estudiante;
    private ComputadoraDTO portatil;
    private Date fechaPrestamo;

    public PrestamoDTO(EstudianteDTO estudiante, ComputadoraDTO portatil) {
        this.estudiante = estudiante;
        this.portatil = portatil;
        this.fechaPrestamo = new Date();
    }

    public EstudianteDTO getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteDTO estudiante) {
        this.estudiante = estudiante;
    }

    public ComputadoraDTO getPortatil() {
        return portatil;
    }

    public void setPortatil(ComputadoraDTO portatil) {
        this.portatil = portatil;
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
