package com.biblioteca.modelo;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class Libro implements Serializable {
    @XmlAttribute(name = "isbn")
    private String isbn;
    @XmlElement(name = "titulo")
    private String titulo;
    @XmlElement(name = "autor")
    private String autor;
    @XmlElement(name = "anio")
    private int anio;
    @XmlElement(name = "precio")
    private double precio;

    @XmlTransient
    private static final long serialVersionUID = 1L;

    public Libro() {}

    public Libro(String isbn, String titulo, String autor, int anio, double precio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.precio = precio;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

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
