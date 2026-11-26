/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba7progra2;

/**
 *
 * @author XPS 13
 */
public class Postre extends Producto {

    private int porciones;

    public Postre(int codigo, String nombre, double precio, int porciones) {
        super(codigo, nombre, precio);
        this.porciones = porciones;
    }

    public int getPorciones() {
        return porciones;
    }

    @Override
    public Categoria getCategoria() {
        return Categoria.POSTRE;
    }

    @Override
    public String getDetalle() {
        return porciones + " porciones";
    }
}
