package com.upb.parcialde;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ComputadoraDAO {
    private static List<ComputadoraDTO> arraylistcomputadora = new ArrayList<>();

    public void gestionComputadoras() {
        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Menú de Gestión de Computadoras\n" +
                            "1. Registrar portátil\n" +
                            "2. Modificar portátil\n" +
                            "3. Eliminar portátil\n" +
                            "4. Buscar portátil por serial\n" +
                            "5. Imprimir todos los portátiles\n" +
                            "6. Volver al menú principal\n" +
                            "Ingrese su opción:"));

            switch (opcion) {
                case 1:
                    registrarComputadora();
                    break;
                case 2:
                    modificarComputadora();
                    break;
                case 3:
                    eliminarComputadora();
                    break;
                case 4:
                    buscarComputadoraPorSerial();
                    break;
                case 5:
                    imprimirComputadoras();
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 6);
    }

    public static void registrarComputadora() {
        while (JOptionPane.showConfirmDialog(null, "¿Desea agregar un computador?") == JOptionPane.YES_OPTION) {
            String serial = JOptionPane.showInputDialog("Ingrese el serial del PC");
            String marca = JOptionPane.showInputDialog("Ingrese la marca del PC");
            float tamano = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el tamaño del PC (DECIMALES CON PUNTO)"));
            float precio = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el precio del PC (DECIMALES CON PUNTO)"));
            validarSistema();
            validarProcesador();

            ComputadoraDTO computadora = new ComputadoraDTO(serial, marca, tamano, precio, validarSistema(), validarProcesador());
            arraylistcomputadora.add(computadora);
        }

        if (arraylistcomputadora.isEmpty()) {
            JOptionPane.showMessageDialog(null, "💔 No se agregó ningún PC 💔");
        } else {
            JOptionPane.showMessageDialog(null, "Se agregaron " + arraylistcomputadora.size() + " computadoras al sistema");
        }
    }

    public static void modificarComputadora() {
        String serial = JOptionPane.showInputDialog("Ingrese el serial del PC a modificar");
        boolean encontrado = false;

        for (ComputadoraDTO computadora : arraylistcomputadora) {
            if (computadora.getSerial().equals(serial)) {
                int opcion = Integer.parseInt(JOptionPane.showInputDialog(
                        "Elija la información registrada a cambiar\n"
                                + "1. Marca del PC\n"
                                + "2. Tamaño del PC\n"
                                + "3. Precio del PC\n"
                                + "4. Sistema operativo del PC\n"
                                + "5. Procesador del PC\n"
                                + "6. Salir\n"));
                switch (opcion) {
                    case 1:
                        computadora.setMarca(JOptionPane.showInputDialog("Ingrese la nueva marca del PC"));
                        break;
                    case 2:
                        computadora.setTamano(Float.parseFloat(JOptionPane.showInputDialog("Ingrese un nuevo tamaño")));
                        break;
                    case 3:
                        computadora.setPrecio(Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo precio")));
                        break;
                    case 4:
                        computadora.setSis(validarSistema());
                        break;
                    case 5:
                        computadora.setProcesador(validarProcesador());
                        break;
                    case 6:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Elección incorrecta");
                }
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "💔 No se encontró PC con ese serial 💔");
        } else {
            JOptionPane.showMessageDialog(null, "Información del PC actualizada exitosamente");
        }
    }

    public static void buscarComputadoraPorSerial() {
        String serial = JOptionPane.showInputDialog("Ingrese el serial del PC a buscar");
        boolean encontrado = false;

        for (ComputadoraDTO computadora : arraylistcomputadora) {
            if (computadora.getSerial().equals(serial)) {
                String infoComputadora = "Serial del PC: " + computadora.getSerial() + "\n" +
                        "Marca del PC: " + computadora.getMarca() + "\n" +
                        "Tamaño del PC: " + computadora.getTamano() + "\n" +
                        "Precio del PC: " + computadora.getPrecio() + "\n" +
                        "Sistema operativo: " + computadora.getSis() + "\n" +
                        "Procesador del PC: " + computadora.getProcesador() + "\n";
                JOptionPane.showMessageDialog(null, infoComputadora);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "💔 No se encontró PC con ese serial 💔");
        }
    }

    public static void eliminarComputadora() {
        String serial = JOptionPane.showInputDialog("Ingrese el serial del PC a eliminar");
        boolean eliminado = false;

        for (ComputadoraDTO computadora : arraylistcomputadora) {
            if (computadora.getSerial().equals(serial)) {
                arraylistcomputadora.remove(computadora);
                JOptionPane.showMessageDialog(null, "PC eliminado de la base de datos");
                eliminado = true;
                break;
            }
        }

        if (!eliminado) {
            JOptionPane.showMessageDialog(null, "No se encontró PC con ese serial");
        }
    }

    public static void imprimirComputadoras() {
        StringBuilder cadena = new StringBuilder("LISTA DE COMPUTADORES\n");
        if (!arraylistcomputadora.isEmpty()) {
            for (ComputadoraDTO computadora : arraylistcomputadora) {
                cadena.append("Serial del PC: ").append(computadora.getSerial()).append("\n")
                        .append("Marca del PC: ").append(computadora.getMarca()).append("\n")
                        .append("Tamaño del PC: ").append(computadora.getTamano()).append("\n")
                        .append("Precio del PC: ").append(computadora.getPrecio()).append("\n")
                        .append("Sistema operativo: ").append(computadora.getSis()).append("\n")
                        .append("Procesador del PC: ").append(computadora.getProcesador()).append("\n")
                        .append("_________________________________________________\n");
            }
        } else {
            cadena.append("No hay computadoras registradas");
        }
        JOptionPane.showMessageDialog(null, cadena.toString());
    }

    private static String validarSistema() {
        String sistema = "";
        int opcions = 0;

        do {
            opcions = Integer.parseInt(JOptionPane.showInputDialog("Elija sistema operativo del PC:\n"
                    + "1. Windows 7\n" + "2. Windows 10\n" + "3. Windows 11\n"));

            switch (opcions) {
                case 1:
                    sistema = "Windows 7";
                    break;
                case 2:
                    sistema = "Windows 10";
                    break;
                case 3:
                    sistema = "Windows 11";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción incorrecta de Sistema operativo. Por favor, elija 1, 2 o 3.");
            }
        } while (opcions != 1 && opcions != 2 && opcions != 3);

        return sistema;
    }

    private static String validarProcesador() {
        String proce = "";
        int opcionp = 0;

        do {
            opcionp = Integer.parseInt(JOptionPane.showInputDialog("Elija el procesador del PC:\n"
                    + "1. AMD Ryzen 7\n" + "2. Intel Core I5\n"));

            switch (opcionp) {
                case 1:
                    proce = "AMD Ryzen 7";
                    break;
                case 2:
                    proce = "Intel Core I5";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, elija 1 o 2.");
            }
        } while (opcionp != 1 && opcionp != 2);

        return proce;
    }

    public static String totalCompu(ArrayList<ComputadoraDTO> listaCompu) {
        String cadena = "";
        int total = 0;

        if (!listaCompu.isEmpty()) {
            total = listaCompu.size();
            cadena = "Cantidad de computadoras registradas: " + total;
            StringBuilder computadorasRegistradas = new StringBuilder("Estos son los computadores actualmente registrados:\n");
            for (ComputadoraDTO objCont : listaCompu) {
                computadorasRegistradas.append(objCont.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, computadorasRegistradas.toString());
        } else {
            cadena = "No se encontraron datos para buscar";
        }

        return cadena;
    }

}