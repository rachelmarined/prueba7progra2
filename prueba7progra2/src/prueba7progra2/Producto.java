/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba7progra2;

import java.io.Serializable;

/**
 *
 * @author XPS 13
 */
public abstract class Producto implements Serializable {

    private int codigo;
    private String nombre;
    private double precio;

    public Producto(int codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public abstract Categoria getCategoria();

    public abstract String getDetalle();

    @Override
    public String toString() {
        return codigo + " - " + nombre + " (L." + precio + ")";
    }
}
