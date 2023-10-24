package com.upb.parcialde;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        ComputadoraDAO computadoraDAO = new ComputadoraDAO();
        PrestamoDAO prestamoDAO = new PrestamoDAO();

        int opcion;
        do {
            opcion = obtenerOpcionMenu();

            switch (opcion) {
                case 1:
                    estudianteDAO.gestionEstudiantes();
                    break;
                case 2:
                    computadoraDAO.gestionComputadoras();
                    break;
                case 3:
                    prestamoDAO.gestionPrestamos();
                    break;
                case 4:
                    prestamoDAO.imprimirInventarioTotal();
                    ComputadoraDAO.totalcompu();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo del programa.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, elija una opción válida.");
            }
        } while (opcion != 5);
    }

    public static int obtenerOpcionMenu() {
        try {
            return Integer.parseInt(JOptionPane.showInputDialog(
                    "Menú de Gestión de Préstamos\n" +
                            "1. Menú de Estudiantes 🧑🏻‍🏫 \n" +
                            "2. Menú de Computadoras 🧑🏻‍💻\n" +
                            "3. Menú de Préstamos 🖥️ \n" +
                            "4. Imprimir Inventario Total 📠 \n" +
                            "5. 💔 Salir del Programa 💔\n" +
                            "Ingrese su opción: "));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
            return obtenerOpcionMenu();
        }
    }
}
