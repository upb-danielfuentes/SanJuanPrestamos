package com.upb.parcialde;

import javax.swing.*;
import java.util.Vector;

public class ComputadoraDAO {
    private static String cadena;
    private static Vector<ComputadoraDTO> vectorcompu;

    static {
        vectorcompu = new Vector<ComputadoraDTO>();
    }

    public static void gestionComputadoras() {
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
            vectorcompu.addElement(new ComputadoraDTO(
                    JOptionPane.showInputDialog("Ingrese el serial del PC"),
                    JOptionPane.showInputDialog("Ingrese la marca del PC"),
                    Float.parseFloat(JOptionPane.showInputDialog("Ingrese el tamaño del PC (DECIMALES CON PUNTO))")),
                    Float.parseFloat(JOptionPane.showInputDialog("Ingrese el precio del PC (DECIMALES CON PUNTO))")),
                    JOptionPane.showInputDialog("Ingrese el sistema operativo del PC"),
                    JOptionPane.showInputDialog("Ingrese el procesador del PC")));
        }
        if (vectorcompu.isEmpty())
            cadena = "💔 No se agregó ningún PC 💔";
        else
            cadena = "Se agregaron " + vectorcompu.size() + " computadoras al sistema";
        JOptionPane.showMessageDialog(null, cadena);
    }

    public static void modificarComputadora() {
        cadena = "";
        String idpc = JOptionPane.showInputDialog("Ingrese el serial del PC a modificar");
        boolean encontrado = false;
        
        for (ComputadoraDTO computadora : vectorcompu) {
            if (computadora.getSerial().equalsIgnoreCase(idpc)) {
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
                        cadena = "Marca actualizada exitosamente";
                        break;
                    case 2:
                        computadora.setTamano(Float.parseFloat(JOptionPane.showInputDialog("Ingrese un nuevo tamaño")));
                        cadena = "Tamaño actualizado exitosamente";
                        break;
                    case 3:
                        computadora.setPrecio(Float.parseFloat(JOptionPane.showInputDialog("Ingrese el nuevo precio")));
                        cadena = "Precio actualizado exitosamente";
                        break;
                    case 4:
                        computadora.setSis(JOptionPane.showInputDialog("Ingrese nuevo sistema operativo"));
                        cadena = "Sistema operativo actualizado exitosamente";
                        break;
                    case 5:
                        computadora.setProcesador(JOptionPane.showInputDialog("Ingrese nuevo procesador"));
                        cadena = "Procesador actualizado exitosamente";
                        break;
                    case 6:
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
            cadena = "💔 No se encontró PC con ese serial 💔";
        }
        
        JOptionPane.showMessageDialog(null, cadena);
    }

    public static void buscarComputadoraPorSerial() {
        cadena = "";
        String idpc = JOptionPane.showInputDialog("Ingrese el serial del PC a buscar");
        boolean encontrado = false;

        for (ComputadoraDTO computadora : vectorcompu) {
            if (computadora.getSerial().equalsIgnoreCase(idpc)) {
                cadena = "Serial del PC: " + computadora.getSerial() + "\n" +
                        "Marca del PC: " + computadora.getMarca() + "\n" +
                        "Tamaño del PC: " + computadora.getTamano() + "\n" +
                        "Precio del PC: " + computadora.getPrecio() + "\n" +
                        "Sistema operativo: " + computadora.getSis() + "\n" +
                        "Procesador del PC: " + computadora.getProcesador() + "\n";
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            cadena = "💔 No se encontró PC con ese serial 💔";
        }

        JOptionPane.showMessageDialog(null, cadena);
    }

    public static void eliminarComputadora() {
        cadena = "";
        String idpc = JOptionPane.showInputDialog("Ingrese el serial del PC a eliminar");
        boolean eliminado = false;

        for (ComputadoraDTO computadora : vectorcompu) {
            if (computadora.getSerial().equalsIgnoreCase(idpc)) {
                vectorcompu.remove(computadora);
                cadena = "PC eliminado de la base de datos";
                eliminado = true;
                break;
            }
        }

        if (!eliminado) {
            cadena = "No se encontró PC con ese serial";
        }

        JOptionPane.showMessageDialog(null, cadena);
    }

    public static void imprimirComputadoras() {
        cadena = "LISTA DE COMPUTADORES\n";
        if (!vectorcompu.isEmpty()) {
            for (ComputadoraDTO computadora : vectorcompu) {
                cadena += "Serial del PC: " + computadora.getSerial() + "\n" +
                        "Marca del PC: " + computadora.getMarca() + "\n" +
                        "Tamaño del PC: " + computadora.getTamano() + "\n" +
                        "Precio del PC: " + computadora.getPrecio() + "\n" +
                        "Sistema operativo: " + computadora.getSis() + "\n" +
                        "Procesador del PC: " + computadora.getProcesador() + "\n" +
                        "_________________________________________________\n";
            }
        } else {
            cadena = "No hay computadoras registradas";
        }
        JOptionPane.showMessageDialog(null, cadena);
    }
}
