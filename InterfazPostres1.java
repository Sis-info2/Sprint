/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sisinfo2;

/**
 *
 * @author Fernanda
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterfazPostres1 extends JFrame {

    private ConexionBD1 conexionBD1;

    private JLabel labelNombre;
    private JLabel labelPrecio;
    private JTextField campoNombre;
    private JTextField campoPrecio;
    private JButton botonAgregar;

    public InterfazPostres1(ConexionBD1 conexionBD1) {
        super("Gestión de Equipos");
        this.conexionBD1 = conexionBD1;
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void inicializarComponentes() {
        labelNombre = new JLabel("Nombre:");
        labelNombre.setBounds(50, 30, 80, 25);
        add(labelNombre);

        campoNombre = new JTextField();
        campoNombre.setBounds(130, 30, 200, 25);
        add(campoNombre);

        labelPrecio = new JLabel("Goles:");
        labelPrecio.setBounds(50, 70, 80, 25);
        add(labelPrecio);

        campoPrecio = new JTextField();
        campoPrecio.setBounds(130, 70, 200, 25);
        add(campoPrecio);

        botonAgregar = new JButton("Agregar");
        botonAgregar.setBounds(150, 120, 100, 25);
        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPostre();
            }
        });
        add(botonAgregar);
    }

    private void agregarPostre() {
        String nombre = campoNombre.getText();
        String precioStr = campoPrecio.getText();

        if (nombre.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos");
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);

            Connection conexion = conexionBD1.conexionEstablecida();
            if (conexion != null) {
                
                String consulta = "SELECT nombre, goles FROM equipos";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                ResultSet resultado = statement.executeQuery();

                
                String primerPostre = "";
                double precioPrimerPostre = 0.0;
                if (resultado.next()) {
                    primerPostre = resultado.getString("nombre");
                    precioPrimerPostre = resultado.getDouble("goles");
                }

                
                double vecesMasCaro = precio / precioPrimerPostre;

                
                String insertarPostre = "INSERT INTO equipos (nombre, goles, puesto,comparacion) VALUES (?, ?, ?, ?)";
                PreparedStatement insertStatement = conexion.prepareStatement(insertarPostre);
                insertStatement.setString(1, nombre);
                insertStatement.setDouble(2, precio);
                insertStatement.setDouble(3, 1); 
                insertStatement.setString(4, "");
                insertStatement.executeUpdate();

                JOptionPane.showMessageDialog(this, "El equipo " + nombre + " ha sido agregado a la base de datos.\nEs " + vecesMasCaro + " veces " + primerPostre);

                
                statement.close();
                insertStatement.close();
                conexion.close();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo establecer la conexión con la base de datos");
            }
        } catch (NumberFormatException | SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar el equipo: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        ConexionBD1 conexionBD1 = new ConexionBD1();
        InterfazPostres1 interfaz = new InterfazPostres1(conexionBD1);
        interfaz.setVisible(true);
    }
}
//este es el primer commit
//comentario2
