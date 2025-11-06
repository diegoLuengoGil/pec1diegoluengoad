package com.biblioteca.json;

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

    private static final String CARPETA_JSON = "data";
    private static final String RUTA_JSON = CARPETA_JSON + "/libros.json";

    // Asegura que la carpeta exista
    private static void crearCarpetaSiNoExiste() {
        try {
            Path path = Path.of(CARPETA_JSON);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            System.err.println("Error creando la carpeta 'data': " + e.getMessage());
        }
    }

    public static void exportarJSON(ArrayList<Libro> libros) {
        crearCarpetaSiNoExiste(); // Se asegura que la carpeta exista
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(RUTA_JSON)) {
            gson.toJson(libros, writer);
            System.out.println("Exportación a JSON realizada correctamente.");
        } catch (IOException e) {
            System.err.println("Error exportando a JSON: " + e.getMessage());
        }
    }

    public static ArrayList<Libro> importarJSON() {
        crearCarpetaSiNoExiste(); // Se asegura que la carpeta exista
        ArrayList<Libro> listaLibros = new ArrayList<>(); // Inicializamos lista vacía
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(RUTA_JSON)) {
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
