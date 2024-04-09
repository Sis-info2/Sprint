/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.intplatoscomida;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 * @author Sidney Angelly Sahonero Garrado
 */
public class Interfaz extends JFrame{
    private JButton anadir;
    private JLabel texto1, texto2;
    private JTextField plato, precio;
    Connection conex;
    String platoText, plato1, plato2, plato3, plato4, plato5, plato6, plato7, plato8, plato9,plato10;
    int precioText;
    
    public Interfaz(){
        setTitle("Listado de platos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(600,500);
        setLocationRelativeTo(null);//ventana en el centro de la pantalla
        setResizable(false);//evitar que se pueda cambiar el tamaño de la ventana manualmente
        setLayout(null);//para decir que hare todo con coordenadas y no divisiones
        getContentPane().setBackground(Color.darkGray);
        
        //inicializar variables
        anadir = new JButton("Agregar");
        texto1 = new JLabel("Plato");
        texto2 = new JLabel("Precio");
        plato = new JTextField();
        precio = new JTextField();
        
        //Disennos
        anadir.setBounds(250, 330, 90, 30);          //posicion y tamano
        
        texto1.setFont(new Font("Segoe UI", 1, 18)); // letra
        texto1.setForeground(new Color(255, 255, 255));     //color texto
        texto1.setBounds(100, 80, 100, 100);       //posicion y tamano
        
        texto2.setFont(new Font("Segoe UI", 1, 18)); // letra
        texto2.setForeground(new Color(255, 255, 255));     //color texto
        texto2.setBounds(100, 180, 100, 100);       //posicion y tamano
        
        plato.setBounds(200, 110, 300, 40);   //posicion y tamano
        precio.setBounds(200, 210, 300, 40);  //posicion y tamano
        
        //acciones
        anadir.addActionListener( new ActionListener(){   //para registrar y luego mostrar
            @Override
            public void actionPerformed(ActionEvent e) {
                platoText = plato.getText();
                precioText = Integer.parseInt(precio.getText());
                Conectar();
                Registrar(platoText, precioText);
            }
        });
        add(texto1);
        add(texto2);
        add(plato);
        add(precio);
        add(anadir);
        
        
        setVisible(true);
    }
    
    public Connection Conectar(){
       conex = null;
    
        String usuario = "sisinfo2_user";//"postgres";
        String contrasena ="MjJfeyXg5JB1hbCf0vYs14Gmw8MVjo3N";// "sidneyPOSTGRE24";
        
        String cadena = "jdbc:postgresql://dpg-coal268l6cac73emur1g-a.oregon-postgres.render.com/sisinfo2";//"jdbc:postgresql://"+ip+":"+puerto+"/"+BD;
    
        try {
            Class.forName("org.postgresql.Driver");
            conex = DriverManager.getConnection(cadena, usuario, contrasena);
   
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar");

        }
        return conex;
    }  
    

    public void Registrar(String pla, int pre) {
        String platoR = pla;
        int precioR = pre;
        Conectar();

        try {
            // Consulta SQL para insertar datos en la tabla solo si el plato no existe
            String sql = "INSERT INTO plato (nombre, precio) "
                   + "SELECT ?, ? "
                   + "WHERE NOT EXISTS ("
                   + "    SELECT nombre "
                   + "    FROM plato "
                   + "    WHERE nombre = ?"
                   + ")";
            PreparedStatement ejecutar = conex.prepareStatement(sql);
            ejecutar.setString(1, platoR);
            ejecutar.setInt(2, precioR);
            ejecutar.setString(3, platoR); // Se usa para verificar si ya existe 

            // Verificar
            int filasAfectadas = ejecutar.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(this, "Plato registrado en la base de datos.");
            } else {
                JOptionPane.showMessageDialog(this, "El plato ya existe en la base de datos.");
            }
            MostrarTabla();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar el plato en la base de datos.");
        }
    }

    public void MostrarTabla() {
        Conectar();
        try {
            // Consulta SQL para recuperar los 10 platos más baratos
            String sql = "SELECT * "
                   + "FROM plato "
                   + "ORDER BY precio ASC "
                   + "LIMIT 10";
            PreparedStatement ejecutar = conex.prepareStatement(sql);
            ResultSet result = ejecutar.executeQuery();

            //ponemos el resultado en StringsBuilder
            StringBuilder[] listaPlatos = new StringBuilder[10];
            for (int i = 0; i < listaPlatos.length; i++) {
                listaPlatos[i] = new StringBuilder();
                listaPlatos[i].append(" ");
            }
            for(int j=0; result.next(); j++){ 
                // Obtener los datos de cada fila
                String nombrePlato = result.getString("nombre");
                int precioPlato = result.getInt("precio");

                // Agregar los datos al StringBuilder
                listaPlatos[j].append((j+1)+". Nombre de plato: ").append(nombrePlato).append(", Precio: ").append(precioPlato);
            }

            // Convertir el StringBuilder a 10 Strings 
            plato1 = listaPlatos[0].toString();
            plato2 = listaPlatos[1].toString();
            plato3 = listaPlatos[2].toString();
            plato4 = listaPlatos[3].toString();
            plato5 = listaPlatos[4].toString();
            plato6 = listaPlatos[5].toString();
            plato7 = listaPlatos[6].toString();
            plato8 = listaPlatos[7].toString();
            plato9 = listaPlatos[8].toString();
            plato10 =listaPlatos[9].toString();
            
            Tabla tabla = new Tabla(plato1,plato2,plato3,plato4,plato5,plato6,plato7,plato8,plato9,plato10);
            tabla.setVisible(true);
            dispose();//se cierre al abrir la otra iu de tabla
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al recuperar la lista de platos de la base de datos.");
        }
    }
    
    
}



