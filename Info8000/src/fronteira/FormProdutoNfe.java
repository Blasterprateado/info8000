
package fronteira;

import Objetos.Produto;
import Objetos.ProdutoNfe;
import br.com.nfe.entity.NFeXmlHelp;
import ferramentas.TabelaProdutosNfe;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author USUARIO
 */
public class FormProdutoNfe extends javax.swing.JFrame {
String caminhoXml;
    TabelaProdutosNfe tabelaProdutos ;
    List<ProdutoNfe> lista ;
    
    
    public FormProdutoNfe() {
        initComponents();
        getContentPane().setBackground(Color.WHITE);
        tableProdutosNFE.setBackground(Color.WHITE);
        jScrollPane1.getViewport().setBackground(Color.white);
        lista = new ArrayList<>();
        atualizaTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Seletor = new javax.swing.JFileChooser();
        PopupBotao = new javax.swing.JPopupMenu();
        menuItemRelacionar = new javax.swing.JMenuItem();
        menuItemCadastrar = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProdutosNFE = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        edtPesquisa = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        Seletor.setMinimumSize(new java.awt.Dimension(600, 400));
        Seletor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeletorActionPerformed(evt);
            }
        });

        menuItemRelacionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/produto_1.png"))); // NOI18N
        menuItemRelacionar.setText("Relacionar com Produto Existente");
        menuItemRelacionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemRelacionarActionPerformed(evt);
            }
        });
        PopupBotao.add(menuItemRelacionar);

        menuItemCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/adicionar.png"))); // NOI18N
        menuItemCadastrar.setText("Cadastrar");
        menuItemCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemCadastrarActionPerformed(evt);
            }
        });
        PopupBotao.add(menuItemCadastrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Importação De XML Nota Fiscal Eletronica");
        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tableProdutosNFE.setModel(new javax.swing.table.DefaultTableModel(
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
        tableProdutosNFE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProdutosNFEMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableProdutosNFEMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableProdutosNFEMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableProdutosNFE);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/busca.png"))); // NOI18N
        jButton1.setText("Selecionar XML");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Pesquisar:");

        edtPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtPesquisaActionPerformed(evt);
            }
        });

        jButton2.setText("Importar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
      
Seletor.setFileFilter(new FileNameExtensionFilter("Xml Nota Fiscal", "xml"));
Seletor.setAcceptAllFileFilterUsed(false);
  
       int resultado = Seletor.showOpenDialog(this);
       if ( resultado == Seletor.CANCEL_OPTION ){
            JOptionPane.showMessageDialog(this, "Xml não importado.");
        }
           
        else{
             caminhoXml=  Seletor.getSelectedFile().getAbsolutePath(); 
             System.out.println(caminhoXml);
             
             NFeXmlHelp feXmlHelp = new NFeXmlHelp(caminhoXml);
          lista= feXmlHelp.getProdutos();
          atualizaTable();
           System.out.println(feXmlHelp.getNumeroNfe());
          
                }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void SeletorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeletorActionPerformed
    
          
     
    }//GEN-LAST:event_SeletorActionPerformed

    private void tableProdutosNFEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProdutosNFEMouseClicked
      if (evt.getClickCount() == 2) {  
 seleciona();
}
    }//GEN-LAST:event_tableProdutosNFEMouseClicked

    private void edtPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtPesquisaActionPerformed

    private void menuItemRelacionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemRelacionarActionPerformed
       
    }//GEN-LAST:event_menuItemRelacionarActionPerformed

    private void menuItemCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemCadastrarActionPerformed
       int linhaSelecionada = -1;
      
        linhaSelecionada = tableProdutosNFE.getSelectedRow();
        if (linhaSelecionada >= 0) { 
            ProdutoNfe produtoNfe =lista.get(linhaSelecionada);
        
             FormCadProduto cadProduto = new FormCadProduto();
             cadProduto.pegaProduto(produtoNfe.getProduto());
             cadProduto.setVisible(true);
        }else {
            JOptionPane.showMessageDialog(null, "Selecione uma Linha na tabela");
        }
    }//GEN-LAST:event_menuItemCadastrarActionPerformed

    private void tableProdutosNFEMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProdutosNFEMousePressed
        maybeShowPopup(evt);
    }//GEN-LAST:event_tableProdutosNFEMousePressed

    private void tableProdutosNFEMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProdutosNFEMouseReleased
        maybeShowPopup(evt);
    }//GEN-LAST:event_tableProdutosNFEMouseReleased
private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            PopupBotao.show(e.getComponent(),
                       e.getX(), e.getY());
        }
    }
    
    
    public void seleciona(){
    int linhaSelecionada = -1;
      
        linhaSelecionada = tableProdutosNFE.getSelectedRow();
        if (linhaSelecionada >= 0) { 
            ProdutoNfe produtoNfe =lista.get(linhaSelecionada);
           
         int resposta=   JOptionPane.showConfirmDialog(this,"Produto ja se encontra em estoque?","Atenção", JOptionPane.YES_NO_OPTION );
         
         if(resposta==JOptionPane.NO_OPTION){
             FormCadProduto cadProduto = new FormCadProduto();
             cadProduto.pegaProduto(produtoNfe.getProduto());
             cadProduto.setVisible(true);
         }else if(resposta==JOptionPane.YES_OPTION){
             
         }
         
         
         
        } 
        else {
            JOptionPane.showMessageDialog(null, "Selecione uma Linha na tabela");
        }
        //this.dispose();
}



    public final void atualizaTable(){
        tabelaProdutos = new TabelaProdutosNfe(lista);
        tableProdutosNFE.setModel(tabelaProdutos);
        tableProdutosNFE.getColumnModel().getColumn(1).setPreferredWidth(400);
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
            java.util.logging.Logger.getLogger(FormProdutoNfe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormProdutoNfe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormProdutoNfe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormProdutoNfe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormProdutoNfe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu PopupBotao;
    private javax.swing.JFileChooser Seletor;
    private javax.swing.JTextField edtPesquisa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menuItemCadastrar;
    private javax.swing.JMenuItem menuItemRelacionar;
    private javax.swing.JTable tableProdutosNFE;
    // End of variables declaration//GEN-END:variables
}
