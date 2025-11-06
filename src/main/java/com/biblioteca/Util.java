package com.biblioteca;

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

    public static String pedirTexto(Scanner scanner, String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }
}
