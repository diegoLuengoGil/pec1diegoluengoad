package com.biblioteca.modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import com.biblioteca.Util;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 * Clase que representa una biblioteca que contiene una lista de libros.
 */
@XmlRootElement(name = "biblioteca")
@XmlAccessorType(XmlAccessType.FIELD)
public class Biblioteca {

    /**
     * Lista de libros en la biblioteca.
     */
    @XmlElement(name = "libro")
    private ArrayList<Libro> libros = new ArrayList<>();

    /**
     * Constructor de la clase Biblioteca.
     */
    public Biblioteca() {
    }

    /**
     * Constructor de la clase Biblioteca con una lista de libros.
     * 
     * @param libros Lista de libros para inicializar la biblioteca.
     */
    public Biblioteca(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    /**
     * Obtiene la lista de libros en la biblioteca.
     * 
     * @return Lista de libros en la biblioteca.
     */
    public ArrayList<Libro> getLibros() {
        return libros;
    }

    /**
     * Establece la lista de libros en la biblioteca.
     * 
     * @param libros Lista de libros para establecer en la biblioteca.
     */
    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    /**
     * Agrega un libro a la biblioteca.
     * 
     * @param libro Libro a agregar a la biblioteca.
     */
    public void agregarLibro(Libro libro) {
        this.libros.add(libro);
    }

    /**
     * Contar la cantidad de libros en la biblioteca.
     * 
     * @return Cantidad de libros en la biblioteca.
     */
    public int contarLibros() {
        return this.libros.size();
    }

    /**
     * Muestra todos los libros en la biblioteca en formato tabular.
     */
    public void mostrarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la biblioteca.");
            return;
        }

        System.out.println("\n=== LISTA DE LIBROS ===");

        // Cabecera de la tabla
        System.out.printf("%-15s | %-30s | %-20s | %-6s | %-8s%n",
                "ISBN", "Título", "Autor", "Año", "Precio");
        System.out.println(
                "-----------------------------------------------------------------------------------------------------");

        // Contenido de la tabla
        for (Libro l : libros) {
            System.out.printf("%-15s | %-30s | %-20s | %-6d | %-8.2f%n",
                    l.getIsbn(),
                    l.getTitulo(),
                    l.getAutor(),
                    l.getAnio(),
                    l.getPrecio());
        }
    }

    /**
     * Elimina un libro de la biblioteca por su ISBN.
     * 
     * @param isbn ISBN del libro a eliminar.
     * @return true si el libro fue eliminado, false si no se encontró.
     */
    private boolean eliminarLibroPorISBN(String isbn) {
        boolean eliminado = false;
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getIsbn().equalsIgnoreCase(isbn)) {
                libros.remove(i);
                eliminado = true;
            }
        }
        return eliminado;
    }

    /**
     * Elimina un libro de la biblioteca por su título.
     * 
     * @param titulo Título del libro a eliminar.
     * @return true si el libro fue eliminado, false si no se encontró.
     */
    private boolean eliminarLibroPorTitulo(String titulo) {
        boolean eliminado = false;
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                libros.remove(i);
                eliminado = true;
            }
        }
        return eliminado;
    }

    /**
     * Muestra el menú para eliminar un libro.
     * 
     * @param scanner objeto Scanner que se utiliza para leer la entrada.
     */
    public void menuEliminarLibro(Scanner scanner) {
        boolean menuActivo = true;

        do {
            System.out.println("\n=== ELIMINAR LIBRO ===");
            System.out.println("1. Eliminar por ISBN");
            System.out.println("2. Eliminar por Título");
            System.out.println("3. Volver");

            int opcion = Util.pedirNumeroEnRango(scanner, "Seleccione una opción: ", 1, 3);

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese ISBN del libro a eliminar: ");
                    String isbn = Util.pedirTexto(scanner, "");
                    if (eliminarLibroPorISBN(isbn)) {
                        System.out.println("Libro eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró ningún libro con ese ISBN.");
                    }
                }
                case 2 -> {
                    System.out.print("Ingrese título del libro a eliminar: ");
                    String titulo = Util
                            .pedirTexto(scanner, "");
                    if (eliminarLibroPorTitulo(titulo)) {
                        System.out.println("Libro eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró ningún libro con ese título.");
                    }
                }
                case 3 -> menuActivo = false;
                default -> System.out.println("Opción no válida.");
            }
        } while (menuActivo);
    }

    /**
     * Guarda la biblioteca en un archivo de objetos.
     */
    public void guardarLibros() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("libros.dat"))) {
            oos.writeObject(getLibros());
            System.out.println("Biblioteca guardada correctamente.");
        } catch (IOException e) {
            System.out.println("Error guardando biblioteca: " + e.getMessage());
        }
    }

    /**
     * Carga la biblioteca desde un archivo de objetos.
     * 
     * @return Lista de libros cargados desde el archivo.
     */
    public static ArrayList<Libro> cargarLibros() {
        ArrayList<Libro> lista = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("libros.dat"))) {
            lista = (ArrayList<Libro>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de biblioteca. Se creará uno nuevo al guardar.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error leyendo biblioteca: " + e.getMessage());
        }
        return lista;
    }
}
