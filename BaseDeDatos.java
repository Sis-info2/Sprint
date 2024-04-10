/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sisinfo2sprint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class BaseDeDatos {
    private Connection conectar = null;

    String usuario="sisinfo2_user"; //usuario = "postgres";
    String contrasenia ="MjJfeyXg5JB1hbCf0vYs14Gmw8MVjo3N"; //contrasenia = "Sonic1899.";
    //private final String baseDeDatos = "PlatosTipicos";
    //private final String ip = "localhost";
    //private final String puerto = "5432";
    
    
    public Connection conexion(){
    Statement st;
    String cadena = "jdbc:postgresql://dpg-coal268l6cac73emur1g-a.oregon-postgres.render.com/sisinfo2";

    try{
        Class.forName("org.postgresql.Driver");
        conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
        //st = conectar.createStatement();
        //st.executeQuery("delete from platotipico");
        JOptionPane.showMessageDialog(null, "Se conecto correctamente la base de datos.");
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error al conectar a la base de datos, error: "+ e.toString());
    }
    return conectar;
    }
    public boolean insertarPlatoTipico(String plato, String precio) throws SQLException{
        plato = plato.toUpperCase();
        boolean res = false;
        Statement st;
        ResultSet rs;
        try{
            st = conectar.createStatement();
            rs = st.executeQuery("select * from insertarplato("+plato+","+precio+");");
            
            while(rs.next()){
                res = rs.getBoolean(1);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al inssertar en la base de datos, error: "+ e.toString());
        }
        return res;
    }
    public boolean verififcarExistencia(String plato){
        plato = plato.toUpperCase();
        boolean res = false;
        Statement st;
        ResultSet rs;
        try{
            st = conectar.createStatement();
            rs = st.executeQuery("select * from yaexiste("+plato+");");
            while(rs.next()){
                res = rs.getBoolean(1);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al verificar en la base de datos, error: " + e.toString());
        }
        return res;
    }
    }
