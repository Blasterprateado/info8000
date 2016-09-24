/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

import Conection.ProdutoBD;
import Conection.VendaBD;
import Objetos.ItensVenda;
import Objetos.Produto;
import ferramentas.TabelaItensVendasVenda;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author user
 */
public class FormVenda extends javax.swing.JFrame {
    TabelaItensVendasVenda model ;
    List<ItensVenda> listaItens = new ArrayList<>();
    Produto produto;
    ProdutoBD produtobd = new ProdutoBD();
    VendaBD vendabd = new VendaBD();
    int idUltimaVenda;
    Double valorVenda=0.0;
    Iterator<ItensVenda> it;
    /**
     * Creates new form FormVenda
     */
    public FormVenda() {
        initComponents();
        atalhos();
        model = new TabelaItensVendasVenda(listaItens);
        tableItens.setModel(model);
        //this.getRootPane().setDefaultButton(btnIncluir);
        
        try {
            idUltimaVenda=vendabd.getUltimaID();
            idUltimaVenda++;
            edtCodigoVenda.setText("Código Venda: "+idUltimaVenda);
        } catch (Exception ex) {
            Logger.getLogger(FormVenda.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erro ao recuperar ultima id "+ ex);
        }
    }
    public final void atalhos(){
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0),"finalizar");
inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0),"cancelar");
inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0),"alterar");
inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0),"excluir");
this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
this.getRootPane().getActionMap().put("finalizar", new AbstractAction(){
    private static final long serialVersionUID = 1L;
    

            @Override
            public void actionPerformed(ActionEvent e) {
                
        btnGravar.doClick();
            }
        });
this.getRootPane().getActionMap().put("cancelar", new AbstractAction(){
    private static final long serialVersionUID = 1L;
    

            @Override
            public void actionPerformed(ActionEvent e) {
                
        btnCancelar.doClick();
            }
        });
this.getRootPane().getActionMap().put("alterar", new AbstractAction(){
    private static final long serialVersionUID = 1L;
    

            @Override
            public void actionPerformed(ActionEvent e) {
            
        alterar();
            }
        });
this.getRootPane().getActionMap().put("excluir", new AbstractAction(){
    private static final long serialVersionUID = 1L;
    

            @Override
            public void actionPerformed(ActionEvent e) {
                
        excluirItem();
            }
        });

    }
    public void alterar(){
            int linha=-1;
        linha=tableItens.getSelectedRow();
        if(linha>=0){
        ItensVenda iv = listaItens.get(linha);
        this.produto=produtobd.getProdutoById(iv.getId_produto());
        edtDescricao.setText(produto.getDescricao());
        edtValorUnitario.setText(produto.getPreco_venda().toString());
        spQtd.setValue(1);
        listaItens.remove(linha);
        valorVenda-=iv.getTotal();
        edtTotal.setText("R$ "+valorVenda.toString());
         model= new TabelaItensVendasVenda(listaItens);
           tableItens.setModel(model);
        }else{
            JOptionPane.showMessageDialog(this, "Selecione um produto na tabela para alterar.");
        }
    }
    public void InserirNaTabelaELista() {
        int qtd = 0;
        int encontrado = 0;
        if (produto != null) {
                    ItensVenda iv = new ItensVenda();
            iv.setId_produto(produto.getId());
            iv.setDescProduto(produto.getDescricao());
            iv.setQtd(Integer.valueOf(spQtd.getValue().toString()));
            
            iv.setValorUnt(produto.getPreco_venda());
            Double total = iv.getValorUnt() * iv.getQtd();
            iv.setTotal(total);
            valorVenda += total;
            edtTotal.setText("R$ "+valorVenda.toString());
            iv.setId_venda(idUltimaVenda);
            if (listaItens.isEmpty() == false) {
                for (int i = 0; listaItens.size() > i; i++) {
                    ItensVenda item = listaItens.get(i);
                    if (item.getId_produto() == iv.getId_produto()) {
                        encontrado = 1;
                        iv = item;
                        listaItens.remove(item);
                    }
                }
                if (encontrado == 1) {
                    System.out.println("entrou no if");
                    qtd = iv.getQtd();
                    qtd+=Integer.valueOf(spQtd.getValue().toString());
                    iv.setQtd(qtd);
                    double valorunitario=iv.getValorUnt();
                    double subTotal=valorunitario*qtd;
                   
                    iv.setTotal(subTotal);
                    listaItens.add(iv);
                } else {
                    listaItens.add(iv);
                }
            } else {
                listaItens.add(iv);
            }
            model = new TabelaItensVendasVenda(listaItens);
            tableItens.setModel(model);
            produto = null;
            edtDescricao.setText("");
            edtValorUnitario.setText("");

        } else {
            JOptionPane.showMessageDialog(this, "Nenhum produto selecionado, selecione!");
        }
        spQtd.setValue(1);
        edtCodBarras.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableItens = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        edtTotal = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        edtDescricao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        edtCodigoVenda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnIncluir = new javax.swing.JButton();
        edtCodBarras = new javax.swing.JTextField();
        spQtd = new javax.swing.JSpinner();
        edtValorUnitario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Venda Rapida");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/confirma.png"))); // NOI18N
        btnGravar.setText("Gravar (F12)");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar (F4)");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tableItens.setModel(new javax.swing.table.DefaultTableModel(
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
        tableItens.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableItensKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableItens);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("F2 - ALTERAR");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("F3 - EXCLUIR ITEM");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("F4 - CANCELAR VENDA");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("F12 - FECHAR VENDA");

        edtTotal.setEditable(false);
        edtTotal.setBackground(new java.awt.Color(102, 102, 255));
        edtTotal.setForeground(new java.awt.Color(255, 255, 255));
        edtTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("R$ #,##0.00"))));
        edtTotal.setText("R$ 0,00");
        edtTotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        edtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtTotalKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Enter ou F8 para pesquisar produto.");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Qdt");

        edtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDescricaoKeyPressed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Valor Unitario:");

        edtCodigoVenda.setEditable(false);
        edtCodigoVenda.setBackground(new java.awt.Color(255, 238, 190));
        edtCodigoVenda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edtCodigoVenda.setForeground(new java.awt.Color(255, 0, 0));
        edtCodigoVenda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtCodigoVenda.setText("Codigo venda: 22222222");
        edtCodigoVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodigoVendaKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Codigo de Barras:");

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/adicionar.png"))); // NOI18N
        btnIncluir.setText("Incluir ");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        edtCodBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtCodBarrasActionPerformed(evt);
            }
        });
        edtCodBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodBarrasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edtCodBarrasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edtCodBarrasKeyTyped(evt);
            }
        });

        edtValorUnitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtValorUnitarioActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Descrição:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(edtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4))
                            .addComponent(edtCodBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(edtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnIncluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(edtCodigoVenda)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtCodBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(edtCodigoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel9)
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(spQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(edtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIncluir))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(244, 244, 244)
                .addComponent(btnGravar)
                .addGap(5, 5, 5)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addComponent(edtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(edtTotal)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGravar)
                            .addComponent(btnCancelar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void edtCodBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtCodBarrasActionPerformed
if(edtCodBarras.getText().isEmpty() || edtCodBarras.getText()==""){
            
FormSelecionaProduto f = new FormSelecionaProduto(this);
f.setVisible(true);
}else
        


        
        Leitor();        // TODO add your handling code here:
    }//GEN-LAST:event_edtCodBarrasActionPerformed

    private void edtValorUnitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtValorUnitarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtValorUnitarioActionPerformed

    private void edtCodBarrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodBarrasKeyPressed
     /* if(evt.getKeyCode()==KeyEvent.VK_ENTER){
          InserirNaTabelaELista();
      }*/
        atalhos(evt);
    }//GEN-LAST:event_edtCodBarrasKeyPressed

    private void edtCodBarrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodBarrasKeyTyped
       // TODO add your handling code here:
       
    }//GEN-LAST:event_edtCodBarrasKeyTyped

    private void edtCodBarrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodBarrasKeyReleased
 
        // TODO add your handling code here:
    }//GEN-LAST:event_edtCodBarrasKeyReleased
public void Leitor(){
    ProdutoBD bd = new ProdutoBD();
        Produto produtoo = bd.getProdutoByCodBarras(Long.valueOf(edtCodBarras.getText()));
        if(produtoo!=null){
            edtDescricao.setText(produtoo.getDescricao());
            edtValorUnitario.setText(produtoo.getPreco_venda().toString());
            edtCodBarras.setText("");
            spQtd.setValue(1);
            this.produto= produtoo;
            InserirNaTabelaELista();
        }else{
          int resposta= JOptionPane.showConfirmDialog(this, "Produto não encontrado, deseja cadastra-lo?", "Produto não encontrado",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
          if(resposta==JOptionPane.YES_OPTION){
              FormCadProduto cad= new FormCadProduto();
              cad.setVisible(true);
          }
        }
        
        
        System.out.println(produtoo); 
}
    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        InserirNaTabelaELista();
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void tableItensKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableItensKeyPressed
     /*  if(evt.getKeyCode()==KeyEvent.VK_F3){
           excluirItem();
       }*/
        atalhos(evt);
    }//GEN-LAST:event_tableItensKeyPressed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int resposta=JOptionPane.showConfirmDialog(this, "Confirma Cancelar ?","Atenção!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(resposta==JOptionPane.YES_OPTION){
                this.dispose();
            }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void edtDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDescricaoKeyPressed
if(evt.getKeyCode()==KeyEvent.VK_ENTER ||evt.getKeyCode()==KeyEvent.VK_F8){
FormSelecionaProduto f = new FormSelecionaProduto(this);
f.setVisible(true);
}

    // TODO add your handling code here:
    }//GEN-LAST:event_edtDescricaoKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        atalhos(evt);
    }//GEN-LAST:event_formKeyPressed

    private void edtTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtTotalKeyPressed
        atalhos(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_edtTotalKeyPressed

    private void edtCodigoVendaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodigoVendaKeyPressed
        atalhos(evt);       // TODO add your handling code here:
    }//GEN-LAST:event_edtCodigoVendaKeyPressed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        System.out.println("teste");        // TODO add your handling code here:
       int resposta=JOptionPane.showConfirmDialog(this, "Confirma Fechamento da venda ?","Atenção!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(resposta==JOptionPane.YES_OPTION){
               fecharVenda();
            }
    }//GEN-LAST:event_btnGravarActionPerformed
public void fecharVenda(){
    
    FormFinalizaVenda form = new FormFinalizaVenda(listaItens,valorVenda,idUltimaVenda);
    form.setVisible(true);
}
    public void atalhos(KeyEvent evt){
        if(evt.getKeyCode()==KeyEvent.VK_F3){
           excluirItem();
       }/*
       else if(evt.getKeyCode()==KeyEvent.VK_F4){
            int resposta=JOptionPane.showConfirmDialog(this, "Confirma Cancelar ?","Atenção!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(resposta==JOptionPane.YES_OPTION){
                this.dispose();
            }
        }
       else if(evt.getKeyCode()==KeyEvent.VK_F12){
            int resposta=JOptionPane.showConfirmDialog(this, "Confirma Fechamento da venda ?","Atenção!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(resposta==JOptionPane.YES_OPTION){
               fecharVenda();
            }
        }*/else if(evt.getKeyCode()==KeyEvent.VK_F2){
          alterar();
        }
    }
    public void recuperaProduto(Produto produto){
    this.produto=produto;
    edtDescricao.setText(produto.getDescricao());
    edtValorUnitario.setText(String.valueOf(produto.getPreco_venda()));
    spQtd.setValue(1);
    
}
    
    public void excluirItem(){
    int linha=-1;
        linha=tableItens.getSelectedRow();
        if(linha>=0){
        ItensVenda iv = listaItens.get(linha);
        listaItens.remove(linha);
        valorVenda-=iv.getTotal();
        edtTotal.setText("R$ "+valorVenda.toString());
         model= new TabelaItensVendasVenda(listaItens);
           tableItens.setModel(model);
        }else{
            JOptionPane.showMessageDialog(this, "Selecione um produto na tabela para excluir.");
        }
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
            java.util.logging.Logger.getLogger(FormVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JTextField edtCodBarras;
    private javax.swing.JTextField edtCodigoVenda;
    private javax.swing.JTextField edtDescricao;
    private javax.swing.JFormattedTextField edtTotal;
    private javax.swing.JTextField edtValorUnitario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spQtd;
    private javax.swing.JTable tableItens;
    // End of variables declaration//GEN-END:variables
}
