package com.biblioteca.xml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import com.biblioteca.Util;
import com.biblioteca.json.GestorJSON;
import com.biblioteca.modelo.Biblioteca;
import com.biblioteca.modelo.Libro;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

/**
 * Clase para gestionar la conversión de JSON a XML y obtener la ruta del archivo XML.
 */
public class GestorXML {

    /**
     * Obtiene la ruta del archivo XML.
     * 
     * @return La ruta del archivo XML.
     */
    public static Path getRutaXML() {
        return (getCarpetaXML().resolve("libros.xml"));
    }

    /**
     * Obtiene la ruta de la carpeta donde se almacenará el archivo XML.
     * 
     * @return La ruta de la carpeta XML.
     */
    private static Path getCarpetaXML() {
        return Path.of("data");
    }

    /**
     * Convierte los datos de la biblioteca desde JSON a XML.
     */
    public static void convertirJSONaXML() {
        Util.crearCarpetaSiNoExiste(getCarpetaXML());

        ArrayList<Libro> libros = GestorJSON.importarJSON();
        Biblioteca biblioteca = new Biblioteca(libros);

        try {
            JAXBContext context = JAXBContext.newInstance(Biblioteca.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            try (var w = Files.newBufferedWriter(getRutaXML())) {
                marshaller.marshal(biblioteca, w);
            }

            System.out.println("Conversión JSON → XML completada correctamente.");
        } catch (JAXBException e) {
            System.err.println("Error convirtiendo a XML: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error escribiendo el archivo XML: " + e.getMessage());
        }
    }
}
