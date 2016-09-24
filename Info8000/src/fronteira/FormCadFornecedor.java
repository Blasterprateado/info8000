/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

import Conection.CidadeBD;
import Conection.ClienteBD;
import Conection.FornecedorBD;
import Objetos.Cidade;
import Objetos.Cliente;
import Objetos.Fornecedor;
import ferramentas.ComboBoxCidade;
import ferramentas.TabelaFornecedor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class FormCadFornecedor extends javax.swing.JFrame {

    TabelaFornecedor model;
    List<Fornecedor> listaFornecedor;
    ComboBoxCidade modelCombo;
    FornecedorBD bd = new FornecedorBD();
    int btnIncluirStatus=0;
    int btnAlterarStatus=0;
    /**
     * Creates new form FormCadCliente
     */
    public FormCadFornecedor() {
        initComponents();
        PreencheTabela();
        btnExcluir.setEnabled(false);
        btnGravar.setEnabled(false);
    }
    
public void AbilidaOuDesabilitaBotoes(){
        if(btnIncluirStatus==1){
            btnExcluir.setEnabled(false);
            btnAlterar.setEnabled(false);
            btnIncluir.setEnabled(false);
            btnGravar.setEnabled(true);
        }else{
            btnExcluir.setEnabled(true);
            btnAlterar.setEnabled(true);
            btnIncluir.setEnabled(true); 
            btnGravar.setEnabled(true);
        }
        if(PanelGuia.getSelectedIndex()==1){
            btnIncluirStatus=1;
           btnExcluir.setEnabled(false);
            btnAlterar.setEnabled(false);
            btnIncluir.setEnabled(false); 
            btnGravar.setEnabled(false);
        }else{
            //btnIncluirStatus=0;
           btnExcluir.setEnabled(true);
            btnAlterar.setEnabled(true);
            btnIncluir.setEnabled(true); 
            btnGravar.setEnabled(true);
        }
    }    
    public void PreencheTabela(){
        modelCombo = new ComboBoxCidade();
        ComboCidade.setModel(modelCombo);
        listaFornecedor = bd.GetListaFornecedor();
        model = new TabelaFornecedor(listaFornecedor);
        tabelaFornecedor.setModel(model);
    }
private void limparCampos(){
            edtCodigo.setText("");
            edtNome.setText("");
            edtEnd.setText("");
            edtBairro.setText("");
            edtNumero.setText("");
            edtRG.setText("");
            edtCPF.setText("");
            ComboCidade.setSelectedItem(-1);
            edtCep.setText("");
            edtTel.setText("");
            
    }
    private Fornecedor preencheFornecedor() {
        if (edtNome.getText().isEmpty() || edtBairro.getText().isEmpty()
                || edtCPF.getText().isEmpty() || edtRG.getText().isEmpty() || edtEnd.getText().isEmpty()
                || edtNumero.getText().isEmpty() || edtTel.getText().isEmpty() || edtCep.getText().isEmpty()) {
            return null;
        } else {
           Fornecedor fornecedor = new Fornecedor();
            if(edtCodigo.getText().isEmpty()){
        
    }else{
                
            fornecedor.setId(Integer.valueOf(edtCodigo.getText()));
    }
            
            fornecedor.setNome(edtNome.getText());
            fornecedor.setBairro(edtBairro.getText());
            fornecedor.setCnpj(edtCPF.getText());
            fornecedor.setIe(edtRG.getText());
            fornecedor.setEndereco(edtEnd.getText());
            fornecedor.setNumero(Integer.valueOf(edtNumero.getText()));
            fornecedor.setTelefone(edtTel.getText());
            Cidade cidade = (Cidade) ComboCidade.getSelectedItem();
            System.out.println(cidade);
            fornecedor.setCidade(cidade.getNome());
            return fornecedor;
        }

    }
    public void alterar(){
        int linhaSelecionada = -1;
        Fornecedor fornecedor;
        linhaSelecionada = tabelaFornecedor.getSelectedRow();
        if (linhaSelecionada >= 0) { 
            int idFornecedor = (int) tabelaFornecedor.getValueAt(linhaSelecionada, 0);
          
            FornecedorBD dao = new FornecedorBD();
            fornecedor= dao.getFornecedorById(idFornecedor);
            edtCodigo.setText(String.valueOf(fornecedor.getId()));
            edtNome.setText(fornecedor.getNome());
            edtEnd.setText(fornecedor.getEndereco());
            edtBairro.setText(fornecedor.getBairro());
            edtNumero.setText(String.valueOf(fornecedor.getNumero()));
            edtRG.setText(fornecedor.getIe());
            edtCPF.setText(fornecedor.getCnpj());
            CidadeBD bd = new CidadeBD();
            Cidade cidade=null;
            try {
                cidade = bd.getListaCidadeBYNOME(fornecedor.getCidade());
            } catch (Exception ex) {
                Logger.getLogger(FormCadCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Cidade alterar" + cidade.getNome());
            
                ComboCidade.getModel().setSelectedItem(cidade.getNome());
               edtCep.setText(cidade.getCep());
            
            edtTel.setText(fornecedor.getTelefone());
            
                
        } 
        else {
            JOptionPane.showMessageDialog(null, "É necesário selecionar uma linha.");
        }
    }
    public void excluir(){
        int linhaSelecionada = -1;
        Fornecedor fornecedor;
        linhaSelecionada = tabelaFornecedor.getSelectedRow();
        if (linhaSelecionada >= 0) { 
            int idFornecedor = (int) tabelaFornecedor.getValueAt(linhaSelecionada, 0);
          
            FornecedorBD dao = new FornecedorBD();
            dao.removerFornecedor(idFornecedor);
            PreencheTabela();
            
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

        PanelGuia = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFornecedor = new javax.swing.JTable();
        panelCadastro = new javax.swing.JPanel();
        edtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        edtEnd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        edtNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        edtBairro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        ComboCidade = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        edtNumero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        edtCep = new javax.swing.JFormattedTextField();
        edtTel = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        edtRG = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        edtCPF = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fornecedores");
        setResizable(false);

        PanelGuia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelGuiaMouseClicked(evt);
            }
        });

        tabelaFornecedor.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaFornecedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaFornecedor);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        PanelGuia.addTab("Seleção", jPanel1);

        edtCodigo.setEditable(false);
        edtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtCodigoActionPerformed(evt);
            }
        });

        jLabel3.setText("Endereço:");

        jLabel2.setText("Razao Social:");

        edtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtNomeActionPerformed(evt);
            }
        });

        jLabel1.setText("Código:");

        jLabel5.setText("Bairro:");

        edtBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtBairroActionPerformed(evt);
            }
        });

        jLabel6.setText("Cidade:");

        jLabel7.setText("Telefone:");

        ComboCidade.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboCidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboCidadeItemStateChanged(evt);
            }
        });

        jLabel4.setText("Nº :");

        jLabel9.setText("Cep:");

        try {
            edtCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            edtTel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel8.setText("INSC. ESTADUAL");

        jLabel10.setText("CNPJ:");

        try {
            edtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        edtCPF.setToolTipText("");

        javax.swing.GroupLayout panelCadastroLayout = new javax.swing.GroupLayout(panelCadastro);
        panelCadastro.setLayout(panelCadastroLayout);
        panelCadastroLayout.setHorizontalGroup(
            panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelCadastroLayout.createSequentialGroup()
                            .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addGap(5, 5, 5))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadastroLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)))
                    .addGroup(panelCadastroLayout.createSequentialGroup()
                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(10, 10, 10)))
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(edtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCadastroLayout.createSequentialGroup()
                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(edtEnd, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCadastroLayout.createSequentialGroup()
                                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelCadastroLayout.createSequentialGroup()
                                        .addComponent(edtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadastroLayout.createSequentialGroup()
                                        .addComponent(edtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8)
                                        .addGap(23, 23, 23)))
                                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ComboCidade, 0, 175, Short.MAX_VALUE)
                                    .addComponent(edtRG))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(edtNumero)
                            .addComponent(edtCep)))
                    .addComponent(edtNome))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCadastroLayout.setVerticalGroup(
            panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadastroLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(edtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(edtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(edtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(edtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(ComboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(edtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(edtTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(edtRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(edtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        PanelGuia.addTab("Cadastro", panelCadastro);

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/adicionar.png"))); // NOI18N
        btnIncluir.setText("Incluir");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Editar.gif"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lixeira.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

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
                .addComponent(btnIncluir)
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
                        .addComponent(btnIncluir)
                        .addComponent(btnAlterar)
                        .addComponent(btnExcluir))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAlterar, btnCancelar, btnExcluir, btnGravar, btnIncluir});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(PanelGuia)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelGuia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void edtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtNomeActionPerformed

    private void edtBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtBairroActionPerformed

    private void edtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtCodigoActionPerformed

    private void ComboCidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboCidadeItemStateChanged
        Cidade cidade = (Cidade) ComboCidade.getSelectedItem();
        edtCep.setText(cidade.getCep());
    }//GEN-LAST:event_ComboCidadeItemStateChanged

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        Fornecedor fornecedor = preencheFornecedor();
        if (edtCodigo.getText().isEmpty()) {
            if (fornecedor != null) {
                try {
                    bd.adicionarFornecedor(fornecedor);
                    JOptionPane.showMessageDialog(this, "Forncedor cadastrado com sucesso!!!");
                    limparCampos();
                    PanelGuia.setSelectedIndex(0);
                    PanelGuia.setEnabled(true);
                } catch (Exception ex) {
                    Logger.getLogger(FormCadCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Não é permitido campos nulos, Verifique!!!");
            }
        } else {
            try {
                bd.AtualizaFornecedor(fornecedor);
                JOptionPane.showMessageDialog(this, "Fornecedor atualizado com sucesso!!!");
                limparCampos();
                PanelGuia.setSelectedIndex(0);
                PanelGuia.setEnabled(true);

            } catch (Exception ex) {
                Logger.getLogger(FormCadCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

PreencheTabela();
AbilidaOuDesabilitaBotoes();
    }//GEN-LAST:event_btnGravarActionPerformed

    private void PanelGuiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelGuiaMouseClicked
        AbilidaOuDesabilitaBotoes();
                PreencheTabela();
                if(PanelGuia.getSelectedIndex()==1){
                    PanelGuia.setEnabled(false);
                }
    }//GEN-LAST:event_PanelGuiaMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if(btnAlterarStatus==0){//0 indica que nao foi clicado
           btnAlterarStatus=1;//1 indica que foi clicado
       }
        PanelGuia.setSelectedIndex(1);
        AbilidaOuDesabilitaBotoes();
        btnGravar.setEnabled(true);
        alterar();
       PanelGuia.setEnabled(false);
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
      limparCampos();
        JOptionPane.showMessageDialog(this,"Cadastro Cancelado");
        PanelGuia.setSelectedIndex(0);
        PanelGuia.setEnabled(true);
        AbilidaOuDesabilitaBotoes();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
         if(btnIncluirStatus==0){//0 indica que nao foi clicado
           btnIncluirStatus=1;//1 indica que foi clicado
           AbilidaOuDesabilitaBotoes();
           btnGravar.setEnabled(true);
       }
        PanelGuia.setSelectedIndex(1);
        PanelGuia.setEnabled(false);
        AbilidaOuDesabilitaBotoes();
        btnGravar.setEnabled(true);
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
     excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tabelaFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaFornecedorMouseClicked
       btnExcluir.setEnabled(true);
    }//GEN-LAST:event_tabelaFornecedorMouseClicked

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
            java.util.logging.Logger.getLogger(FormCadCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCadCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCadCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCadCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCadCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboCidade;
    private javax.swing.JTabbedPane PanelGuia;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JTextField edtBairro;
    private javax.swing.JFormattedTextField edtCPF;
    private javax.swing.JFormattedTextField edtCep;
    private javax.swing.JTextField edtCodigo;
    private javax.swing.JTextField edtEnd;
    private javax.swing.JTextField edtNome;
    private javax.swing.JTextField edtNumero;
    private javax.swing.JFormattedTextField edtRG;
    private javax.swing.JFormattedTextField edtTel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel panelCadastro;
    private javax.swing.JTable tabelaFornecedor;
    // End of variables declaration//GEN-END:variables
}
