package com.upb.parcialde;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class PrestamoDAOTest {

    private PrestamoDAO prestamoDAO;

    @BeforeEach
    public void setUp() {
        prestamoDAO = new PrestamoDAO();
    }

    @Test
    public void testRegistrarPrestamo() {
        EstudianteDTO estudiante = new EstudianteDTO("1234567890", "Nombre Estudiante", "Apellido Estudiante","32123",1,1,"1");
        ComputadoraDTO computadora = new ComputadoraDTO("Serial123", "Marca Computadora",1,100,"Linux","Intel");
        PrestamoDAO.estudiantes.add(estudiante);
        PrestamoDAO.computadoras.add(computadora);

        PrestamoDAO.registrarPrestamo();
        assertEquals(1, PrestamoDAO.prestamos.size());
    }

    @Test
    public void testModificarPrestamo() {
        EstudianteDTO estudiante = new EstudianteDTO("1234567890", "Nombre Estudiante", "Apellido Estudiante","32123",1,1,"1");
        ComputadoraDTO computadora = new ComputadoraDTO("Serial123", "Marca Computadora",1,100,"Linux","Intel");
        PrestamoDTO prestamo = new PrestamoDTO(estudiante, computadora);
        PrestamoDAO.estudiantes.add(estudiante);
        PrestamoDAO.computadoras.add(computadora);
        PrestamoDAO.prestamos.add(prestamo);

        PrestamoDAO.modificarPrestamo();
        assertEquals("NuevaCedula", prestamo.getEstudiante().getCedula());
        assertEquals("NuevoSerial", prestamo.getPortatil().getSerial());
    }

    @Test
    public void testDevolucionEquipo() {
        EstudianteDTO estudiante = new EstudianteDTO("1234567890", "Nombre Estudiante", "Apellido Estudiante","32123",1,1,"1");
        ComputadoraDTO computadora = new ComputadoraDTO("Serial123", "Marca Computadora",1,100,"Linux","Intel");
        PrestamoDTO prestamo = new PrestamoDTO(estudiante, computadora);
        PrestamoDAO.estudiantes.add(estudiante);
        PrestamoDAO.computadoras.add(computadora);
        PrestamoDAO.prestamos.add(prestamo);

        PrestamoDAO.devolucionEquipo();
        assertEquals(0, PrestamoDAO.prestamos.size());
    }

    @Test
    public void testBuscarEquipo() {
        EstudianteDTO estudiante = new EstudianteDTO("1234567890", "Nombre Estudiante", "Apellido Estudiante","32123",1,1,"1");
        ComputadoraDTO computadora = new ComputadoraDTO("Serial123", "Marca Computadora",1,100,"Linux","Intel");
        PrestamoDTO prestamo = new PrestamoDTO(estudiante, computadora);
        PrestamoDAO.estudiantes.add(estudiante);
        PrestamoDAO.computadoras.add(computadora);
        PrestamoDAO.prestamos.add(prestamo);
    }
}
