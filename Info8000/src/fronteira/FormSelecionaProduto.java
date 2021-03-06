/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

import Conection.ClienteBD;
import Conection.ProdutoBD;
import Objetos.Cliente;
import Objetos.Produto;
import ferramentas.TabelaClientes;
import ferramentas.TabelaListarProdutos;
import ferramentas.TabelaProdutos;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author user
 */
public class FormSelecionaProduto extends javax.swing.JFrame {
FormVenda formVenda;
FormOrdemServico formOs;
int idProduto;
    TabelaListarProdutos model;
    ProdutoBD bd;
    List<Produto> listaProduto;
    int form =0;
    FormVendaCompleta formVendaCom;

    /**
     * Creates new form FormSelecionaProduto
     */
    public FormSelecionaProduto() {
        initComponents();
        PreencheTabela();
    }

    FormSelecionaProduto(FormVenda aThis) {
       initComponents();
       this.formVenda=aThis;
       PreencheTabela();
       form=1;
      
    }
    
    FormSelecionaProduto(FormOrdemServico aThis) {
       initComponents();
       this.formOs=aThis;
       PreencheTabela();
       form=2;
      
    }
    FormSelecionaProduto(FormVendaCompleta aThis) {
       initComponents();
       this.formVendaCom=aThis;
       PreencheTabela();
       form=3;
      
    }
 public final void PreencheTabela(){
     bd = new ProdutoBD();
        try {
            listaProduto=bd.getListaProduto();
            model = new TabelaListarProdutos(listaProduto);
            tableProdutos.setModel(model);
            tableProdutos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tableProdutos.getColumnModel().getColumn(0).setPreferredWidth(80);
            tableProdutos.getColumnModel().getColumn(1).setPreferredWidth(150);
            tableProdutos.getColumnModel().getColumn(2).setPreferredWidth(130);
            tableProdutos.getColumnModel().getColumn(3).setPreferredWidth(130);
            tableProdutos.getColumnModel().getColumn(4).setPreferredWidth(80);
            tableProdutos.getColumnModel().getColumn(5).setPreferredWidth(150);
            tableProdutos.getColumnModel().getColumn(6).setPreferredWidth(150);
           
            
        } catch (Exception ex) {
            Logger.getLogger(FormCadProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
 }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        edtPesquisa = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();
        btnSeleciona = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProdutos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleciona  Produto");
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        edtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtPesquisaKeyPressed(evt);
            }
        });

        btnCancelar.setText("Cancelar");

        btnIncluir.setText("Incluir");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        btnSeleciona.setText("Selecionar");
        btnSeleciona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionaActionPerformed(evt);
            }
        });

        jLabel1.setText("Pesquisar:");

        tableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProdutos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSeleciona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnIncluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(333, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleciona)
                    .addComponent(btnIncluir)
                    .addComponent(btnCancelar))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 65, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelecionaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionaActionPerformed
        // TODO add your handling code here:
        seleciona();
    }//GEN-LAST:event_btnSelecionaActionPerformed

    private void tableProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProdutosMouseClicked
  if (evt.getClickCount() > 1) {  
 seleciona();
}         // TODO add your handling code here:
    }//GEN-LAST:event_tableProdutosMouseClicked

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
       FormCadProduto f = new FormCadProduto();
       f.setVisible(true);
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
PreencheTabela();        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusGained

    private void edtPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtPesquisaKeyPressed
TabelaListarProdutos tabela_model = (TabelaListarProdutos) tableProdutos.getModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela_model);
        tableProdutos.setRowSorter(sorter);
        String text = edtPesquisa.getText().toUpperCase();
        if (text.length() == 0)
        {
             sorter.setRowFilter(null);
        }
        else
        {
             try
             {
                   sorter.setRowFilter(RowFilter.regexFilter("(?i)"+text,1));
             }
             catch (PatternSyntaxException pse)
             {
                   System.err.println("Erro");
             }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_edtPesquisaKeyPressed
public void seleciona(){
    int linhaSelecionada = -1;
        Produto produto;
        linhaSelecionada = tableProdutos.getSelectedRow();
        if (linhaSelecionada >= 0) { 
             idProduto = (int) tableProdutos.getValueAt(linhaSelecionada, 0);
           produto= bd.getProdutoById(idProduto);
           System.out.println(produto);
      
           
           if(form==1){
               formVenda.recuperaProduto(produto);
                this.dispose();
           }else if(form==2){
               formOs.recuperaProduto(produto);
                this.dispose();
           }else if(form==3){
               formVendaCom.recuperaProduto(produto);
                this.dispose();
            }else if(formVenda!=null){
               formVenda.recuperaProduto(produto);
                this.dispose();
           }
         this.dispose();
        } 
        else {
            JOptionPane.showMessageDialog(null, "Selecione uma Linha na tabela");
        }
        this.dispose();
}
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
            java.util.logging.Logger.getLogger(FormSelecionaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormSelecionaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormSelecionaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormSelecionaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormSelecionaProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnSeleciona;
    private javax.swing.JTextField edtPesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProdutos;
    // End of variables declaration//GEN-END:variables
}
