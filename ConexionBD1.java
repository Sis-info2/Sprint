/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sisinfo2;

/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.logging.Level;
//import java.sql.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionBD1 {
    
  private Connection connection = null;
  private ResultSet rs = null;
  private Statement s = null;
    
    public void Conexion(){
     if(connection != null){
        return;
     }
     
     String url ="jdbc:postgresql://localhost:5432/postgres";
     String password ="1234";
     try{
     Class.forName("org.postgresql.Driver");
     
     connection = DriverManager.getConnection(url,"postgres", password);
     
     if(connection != null){
     System.out.println("Conectado a la base de datos...");
     }
     }catch(Exception e){
       System.out.println("Problemas de Conexion");
     }
    }
}*/

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernanda
 */
public class ConexionBD1 {
    
    Connection conectar =null;
    String usuario="sisinfo2_user";
    String contrasenia ="MjJfeyXg5JB1hbCf0vYs14Gmw8MVjo3N";
    String bd="sisinfo2";
    //String ip="localhost";
    String puerto ="5432";
    
    String cadena = "jdbc:postgresql://dpg-coal268l6cac73emur1g-a.oregon-postgres.render.com/sisinfo2";
    
    public Connection conexionEstablecida(){
      try{
          Class.forName("org.postgresql.Driver");
          conectar =DriverManager.getConnection(cadena,usuario,contrasenia);
          JOptionPane.showMessageDialog(null, "Se conecto a la base de datos");
      }catch(HeadlessException | ClassNotFoundException | SQLException e){
        JOptionPane.showMessageDialog(null, "No se conecto a la base de datos,error:"+ e.toString());
      }
      return conectar;
    }
}
