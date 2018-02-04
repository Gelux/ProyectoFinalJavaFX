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
public class Producto {
    private String foto;
    private String nombre;
    private String description;
    private double precio;
    private int stock;
    private int codBarras;
    private Date fechaAlta;
    private Date  fechaModificacion;

    public Producto(String foto, String nombre, String description, double precio, int stock, int codBarras, Date fechaAlta, Date fechaModificacion) {
        this.foto = foto;
        this.nombre = nombre;
        this.description = description;
        this.precio = precio;
        this.stock = stock;
        this.codBarras = codBarras;
        this.fechaAlta = fechaAlta;
        this.fechaModificacion = fechaModificacion;
    }
    
    
    
}