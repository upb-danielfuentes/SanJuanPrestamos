package com.upb.parcialde;

public class PrestamoDTO {
    private EstudianteDTO estudiante;
    private ComputadoraDTO computadora;


    public PrestamoDTO(EstudianteDTO estudiante, ComputadoraDTO computadora) {
        this.estudiante = estudiante;
        this.computadora = computadora;
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
}
