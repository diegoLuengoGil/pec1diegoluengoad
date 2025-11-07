package com.biblioteca.json;

import com.biblioteca.Util;
import com.biblioteca.modelo.Libro;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import java.lang.reflect.Type;

/**
 * Clase para gestionar la importación y exportación de datos en formato JSON.
 */
public class GestorJSON {

    /**
     * Obtiene la ruta del archivo JSON.
     * 
     * @return La ruta del archivo JSON.
     */
    private static Path getRutaJSON() {
        return (getCarpetaJSON().resolve("libros.json"));
    }

    /**
     * Obtiene la ruta de la carpeta donde se almacenará el archivo JSON.
     * 
     * @return La ruta de la carpeta JSON.
     */
    private static Path getCarpetaJSON() {
        return Path.of("data");
    }

    /**
     * Exporta una lista de libros en formato JSON.
     * 
     * @param libros La lista de libros a exportar.
     */
    public static void exportarJSON(ArrayList<Libro> libros) {
        Path ubicacionJSON = getRutaJSON();
        Util.crearCarpetaSiNoExiste(getCarpetaJSON()); // Se asegura que la carpeta exista
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(ubicacionJSON.toFile())) {
            gson.toJson(libros, writer);
            System.out.println("Exportación a JSON realizada correctamente.");
        } catch (IOException e) {
            System.out.println("Error exportando a JSON: " + e.getMessage());
        }
    }

    /**
     * Importa una lista de libros desde un archivo JSON.
     * 
     * @return La lista de libros importados.
     */
    public static ArrayList<Libro> importarJSON() {
        Path ubicacionJSON = getRutaJSON();
        Util.crearCarpetaSiNoExiste(getCarpetaJSON()); // Se asegura que la carpeta exista
        ArrayList<Libro> listaLibros = new ArrayList<>(); // Inicializamos lista vacía
        Gson gson = new Gson();
        Type librosArray = new TypeToken<List<Libro>>(){}.getType();

        try (FileReader reader = new FileReader(ubicacionJSON.toFile())) {
            listaLibros = gson.fromJson(reader, librosArray);
            System.out.println("Importación desde JSON realizada correctamente.");
        } catch (IOException e) {
            System.out.println("Error importando desde JSON: " + e.getMessage());
        }

        return listaLibros;
    }

}
