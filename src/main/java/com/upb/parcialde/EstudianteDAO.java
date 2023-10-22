package com.upb.parcialde;

import javax.swing.*;
import java.util.Vector;

public class EstudianteDAO {
    private static String cadena;
    private static Vector<EstudianteDTO> vectorestu;

    static {
        vectorestu = new Vector<EstudianteDTO>();
    }

    public EstudianteDAO() {
        cadena = "";
        vectorestu = new Vector<EstudianteDTO>();
    }

    public static void gestionEstudiantes() {
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
            vectorestu.addElement(new EstudianteDTO(
                    JOptionPane.showInputDialog("Ingrese la cédula del estudiante"),
                    JOptionPane.showInputDialog("Ingrese el nombre del estudiante"),
                    JOptionPane.showInputDialog("Ingrese el apellido del estudiante"),
                    JOptionPane.showInputDialog("Ingrese el teléfono del estudiante"),
                    Integer.parseInt(
                            JOptionPane.showInputDialog("Ingrese el número del semestre actual del estudiante")),
                    Float.parseFloat(JOptionPane.showInputDialog("Ingrese el promedio actual del estudiante")),
                    JOptionPane.showInputDialog("¿Cuál es el número serial del estudiante?")));
        }
        if (vectorestu.isEmpty())
            cadena = "💔 No se agregó ningún estudiante 💔";
        else
            cadena = "Se agregaron " + vectorestu.size() + " estudiantes al sistema";
        JOptionPane.showMessageDialog(null, cadena);
    }

    public static void modificarEstudiante() {
        cadena = "";
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula del estudiante a modificar");
        boolean encontrado = false;

        for (EstudianteDTO estudiante : vectorestu) {
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
                        cadena = "Nombre actualizado exitosamente";
                        break;
                    case 2:
                        estudiante.setApellido(JOptionPane.showInputDialog("Ingrese un nuevo apellido"));
                        cadena = "Apellido actualizado exitosamente";
                        break;
                    case 3:
                        estudiante.setTel(JOptionPane.showInputDialog("Ingrese el nuevo teléfono"));
                        cadena = "Teléfono actualizado exitosamente";
                        break;
                    case 4:
                        estudiante.setNumsem(Integer.parseInt(
                                JOptionPane.showInputDialog("Ingrese el nuevo semestre que se está cursando")));
                        cadena = "Número de semestre actualizado exitosamente";
                        break;
                    case 5:
                        estudiante.setPromacum(
                                Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo promedio total")));
                        cadena = "Promedio actualizado exitosamente";
                        break;
                    case 6:
                        estudiante.setSerial(JOptionPane.showInputDialog("Ingrese el nuevo número serial"));
                        cadena = "Número serial actualizado exitosamente";
                        break;
                    case 7:
                        cadena = "No se hizo ningún cambio";
                        break;
                    default:
                        cadena = "Elección incorrecta";
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            cadena = "💔 No se encontró estudiante con esa cédula 💔";
        }

        JOptionPane.showMessageDialog(null, cadena);
    }

    public static void buscarEstudiantePorCedula() {
        cadena = "";
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula del estudiante a buscar");
        boolean encontrado = false;

        for (EstudianteDTO estudiante : vectorestu) {
            if (estudiante.getCedula().equalsIgnoreCase(cedula)) {
                cadena = "Cédula del estudiante: " + estudiante.getCedula() + "\n" +
                        "Nombre del estudiante: " + estudiante.getNombre() + "\n" +
                        "Apellido del estudiante: " + estudiante.getApellido() + "\n" +
                        "Teléfono del estudiante: " + estudiante.getTel() + "\n" +
                        "Semestre actual del estudiante: " + estudiante.getNumsem() + "\n" +
                        "Promedio actual del estudiante: " + estudiante.getPromacum() + "\n" +
                        "Número serial del estudiante: " + estudiante.getSerial() + "\n";
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            cadena = "💔 No se encontró estudiante con esa cédula 💔";
        }

        JOptionPane.showMessageDialog(null, cadena);
    }

    public static void eliminarEstudiante() {
        cadena = "";
        String cedula = JOptionPane.showInputDialog("Ingrese la cédula del estudiante a eliminar");
        boolean eliminado = false;

        for (EstudianteDTO estudiante : vectorestu) {
            if (estudiante.getCedula().equalsIgnoreCase(cedula)) {
                vectorestu.remove(estudiante);
                cadena = "Estudiante eliminado de la base de datos";
                eliminado = true;
                break;
            }
        }

        if (!eliminado) {
            cadena = "💔 No se encontró estudiante con esa cédula 💔";
        }

        JOptionPane.showMessageDialog(null, cadena);
    }

    public static void imprimirEstudiantes() {
        cadena = "LISTA DE ESTUDIANTES\n";
        for (EstudianteDTO estudiante : vectorestu) {
            cadena += "Cédula del estudiante: " + estudiante.getCedula() + "\n" +
                    "Nombre del estudiante: " + estudiante.getNombre() + "\n" +
                    "Apellido del estudiante: " + estudiante.getApellido() + "\n" +
                    "Teléfono del estudiante: " + estudiante.getTel() + "\n" +
                    "Semestre actual del estudiante: " + estudiante.getNumsem() + "\n" +
                    "Promedio actual del estudiante: " + estudiante.getPromacum() + "\n" +
                    "Número serial del estudiante: " + estudiante.getSerial() + "\n" +
                    "_________________________________________________\n";
        }

        if (vectorestu.isEmpty()) {
            cadena = "💔 No hay estudiantes registrados 💔";
        }

        JOptionPane.showMessageDialog(null, cadena);
    }
}
