/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba7progra2;

/**
 *
 * @author XPS 13
 */
public class Plato extends Producto {

    private String acompanamiento;

    public Plato(int codigo, String nombre, double precio, String acompanamiento) {
        super(codigo, nombre, precio);
        this.acompanamiento = acompanamiento;
    }

    public String getAcompanamiento() {
        return acompanamiento;
    }

    @Override
    public Categoria getCategoria() {
        return Categoria.PLATO;
    }

    @Override
    public String getDetalle() {
        return "Acompañamiento: " + acompanamiento;
    }
}
