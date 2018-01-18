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
    
    public Libro(String foto, String nombre, String description, double precio, int stock, int codBarras, Date fechaAlta, Date fechaModificacion) {
        super(foto, nombre, description, precio, stock, codBarras, fechaAlta, fechaModificacion);
    }
    
}
