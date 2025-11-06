package com.biblioteca;

import java.util.Scanner;

public class Main {

    // Métodos a implementar según funcionalidad
    private static void gestionarLibros() {
        System.out.println("Función gestionar libros llamada.");
        // Aquí iría un submenú con añadir, eliminar, mostrar
    }

    private static void manejoDatosMenu(Scanner scanner) {
        boolean menuActivo = true;

        do {
            System.out.println("\n=== MENÚ DE MANEJO DE DATOS ===");
            System.out.println("1. Exportar libros a JSON");
            System.out.println("2. Importar libros desde JSON");
            System.out.println("3. Convertir JSON a XML");
            System.out.println("4. Volver al menú principal");

            int opcion = Util.pedirNumeroEnRango(scanner, "Seleccione una opción: ", 1, 4);

            switch (opcion) {
                case 1 -> System.out.println("prueba");// exportarLibrosJSON();
                case 2 -> System.out.println("prueba");// importarLibrosJSON();
                case 3 -> System.out.println("prueba");// convertirJSONaXML();
                case 4 -> menuActivo = false;
                default -> System.out.println("Opción no válida.");
            }
        } while (menuActivo);
    }

    private static void consultasXML() {
        System.out.println("Función consultas XML llamada.");
        // Aquí irían las consultas XPath
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean menuActivo = true;

        do {

            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestionar libros");
            System.out.println("2. Manejo de datos");
            System.out.println("3. Consultas sobre XML");
            System.out.println("4. Salir");

            int opcion = Util.pedirNumeroEnRango(scanner, "Seleccione una opción: ", 1, 4);

            switch (opcion) {
                case 1 -> gestionarLibros();
                case 2 -> manejoDatosMenu(scanner);
                case 3 -> consultasXML();
                case 4 -> menuActivo = false;
                default -> System.out.println("Opción no válida.");
            }
        } while (menuActivo);
    }

    public static void main(String[] args) {
        menu();
    }
}