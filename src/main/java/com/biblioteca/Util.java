package com.biblioteca;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Util {
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

    public static String pedirTexto(Scanner scanner, String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    public static void crearCarpetaSiNoExiste(Path path) {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            System.err.println("Error creando la carpeta 'data': " + e.getMessage());
        }
    }

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
