package com.biblioteca;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Utilidades varias para la aplicacin.
 */
public class Util {
    /**
     * Pide un numero entero por consola.
     * @param scanner objeto Scanner que se utiliza para leer la entrada.
     * @param mensaje mensaje que se muestra al usuario.
     * @return numero entero introducido por el usuario.
     */
    public static int pedirNumero(Scanner scanner, String mensaje) {
        System.out.println(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada invalida. " + mensaje);
            scanner.next(); // Limpiar la entrada invalida
        }
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        return numero;
    }

    /**
     * Pide un numero entero por consola, validando que este en un rango.
     * @param scanner objeto Scanner que se utiliza para leer la entrada.
     * @param mensaje mensaje que se muestra al usuario.
     * @param min valor mnimo del rango.
     * @param max valor mximo del rango.
     * @return numero entero introducido por el usuario, dentro del rango.
     */
    public static int pedirNumeroEnRango(Scanner scanner, String mensaje, int min, int max) {
        int numero;
        System.out.println("Por favor ingrese un numero entre " + min + " y " + max + ".");
        do {
            numero = pedirNumero(scanner, mensaje);
            if (numero < min || numero > max) {
                System.out.println("Por favor ingrese un numero entre " + min + " y " + max + ".");
            }
        } while (numero < min || numero > max);
        return numero;
    }

    /**
     * Pide un numero con decimales por consola.
     * @param scanner objeto Scanner que se utiliza para leer la entrada.
     * @param mensaje mensaje que se muestra al usuario.
     * @return numero con decimales introducido por el usuario.
     */
    public static double pedirDouble(Scanner scanner, String mensaje) {
        System.out.println(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada invalida. " + mensaje);
            scanner.next(); // Limpiar la entrada invalida
        }
        double numero = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer
        return numero;
    }

    /**
     * Pide un texto por consola.
     * @param scanner objeto Scanner que se utiliza para leer la entrada.
     * @param mensaje mensaje que se muestra al usuario.
     * @return texto introducido por el usuario.
     */
    public static String pedirTexto(Scanner scanner, String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    /**
     * Crea una carpeta en la ruta especificada si no existe.
     * @param path ruta de la carpeta a crear.
     * @throws IOException si no se puede crear la carpeta.
     */
    public static void crearCarpetaSiNoExiste(Path path) {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            System.err.println("Error creando la carpeta 'data': " + e.getMessage());
        }
    }

/**
 * Crea una carpeta en la ruta especificada si no existe.
 * @param ruta de la carpeta a crear.
 * @throws IOException si no se puede crear la carpeta.
 */
    public static void crearCarpetaSiNoExiste(String ruta) {
        try {
            Path path = Path.of(ruta);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            System.err.println("Error creando la carpeta 'data': " + e.getMessage());
        }
    }
}
