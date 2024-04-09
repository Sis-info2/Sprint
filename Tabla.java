/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.intplatoscomida;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author MAGDA
 */
public class Tabla extends JFrame{
    private JPanel panel;
    private JLabel[] resultTabla;
    
    public Tabla(String a, String b, String c,String d,String e,String f,String g, String h,String i,String j){
        
        panel = new JPanel(new GridLayout(10,1));
        
        setTitle("lista de 10 platos de menor a mayor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setSize(600,500);
        setLocationRelativeTo(null);
        setResizable(false);
        
        resultTabla = new JLabel[10];
            resultTabla[0] = new JLabel("  "+a); 
            resultTabla[1] = new JLabel("  "+b);
            resultTabla[2] = new JLabel("  "+c); 
            resultTabla[3] = new JLabel("  "+d);
            resultTabla[4] = new JLabel("  "+e); 
            resultTabla[5] = new JLabel("  "+f);
            resultTabla[6] = new JLabel("  "+g); 
            resultTabla[7] = new JLabel("  "+h);
            resultTabla[8] = new JLabel("  "+i); 
            resultTabla[9] = new JLabel("  "+j);
        
        // rellenar con labels el panel
        panel = new JPanel(new GridLayout(10,1));
        for(int k=0; k<10 ;k++){   
            resultTabla[k].setFont(new Font("Segoe UI", 0, 13)); // letra
            resultTabla[k].setForeground(new Color(0, 0, 0)); 
            panel.add(resultTabla[k]);
        }
        getContentPane().add(panel, BorderLayout.CENTER);//agrega el panel al centro del frame
        System.out.print(resultTabla[9]);
        setVisible(true);
    }
}
