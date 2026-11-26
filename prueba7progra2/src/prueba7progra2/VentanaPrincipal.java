/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba7progra2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author XPS 13
 */
public class VentanaPrincipal extends JFrame {

    Restaurante r;
    Pedido pedido;

    JTextField txtCodigo = new JTextField();
    JTextField txtNombre = new JTextField();
    JTextField txtPrecio = new JTextField();
    JTextField txtExtra = new JTextField();
    JComboBox<Categoria> cbCategoria = new JComboBox<>(Categoria.values());
    DefaultTableModel modeloProductos = new DefaultTableModel(new String[]{"Codigo", "Nombre", "Precio", "Categoria", "Detalle"}, 0);

    JComboBox<Producto> cbProductos = new JComboBox<>();
    JTextField txtCantidad = new JTextField("1", 4);
    JComboBox<Modalidad> cbModalidad = new JComboBox<>(Modalidad.values());
    JLabel lblTotal = new JLabel("Total: 0.0");
    DefaultTableModel modeloDetalle = new DefaultTableModel(new String[]{"Producto", "Cantidad", "Subtotal"}, 0);
    JTable tablaDetalle = new JTable(modeloDetalle);

    JComboBox<Estado> cbEstado = new JComboBox<>(Estado.values());
    DefaultTableModel modeloPedidos = new DefaultTableModel(new String[]{"ID", "Fecha", "Modalidad", "Estado", "Total"}, 0);
    JTable tablaPedidos = new JTable(modeloPedidos);

    public VentanaPrincipal(Restaurante r) {
        this.r = r;
        pedido = new Pedido(r.siguienteIdPedido(), Modalidad.ESTABLECIMIENTO);
        setTitle("Restaurante");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel titulo = new JLabel("Sistema de Pedidos del Restaurante", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Productos", panelProductos());
        tabs.add("Nuevo pedido", panelPedido());
        tabs.add("Consultar pedidos", panelConsulta());
        tabs.add("Archivos", panelArchivos());
        tabs.addChangeListener(e -> actualizarTodo());

        add(titulo, BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);
        actualizarTodo();
    }

    JPanel panelProductos() {
        JPanel form = new JPanel(new GridLayout(6, 2));
        JButton btn = new JButton("Agregar producto");
        form.add(new JLabel("Categoria:"));
        form.add(cbCategoria);
        form.add(new JLabel("Codigo:"));
        form.add(txtCodigo);
        form.add(new JLabel("Nombre:"));
        form.add(txtNombre);
        form.add(new JLabel("Precio:"));
        form.add(txtPrecio);
        form.add(new JLabel("Acompañamiento / ml / porciones:"));
        form.add(txtExtra);
        form.add(new JLabel());
        form.add(btn);
        btn.addActionListener(e -> agregarProducto());

        JPanel p = new JPanel(new BorderLayout());
        p.add(form, BorderLayout.NORTH);
        p.add(new JScrollPane(new JTable(modeloProductos)), BorderLayout.CENTER);
        return p;
    }

    JPanel panelPedido() {
        JPanel arriba = new JPanel(new FlowLayout());
        JButton btnAgregar = new JButton("Agregar");
        JButton btnQuitar = new JButton("Quitar");
        arriba.add(cbProductos);
        arriba.add(new JLabel("Cantidad:"));
        arriba.add(txtCantidad);
        arriba.add(btnAgregar);
        arriba.add(btnQuitar);

        JPanel abajo = new JPanel(new FlowLayout());
        JButton btnCrear = new JButton("Crear pedido");
        abajo.add(cbModalidad);
        abajo.add(lblTotal);
        abajo.add(btnCrear);

        btnAgregar.addActionListener(e -> agregarAlPedido());
        btnQuitar.addActionListener(e -> {
            pedido.quitarProducto(tablaDetalle.getSelectedRow());
            actualizarTodo();
        });
        btnCrear.addActionListener(e -> crearPedido());

        JPanel p = new JPanel(new BorderLayout());
        p.add(arriba, BorderLayout.NORTH);
        p.add(new JScrollPane(tablaDetalle), BorderLayout.CENTER);
        p.add(abajo, BorderLayout.SOUTH);
        return p;
    }

    JPanel panelConsulta() {
        JPanel abajo = new JPanel(new FlowLayout());
        JButton btnEstado = new JButton("Cambiar estado");
        JButton btnDetalle = new JButton("Ver detalle");
        abajo.add(cbEstado);
        abajo.add(btnEstado);
        abajo.add(btnDetalle);

        btnEstado.addActionListener(e -> {
            int fila = tablaPedidos.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un pedido");
                return;
            }
            r.getPedidos().get(fila).setEstado((Estado) cbEstado.getSelectedItem());
            actualizarTodo();
        });
        btnDetalle.addActionListener(e -> verDetalle());

        JPanel p = new JPanel(new BorderLayout());
        p.add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);
        p.add(abajo, BorderLayout.SOUTH);
        return p;
    }

    JPanel panelArchivos() {
        JPanel p = new JPanel(new FlowLayout());
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCargar = new JButton("Cargar");
        p.add(btnGuardar);
        p.add(btnCargar);

        btnGuardar.addActionListener(e -> {
            try {
                r.guardar();
                JOptionPane.showMessageDialog(this, "Datos guardados");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage());
            }
        });
        btnCargar.addActionListener(e -> {
            try {
                r.cargar();
                pedido = new Pedido(r.siguienteIdPedido(), Modalidad.ESTABLECIMIENTO);
                actualizarTodo();
                JOptionPane.showMessageDialog(this, "Datos cargados");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar: " + ex.getMessage());
            }
        });
        return p;
    }

    void agregarProducto() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            String extra = txtExtra.getText();

            if (nombre.isEmpty() || extra.isEmpty() || codigo <= 0 || precio <= 0) {
                JOptionPane.showMessageDialog(this, "Datos invalidos");
                return;
            }

            Producto p;
            Categoria c = (Categoria) cbCategoria.getSelectedItem();
            if (c == Categoria.PLATO) {
                p = new Plato(codigo, nombre, precio, extra);
            } else if (c == Categoria.BEBIDA) {
                p = new Bebida(codigo, nombre, precio, Integer.parseInt(extra));
            } else {
                p = new Postre(codigo, nombre, precio, Integer.parseInt(extra));
            }

            if (r.agregarProducto(p)) {
                JOptionPane.showMessageDialog(this, "Producto agregado");
                actualizarTodo();
            } else {
                JOptionPane.showMessageDialog(this, "Ese codigo ya existe");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese numeros validos");
        }
    }

    void agregarAlPedido() {
        Producto p = (Producto) cbProductos.getSelectedItem();
        if (p == null) {
            JOptionPane.showMessageDialog(this, "No hay productos");
            return;
        }
        try {
            int cantidad = Integer.parseInt(txtCantidad.getText());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "Cantidad invalida");
                return;
            }
            pedido.agregarProducto(p, cantidad);
            actualizarTodo();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad invalida");
        }
    }

    void crearPedido() {
        if (pedido.getDetalles().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El pedido no tiene productos");
            return;
        }
        pedido.setModalidad((Modalidad) cbModalidad.getSelectedItem());
        r.agregarPedido(pedido);
        JOptionPane.showMessageDialog(this, "Pedido creado, total: " + pedido.calcularTotal());
        pedido = new Pedido(r.siguienteIdPedido(), Modalidad.ESTABLECIMIENTO);
        actualizarTodo();
    }

    void verDetalle() {
        int fila = tablaPedidos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido");
            return;
        }
        Pedido p = r.getPedidos().get(fila);
        String texto = "";
        for (DetallePedido d : p.getDetalles()) {
            texto += d.getCantidad() + " x " + d.getProducto().getNombre() + " = " + d.getSubtotal() + "\n";
        }
        texto += "Total: " + p.calcularTotal();
        JOptionPane.showMessageDialog(this, texto);
    }

    void actualizarTodo() {
        modeloProductos.setRowCount(0);
        cbProductos.removeAllItems();
        for (Producto p : r.getProductos()) {
            modeloProductos.addRow(new Object[]{p.getCodigo(), p.getNombre(), p.getPrecio(), p.getCategoria(), p.getDetalle()});
            cbProductos.addItem(p);
        }

        modeloDetalle.setRowCount(0);
        for (DetallePedido d : pedido.getDetalles()) {
            modeloDetalle.addRow(new Object[]{d.getProducto().getNombre(), d.getCantidad(), d.getSubtotal()});
        }
        lblTotal.setText("Total: " + pedido.calcularTotal());

        modeloPedidos.setRowCount(0);
        for (Pedido p : r.getPedidos()) {
            modeloPedidos.addRow(new Object[]{p.getId(), p.getFecha(), p.getModalidad(), p.getEstado(), p.calcularTotal()});
        }
    }
}
