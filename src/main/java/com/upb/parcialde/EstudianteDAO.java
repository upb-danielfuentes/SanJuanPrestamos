package com.upb.parcialde;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    public static List<EstudianteDTO> arraylistestudiante = new ArrayList<>();

    public void gestionEstudiantes() {
        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Menú de Gestión de Estudiantes\n" +
                            "1. Registrar estudiantes\n" +
                            "2. Modificar estudiantes\n" +
                            "3. Eliminar estudiante\n" +
                            "4. Buscar Estudiante por Cedula\n" +
                            "5. Imprimir todos los estudiantes\n" +
                            "6. Volver al menú principal\n" +
                            "Ingrese su opción:"));

            switch (opcion) {
                case 1:
                    registrarEstudiante();
                    break;
                case 2:
                    modificarEstudiante();
                    break;
                case 3:
                    eliminarEstudiante();
                    break;
                case 4:
                    buscarEstudiantePorCedula();
                    break;
                case 5:
                    imprimirEstudiantes();
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 6);
    }

    public static void registrarEstudiante() {
        while (JOptionPane.showConfirmDialog(null, "¿Desea agregar un estudiante?") == JOptionPane.YES_OPTION) {
            String cedula = JOptionPane.showInputDialog("Ingrese la cédula del estudiante");
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante");
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido del estudiante");
            String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del estudiante");
            int numSemestre = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número del semestre actual del estudiante"));
            float promedio = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el promedio actual del estudiante"));
            String serial = JOptionPane.showInputDialog("¿Cuál es el número serial del estudiante?");

            EstudianteDTO estudiante = new EstudianteDTO(cedula, nombre, apellido, telefono, numSemestre, promedio, serial);
            arraylistestudiante.add(estudiante);
        }

        if (arraylistestudiante.isEmpty()) {
            JOptionPane.showMessageDialog(null, "💔 No se agregó ningún estudiante 💔");
        } else {
            JOptionPane.showMessageDialog(null, "Se agregaron " + arraylistestudiante.size() + " estudiantes al sistema");
        }
    }

    public static void modificarEstudiante() {
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula del estudiante a modificar");
        boolean encontrado = false;

        for (EstudianteDTO estudiante : arraylistestudiante) {
            if (estudiante.getCedula().equalsIgnoreCase(cedula)) {
                int opcion = Integer.parseInt(JOptionPane.showInputDialog(
                        "Elija la información registrada a cambiar\n"
                                + "1. Nombre del estudiante\n"
                                + "2. Apellido del estudiante\n"
                                + "3. Teléfono del estudiante\n"
                                + "4. Número del semestre\n"
                                + "5. Promedio\n"
                                + "6. Número serial\n"
                                + "7. Salir\n"));
                switch (opcion) {
                    case 1:
                        estudiante.setNombre(JOptionPane.showInputDialog("Ingrese el nuevo nombre del estudiante"));
                        break;
                    case 2:
                        estudiante.setApellido(JOptionPane.showInputDialog("Ingrese un nuevo apellido"));
                        break;
                    case 3:
                        estudiante.setTel(JOptionPane.showInputDialog("Ingrese el nuevo teléfono"));
                        break;
                    case 4:
                        estudiante.setNumsem(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo semestre que se está cursando")));
                        break;
                    case 5:
                        estudiante.setPromacum(Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo promedio total")));
                        break;
                    case 6:
                        estudiante.setSerial(JOptionPane.showInputDialog("Ingrese el nuevo número serial"));
                        break;
                    case 7:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Elección incorrecta");
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "💔 No se encontró estudiante con esa cédula 💔");
        } else {
            JOptionPane.showMessageDialog(null, "Información del estudiante actualizada exitosamente");
        }
    }

    public static void buscarEstudiantePorCedula() {
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula del estudiante a buscar");
        boolean encontrado = false;

        for (EstudianteDTO estudiante : arraylistestudiante) {
            if (estudiante.getCedula().equalsIgnoreCase(cedula)) {
                String infoEstudiante = "Cédula del estudiante: " + estudiante.getCedula() + "\n" +
                        "Nombre del estudiante: " + estudiante.getNombre() + "\n" +
                        "Apellido del estudiante: " + estudiante.getApellido() + "\n" +
                        "Teléfono del estudiante: " + estudiante.getTel() + "\n" +
                        "Semestre actual del estudiante: " + estudiante.getNumsem() + "\n" +
                        "Promedio actual del estudiante: " + estudiante.getPromacum() + "\n" +
                        "Número serial del estudiante: " + estudiante.getSerial() + "\n";
                JOptionPane.showMessageDialog(null, infoEstudiante);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "💔 No se encontró estudiante con esa cédula 💔");
        }
    }

    public static void eliminarEstudiante() {
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula del estudiante a eliminar");
        boolean eliminado = false;

        for (EstudianteDTO estudiante : arraylistestudiante) {
            if (estudiante.getCedula().equalsIgnoreCase(cedula)) {
                arraylistestudiante.remove(estudiante);
                JOptionPane.showMessageDialog(null, "Estudiante eliminado de la base de datos");
                eliminado = true;
                break;
            }
        }

        if (!eliminado) {
            JOptionPane.showMessageDialog(null, "💔 No se encontró estudiante con esa cédula 💔");
        }
    }

    public static void imprimirEstudiantes() {
        StringBuilder cadena = new StringBuilder("LISTA DE ESTUDIANTES\n");
        for (EstudianteDTO estudiante : arraylistestudiante) {
            cadena.append("Cédula del estudiante: ").append(estudiante.getCedula()).append("\n")
                    .append("Nombre del estudiante: ").append(estudiante.getNombre()).append("\n")
                    .append("Apellido del estudiante: ").append(estudiante.getApellido()).append("\n")
                    .append("Teléfono del estudiante: ").append(estudiante.getTel()).append("\n")
                    .append("Semestre actual del estudiante: ").append(estudiante.getNumsem()).append("\n")
                    .append("Promedio actual del estudiante: ").append(estudiante.getPromacum()).append("\n")
                    .append("Número serial del estudiante: ").append(estudiante.getSerial()).append("\n")
                    .append("_________________________________________________\n");
        }

        if (arraylistestudiante.isEmpty()) {
            cadena.append("💔 No hay estudiantes registrados 💔");
        }

        JOptionPane.showMessageDialog(null, cadena.toString());
    }
}
