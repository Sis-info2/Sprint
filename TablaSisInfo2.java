/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sisinfo2sprint;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TablaSisInfo2 extends JFrame{
    private JTextField platoTipico, precio;
    private JButton insertar;
    private JLabel titulo, parametro1, parametro2;
    private BaseDeDatos base = new BaseDeDatos();

    public TablaSisInfo2(){
        setSize(500, 300); //Establecemos el tamanio de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Platos Tipicos");
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        base.conexion();
        
        insertar = new JButton("Insertar");
        
        insertar.setBackground(new Color(173, 216, 230)); // color del boton
        insertar.setFont(new Font("Segoe UI", 1, 18)); // letra del boton
        insertar.setForeground(new Color(255, 255, 255)); //color texto
        insertar.setBounds(350, 200, 100, 50);
        add(insertar);
        
        platoTipico = new JTextField();
        
        platoTipico.setBackground(new Color(211, 211, 211)); // color del boton
        platoTipico.setFont(new Font("Segoe UI", 1, 18)); // letra del boton
        platoTipico.setForeground(new Color(0, 0, 0));
        platoTipico.setBounds(100,100,300, 25);
        add(platoTipico);
        
        precio = new JTextField();
        
        precio.setBackground(new Color(211, 211, 211)); // color del boton
        precio.setFont(new Font("Segoe UI", 1, 18)); // letra del boton
        precio.setForeground(new Color(0, 0, 0));
        precio.setBounds(100,175,150, 25);
        add(precio);
        
        titulo = new JLabel("PLATOS TÍPICOS");
        
        titulo.setFont(new Font("Segoe UI", 1, 30)); // letra
        titulo.setForeground(new Color(0,0,0)); //color texto
        titulo.setBounds(50, 0, 400, 50);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo);
        
        parametro1 = new JLabel("Nombre del plato:");
        
        parametro1.setFont(new Font("Segoe UI", 0, 18)); // letra
        parametro1.setForeground(new Color(0,0,0)); //color texto
        parametro1.setBounds(50, 75, 400, 25);
        parametro1.setHorizontalAlignment(SwingConstants.LEFT);
        parametro1.setVerticalAlignment(SwingConstants.TOP);
        add(parametro1);
        
        parametro2 = new JLabel("Precio del plato(Bs.):");
        
        parametro2.setFont(new Font("Segoe UI", 0, 18)); // letra
        parametro2.setForeground(new Color(0,0,0)); //color texto
        parametro2.setBounds(50, 150, 400, 25);
        parametro2.setHorizontalAlignment(SwingConstants.LEFT);
        parametro2.setVerticalAlignment(SwingConstants.TOP);
        add(parametro2);
        
        insertar.addActionListener( new ActionListener(){
            String pTipico, precioPTipico;
            @Override
            public void actionPerformed(ActionEvent e) {
                pTipico = (String) platoTipico.getText();
                precioPTipico = (String) precio.getText();
                
                if((pTipico.equals("")) || (precioPTipico.equals(""))){
                    JOptionPane.showMessageDialog(null, "Alguno de los campos está vacío. Intente nuevamente");
                    platoTipico.setText("");
                    precio.setText("");
                }else{
                    if(base.verififcarExistencia(pTipico)){
                        try {
                            if(base.insertarPlatoTipico(pTipico, precioPTipico)){
                                JOptionPane.showMessageDialog(null, "Se insertó correctamente en la base de datos.");
                                platoTipico.setText("");
                                precio.setText("");
                            }else{
                                JOptionPane.showMessageDialog(null, "Se presentó un error al insertar en la base de datos. Intente de nuevo más tarde.");
                                platoTipico.setText("");
                                precio.setText("");
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(TablaSisInfo2.class.getName()).log(Level.SEVERE, null,ex);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Ese plato ya se encuentra en la base de" + "datos. Intente con otro.");
                        platoTipico.setText("");
                        precio.setText("");
                    }
                }
            }
        });
        setVisible(true);
    }
}