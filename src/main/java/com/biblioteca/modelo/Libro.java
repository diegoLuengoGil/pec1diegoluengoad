package com.biblioteca.modelo;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 * Clase que representa un libro en la biblioteca.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Libro implements Serializable {
    /**
     * ISBN del libro.
     */
    @XmlAttribute(name = "isbn")
    private String isbn;
    /**
     * Título del libro.
     */
    @XmlElement(name = "titulo")
    private String titulo;
    /**
     * Autor del libro.
     */
    @XmlElement(name = "autor")
    private String autor;
    /**
     * Año de publicación del libro.
     */
    @XmlElement(name = "anio")
    private int anio;
    /**
     * Precio del libro.
     */
    @XmlElement(name = "precio")
    private double precio;

    /**
     * SerialVersionUID para la serialización.
     */
    @XmlTransient
    private static final long serialVersionUID = 1L;

    /**
     * Constructor por defecto.
     */
    public Libro() {
    }

    /**
     * Constructor con parámetros.
     * 
     * @param isbn   ISBN del libro.
     * @param titulo Título del libro.
     * @param autor  Autor del libro.
     * @param anio   Año de publicación del libro.
     * @param precio Precio del libro.
     */
    public Libro(String isbn, String titulo, String autor, int anio, double precio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.precio = precio;
    }

    /**
     * Obtiene el ISBN del libro.
     * 
     * @return ISBN del libro.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece el ISBN del libro.
     * 
     * @param isbn ISBN del libro.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene el título del libro.
     * 
     * @return Título del libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del libro.
     * 
     * @param titulo Título del libro.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    /**
     * Establece el autor del libro.
     * 
     * @param autor Autor del libro.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene el año de publicación del libro.
     * 
     * @return Año de publicación del libro.
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Establece el año de publicación del libro.
     * 
     * @param anio Año de publicación del libro.
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * Obtiene el precio del libro.
     * 
     * @return Precio del libro.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del libro.
     * 
     * @param precio Precio del libro.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    /**
     * Representación en cadena del libro.
     * @return Cadena con los detalles del libro.
     */
    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anio=" + anio + // Aquí mostrará el formato ISO (aaaa-MM-dd)
                ", precio=" + precio +
                '}';
    }
}
