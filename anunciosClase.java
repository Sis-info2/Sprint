
package sisinfo2;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class anunciosClase extends JFrame {
    
    public anunciosClase() {
        setTitle("Anuncios");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        add(scrollPane);
        
        obtenerAnuncios(panel);
        
  
        setVisible(true);
    }
    
    private void obtenerAnuncios(JPanel panel) {
        ConexionBD conexion = new ConexionBD();
        try {
            Connection conn = conexion.conexionEstablecida();
            if (conn != null) {
                Statement stmt = conn.createStatement();
                String query = "SELECT anuncios FROM materias";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String anuncio = rs.getString("anuncios");
                    JTextArea textArea = new JTextArea(anuncio);
                    textArea.setEditable(false);
                    textArea.setLineWrap(true); 
                    textArea.setWrapStyleWord(true); 
                    panel.add(textArea);
                    panel.add(Box.createRigidArea(new Dimension(0, 10))); 
                }
                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener los anuncios de la base de datos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
     
        SwingUtilities.invokeLater(() -> new anunciosClase());
    }
}

