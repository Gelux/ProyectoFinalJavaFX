/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.time.LocalDate;
import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 *
 * @author dam
 */
public class Producto {
    private final StringProperty foto;
    private final StringProperty nombre;
    private final StringProperty description;
    private final DoubleProperty precio;
    private final IntegerProperty stock;
    private final LongProperty codBarras;
    private final ObjectProperty fechaAlta;
    private final ObjectProperty  fechaModificacion;
    
    public Producto(String foto, String nombre, String description, Double precio, int stock, long codBarras, Date fechaAlta, Date fechaModificacion) {
        this.foto = new SimpleStringProperty(foto);
        this.nombre = new SimpleStringProperty(nombre);;
        this.description = new SimpleStringProperty(description);;
        this.precio = new SimpleDoubleProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.codBarras = new SimpleLongProperty(codBarras);
        this.fechaAlta = new SimpleObjectProperty(fechaAlta);
        this.fechaModificacion = new SimpleObjectProperty(fechaModificacion);
    }
    
    public Producto(String nombre, String description, Double precio, int stock, long codBarras, Date fechaAlta, Date fechaModificacion) {
        this.nombre = new SimpleStringProperty(nombre);
        this.description = new SimpleStringProperty(description);
        this.precio = new SimpleDoubleProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.codBarras = new SimpleLongProperty(codBarras);
        this.fechaAlta = new SimpleObjectProperty(fechaAlta);
        this.fechaModificacion = new SimpleObjectProperty(fechaModificacion);
        this.foto = null;
    }
    
     public String getFoto() {
        return foto.get();
    }

    public void setFoto(String foto) {
        this.foto.set(foto);
    }

    public StringProperty fotoProperty() {
        return foto;
    }
     public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }
    
     public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }
    
   public double getPrecio() {
        return precio.get();
    }

    public void setPrecio(double precio) {
        this.precio.set(precio);
    }

    public DoubleProperty precioProperty() {
        return precio;
    }
    
    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    
    public long getCodBarras() {
        return codBarras.get();
    }

    public void setCodBarras(int codBarras) {
        this.codBarras.set(codBarras);
    }
    public LongProperty codBarrasProperty() {
        return codBarras;
    }
    
    public Date getFechaAlta() {
        return (Date) fechaAlta.get();
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta.set(fechaAlta);
    }

    public ObjectProperty fechaAltaProperty() {
        return fechaAlta;
    }
    
    
    public Date getFechaModificacion() {
        return (Date) fechaModificacion.get();
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion.set(fechaModificacion);
    }

    public ObjectProperty fechaModificacionProperty() {
        return fechaModificacion;
    }

    

   
    
    
    
}