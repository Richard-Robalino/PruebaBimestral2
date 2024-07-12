import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIniciarSesion;

    public Login() {
        super("Login");
        setLayout(new FlowLayout());

        JLabel lblUsuario = new JLabel("Usuario:");
        add(lblUsuario);

        txtUsuario = new JTextField(20);
        add(txtUsuario);

        JLabel lblContrasena = new JLabel("Contraseña:");
        add(lblContrasena);

        txtContrasena = new JPasswordField(20);
        add(txtContrasena);

        btnIniciarSesion = new JButton("Iniciar Sesión");
        add(btnIniciarSesion);

        btnIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String contrasena = new String(txtContrasena.getPassword());

                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_hospitalario", "root", "123456");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM usuario WHERE username='" + usuario + "' AND password='" + contrasena + "'");

                    if (rs.next()) {
                        Seleccion ventanaSeleccion = new Seleccion();
                        ventanaSeleccion.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos");
                }
            }
        });
        setSize(500, 400);
    }

    public static void main(String[] args) {
        Login ventanaLogin = new Login();
        ventanaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaLogin.pack();
        ventanaLogin.setVisible(true);
    }
}
