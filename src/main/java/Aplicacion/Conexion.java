
package Aplicacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    Connection con;

    public Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            
            String myBD = "jdbc:postgresql://localhost:5432/lista_comparacion";
            con = DriverManager.getConnection(myBD, "postgres", "1234567890");
            //JOptionPane.showMessageDialog(null, "Se conectó correctamente a la BD");
            return con;
            
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "Error al conectar a la BD");
            System.out.println(e.toString());
        }
        return null;
    }

}

