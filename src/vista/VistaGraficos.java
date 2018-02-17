/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author franp
 */
public class VistaGraficos extends JFrame {

    JPanel panel;
    
    public VistaGraficos(){
        initPanel();
        initPantalla();
    }

    private void initPanel() {

        panel = new JPanel() {

        }; //Inicializo panel con graphics
        add(panel); //Lo añado al oeste del JFrame
        panel.setPreferredSize(new Dimension(800, 600)); //Establezco dimensiones del panel

    }
    
    private void initPantalla(){
        
        setTitle("Vista Graficos");
        setSize(800,600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    public static void main(String[] args) {
        new VistaGraficos();
    }
}
