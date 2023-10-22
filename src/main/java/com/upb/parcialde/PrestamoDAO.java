package com.upb.parcialde;

import javax.swing.*;
import java.util.ArrayList;

public class PrestamoDAO {
    
    private static ArrayList<PrestamoDTO> prestamos = new ArrayList<>();
    private static ArrayList<EstudianteDTO> estudiantes = new ArrayList<>();
    private static ArrayList<ComputadoraDTO> computadoras = new ArrayList<>();

    public static void gestionPrestamos() {
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

    private static void registrarPrestamo() {
        String cedulaEstudiante = JOptionPane.showInputDialog("Ingrese la cédula del estudiante:");
        EstudianteDTO estudiante = buscarEstudiantePorCedula(cedulaEstudiante);
        if (estudiante == null) {
            JOptionPane.showMessageDialog(null, "El estudiante no existe.");
            return;
        }
        String serialPortatil = JOptionPane.showInputDialog("Ingrese el serial del portátil:");
        ComputadoraDTO portatil = buscarPortatilPorSerial(serialPortatil);
        if (portatil == null) {
            JOptionPane.showMessageDialog(null, "La computadora no existe.");
            return;
        }

        if (estudiante != null && portatil != null) {
            PrestamoDTO nuevoPrestamo = new PrestamoDTO(estudiante, portatil);
            prestamos.add(nuevoPrestamo);
            JOptionPane.showMessageDialog(null, "Préstamo registrado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo registrar el préstamo. Verifique la cédula del estudiante y el serial del portátil.");
        }
    }

    private static void modificarPrestamo() {
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

        if (opcion == 1) {
            String nuevaCedula = JOptionPane.showInputDialog("Ingrese la nueva cédula del estudiante:");
            estudiante = buscarEstudiantePorCedula(nuevaCedula);
            if (estudiante == null) {
                JOptionPane.showMessageDialog(null, "El estudiante no existe.");
                return;
            }
            for (PrestamoDTO prestamo : prestamos) {
                if (prestamo.getEstudiante().equals(estudiante) && prestamo.getPortatil().equals(computadora)) {
                    prestamo.getEstudiante().getCedula().equals(nuevaCedula);
                    JOptionPane.showMessageDialog(null, "Préstamo modificado con éxito.");
                    return;
                }
            }
        } else if (opcion == 2) {
            String nuevoSerial = JOptionPane.showInputDialog("Ingrese el nuevo serial de la computadora:");
            computadora = buscarComputadoraPorSerial(nuevoSerial);
            if (computadora == null) {
                JOptionPane.showMessageDialog(null, "La computadora no existe.");
                return;
            }
            for (PrestamoDTO prestamo : prestamos) {
                if (prestamo.getEstudiante().equals(estudiante) && prestamo.getPortatil().equals(computadora)) {
                    prestamo.getPortatil().getSerial().equals(nuevoSerial);
                    JOptionPane.showMessageDialog(null, "Préstamo modificado con éxito.");
                    return;
                }
            }
        }
    }
    
    private static void devolucionEquipo() {
        String cedulaOSerial = JOptionPane.showInputDialog("Ingrese la cédula del estudiante o el serial de la computadora, que va a devolver:");
        EstudianteDTO estudiante = buscarEstudiantePorCedula(cedulaOSerial);
        ComputadoraDTO computadora = null;
        if (estudiante == null) {
            computadora = buscarComputadoraPorSerial(cedulaOSerial);
        }
        if (estudiante == null && computadora == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el estudiante ni la computadora.");
            return;
        }

        for (PrestamoDTO prestamo : prestamos) {
            if (prestamo.getEstudiante().equals(estudiante) && prestamo.getPortatil().equals(computadora)) {
                prestamos.remove(prestamo);
                JOptionPane.showMessageDialog(null, "Préstamo devuelto y eliminado con éxito.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontró un préstamo asociado a esta cédula o serial.");
    }
    
    private static void buscarEquipo() {
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

        for (PrestamoDTO prestamo : prestamos) {
            if (prestamo.getEstudiante().equals(estudiante) && prestamo.getPortatil().equals(computadora)) {
                JOptionPane.showMessageDialog(null, "Préstamo encontrado:\nCédula del estudiante: " + prestamo.getEstudiante().getCedula() + "\nSerial de la computadora: " + prestamo.getPortatil().getSerial());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No se encontró un préstamo asociado a esta cédula o serial.");
    }

    public static ComputadoraDTO buscarPortatilPorSerial(String serial) {
        ComputadoraDTO[] listaDePortatiles = new ComputadoraDTO[] {};
        for (ComputadoraDTO portatil : listaDePortatiles) {
            if (portatil.getSerial().equals(serial)) {
                return portatil;
            }
        }
        return null;
    }

    public static EstudianteDTO buscarEstudiantePorCedula(String cedula) {
    EstudianteDTO[] listaDeEstudiantes = new EstudianteDTO[] {};
    for (EstudianteDTO estudiante : listaDeEstudiantes) {
        if (estudiante.getCedula().equals(cedula)) {
            return estudiante;
        }
    }
    return null;
}

    public static void buscarPrestamoPorCedula(String cedula) {
        for (PrestamoDTO prestamo : prestamos) {
            if (prestamo.getEstudiante().getCedula().equals(cedula)) {
                JOptionPane.showMessageDialog(null, "Préstamo encontrado:\n" +
                        "Estudiante: " + prestamo.getEstudiante().getNombre() + " " + prestamo.getEstudiante().getApellido() + "\n" +
                        "Portátil: " + prestamo.getPortatil().getMarca() + " (Serial: " + prestamo.getPortatil().getSerial() + ")\n" +
                        "Fecha de Préstamo: " + prestamo.getFechaPrestamo());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Préstamo no encontrado para la cédula especificada.");
    }

    public static void buscarPrestamoPorSerial(String serial) {
        for (PrestamoDTO prestamo : prestamos) {
            if (prestamo.getPortatil().getSerial().equals(serial)) {
                JOptionPane.showMessageDialog(null, "Préstamo encontrado:\n" +
                        "Estudiante: " + prestamo.getEstudiante().getNombre() + " " + prestamo.getEstudiante().getApellido() + "\n" +
                        "Portátil: " + prestamo.getPortatil().getMarca() + " (Serial: " + prestamo.getPortatil().getSerial() + ")\n" +
                        "Fecha de Préstamo: " + prestamo.getFechaPrestamo());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Préstamo no encontrado para el serial especificado.");
    }

    public static void imprimirTodosLosPrestamos() {
        StringBuilder prestamosText = new StringBuilder("Lista de Préstamos:\n");
        for (PrestamoDTO prestamo : prestamos) {
            prestamosText.append("Estudiante: ").append(prestamo.getEstudiante().getNombre()).append(" ").append(prestamo.getEstudiante().getApellido()).append("\n");
            prestamosText.append("Portátil: ").append(prestamo.getPortatil().getMarca()).append(" (Serial: ").append(prestamo.getPortatil().getSerial()).append(")\n");
            prestamosText.append("Fecha de Préstamo: ").append(prestamo.getFechaPrestamo()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, prestamosText.toString());
    }

    public static void imprimirInventarioTotal() {
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

    private static ComputadoraDTO buscarComputadoraPorSerial(String serial2) {
        String serial = JOptionPane.showInputDialog("Ingrese el serial de la computadora:");
        for (ComputadoraDTO computadora : computadoras) {
            if (computadora.getSerial().equals(serial)) {
                JOptionPane.showMessageDialog(null, "Computadora encontrada:\nSerial: " + computadora.getSerial() + "\nMarca: " + computadora.getMarca());
                return computadora;
            }
        }
        JOptionPane.showMessageDialog(null, "Computadora no encontrada.");
        return null;
    }

}
