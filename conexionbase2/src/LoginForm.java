import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class LoginForm extends JFrame {

    private JTextField nom;
    private JTextField eda;
    private JPanel panel2;
    private JButton Guardar;
    private JTextField not1;
    private JTextField not2;
    private JButton verInformacionBaseButton;


    public LoginForm() {
        setTitle("Conexión a Base de Datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(300, 300);
        setContentPane(panel2);

        nom = new JTextField(20);
        eda = new JTextField(20);
        not1 = new JTextField(20);
        not2 = new JTextField(20);
        Guardar = new JButton("Guardar");



        JPanel panel = new JPanel();
        panel.add(new JLabel("Nombre:"));
        panel.add(nom);
        panel.add(new JLabel("Edad:"));
        panel.add(eda);
        panel.add(new JLabel("Nota 1:"));
        panel.add(not1);
        panel.add(new JLabel("Nota 2:"));
        panel.add(not2);
        panel.add(Guardar);

        setContentPane(panel);

        Guardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/esfotventas";
                String user = "root";
                String password = "123456";

                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    if (connection != null) {
                        JOptionPane.showMessageDialog(null, "Conexión exitosa");

                        String nombre = nom.getText();
                        String edad = eda.getText();
                        String nota1 = not1.getText();
                        String nota2= not2.getText();

                        String sql = "INSERT INTO estudiantes(nombre_estu, edad,nota1,nota2) VALUES (?, ?, ?, ?)";
                        PreparedStatement pstmt = connection.prepareStatement(sql);
                        pstmt.setString(1, nombre);
                        pstmt.setInt(2, Integer.parseInt(edad));
                        pstmt.setDouble(3, Double.parseDouble(nota1));
                        pstmt.setDouble(4, Double.parseDouble(nota2));

                        int rowsAffected = pstmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "Registrado correctamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo registrar");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Conexión no exitosa");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Edad debe ser un número válido");
                }
            }
        });
        verInformacionBaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAllStudents();
            }
        });
    }

    private void loadAllStudents() {
        String url = "jdbc:mysql://localhost:3306/esfotventas";
        String username = "root";
        String password = "123456";

        try {
            String nombre=nom.getText();
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM estudiantes where nombre=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,nombre);
            ResultSet rs=preparedStatement.executeQuery();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginForm frame = new LoginForm();
                frame.setVisible(true);
            }
        });
    }
}
