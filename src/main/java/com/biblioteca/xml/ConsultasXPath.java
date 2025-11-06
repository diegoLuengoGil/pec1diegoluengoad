package com.biblioteca.xml;

import java.io.File;
import java.util.Scanner;


import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.xpath.*;
import org.w3c.dom.*;

import com.biblioteca.Util;

public class ConsultasXPath {

    public static void librosPosterioresA(int anio) {
        ejecutarConsulta("//libro[anio > " + anio + "]");
    }

    public static void librosAnterioresA(int anio) {
        ejecutarConsulta("//libro[anio < " + anio + "]");
    }

    public static void librosDelAnio(int anio) {
        ejecutarConsulta("//libro[anio = " + anio + "]");
    }

    public static void autoresConPalabraClave(String palabra) {
        ejecutarConsulta("//libro[contains(translate(autor,'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÜÑ','abcdefghijklmnopqrstuvwxyzáéíóúüñ'),'"
                + palabra.toLowerCase() + "')]");
    }

    public static void librosConPrecioMayorA(double precio) {
        ejecutarConsulta("//libro[precio > " + precio + "]");
    }

    public static void librosConPrecioMenorA(double precio) {
        ejecutarConsulta("//libro[precio < " + precio + "]");
    }

    public static void librosConPrecioIgualA(double precio) {
        ejecutarConsulta("//libro[precio = " + precio + "]");
    }

    private static void ejecutarConsulta(String expresionXPath) {
        try {
            File xmlFile = GestorXML.getRutaXML().toFile();
            if (!xmlFile.exists()) {
                System.err.println("No se encontró el archivo XML en " + GestorXML.getRutaXML().toString());
            } else {

                // DocumentBuilderFactory crea el analizador DOM.
                // Se desactiva el uso de espacios de nombres (namespaceAware).
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setNamespaceAware(false);

                // Crear el objeto Document a partir del fichero XML.
                Document doc = dbf.newDocumentBuilder().parse(xmlFile);

                // Normalizar el documento evita errores por nodos de texto partidos.
                doc.getDocumentElement().normalize();

                // Crear el evaluador XPath
                XPath xpath = XPathFactory.newInstance().newXPath();

                NodeList nodos = (NodeList) xpath.evaluate(
                        expresionXPath,
                        doc, XPathConstants.NODESET);

                if (nodos.getLength() == 0) {
                    System.out.println("No se encontraron resultados para esta consulta.");
                } else {
                    System.out.println("\nRESULTADOS DE LA CONSULTA:");
                    for (int i = 0; i < nodos.getLength(); i++) {
                        System.out.println("--------------------------------");
                        NodeList hijos = nodos.item(i).getChildNodes();
                        for (int j = 0; j < hijos.getLength(); j++) {
                            if (hijos.item(j).getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                                System.out.printf("%s: %s%n",
                                        hijos.item(j).getNodeName(),
                                        hijos.item(j).getTextContent());
                            }
                        }
                    }
                    System.out.println("--------------------------------");
                }
            }

        } catch (Exception e) {
            System.err.println("Error ejecutando la consulta XPath: " + e.getMessage());
        }
    }

    private static void menuConsultasPorPrecio(Scanner scanner) {
        boolean menuActivo = true;

        do {
            System.out.println("\n=== CONSULTAS POR PRECIO ===");
            System.out.println("1. Libros con precio superior a un valor");
            System.out.println("2. Libros con precio inferior a un valor");
            System.out.println("3. Libros con precio igual a un valor");
            System.out.println("4. Volver");

            int opcion = Util.pedirNumeroEnRango(scanner, "Seleccione una opción: ", 1, 4);

            double precio;

            switch (opcion) {
                case 1 -> {
                    precio = Util.pedirDouble(scanner, "Introduzca el precio: ");
                    librosConPrecioMayorA(precio);
                }
                case 2 -> {
                    precio = Util.pedirDouble(scanner, "Introduzca el precio: ");
                    librosConPrecioMenorA(precio);
                }
                case 3 -> {
                    precio = Util.pedirDouble(scanner, "Introduzca el precio: ");
                    librosConPrecioIgualA(precio);
                }
                case 4 -> menuActivo = false;
            }
        } while (menuActivo);
    }

    private static void menuConsultasPorAnio(Scanner scanner) {
        boolean menuActivo = true;

        do {
            System.out.println("\n=== CONSULTAS POR AÑO ===");
            System.out.println("1. Libros posteriores a un año");
            System.out.println("2. Libros anteriores a un año");
            System.out.println("3. Libros del año exacto");
            System.out.println("4. Volver");

            int opcion = Util.pedirNumeroEnRango(scanner, "Seleccione una opción: ", 1, 4);

            int anio;

            switch (opcion) {
                case 1 -> {
                    anio = Util.pedirNumero(scanner, "Introduzca el año: ");
                    librosPosterioresA(anio);
                }
                case 2 -> {
                    anio = Util.pedirNumero(scanner, "Introduzca el año: ");
                    librosAnterioresA(anio);
                }
                case 3 -> {
                    anio = Util.pedirNumero(scanner, "Introduzca el año: ");
                    librosDelAnio(anio);
                }
                case 4 -> menuActivo = false;
            }
        } while (menuActivo);
    }

    public static void consultasXML(Scanner scanner) {
        boolean menuActivo = true;

        do {
            System.out.println("\n=== CONSULTAS XPath ===");
            System.out.println("1. Consultar por año");
            System.out.println("2. Buscar autores por palabra clave");
            System.out.println("3. Consultar por precio");
            System.out.println("4. Volver al menú principal");

            int opcion = Util.pedirNumeroEnRango(scanner, "Seleccione una opción: ", 1, 4);

            switch (opcion) {
                case 1 -> menuConsultasPorAnio(scanner);
                case 2 -> {
                    System.out.print("Introduzca palabra clave para buscar en autores: ");
                    String palabra = scanner.nextLine().trim();
                    autoresConPalabraClave(palabra);
                }
                case 3 -> menuConsultasPorPrecio(scanner);
                case 4 -> menuActivo = false;
            }

        } while (menuActivo);
    }

}
