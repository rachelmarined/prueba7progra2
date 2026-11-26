/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba7progra2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author XPS 13
 */
public class Restaurante {

    private ArrayList<Producto> productos;
    private ArrayList<Pedido> pedidos;

    public Restaurante() {
        productos = new ArrayList<>();
        pedidos = new ArrayList<>();
    }

    public Producto buscarProducto(int codigo) {
        for (Producto p : productos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public boolean agregarProducto(Producto p) {
        if (buscarProducto(p.getCodigo()) != null) {
            return false;
        }
        productos.add(p);
        return true;
    }

    public int siguienteIdPedido() {
        int mayor = 0;
        for (Pedido p : pedidos) {
            if (p.getId() > mayor) {
                mayor = p.getId();
            }
        }
        return mayor + 1;
    }

    public void agregarPedido(Pedido p) {
        pedidos.add(p);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void guardar() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("productos.dat"));
        out.writeObject(productos);
        out.close();

        ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream("pedidos.dat"));
        out2.writeObject(pedidos);
        out2.close();
    }

    public void cargar() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("productos.dat"));
        productos = (ArrayList<Producto>) in.readObject();
        in.close();

        ObjectInputStream in2 = new ObjectInputStream(new FileInputStream("pedidos.dat"));
        pedidos = (ArrayList<Pedido>) in2.readObject();
        in2.close();
    }
}
