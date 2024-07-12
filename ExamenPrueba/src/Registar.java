import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Registar extends JFrame {
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEdad;
    private JTextField txtCedula;
    private JTextField txthistorial;
    private JTextField txttelefono;
    private JTextField txtdescripcion;
    private JButton btnInsertar;
    private JButton btnBuscar;

    public Registar() {
        super("Insertar");
        setLayout(new FlowLayout());

        JLabel lblcedeula = new JLabel("Cedula:");
        add(lblcedeula);

        txtCedula = new JTextField(20);
        add(txtCedula);

        JLabel lblhistorial = new JLabel("Numero de Historial:");
        add(lblhistorial);

        txthistorial = new JTextField(20);
        add(txthistorial);

        JLabel lblNombre = new JLabel("Nombre:");
        add(lblNombre);

        txtNombre = new JTextField(20);
        add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        add(lblApellido);

        txtApellido = new JTextField(20);
        add(txtApellido);

        JLabel lblEdad = new JLabel("Edad:");
        add(lblEdad);

        txtEdad = new JTextField(20);
        add(txtEdad);


        JLabel lbltelefono = new JLabel("Telefono:");
        add(lbltelefono);

        txttelefono= new JTextField(20);
        add(txttelefono);

        JLabel lblDescripcion= new JLabel("Descripcion Enfermedad:");
        add(lblDescripcion);

        txtdescripcion= new JTextField(20);
        add(txtdescripcion);

        btnInsertar = new JButton("Insertar");
        add(btnInsertar);


        btnInsertar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String cedula = txtCedula.getText();
                String telefono = txttelefono.getText();
                int n_historial = Integer.parseInt(txthistorial.getText());
                int edad = Integer.parseInt(txtEdad.getText());
                String descripcion = txtdescripcion.getText();
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_hospitalario", "root", "123456");
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("INSERT INTO paciente (cedula, n_historial_clinico, nombre, apellido, telefono, edad, descripcion_enfermedad) VALUES ('" + cedula + "', " + n_historial + ", '" + nombre + "','"+apellido+"','"+telefono+"',"+edad+",'"+descripcion+"')");

                    JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al insertar datos");
                }
            }
        });

        JButton btnVolver = new JButton("Volver");
        add(btnVolver);

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Seleccion ventanaSeleccion = new Seleccion();
                ventanaSeleccion.setVisible(true);
                dispose();
            }
        });


        JButton btnBuscar = new JButton("Buscar");
        add(btnBuscar);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Buscar ventanaBuscar = new Buscar();
                ventanaBuscar.setVisible(true);
                dispose();
            }
        });
        setSize(200, 500);
    }

    public static void main(String[] args) {
        Registar ventanaInsertar = new Registar();
        ventanaInsertar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaInsertar.pack();
        ventanaInsertar.setVisible(true);
    }
}