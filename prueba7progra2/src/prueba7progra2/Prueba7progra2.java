/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba7progra2;

/**
 *
 * @author XPS 13
 */
public class Prueba7progra2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Restaurante r = new Restaurante();
        try {
            r.cargar();
        } catch (Exception e) {
            System.out.println("No habia datos guardados");
        }
        VentanaPrincipal v = new VentanaPrincipal(r);
        v.setVisible(true);
    }
}
