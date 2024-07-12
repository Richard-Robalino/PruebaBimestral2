//Mostrar informacion al dar clic en el boton y ver registro
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class conexion_base_datos extends JFrame {
    private JButton Ver_datos;
    private JLabel mostrar_datos;
    private JPanel panel1;


    public conexion_base_datos() {
        // Configurar el JFrame
        setTitle("Conexión a Base de Datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300); // Configurar el tamaño del JFrame
        setContentPane(panel1);

        // Acción al presionar el botón Ver_datos
        Ver_datos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url= "jdbc:mysql://localhost:3306/esfotventas";
                String user="root";
                String password="123456";
                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    System.out.println("Conectado a la base de datos");
                    String query="select correo from clientes  ";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    StringBuilder datos = new StringBuilder("<html>");

                    while (resultSet.next()) {
                        //datos.append("<b>Nombre:</b> ").append(resultSet.getString("nombre")).append("<br>");
                        datos.append("<b>Correo:</b> ").append(resultSet.getString("correo")).append("<br>");
                        //datos.append("<b>Password:</b> ").append(resultSet.getString("password")).append("<br><br>");
                    }
                    datos.append("</html>");
                    mostrar_datos.setText(datos.toString());
                } catch (SQLException ex) {
                    mostrar_datos.setText("Error: " + ex.getMessage());
                }
            }
        });   }

    public static void main(String[] args) {
        conexion_base_datos frame = new conexion_base_datos();
        frame.setVisible(true);
        frame.setSize(600, 400); // Configurar el tamaño del panel
    }
}