/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author dam
 */
public class Libro extends Producto {
    
    //el ISBN son 13 digitos***********
    
    long ISBN;
    String genero;
    String autor;
    String editorial;
    Date anoPublicacion;
    
     public Libro(long ISBN, String genero, String autor, Date anoPublicacion, String editorial, String foto, String nombre, String description, double precio, int stock, int codBarras, Date fechaAlta, Date fechaModificacion) {
        super(foto, nombre, description, precio, stock, codBarras, fechaAlta, fechaModificacion);
        this.ISBN = ISBN;
        this.genero = genero;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
        this.editorial=editorial;
    }
    
    public Libro(String foto, String nombre, String description, double precio, int stock, int codBarras, Date fechaAlta, Date fechaModificacion) {
        super(foto, nombre, description, precio, stock, codBarras, fechaAlta, fechaModificacion);
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(Date anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

   
    
}
