
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Seleccion extends JFrame {
    private JButton btnInsertar;
    private JButton btnBuscar;


    public Seleccion() {
        super("Selección");
        setLayout(new FlowLayout());

        btnInsertar = new JButton("Insertar");
        add(btnInsertar);

        btnBuscar = new JButton("Buscar");
        add(btnBuscar);



        btnInsertar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Registar ventanaInsertar = new Registar();
                ventanaInsertar.setVisible(true);
                dispose();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Buscar ventanaBuscar = new Buscar();
                ventanaBuscar.setVisible(true);
                dispose();
            }
        });


        setSize(500, 400);
    }

    public static void main(String[] args) {
        Seleccion ventanaSeleccion = new Seleccion();
        ventanaSeleccion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaSeleccion.pack();
        ventanaSeleccion.setVisible(true);
    }
}