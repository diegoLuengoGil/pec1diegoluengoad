package com.biblioteca.json;

import com.biblioteca.Util;
import com.biblioteca.modelo.Libro;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorJSON {

    private static Path getRutaJSON() {
        return (getCarpetaJSON().resolve("libros.json"));
    }

    private static Path getCarpetaJSON() {
        return Path.of("data");
    }

    public static void exportarJSON(ArrayList<Libro> libros) {
        Path ubicacionJSON = getRutaJSON();
        Util.crearCarpetaSiNoExiste(getCarpetaJSON()); // Se asegura que la carpeta exista
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(ubicacionJSON.toFile())) {
            gson.toJson(libros, writer);
            System.out.println("Exportación a JSON realizada correctamente.");
        } catch (IOException e) {
            System.err.println("Error exportando a JSON: " + e.getMessage());
        }
    }

    public static ArrayList<Libro> importarJSON() {
        Path ubicacionJSON = getRutaJSON();
        Util.crearCarpetaSiNoExiste(getCarpetaJSON()); // Se asegura que la carpeta exista
        ArrayList<Libro> listaLibros = new ArrayList<>(); // Inicializamos lista vacía
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(ubicacionJSON.toFile())) {
            Libro[] librosArray = gson.fromJson(reader, Libro[].class);
            if (librosArray != null) { // Evita NullPointer si el JSON está vacío
                for (Libro l : librosArray) {
                    listaLibros.add(l);
                }
            }
            System.out.println("Importación desde JSON realizada correctamente.");
        } catch (IOException e) {
            System.err.println("Error importando desde JSON: " + e.getMessage());
        }

        return listaLibros;
    }

}
