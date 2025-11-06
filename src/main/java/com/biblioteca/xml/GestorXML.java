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

public class GestorXML {

    public static Path getRutaXML() {
        return (getCarpetaXML().resolve("libros.xml"));
    }

    private static Path getCarpetaXML() {
        return Path.of("data");
    }

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
