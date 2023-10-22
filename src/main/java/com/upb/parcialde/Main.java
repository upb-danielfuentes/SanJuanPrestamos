package com.upb.parcialde;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        new EstudianteDAO();
        new ComputadoraDAO();
        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "Menu SuperDotado de Gestión de Préstamos\n" +
                            "1. MENU ESTUDIANTES 🧑🏻‍🏫 \n" +
                            "2. MENU COMPUTADOR 🧑🏻‍💻 \n" +
                            "3. MENU GESTIÓN DE PRÉSTAMOS \n" +
                            "4. IMPRIMIR INVENTARIO TOTAL\n" +
                            "5. 💔 SALIR DEL PROGRAMA 💔 \n" +
                            "Ingrese su opción: "));

            switch (opcion) {
                case 1:
                    EstudianteDAO.gestionEstudiantes();
                    break;
                case 2:
                    ComputadoraDAO.gestionComputadoras();
                    break;
                case 3:
                    PrestamoDAO.gestionPrestamos();
                    break;
                case 4:
                    PrestamoDAO.imprimirInventarioTotal();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo del programa.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 5);
    }
}