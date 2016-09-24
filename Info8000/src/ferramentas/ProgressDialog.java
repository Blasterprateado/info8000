/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;

import java.awt.Color;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 *
 * @author Marcos
 */
public class ProgressDialog {

    public ProgressDialog() {
        
        String mensagem ="";
        JFrame f = new JFrame("Aguarde...");
        JPanel panel = new JPanel();
        Dimension d = new Dimension(280, 50);
        //JProgressBar progress = new JProgressBar(10, 100);
        ////progress.setSize(d);
        //progress.setBackground(Color.WHITE);
        //progress.setForeground(Color.red);
        JTextField edtTxt = new JTextField("Gerando Relatorio - Aguarde!");
        edtTxt.setText("Gerando Relatorio - Aguarde!");
        edtTxt.setSize(2000, 30);
       
        edtTxt.setPreferredSize(d);
       // panel.add(edtTxt);
        panel.add(edtTxt);
        panel.setSize(2000, 40);
        
        f.add(panel);
        f.setSize(300, 100);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        //JOptionPane.showMessageDialog(null, mensagem," Resposta",-1);
        for(int i =0 ; i<120; i++){
            
            mensagem += " - " +i;
            edtTxt.setText(mensagem);
           // JOptionPane.showMessageDialog(null, mensagem,"Resposta",-1);
            //progress.setValue(i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
//                Logger.getLogger(Exemplo01.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //JOptionPane.showMessageDialog(null, "Fim Da Contagem!", "Resposta", -1);
        f.dispose();
        
    }
    }

   
    

