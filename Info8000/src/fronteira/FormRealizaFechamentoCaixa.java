/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;


import Conection.CaixaBD;
import Conection.CaixaDiarioBD;
import Objetos.Caixa;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class FormRealizaFechamentoCaixa extends javax.swing.JFrame {
Double total, retirada, valorRestante;
Caixa caixa;
    CaixaBD caixabd;

    /**
     * Creates new form FormRealizaFechamentoCaixa
     */
    public FormRealizaFechamentoCaixa(Double TotalCaixa, Caixa caixa) {
        initComponents();
        this.total=TotalCaixa;
        edtTotalCaixa.setText("R$ "+String.valueOf(total));
        this.caixa=caixa;
        if(caixa.getStatus()=="FECHADO"){
            JOptionPane.showMessageDialog(this,"Caixa ja fechado");
            this.dispose();
        }
        
    }

    private FormRealizaFechamentoCaixa() {
       initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edtTotalCaixa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        edtRetirada = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        edtRestante = new javax.swing.JTextField();
        btnFechar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fechamento caixa");
        setResizable(false);

        edtTotalCaixa.setEditable(false);

        jLabel1.setText("Total em Caixa:");

        jLabel2.setText("Retirada:");

        edtRetirada.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edtRetiradaFocusLost(evt);
            }
        });

        jLabel3.setText("Valor Restante:");

        edtRestante.setEditable(false);

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnFechar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(40, 40, 40))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(10, 10, 10)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(edtTotalCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(edtRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(10, 10, 10)
                            .addComponent(edtRestante, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {edtRestante, edtRetirada, edtTotalCaixa});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnFechar, jButton2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtTotalCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(edtRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(edtRestante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechar)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {edtRestante, edtRetirada, edtTotalCaixa});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnFechar, jButton2});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void edtRetiradaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edtRetiradaFocusLost
    retirada=Double.valueOf(edtRetirada.getText());
        valorRestante=total-retirada;
        edtRestante.setText("R$ "+valorRestante.toString());
     
    }//GEN-LAST:event_edtRetiradaFocusLost
 public Caixa verifica(){
      int ultimoidCaixa=0;
     CaixaDiarioBD   bd = new CaixaDiarioBD();
     CaixaBD caixabd= new CaixaBD();
     Caixa caixa = new Caixa();
        try {
         caixa=caixabd.getUltimoCaixaById(0);
           ultimoidCaixa=caixa.getId();
        
        } catch (Exception ex) {
            Logger.getLogger(FormCaixaDiario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,"Erro Ao Obter Dados " +ex);
        }
        return caixa;
         
    }
    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
      caixa=verifica();
        if(edtRetirada.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Faça uma retirada");
          edtRetirada.requestFocus();
      }else{
        if(edtRestante.getText().isEmpty()){
          JOptionPane.showMessageDialog(this, "Valor restante não pode estar vazio");
      }
       int confirma= JOptionPane.showConfirmDialog(this, "Confirma Fechamento do caixa?","Mensagem", JOptionPane.YES_NO_OPTION );
      if(confirma==JOptionPane.YES_OPTION){
       caixa.setStatus("FECHADO");
       caixa.setValoratual(valorRestante);
       caixabd= new CaixaBD();
       valorRestante=total-retirada;
          try {
              caixabd.AtualizaCAixa(caixa);
              CaixaDiarioBD caixadiario = new CaixaDiarioBD();
              caixadiario.fecharcaixa();
              JOptionPane.showMessageDialog(this, "Caixa Fechado com sucesso");
          } catch (Exception ex) {
              Logger.getLogger(FormRealizaFechamentoCaixa.class.getName()).log(Level.SEVERE, null, ex);
               JOptionPane.showMessageDialog(this, "Erro ao fechar caixa " +ex );
          }
      }
      this.dispose();
        } 
    }//GEN-LAST:event_btnFecharActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormRealizaFechamentoCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormRealizaFechamentoCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormRealizaFechamentoCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormRealizaFechamentoCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormRealizaFechamentoCaixa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFechar;
    private javax.swing.JTextField edtRestante;
    private javax.swing.JTextField edtRetirada;
    private javax.swing.JTextField edtTotalCaixa;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
