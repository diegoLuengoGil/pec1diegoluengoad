package com.biblioteca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import com.biblioteca.json.GestorJSON;
import com.biblioteca.modelo.Biblioteca;
import com.biblioteca.modelo.Libro;
import com.biblioteca.xml.ConsultasXPath;
import com.biblioteca.xml.GestorXML;

public class Main {

    private static void anadirLibro(Scanner scanner, Biblioteca biblioteca) {
        // Pedir datos del libro
        String isbn = Util.pedirTexto(scanner, "ISBN: ");
        String titulo = Util.pedirTexto(scanner, "Título: ");
        String autor = Util.pedirTexto(scanner, "Autor: ");
        double precio = Util.pedirDouble(scanner, "Precio: ");

        int anio = Util.pedirNumeroEnRango(scanner, "Año de publicación: ", 0, 2100);

        Libro libro = new Libro(isbn, titulo, autor, anio, precio);
        biblioteca.agregarLibro(libro);
        System.out.println("Libro añadido correctamente.");
    }

    // Métodos a implementar según funcionalidad
    private static void gestionarLibros(Biblioteca biblioteca) {
        boolean menuActivo = true;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\n=== MENÚ GESTIONAR LIBROS ===");
            System.out.println("1. Añadir libro");
            System.out.println("2. Eliminar libro por ISBN");
            System.out.println("3. Mostrar todos los libros");
            System.out.println("4. Volver al menú principal");

            int opcion = Util.pedirNumeroEnRango(scanner, "Seleccione una opción: ", 1, 4);

            switch (opcion) {
                case 1 -> anadirLibro(scanner, biblioteca);
                case 2 -> biblioteca.menuEliminarLibro(scanner);
                case 3 -> biblioteca.mostrarLibros();
                case 4 -> menuActivo = false;
                default -> System.out.println("Opción no válida.");
            }
        } while (menuActivo);

    }

    private static void manejoDatosMenu(Scanner scanner, Biblioteca biblioteca) {
        boolean menuActivo = true;

        do {
            System.out.println("\n=== MENÚ DE MANEJO DE DATOS ===");
            System.out.println("1. Exportar libros a JSON");
            System.out.println("2. Importar libros desde JSON");
            System.out.println("3. Convertir JSON a XML");
            System.out.println("4. Volver al menú principal");

            int opcion = Util.pedirNumeroEnRango(scanner, "Seleccione una opción: ", 1, 4);

            switch (opcion) {
                case 1 -> GestorJSON.exportarJSON(biblioteca.getLibros());
                case 2 -> biblioteca.setLibros((GestorJSON.importarJSON()));
                case 3 -> GestorXML.convertirJSONaXML();
                case 4 -> menuActivo = false;
                default -> System.out.println("Opción no válida.");
            }
        } while (menuActivo);
    }

    public static void menu() {
        Biblioteca biblioteca;
        Scanner scanner = new Scanner(System.in);
        boolean menuActivo = true;

        ArrayList<Libro> listaCargada = Biblioteca.cargarLibros();
        if (listaCargada.isEmpty()) {
            // No hay libros, usamos constructor vacío
            biblioteca = new Biblioteca();
            System.out.println("No se encontraron libros guardados. Se ha creado una biblioteca nueva.");
        } else {
            // Hay libros, inicializamos la biblioteca con ellos
            biblioteca = new Biblioteca(listaCargada);
            System.out.println("Se han cargado " + listaCargada.size() + " libros de la biblioteca.");
        }

        do {

            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestionar libros");
            System.out.println("2. Manejo de datos");
            System.out.println("3. Consultas sobre XML");
            System.out.println("4. Salir");

            int opcion = Util.pedirNumeroEnRango(scanner, "Seleccione una opción: ", 1, 4);

            switch (opcion) {
                case 1 -> gestionarLibros(biblioteca);
                case 2 -> manejoDatosMenu(scanner, biblioteca);
                case 3 -> ConsultasXPath.consultasXML(scanner);
                case 4 -> menuActivo = false;
                default -> System.out.println("Opción no válida.");
            }
        } while (menuActivo);
        biblioteca.guardarLibros();
    }

    public static void main(String[] args) {
        menu();
    }
}