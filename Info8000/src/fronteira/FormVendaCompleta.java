/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

import Conection.ClienteBD;
import Conection.FuncionarioBD;
import Conection.ItensVendaBD;
import Conection.ProdutoBD;
import Conection.VendaBD;
import Objetos.Cliente;
import Objetos.Funcionario;
import Objetos.ItensVenda;
import Objetos.Produto;
import Objetos.Venda;
import ferramentas.TabelaItensVendasVenda;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author user
 */
public class FormVendaCompleta extends javax.swing.JFrame {
Produto produto;
 TabelaItensVendasVenda model ;
    List<ItensVenda> listaItens = new ArrayList<>();
    
    ProdutoBD produtobd = new ProdutoBD();
    VendaBD vendabd = new VendaBD();
    int idUltimaVenda;
    Cliente cliente;
    Venda vendaEditar = null;
   Funcionario vendedor;
    Double valorVenda=0.0;
    Iterator<ItensVenda> it;
    /**
     * Creates new form FormOrdemServico
     */
    public FormVendaCompleta() {
        initComponents();
        pegaDataAtual();
       atualizaListas();
       try {
            idUltimaVenda=vendabd.getUltimaID();
            idUltimaVenda++;
            edtCodigo.setText(""+idUltimaVenda);
        } catch (Exception ex) {
            Logger.getLogger(FormVenda.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erro ao recuperar ultima id "+ ex);
        }
    }
    public FormVendaCompleta(Venda venda) {
        initComponents();
       
       vendaEditar = venda;
        edtCodigo.setText(String.valueOf(vendaEditar.getId()));
    try {
        ItensVendaBD  itensVendaBD = new ItensVendaBD();
     listaItens= itensVendaBD.getListaItensVendaByIdVenda(vendaEditar.getId());
     
    } catch (Exception ex) {
        Logger.getLogger(FormVendaCompleta.class.getName()).log(Level.SEVERE, null, ex);
    }
         pegaDataAtual();
       atualizaListas();
        edtTotal.setText(vendaEditar.getValor_venda().toString());
        edtCliente.setText(vendaEditar.getCliente());
        edtVendedor.setText(vendaEditar.getFuncionario());
        cliente = new ClienteBD().getClienteById(vendaEditar.getId_Cliente());
        vendedor = new FuncionarioBD().getFuncionarioById(vendaEditar.getId_funcionario());
        valorVenda= vendaEditar.getValor_venda();
    }
    
    public void atualizaListas(){
    try {
        model = new TabelaItensVendasVenda(listaItens);
        tableProdutos.setModel(model);
        
    } catch (Exception ex) {
        Logger.getLogger(FormVendaCompleta.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        edtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        edtCliente = new javax.swing.JTextField();
        edtVendedor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        edtData = new javax.swing.JFormattedTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableProdutos = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        edtCodBarras = new javax.swing.JTextField();
        edtDescricao = new javax.swing.JTextField();
        edtValorUnt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        spQtd = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        btnIncluir = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnIncluir1 = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        edtTotal = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rbCondicional = new javax.swing.JCheckBox();

        jScrollPane1.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Venda Completa");
        setResizable(false);

        jLabel1.setText("Código:");

        edtCodigo.setEditable(false);

        jLabel2.setText("Cliente:");

        edtCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edtClienteFocusLost(evt);
            }
        });
        edtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtClienteKeyPressed(evt);
            }
        });

        edtVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtVendedorKeyPressed(evt);
            }
        });

        jLabel3.setText("Data:");

        jLabel4.setText("Vendedor:");

        edtData.setEditable(false);

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
        jScrollPane4.setViewportView(tableProdutos);

        jLabel7.setText("Produtos:");

        edtCodBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtCodBarrasActionPerformed(evt);
            }
        });
        edtCodBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodBarrasKeyPressed(evt);
            }
        });

        edtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDescricaoKeyPressed(evt);
            }
        });

        edtValorUnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtValorUntActionPerformed(evt);
            }
        });

        jLabel11.setText("Codigo de Barras:");

        jLabel12.setText("Valor Unitario:");

        jLabel13.setText("Descrição:");

        jLabel14.setText("Qdt");

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/adicionar.png"))); // NOI18N
        btnIncluir.setText("Incluir(F2)");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lixeira.png"))); // NOI18N
        jButton3.setText("Excluir item");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/confirma.png"))); // NOI18N
        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnIncluir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/adicionar.png"))); // NOI18N
        btnIncluir1.setText("Nova Venda");
        btnIncluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluir1ActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Editar.gif"))); // NOI18N
        btnAlterar.setText("Alterar");

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lixeira.png"))); // NOI18N
        btnExcluir.setText("Excluir");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGravar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIncluir1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcluir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGravar)
                        .addComponent(btnCancelar)
                        .addComponent(btnIncluir1)
                        .addComponent(btnAlterar)
                        .addComponent(btnExcluir))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAlterar, btnCancelar, btnExcluir, btnGravar, btnIncluir1});

        jLabel6.setText(" F8 para Buscar Produto");

        jLabel8.setText("Total:");

        edtTotal.setEditable(false);
        edtTotal.setBackground(new java.awt.Color(204, 204, 255));
        edtTotal.setForeground(new java.awt.Color(255, 51, 0));
        edtTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));
        edtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtTotalActionPerformed(evt);
            }
        });

        jLabel9.setText(" F8 ou Enter para Buscar Cliente");

        jLabel10.setText(" F8 ou Enter para Buscar Vendedor");

        rbCondicional.setText("Condicional");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(232, 232, 232))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
            .addComponent(jScrollPane4)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(edtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addGap(8, 8, 8)
                                .addComponent(edtValorUnt, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnIncluir))
                            .addComponent(edtCodBarras)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(edtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(edtData, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(edtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(91, 91, 91)
                                        .addComponent(jLabel2)
                                        .addGap(131, 131, 131)
                                        .addComponent(jLabel9))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10))
                                    .addComponent(edtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rbCondicional)
                                .addGap(38, 38, 38))))
                    .addComponent(jLabel7))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jLabel9))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addComponent(edtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rbCondicional, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 0, 0)
                .addComponent(jLabel6)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtCodBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(spQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(edtValorUnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIncluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel8)
                    .addComponent(edtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void edtCodBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtCodBarrasActionPerformed
        if(edtCodBarras.getText().isEmpty() || edtCodBarras.getText()==""){
            
FormSelecionaProduto f = new FormSelecionaProduto(this);
f.setVisible(true);
}else
   Leitor();     
    }//GEN-LAST:event_edtCodBarrasActionPerformed
public void Leitor(){
    ProdutoBD bd = new ProdutoBD();
        Produto produtoo = bd.getProdutoByCodBarras(Long.valueOf(edtCodBarras.getText()));
        if(produtoo!=null){
            edtDescricao.setText(produtoo.getDescricao());
            edtValorUnt.setText(produtoo.getPreco_venda().toString());
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
            tableProdutos.setModel(model);
            produto = null;
            edtDescricao.setText("");
            edtValorUnt.setText("");

        } else {
            JOptionPane.showMessageDialog(this, "Nenhum produto selecionado, selecione!");
        }
        spQtd.setValue(1);
        edtCodBarras.requestFocus();
    }
    private void edtValorUntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtValorUntActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtValorUntActionPerformed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        if (cliente!=null && vendedor!=null && listaItens.isEmpty()==false) {
            int resposta = JOptionPane.showConfirmDialog(this, "Confirma Fechamento da venda ?", "Atenção!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resposta == JOptionPane.YES_OPTION) {
                Double total = 0.0;
                Iterator<ItensVenda> it = listaItens.iterator();
                while (it.hasNext()) {
                    total += it.next().getTotal();
                }
                
                valorVenda = total;
                
                fecharVenda();
            }
        }else{
           
            if(cliente==null && vendedor==null){
                JOptionPane.showMessageDialog(this, "Campo cliente e vendedor são obrigatorios! Siga as Instruções e selecione.");
                edtCliente.setBackground(Color.RED);
               edtCliente.setForeground(Color.WHITE);
                edtVendedor.setBackground(Color.RED);
                edtVendedor.setForeground(Color.WHITE);
            }else if(cliente==null){
               JOptionPane.showMessageDialog(this, "Campo cliente é obrigatorio! Siga as Instruções e selecione um.");
               edtCliente.requestFocus();
               edtCliente.setBackground(Color.RED);
               edtCliente.setForeground(Color.WHITE);
            }else if(vendedor==null){
                JOptionPane.showMessageDialog(this, "Campo vendedor é obrigatorio! Siga as Instruções e selecione um.");
              edtVendedor.requestFocus();
               edtVendedor.setBackground(Color.RED);
               edtVendedor.setForeground(Color.WHITE);
            }else if(listaItens.isEmpty()){
                JOptionPane.showMessageDialog(this, "É necessário  selecionar algum produto.");
                edtCodBarras.setBackground(Color.RED);
                edtDescricao.setBackground(Color.RED);
              
            }else{
                JOptionPane.showMessageDialog(this, "Verifique os campos! Não são permitidos campos nulos");
            }
        }
    }//GEN-LAST:event_btnGravarActionPerformed
public void fecharVenda(){
  
          Venda venda = new Venda();
          venda.setCaixa("Venda Normal");
          venda.setCliente(edtCliente.getText());
          venda.setFuncionario(edtVendedor.getText());
          venda.setValor_venda(valorVenda);
          venda.setData_venda(pegaData());
          venda.setId_Cliente(cliente.getId());
          venda.setId_funcionario(vendedor.getId());
          if(rbCondicional.isSelected()){
              venda.setSituacao(1);
              
          }else{
              venda.setSituacao(0);
          }
          if(vendaEditar!=null){
              venda.setId(vendaEditar.getId());
              venda.setData_venda(vendaEditar.getData_venda());
          }
          
          
      FormFinalizaVenda form = new FormFinalizaVenda(listaItens,valorVenda,idUltimaVenda, venda,cliente,vendedor);
    form.setVisible(true);
}
public final java.sql.Date pegaData(){
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
    private void edtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtTotalActionPerformed

    private void edtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtClienteKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_F8 || evt.getKeyCode() == KeyEvent.VK_ENTER ){
           FormSelecionaCliente form = new  FormSelecionaCliente(this);
           form.setVisible(true);
       }
    }//GEN-LAST:event_edtClienteKeyPressed

    private void edtCodBarrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodBarrasKeyPressed
         if (evt.getKeyCode() == KeyEvent.VK_F8 ){
           FormSelecionaProduto form = new  FormSelecionaProduto();
           form.setVisible(true);
       }
    }//GEN-LAST:event_edtCodBarrasKeyPressed

    private void edtDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDescricaoKeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER ||evt.getKeyCode()==KeyEvent.VK_F8){
FormSelecionaProduto f = new FormSelecionaProduto(this);
f.setVisible(true);
}
    }//GEN-LAST:event_edtDescricaoKeyPressed

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
InserirNaTabelaELista();        // TODO add your handling code here:
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        excluirItem();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
      int resposta=JOptionPane.showConfirmDialog(this, "Confirma Cancelar ?","Atenção!",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(resposta==JOptionPane.YES_OPTION){
                this.dispose();
            }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnIncluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluir1ActionPerformed
        // TODO add your handling code here:
        limparCampos();
    }//GEN-LAST:event_btnIncluir1ActionPerformed

    private void edtClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edtClienteFocusLost
  
    }//GEN-LAST:event_edtClienteFocusLost

    private void edtVendedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtVendedorKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F8 || evt.getKeyCode() == KeyEvent.VK_ENTER ){
           FormSelecionaFuncionario form = new  FormSelecionaFuncionario(this);
           form.setVisible(true);
       }
    }//GEN-LAST:event_edtVendedorKeyPressed
public void limparCampos(){
    edtCliente.setText("");
    edtCodBarras.setText("");
    valorVenda=0.0;
    edtCodigo.setText("");
    edtData.setText(pegaDataAtual());
    edtDescricao.setText("");
    edtVendedor.setText("");
    edtTotal.setText("");
    edtValorUnt.setText("");
    listaItens = new ArrayList<ItensVenda>();
    model = new  TabelaItensVendasVenda(listaItens);
    tableProdutos.setModel(model);
}
    public final String pegaDataAtual() {
        String data = "dd/MM/yyyy";
        String hora = "h:mm - a";
        String data1, hora1;
        java.util.Date agora = new java.util.Date();
        SimpleDateFormat formata = new SimpleDateFormat(data);
        data1 = formata.format(agora);
        formata = new SimpleDateFormat(hora);
        hora1 = formata.format(agora);
        edtData.setText(data1);
        return data1;
    }
    public void excluirItem(){
    int linha=-1;
        linha=tableProdutos.getSelectedRow();
        if(linha>=0){
        ItensVenda iv = listaItens.get(linha);
       
        listaItens.remove(linha);
            System.out.println(listaItens);
        valorVenda-=iv.getTotal();
        
        edtTotal.setText("R$ "+valorVenda.toString());
         model= new TabelaItensVendasVenda(listaItens);
           tableProdutos.setModel(model);
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
            java.util.logging.Logger.getLogger(FormVendaCompleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormVendaCompleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormVendaCompleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormVendaCompleta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormVendaCompleta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnIncluir1;
    private javax.swing.JTextField edtCliente;
    private javax.swing.JTextField edtCodBarras;
    private javax.swing.JTextField edtCodigo;
    private javax.swing.JFormattedTextField edtData;
    private javax.swing.JTextField edtDescricao;
    private javax.swing.JFormattedTextField edtTotal;
    private javax.swing.JTextField edtValorUnt;
    private javax.swing.JTextField edtVendedor;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JCheckBox rbCondicional;
    private javax.swing.JSpinner spQtd;
    private javax.swing.JTable tableProdutos;
    // End of variables declaration//GEN-END:variables

    void retornaCliente(Cliente cliente) {
       this.cliente=cliente;
       edtCliente.setText(cliente.getNome());
       edtCliente.setBackground(Color.WHITE);
       validate();
    }

  

    void recuperaProduto(Produto produto) {
        this.produto=produto;
        //edtCodBarras.setText(String.valueOf(produto.getCodBarras()));
        spQtd.setValue(1);
        edtDescricao.setText(produto.getDescricao());
        edtValorUnt.setText(String.valueOf(produto.getPreco_venda()));
        edtCodBarras.setBackground(Color.WHITE);
                edtDescricao.setBackground(Color.WHITE);
    }

    void retornaFuncionario(Funcionario funcionario) {
       this.vendedor= funcionario;
       edtVendedor.setText(vendedor.getNome());
       edtVendedor.setBackground(Color.WHITE);
        validate();
    }
}