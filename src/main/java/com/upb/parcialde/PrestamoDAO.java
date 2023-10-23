package com.upb.parcialde;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {
    private static List<PrestamoDTO> prestamos = new ArrayList<>();
    private static List<EstudianteDTO> estudiantes = new ArrayList<>();
    private static List<ComputadoraDTO> computadoras = new ArrayList<>();

    public void gestionPrestamos() {
        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Menú de Gestión de Préstamos\n" +
                            "1. Registrar préstamo de equipo\n" +
                            "2. Modificar préstamo de equipo\n" +
                            "3. Devolución de equipo\n" +
                            "4. Buscar equipo\n" +
                            "5. Imprimir todos los préstamos\n" +
                            "6. Volver al menú principal\n" +
                            "Ingrese su opción:"));

            switch (opcion) {
                case 1:
                    registrarPrestamo();
                    break;
                case 2:
                    modificarPrestamo();
                    break;
                case 3:
                    devolucionEquipo();
                    break;
                case 4:
                    buscarEquipo();
                    break;
                case 5:
                    imprimirTodosLosPrestamos();
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 6);
    }

    public static void registrarPrestamo() {
        String cedulaEstudiante = JOptionPane.showInputDialog("Ingrese la cédula del estudiante:");
        EstudianteDTO estudiante = buscarEstudiantePorCedula(cedulaEstudiante);

        if (estudiante == null) {
            JOptionPane.showMessageDialog(null, "El estudiante no existe.");
            return;
        }

        String serialComputadora = JOptionPane.showInputDialog("Ingrese el serial del portátil:");
        ComputadoraDTO computadora = buscarComputadoraPorSerial(serialComputadora);

        if (computadora == null) {
            JOptionPane.showMessageDialog(null, "La computadora no existe.");
            return;
        }

        PrestamoDTO nuevoPrestamo = new PrestamoDTO(estudiante, computadora);
        prestamos.add(nuevoPrestamo);
        JOptionPane.showMessageDialog(null, "Préstamo registrado con éxito.");
    }

    public static void modificarPrestamo() {
        String cedulaOSerial = JOptionPane.showInputDialog("Ingrese la cédula del estudiante o el serial de la computadora:");
        EstudianteDTO estudiante = buscarEstudiantePorCedula(cedulaOSerial);
        ComputadoraDTO computadora = null;

        if (estudiante == null) {
            computadora = buscarComputadoraPorSerial(cedulaOSerial);
        }

        if (estudiante == null && computadora == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el estudiante ni la computadora.");
            return;
        }

        int opcion = Integer.parseInt(JOptionPane.showInputDialog("¿Qué desea modificar?\n" +
                "1. Cédula del estudiante\n" +
                "2. Serial de la computadora"));

        switch (opcion) {
            case 1:
                if (estudiante == null) {
                    JOptionPane.showMessageDialog(null, "El estudiante no existe.");
                    return;
                }
                String nuevaCedula = JOptionPane.showInputDialog("Ingrese la nueva cédula del estudiante:");
                if (buscarEstudiantePorCedula(nuevaCedula) == null) {
                    estudiante.setCedula(nuevaCedula);
                    JOptionPane.showMessageDialog(null, "Préstamo modificado con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null, "El estudiante con la nueva cédula ya existe.");
                }
                break;
            case 2:
                if (computadora == null) {
                    JOptionPane.showMessageDialog(null, "La computadora no existe.");
                    return;
                }
                String nuevoSerial = JOptionPane.showInputDialog("Ingrese el nuevo serial de la computadora:");
                if (buscarComputadoraPorSerial(nuevoSerial) == null) {
                    computadora.setSerial(nuevoSerial);
                    JOptionPane.showMessageDialog(null, "Préstamo modificado con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null, "La computadora con el nuevo serial ya existe.");
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
        }
    }

    public static void devolucionEquipo() {
        String cedulaOSerial = JOptionPane.showInputDialog("Ingrese la cédula del estudiante o el serial de la computadora, que va a devolver:");
        EstudianteDTO estudiante = buscarEstudiantePorCedula(cedulaOSerial);
        ComputadoraDTO computadora = null;

        if (estudiante == null) {
            computadora = buscarComputadoraPorSerial(cedulaOSerial);
        }

        if (estudiante != null || computadora != null) {
            PrestamoDTO prestamoEncontrado = null;
            for (PrestamoDTO prestamo : prestamos) {
                if (prestamo.getEstudiante().equals(estudiante) && prestamo.getComputadora().equals(computadora)) {
                    prestamoEncontrado = prestamo;
                    break;
                }
            }

            if (prestamoEncontrado != null) {
                prestamos.remove(prestamoEncontrado);
                JOptionPane.showMessageDialog(null, "Préstamo devuelto y eliminado con éxito.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un préstamo asociado a esta cédula o serial.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el estudiante ni la computadora.");
        }
    }

    public static void buscarEquipo() {
        String cedulaOSerial = JOptionPane.showInputDialog("Ingrese la cédula del estudiante o el serial de la computadora:");
        EstudianteDTO estudiante = buscarEstudiantePorCedula(cedulaOSerial);
        ComputadoraDTO computadora = null;

        if (estudiante == null) {
            computadora = buscarComputadoraPorSerial(cedulaOSerial);
        }

        if (estudiante != null || computadora != null) {
            for (PrestamoDTO prestamo : prestamos) {
                if (prestamo.getEstudiante().equals(estudiante) && prestamo.getComputadora().equals(computadora)) {
                    JOptionPane.showMessageDialog(null, "Préstamo encontrado:\nCédula del estudiante: " + prestamo.getEstudiante().getCedula() + "\nSerial de la computadora: " + prestamo.getComputadora().getSerial());
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "No se encontró un préstamo asociado a esta cédula o serial.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró el estudiante ni la computadora.");
        }
    }

    private static EstudianteDTO buscarEstudiantePorCedula(String cedula) {
        for (EstudianteDTO estudiante : estudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                JOptionPane.showMessageDialog(null, "Estudiante encontrado:\nCédula: " + estudiante.getCedula() + "\nNombre: " + estudiante.getNombre());
                return estudiante;
            }
        }
        JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
        return null;
    }

    public static void imprimirTodosLosPrestamos() {
        if (prestamos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay préstamos registrados.");
            return;
        }

        StringBuilder prestamosText = new StringBuilder("Lista de Préstamos:\n");
        for (PrestamoDTO prestamo : prestamos) {
            EstudianteDTO estudiante = prestamo.getEstudiante();
            ComputadoraDTO computadora = prestamo.getComputadora();

            prestamosText.append("Estudiante: ").append(estudiante.getNombre()).append(" ").append(estudiante.getApellido()).append("\n");
            prestamosText.append("Portátil: ").append(computadora.getMarca()).append(" (Serial: ").append(computadora.getSerial()).append(")\n");
            prestamosText.append("Fecha de Préstamo: ").append(prestamo.getFechaPrestamo()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, prestamosText.toString());
    }

    public void imprimirInventarioTotal() {
        StringBuilder inventarioString = new StringBuilder("Inventario Total:\n\n");
        inventarioString.append("Estudiantes:\n");
        for (EstudianteDTO estudiante : estudiantes) {
            inventarioString.append("Cédula: ").append(estudiante.getCedula()).append(", Nombre: ").append(estudiante.getNombre()).append("\n");
        }
        inventarioString.append("\nComputadoras:\n");
        for (ComputadoraDTO computadora : computadoras) {
            inventarioString.append("Serial: ").append(computadora.getSerial()).append(", Marca: ").append(computadora.getMarca()).append("\n");
        }
        JOptionPane.showMessageDialog(null, inventarioString.toString());
    }

    private static ComputadoraDTO buscarComputadoraPorSerial(String serial) {
        for (ComputadoraDTO computadora : computadoras) {
            if (computadora.getSerial().equalsIgnoreCase(serial)) {
                JOptionPane.showMessageDialog(null, "Computadora encontrada:\nSerial: " + computadora.getSerial() + "\nMarca: " + computadora.getMarca());
                return computadora;
            }
        }
        JOptionPane.showMessageDialog(null, "Computadora no encontrada.");
        return null;
    }
}
