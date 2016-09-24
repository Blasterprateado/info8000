/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

import Conection.CaixaBD;
import Conection.CaixaDiarioBD;
import Conection.ConectionSingleton;
import Conection.Itens_OsBD;
import Conection.LancamentoCaixaBD;
import Conection.OrdemServicoBD;
import Conection.ProdutoBD;
import Objetos.Caixa;
import Objetos.CaixaDiario;
import Objetos.Itens_Os;
import Objetos.LancamentoCaixa;
import Objetos.OrdemServico;
import Objetos.Produto;
import java.awt.AWTKeyStroke;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Marcos
 */
public class FormFinalizaOS extends javax.swing.JFrame {
List<Itens_Os> itens;
double valorVenda=0.0,desconto=0.0,valorRecebido=0.0,troco=0.0,valorAReceber=0.0;
    
    ProdutoBD produtoBd = new ProdutoBD();
    Itens_OsBD itensbd  = new Itens_OsBD();
    OrdemServico os;
    int ultimaid;
    /**
     * Creates new form FormFinalizaVenda
     */
    public FormFinalizaOS() {
        initComponents();
    }

   public FormFinalizaOS(List<Itens_Os> listaItens, Double valorOS, Integer numeroOS, OrdemServico os) {
         initComponents();
         this.itens=listaItens;
         this.valorVenda=valorOS;
         valorAReceber=valorOS;
         ultimaid=numeroOS;
         this.os=os;
         edtValorDaVenda.setText("R$ "+valorOS.toString());
         //labelNumeroVenda.setText(numeroOS.toString());
         edtDesconto.setText(String.valueOf(desconto));
//         edtValorAReceber.setText(String.valueOf(valorVenda));
         atalhos();
    }
 public void considera(){
     considerarEnterComoTab(edtDesconto);
     considerarEnterComoTab(edtTroco);
     considerarEnterComoTab(edtValorDaVenda);
     considerarEnterComoTab(edtValorEmDinheiro);
 }
   public int verifica(){
     CaixaDiarioBD   bd = new CaixaDiarioBD();
     CaixaBD caixabd= new CaixaBD();
     Caixa caixa = new Caixa();
        try {
         caixa=caixabd.getUltimoCaixaById(0);
           int ultimoidCaixa=caixa.getId();
        if(caixa.getStatus().equals("FECHADO")){
           return 2;
        }else if(caixa.getStatus().equals("ABERTO")){
        return 1;
        }
        } catch (Exception ex) {
            Logger.getLogger(FormCaixaDiario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,"Erro Ao Obter Dados " +ex);
        }
        return 0;
         
    }
   public void lancaEmCaixa(){
       int resposta=verifica();
      if(resposta==1){
        LancamentoCaixa lancamento = new  LancamentoCaixa();
        LancamentoCaixaBD bd = new LancamentoCaixaBD();
        CaixaDiario caixadiario = new CaixaDiario();
        CaixaDiarioBD diariobd = new CaixaDiarioBD();
    
            
            
            
            lancamento.setTipo("Ordem De Serviço");
            
            caixadiario.setTipolancamento("Ordem De Serviço");
             lancamento.setObs("OS");
             
                 caixadiario.setValor(valorAReceber);
             lancamento.setValor(valorAReceber);
     
           try {
               bd.adicionarlancamentoCaixa(lancamento);
               diariobd.adicionarClasseCaixa(caixadiario);
               this.dispose();
                              //  JOptionPane.showMessageDialog(this,"Lançamento adicionado com sucesso");
           } catch (Exception ex) {
               Logger.getLogger(FormFinalizaOS.class.getName()).log(Level.SEVERE, null, ex);
               JOptionPane.showMessageDialog(this,"Erro ao realizar lancamento " + ex);
           }
                                
                              
        
                           
                    
        }else{
          JOptionPane.showMessageDialog(this,"Caixa Fechado Abra-o");
      }
      


   }
    public final void atalhos(){
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0),"gravar");
inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0), "continuar");
this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
this.getRootPane().getActionMap().put("gravar", new AbstractAction(){
    private static final long serialVersionUID = 1L;
    

            @Override
            public void actionPerformed(ActionEvent e) {
                
        btnFinalizar.doClick();
            }
        });
this.getRootPane().getActionMap().put("continuar", new AbstractAction() {
 private static final long serialVersionUID = 1L;
            @Override
            public void actionPerformed(ActionEvent e) {
               // btnContinuar.doClick();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        edtValorDaVenda = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        edtValorEmDinheiro = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        edtTroco = new javax.swing.JTextField();
        btnFinalizar = new javax.swing.JButton();
        edtDesconto = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Finalizar Venda");
        setBackground(new java.awt.Color(153, 153, 153));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Entrega de equipamento");

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Total a pagar");

        edtValorDaVenda.setEditable(false);
        edtValorDaVenda.setBackground(new java.awt.Color(255, 229, 149));
        edtValorDaVenda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Desconto");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Valor Recebido:");

        edtValorEmDinheiro.setBackground(new java.awt.Color(204, 255, 204));
        edtValorEmDinheiro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edtValorEmDinheiro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edtValorEmDinheiroFocusLost(evt);
            }
        });
        edtValorEmDinheiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtValorEmDinheiroKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edtValorEmDinheiroKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Troco");

        edtTroco.setEditable(false);
        edtTroco.setBackground(new java.awt.Color(255, 229, 149));
        edtTroco.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        edtTroco.setForeground(new java.awt.Color(255, 0, 0));

        btnFinalizar.setText("Finalizar OS (F12)");
        btnFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarActionPerformed(evt);
            }
        });

        edtDesconto.setBackground(new java.awt.Color(255, 107, 108));
        edtDesconto.setForeground(new java.awt.Color(255, 255, 255));
        edtDesconto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtDesconto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edtDescontoFocusLost(evt);
            }
        });
        edtDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edtDescontoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtValorEmDinheiro)
                    .addComponent(edtValorDaVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addComponent(edtDesconto)
                    .addComponent(edtTroco))
                .addGap(88, 88, 88))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {edtDesconto, edtTroco, edtValorDaVenda, edtValorEmDinheiro});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(edtValorDaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel4)
                .addGap(0, 0, 0)
                .addComponent(edtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(0, 0, 0)
                .addComponent(edtValorEmDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel14)
                .addGap(5, 5, 5)
                .addComponent(edtTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFinalizar)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {edtDesconto, edtTroco, edtValorDaVenda, edtValorEmDinheiro});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 385, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void edtDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDescontoKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(edtDesconto.getText().isEmpty() || edtDesconto.getText()==""){
                edtDesconto.setText("0");
                desconto=Double.valueOf(edtDesconto.getText());
                Double Valor=valorVenda-desconto;
                valorAReceber=Valor;
                //edtValorAReceber.setText(Valor.toString());
            }else{
                desconto=Double.valueOf(edtDesconto.getText());
                Double Valor=valorVenda-desconto;
                //edtValorAReceber.setText(Valor.toString());
                valorAReceber=Valor;
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_edtDescontoKeyReleased
  public final java.sql.Date pegaDataAtual(){
        String data = "dd/MM/yyyy";
		String hora = "h:mm - a";
		String data1, hora1;
		java.util.Date agora = new java.util.Date();;
                 
java.sql.Date dataSql = new java.sql.Date(agora.getTime());  
		SimpleDateFormat formata = new SimpleDateFormat(data);
		data1 = formata.format(agora);
		formata = new SimpleDateFormat(hora);
		hora1 = formata.format(agora);
		     
       return dataSql;               
    }
    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
int i = verifica();
        if(i==1){
        OrdemServicoBD bd = new OrdemServicoBD();
       os.setStatus("Entregue");
    try {
        Iterator<Itens_Os> it = itens.iterator();
        while(it.hasNext()){
            Itens_Os item = it.next();
            
          Produto produto=  produtoBd.getProdutoByNome(item.getDesc());
          if(produto!=null){
          Double qtdEstoque=produto.getQtd_estoque();
          qtdEstoque--;
          produto.setQtd_estoque(qtdEstoque);
          produtoBd.AtualizaProduto(produto);
          }else{
              
          }
        }
        bd.AtualizaOrdemServico(os);
        relatorio();
        //this.dispose();
        lancaEmCaixa();
        
       JOptionPane.showMessageDialog(this, "Ordem de serviço entregue com sucesso!");
    } catch (Exception ex) {
        Logger.getLogger(FormFinalizaOS.class.getName()).log(Level.SEVERE, null, ex);
    }}
        else if(i==2){
            JOptionPane.showMessageDialog(this, "Caixa Fechado!! Abra-o");
            FormCaixaDiario form = new FormCaixaDiario();
            form.setVisible(true);
            //this.dispose();
        }

    }//GEN-LAST:event_btnFinalizarActionPerformed

   
    public void relatorio(){
    
        InputStream inputStream = getClass().getResourceAsStream("/Relatorios/RelatorioOS.jasper");
     Map parametros = new HashMap();
        parametros.put("id",ultimaid);
       
           
        try {
            ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
 
            // abre o relatório
            //ReportUtils.openReport( "Ordem De Serviço", inputStream, parametros,
                  //  connection );
            FormProgressDialog pro = new FormProgressDialog( "Ordem De Serviço", inputStream, parametros,
    connection );
              pro.setVisible(true);
 
        } catch ( SQLException exc ) {
            exc.printStackTrace();
            //JOptionPane.showMessageDialog(this, "erro" +exc+edtObservacao.getText());
        } catch ( JRException exc ) {
            exc.printStackTrace();
            JOptionPane.showMessageDialog(this, "erro" +exc);
        } catch (Exception ex) {
//            Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "erro" +ex);
        }
}
    
    public static void considerarEnterComoTab (Component comp) {
		Set<AWTKeyStroke> keystrokes = comp.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
		Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke> (keystrokes);
		newKeystrokes.add (AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
		comp.setFocusTraversalKeys (KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes); 
	}
    private void edtValorEmDinheiroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtValorEmDinheiroKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(edtValorEmDinheiro.getText().isEmpty() || edtValorEmDinheiro.getText()==""){
                edtValorEmDinheiro.setText("0");
                valorRecebido=0;
                troco=valorRecebido-valorAReceber;
                edtTroco.setText(String.valueOf("R$ "+troco));
            }else{
                valorRecebido=Double.valueOf(edtValorEmDinheiro.getText());
                troco=valorRecebido-valorAReceber;
                edtTroco.setText(String.valueOf("R$ "+troco));
            }
        }
    }//GEN-LAST:event_edtValorEmDinheiroKeyReleased

    private void edtValorEmDinheiroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtValorEmDinheiroKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtValorEmDinheiroKeyPressed

    private void edtDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edtDescontoFocusLost
if(edtDesconto.getText().isEmpty() || edtDesconto.getText()==""){
                edtDesconto.setText("0");
                desconto=Double.valueOf(edtDesconto.getText());
                Double Valor=valorVenda-desconto;
                valorAReceber=Valor;
                //edtValorAReceber.setText(Valor.toString());
            }else{
                desconto=Double.valueOf(edtDesconto.getText());
                Double Valor=valorVenda-desconto;
                //edtValorAReceber.setText(Valor.toString());
                valorAReceber=Valor;
            }        // TODO add your handling code here:
    }//GEN-LAST:event_edtDescontoFocusLost

    private void edtValorEmDinheiroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edtValorEmDinheiroFocusLost
if(edtValorEmDinheiro.getText().isEmpty() || edtValorEmDinheiro.getText()==""){
                edtValorEmDinheiro.setText("0");
                valorRecebido=0;
                troco=valorRecebido-valorAReceber;
                edtTroco.setText(String.valueOf("R$ "+troco));
            }else{
                valorRecebido=Double.valueOf(edtValorEmDinheiro.getText());
                troco=valorRecebido-valorAReceber;
                edtTroco.setText(String.valueOf("R$ "+troco));
            }        // TODO add your handling code here:
    }//GEN-LAST:event_edtValorEmDinheiroFocusLost

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
            java.util.logging.Logger.getLogger(FormFinalizaOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormFinalizaOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormFinalizaOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormFinalizaOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormFinalizaOS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JFormattedTextField edtDesconto;
    private javax.swing.JTextField edtTroco;
    private javax.swing.JTextField edtValorDaVenda;
    private javax.swing.JTextField edtValorEmDinheiro;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
