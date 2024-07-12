import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Buscar extends JFrame {
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnMenu;
    private JButton btnRegistar;
    private JTextArea txtResultados;

    public Buscar() {
        super("Buscar");
        setLayout(new BorderLayout());

        JPanel pnlBuscar = new JPanel();
        add(pnlBuscar, BorderLayout.NORTH);

        JLabel lblBuscar = new JLabel("Buscar por cedula:");
        pnlBuscar.add(lblBuscar);

        txtBuscar = new JTextField(20);
        pnlBuscar.add(txtBuscar);

        btnBuscar = new JButton("Buscar");
        pnlBuscar.add(btnBuscar);



        txtResultados = new JTextArea(10, 20);
        add(new JScrollPane(txtResultados), BorderLayout.CENTER);

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cedula = txtBuscar.getText();

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_hospitalario", "root", "123456");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM paciente WHERE cedula LIKE '%" + cedula + "%'");

                    txtResultados.setText("");
                    if (rs.next()) {
                        txtResultados.append("Cedula: " + rs.getInt("cedula") + "\n");
                        txtResultados.append("N_Historial: " + rs.getInt("n_historial_clinico") + "\n");
                        txtResultados.append("Nombre: " + rs.getString("nombre") + "\n");
                        txtResultados.append("Apellido: " + rs.getString("apellido") + "\n");
                        txtResultados.append("Telefono: " + rs.getString("telefono") + "\n");
                        txtResultados.append("Edad: " + rs.getInt("edad") + "\n");
                        txtResultados.append("Descripcion de la enfermedad: " + rs.getString("descripcion_enfermedad") + "\n\n");

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Usuario incorrectos");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar datos");
                }
            }
        });



        JButton btnRegistar = new JButton("Volver Registro");
        add(btnRegistar, BorderLayout.SOUTH);

        btnRegistar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               Registar ventanaRegistar = new Registar();
                ventanaRegistar.setVisible(true);
                dispose();
            }
        });


        setSize(500, 400);
    }

    public static void main(String[] args) {
        Buscar ventanaBuscar = new Buscar();
        ventanaBuscar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaBuscar.pack();
        ventanaBuscar.setVisible(true);
    }
}