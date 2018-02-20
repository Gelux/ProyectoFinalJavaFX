/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author dam
 */
public class Libro extends Producto {
    
    //el ISBN son 13 digitos***********
    
    private final LongProperty ISBN;
    private final StringProperty genero;
    private final StringProperty autor;
    private final StringProperty editorial;
    private final StringProperty anoPublicacion;
    
     public Libro(long ISBN, String genero, String autor, String anoPublicacion, String editorial, String foto, String nombre, String description, double precio, int stock, long codBarras, Date fechaAlta, Date fechaModificacion) {
        super(foto, nombre, description, precio, stock, codBarras, fechaAlta, fechaModificacion);
        this.ISBN = new SimpleLongProperty(ISBN);
        this.genero = new SimpleStringProperty(genero);
        this.autor = new SimpleStringProperty(autor);
        this.anoPublicacion = new SimpleStringProperty(editorial);
        this.editorial=new SimpleStringProperty(editorial);
    }
     
     public Libro(long ISBN, String genero, String autor, String anoPublicacion, String editorial, String nombre, String description, double precio, int stock, long codBarras, Date fechaAlta, Date fechaModificacion) {
        super(nombre, description, precio, stock, codBarras, fechaAlta, fechaModificacion);
        this.ISBN = new SimpleLongProperty(ISBN);
        this.genero = new SimpleStringProperty(genero);
        this.autor = new SimpleStringProperty(autor);
        this.anoPublicacion = new SimpleStringProperty(anoPublicacion);
        this.editorial=new SimpleStringProperty(editorial);
    }
    

    public long getISBN() {
        return ISBN.get();
    }

    public void setISBN(long ISBN) {
        this.ISBN.set(ISBN);
    }

    public LongProperty ISBNProperty() {
        return ISBN;
    }
    

    public String getGenero() {
        return genero.get();
    }

    public void setGenero(String genero) {
        this.genero.set(genero);
    }

    public StringProperty generoProperty() {
        return genero;
    }
    
    public String getAutor() {
        return autor.get();
    }

    public void setAutor(String autor) {
        this.autor.set(autor);
    }

    public StringProperty autorProperty() {
        return autor;
    }
    
    public String getEditorial() {
        return editorial.get();
    }

    public void setEditorial(String editorial) {
        this.editorial.set(editorial);
    }

    public StringProperty editorialProperty() {
        return editorial;
    }
    
    
    public String getAnoPublicacion() {
        return (String) anoPublicacion.get();
    }

    public void setAnoPublicacion(String anoPublicacion) {
        this.anoPublicacion.set(anoPublicacion);
    }

    public StringProperty anoPublicacionProperty() {
        return anoPublicacion;
    }



   
    
}
