/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba7progra2;

/**
 *
 * @author XPS 13
 */
public class Bebida extends Producto {

    private int mililitros;

    public Bebida(int codigo, String nombre, double precio, int mililitros) {
        super(codigo, nombre, precio);
        this.mililitros = mililitros;
    }

    public int getMililitros() {
        return mililitros;
    }

    @Override
    public Categoria getCategoria() {
        return Categoria.BEBIDA;
    }

    @Override
    public String getDetalle() {
        return mililitros + " ml";
    }
}
