/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.sql.*;
import java.util.Scanner;

public class equipo {
    
    public static void main(String[] args) throws SQLException {
        ConexionBD1 conexionBD1 = new ConexionBD1();
        Connection conexion = conexionBD1.conexionEstablecida();
        if (conexion != null) {
            System.out.println("Conexión establecida con la base de datos");

            try {
                String consulta = "SELECT nombre, goles, puesto, comparacion FROM equipos";
                PreparedStatement st = conexion.prepareStatement(consulta);
                st.setString(1, "nombre");
                st.setString(2, "goles");
                st.setString(3, "puesto");
                st.setString(4, "comparacion");
                ResultSet resultado = st.executeQuery();

                
                String primerEquipo = "";
                int golesPrimerEquipo = 0;
                int PuestoEquipo = 0;
                String ComparacionEquipo = "";
                if (resultado.next()) {
                    primerEquipo = resultado.getString("nombre");
                    golesPrimerEquipo = resultado.getInt("goles");
                    PuestoEquipo = resultado.getInt("puesto");
                    ComparacionEquipo = resultado.getString("comparacion");
                }

                System.out.println("El primer equipo en la lista es: " + primerEquipo);
                System.out.println("goles del primer postre: " + golesPrimerEquipo);
                System.out.println("puesto del primer postre: " + PuestoEquipo);
                System.out.println("Comparacion del primer postre: " + ComparacionEquipo);

             
                Scanner scanner = new Scanner(System.in);
                System.out.print("Ingrese el nombre del nuevo equipo: ");
                String nuevoEquipo = scanner.nextLine();
                System.out.print("Ingrese los goles del nuevo equipo: ");
                int golesNuevoEquipo = scanner.nextInt();

                
                double vecesMasGoles = golesNuevoEquipo / golesPrimerEquipo;
                System.out.println("El equipo " + nuevoEquipo + " es " + vecesMasGoles + " veces más goles que " + primerEquipo);

                String insertarPostre = "INSERT INTO equipos (nombre, goles, puesto, comparacion) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStatement = conexion.prepareStatement(insertarPostre);
                insertStatement.setString(1, nuevoEquipo);
                insertStatement.setInt(2, golesNuevoEquipo);
                insertStatement.setInt(3, 1);
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
