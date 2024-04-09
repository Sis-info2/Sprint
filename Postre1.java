/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sisinfo2;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author Fernanda
 */
public class Postre1 {

    public static void main(String[] args) {
        ConexionBD1 conexionBD1 = new ConexionBD1();
        Connection conexion = conexionBD1.conexionEstablecida();
        if (conexion != null) {
            System.out.println("Conexión establecida con la base de datos");

            try {
                String consulta = "SELECT nombre, goles FROM equipos";
                PreparedStatement st = conexion.prepareStatement(consulta);
                //st.setString(1, "postre");
                ResultSet resultado = st.executeQuery();

                
                String primerPostre = "";
                double precioPrimerPostre = 0.0;
                if (resultado.next()) {
                    primerPostre = resultado.getString("nombre");
                    precioPrimerPostre = resultado.getDouble("goles");
                }

                System.out.println("El primer equipo en la lista : " + primerPostre);
                System.out.println("Goles del primer equipo: " + precioPrimerPostre);

             
                Scanner scanner = new Scanner(System.in);
                System.out.print("Ingrese el nombre del nuevo equipo: ");
                String nuevoPostre = scanner.nextLine();
                System.out.print("Ingrese los goles del nuevo equipo: ");
                double precioNuevoPostre = scanner.nextDouble();

                
                double vecesMasCaro = precioNuevoPostre / precioPrimerPostre;
                System.out.println("El equipo " + nuevoPostre + " es " + vecesMasCaro + " veces " + primerPostre);

                String insertarPostre = "INSERT INTO equipos (nombre, goles,puesto,comparacion) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStatement = conexion.prepareStatement(insertarPostre);
                insertStatement.setString(1, nuevoPostre);
                insertStatement.setDouble(2, precioNuevoPostre);
                 insertStatement.setDouble(3, 1); 
                insertStatement.setString(4, "");
                insertStatement.executeUpdate();
                System.out.println("El nuevo equipo ha sido añadido a la base de datos");

                st.close();
                insertStatement.close();
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo establecer la conexión con la base de datos");
        }
    }
}

