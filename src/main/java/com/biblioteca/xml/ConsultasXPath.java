package com.biblioteca.xml;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.xpath.*;
import org.w3c.dom.*;

import com.biblioteca.Util;

/**
 * Clase para realizar consultas XPath sobre el archivo XML de la biblioteca.
 */
public class ConsultasXPath {

    /**
     * Muestra los libros posteriores a un año determinado.
     * 
     * @param anio Año de referencia.
     */
    public static void librosPosterioresA(int anio) {
        ejecutarConsulta("//libro[anio > " + anio + "]");
    }

    /**
     * Muestra los libros anteriores a un año determinado.
     * 
     * @param anio Año de referencia.
     */
    public static void librosAnterioresA(int anio) {
        ejecutarConsulta("//libro[anio < " + anio + "]");
    }

    /**
     * Muestra los libros del año determinado.
     * 
     * @param anio Año de referencia.
     */
    public static void librosDelAnio(int anio) {
        ejecutarConsulta("//libro[anio = " + anio + "]");
    }

    /**
     * Muestra los autores que contienen una palabra clave.
     * @param palabra Palabra clave a buscar en los nombres de los autores.
     */
    public static void autoresConPalabraClave(String palabra) {
        ejecutarConsulta(
                "//libro[contains(translate(autor,'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÜÑ','abcdefghijklmnopqrstuvwxyzáéíóúüñ'),'"
                        + palabra.toLowerCase() + "')]");
    }

    /**
     * Muestra los libros con precio mayor al valor especificado.
     * 
     * @param precio Valor de referencia.
     */
    public static void librosConPrecioMayorA(double precio) {
        ejecutarConsulta("//libro[precio > " + precio + "]");
    }

    /**
     * Muestra los libros con precio menor al valor especificado.
     * 
     * @param precio Valor de referencia.
     */
    public static void librosConPrecioMenorA(double precio) {
        ejecutarConsulta("//libro[precio < " + precio + "]");
    }

    /**
     * Muestra los libros con precio igual al valor especificado.
     * 
     * @param precio Valor de referencia.
     */
    public static void librosConPrecioIgualA(double precio) {
        ejecutarConsulta("//libro[precio = " + precio + "]");
    }

    /**
     * Ejecuta una consulta XPath y muestra los resultados.
     * 
     * @param expresionXPath Expresión XPath a evaluar.
     */
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
                    System.out.printf("%-30s %-25s %-6s %-8s%n", "TÍTULO", "AUTOR", "AÑO", "PRECIO");
                    System.out.println("------------------------------------------------------------------------");

                    for (int i = 0; i < nodos.getLength(); i++) {
                        var hijos = nodos.item(i).getChildNodes();
                        String titulo = "", autor = "", anio = "", precio = "";

                        for (int j = 0; j < hijos.getLength(); j++) {
                            var nodo = hijos.item(j);
                            if (nodo.getNodeType() != org.w3c.dom.Node.ELEMENT_NODE)
                                continue;

                            switch (nodo.getNodeName()) {
                                case "titulo" -> titulo = nodo.getTextContent();
                                case "autor" -> autor = nodo.getTextContent();
                                case "anio" -> anio = nodo.getTextContent();
                                case "precio" -> precio = nodo.getTextContent();
                            }
                        }

                        System.out.printf("%-30s %-25s %-6s %-8s%n", titulo, autor, anio, precio);
                    }
                    System.out.println("------------------------------------------------------------------------");
                }
            }

        } catch (Exception e) {
            System.err.println("Error ejecutando la consulta XPath: " + e.getMessage());
        }
    }

    /**
     * Muestra el menú de consultas por precio.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
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

    /**
     * Muestra el menú de consultas por año.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
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

    /**
     * Muestra el menú principal de consultas XPath.
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
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
