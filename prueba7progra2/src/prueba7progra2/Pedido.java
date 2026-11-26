/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba7progra2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author XPS 13
 */
public class Pedido implements Serializable {

    private int id;
    private Date fecha;
    private ArrayList<DetallePedido> detalles;
    private Modalidad modalidad;
    private Estado estado;

    public Pedido(int id, Modalidad modalidad) {
        this.id = id;
        this.modalidad = modalidad;
        fecha = new Date();
        detalles = new ArrayList<>();
        estado = Estado.PENDIENTE;
    }

    public void agregarProducto(Producto p, int cantidad) {
        detalles.add(new DetallePedido(p, cantidad));
    }

    public void quitarProducto(int pos) {
        if (pos >= 0 && pos < detalles.size()) {
            detalles.remove(pos);
        }
    }

    public double calcularTotal() {
        return calcularTotal(0);
    }

    private double calcularTotal(int i) {
        if (i == detalles.size()) {
            return 0;
        }
        return detalles.get(i).getSubtotal() + calcularTotal(i + 1);
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public ArrayList<DetallePedido> getDetalles() {
        return detalles;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
