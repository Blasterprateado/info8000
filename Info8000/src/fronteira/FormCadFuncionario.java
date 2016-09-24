/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

import Conection.CidadeBD;
import Conection.FuncionarioBD;
import Objetos.Cidade;
import Objetos.Funcionario;
import ferramentas.ComboBoxCidade;
import ferramentas.TabelaFuncionario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class FormCadFuncionario extends javax.swing.JFrame {
    TabelaFuncionario model ;
    List<Funcionario> listaFuncionario;
    FuncionarioBD BD = new FuncionarioBD();
    int btnIncluirStatus=0;
    int btnAlterarStatus=0;
    ComboBoxCidade modelCombo = new ComboBoxCidade();
    /**
     * Creates new form FormCadFuncionario
     */
    public FormCadFuncionario() {
         initComponents();
         preencheTabela();
         PanelGuia.setSelectedIndex(0);
         btnGravar.setEnabled(false);
         
    }
    private void preencheTabela(){
        try {
            listaFuncionario= BD.getListaFuncionario();
            model = new TabelaFuncionario(listaFuncionario);
            tabelaFuncionario.setModel(model);
             modelCombo = new ComboBoxCidade();
             ComboCidade.setModel(modelCombo);
        } catch (Exception ex) {
            Logger.getLogger(FormCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void AbilidaOuDesabilitaBotoes(){
        if(btnIncluirStatus==1){
            btnExcluir.setEnabled(false);
            btnAlterar.setEnabled(false);
            btnIncluir.setEnabled(false);
            btnGravar.setEnabled(true);
        }
        if(PanelGuia.getSelectedIndex()==1){
            btnIncluirStatus=1;
           btnExcluir.setEnabled(false);
            btnAlterar.setEnabled(false);
            btnIncluir.setEnabled(false); 
            btnGravar.setEnabled(true);
        }else{
            //btnIncluirStatus=0;
            btnGravar.setEnabled(false);
           btnExcluir.setEnabled(true);
            btnAlterar.setEnabled(true);
            btnIncluir.setEnabled(true);  
        }
    }
    private void limpaCampos(){
        edtBairro.setText("");
        edtCPF.setText("");
        edtCep.setText("");
        edtCodigo.setText("");
        edtEnd.setText("");
        edtNome.setText("");
        edtNumero.setText("");
        edtRG.setText("");
        edtSalario.setText("");
        edtSenha.setText("");
        edtTel.setText("");
        edtUsuario.setText("");
        dataAdm.setDate(null);
        dataDm.setDate(null);
        
    }
    public void excluir(){
        int linhaSelecionada = -1;
        Funcionario funcionariio;
        linhaSelecionada = tabelaFuncionario.getSelectedRow();
        if (linhaSelecionada >= 0) { 
            int idFuncionario = (int) tabelaFuncionario.getValueAt(linhaSelecionada, 0);
          
            FuncionarioBD dao = new FuncionarioBD();
            dao.removerFuncionario(idFuncionario);
            preencheTabela();
            
    }                                          
    }
    private void alterar(){
     int linhaSelecionada = -1;
        Funcionario funcionario;
        linhaSelecionada = tabelaFuncionario.getSelectedRow();
        if (linhaSelecionada >= 0) { 
            int idCliente = (int) tabelaFuncionario.getValueAt(linhaSelecionada, 0);
          
            FuncionarioBD dao = new FuncionarioBD();
            funcionario = dao.getFuncionarioById(idCliente);
            System.out.println(funcionario);
            edtCodigo.setText(String.valueOf(funcionario.getId()));
            edtNome.setText(funcionario.getNome());
            edtEnd.setText(funcionario.getEndereco());
            edtBairro.setText(funcionario.getBairro());
            edtNumero.setText(String.valueOf(funcionario.getNumero()));
            System.out.println("numero funcionario "+funcionario.getNumero());
            edtRG.setText(funcionario.getRg());
            edtCPF.setText(funcionario.getCpf());
            edtSalario.setText(funcionario.getSalario().toString());
            CidadeBD bd = new CidadeBD();
            Cidade cidade=null;
            try {
                System.out.println("id cidade "+funcionario.getCidade());
                cidade = bd.getListaCidadeBYID(funcionario.getCidade());
                ComboCidade.getModel().setSelectedItem(cidade);
                edtCep.setText(cidade.getCep());
                System.out.println("Cidade alterar" + cidade.getNome());
            } catch (Exception ex) {
                Logger.getLogger(FormCadCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
                
            
                edtTel.setText(funcionario.getTel());
                dataAdm.setDate(funcionario.getDataAdm());
                dataDm.setDate(funcionario.getDataDm());
                edtSenha.setText(funcionario.getSenha());
                edtUsuario.setText(funcionario.getUsuario());
                edtNome.setText(funcionario.getNome());
            
                
        } 
        else {
            JOptionPane.showMessageDialog(null, "É necesário selecionar uma linha.");
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

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnIncluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        PanelGuia = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaFuncionario = new javax.swing.JTable();
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
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        edtSalario = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        edtUsuario = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        edtSenha = new javax.swing.JTextField();
        dataAdm = new com.toedter.calendar.JDateChooser();
        dataDm = new com.toedter.calendar.JDateChooser();
        ComboPermissao = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Funcionarios");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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

        PanelGuia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelGuiaMouseClicked(evt);
            }
        });

        tabelaFuncionario.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelaFuncionario);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        PanelGuia.addTab("Seleção", jPanel1);

        edtCodigo.setEditable(false);
        edtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtCodigoActionPerformed(evt);
            }
        });

        jLabel3.setText("Endereço:");

        jLabel2.setText("Nome:");

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

        jLabel8.setText("RG:");

        jLabel10.setText("CPF:");

        try {
            edtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel11.setText("Data Adm:");

        jLabel12.setText("Data Dm:");

        jLabel13.setText("Salario:");

        jLabel14.setText("Usuario:");

        jLabel15.setText("Senha:");

        ComboPermissao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gerente", "Administrador", "Basico" }));

        jLabel16.setText("Permissão:");

        javax.swing.GroupLayout panelCadastroLayout = new javax.swing.GroupLayout(panelCadastro);
        panelCadastro.setLayout(panelCadastroLayout);
        panelCadastroLayout.setHorizontalGroup(
            panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(panelCadastroLayout.createSequentialGroup()
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
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(edtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtNome)
                            .addGroup(panelCadastroLayout.createSequentialGroup()
                                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(edtEnd)
                                    .addGroup(panelCadastroLayout.createSequentialGroup()
                                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelCadastroLayout.createSequentialGroup()
                                                    .addComponent(edtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel6))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCadastroLayout.createSequentialGroup()
                                                    .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(edtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(panelCadastroLayout.createSequentialGroup()
                                                            .addGap(1, 1, 1)
                                                            .addComponent(dataAdm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel8)
                                                        .addComponent(jLabel12))))
                                            .addGroup(panelCadastroLayout.createSequentialGroup()
                                                .addComponent(edtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel15)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(edtSenha)
                                            .addComponent(ComboCidade, 0, 175, Short.MAX_VALUE)
                                            .addComponent(edtRG)
                                            .addComponent(dataDm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelCadastroLayout.createSequentialGroup()
                                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(edtCPF)
                                            .addComponent(edtNumero)
                                            .addComponent(edtCep)))
                                    .addGroup(panelCadastroLayout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ComboPermissao, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(panelCadastroLayout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(edtSalario)))))))
                .addContainerGap(32, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel11)
                        .addComponent(dataAdm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dataDm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(edtSalario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(panelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(edtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(edtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboPermissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addContainerGap())
        );

        PanelGuia.addTab("Cadastro", panelCadastro);

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

    private void edtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtCodigoActionPerformed

    private void edtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtNomeActionPerformed

    private void edtBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtBairroActionPerformed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        // TODO add your handling code here:
        FuncionarioBD bd = new FuncionarioBD();
        try {
            Funcionario f = preencheFuncionario();
            if(f!=null){
                if(edtCodigo.getText().isEmpty()){
                bd.adicionarFuncionario(f);
                 btnAlterarStatus=0;
        btnIncluirStatus=0;
                JOptionPane.showMessageDialog(this, "Funcionario Cadastrado com Sucesso");
                
                PanelGuia.setSelectedIndex(0);
                PanelGuia.setEnabled(true);
                btnIncluirStatus=0;
                btnAlterarStatus=0;
                AbilidaOuDesabilitaBotoes();
                limpaCampos();
            }else{
                    try {
                        bd.AtualizaFuncionario(f);
                        JOptionPane.showMessageDialog(this, "Funcionario Atualizado com Sucesso");
                         PanelGuia.setSelectedIndex(0);
                PanelGuia.setEnabled(true);
                btnIncluirStatus=0;
                btnAlterarStatus=0;
                AbilidaOuDesabilitaBotoes();
                limpaCampos();
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(this, "Erro ao Atualizar Funcionario");
                        
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Não é Permitido Campos Nulos, Verifique!!!");
            }
        } catch (Exception ex) {
            Logger.getLogger(FormCadFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        preencheTabela();
    }//GEN-LAST:event_btnGravarActionPerformed
public Funcionario preencheFuncionario (){
    if(edtNome.getText().isEmpty()||edtBairro.getText().isEmpty()||dataAdm.getDate()==null||edtEnd.getText().isEmpty()||edtNumero.getText().isEmpty()||edtSalario.getText().isEmpty()||edtSenha.getText().isEmpty()||edtUsuario.getText().isEmpty()){
    
return null;
    }
    else{
    Funcionario funcionario = new Funcionario();
    funcionario.setNome(edtNome.getText());
    funcionario.setEndereco(edtEnd.getText());
    funcionario.setBairro(edtBairro.getText());
    funcionario.setNumero(edtNumero.getText());
    funcionario.setPermissao(ComboPermissao.getSelectedItem().toString());
    funcionario.setSalario(Double.valueOf(edtSalario.getText()));
    funcionario.setSenha(edtSenha.getText());
    funcionario.setUsuario(edtUsuario.getText());
    if(dataDm.getDate()!=null){
     java.sql.Date dataSqlDm = new java.sql.Date(dataDm.getDate().getTime());
              funcionario.setDataDm(dataSqlDm);
    }
               java.sql.Date dataSqlAD = new java.sql.Date(dataAdm.getDate().getTime());
              funcionario.setDataAdm(dataSqlAD);
   funcionario.setTel(edtTel.getText());
    funcionario.setCpf(edtCPF.getText());
    funcionario.setRg(edtRG.getText());
    funcionario.setCepCidade(edtCep.getText());
   Cidade cidade = (Cidade) ComboCidade.getSelectedItem();
   funcionario.setCidade(cidade.getId());
   if(edtCodigo.getText().isEmpty()){
       
   }else{
       funcionario.setId(Integer.valueOf(edtCodigo.getText()));
   }
        System.out.println(cidade.getNome());
        System.out.println(cidade.getId());
            return funcionario;
    }
}
    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
if(btnIncluirStatus==0){//0 indica que nao foi clicado
           btnIncluirStatus=1;//1 indica que foi clicado
       }
        PanelGuia.setSelectedIndex(1);
        AbilidaOuDesabilitaBotoes();        // TODO add your handling code here:
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         if(btnIncluirStatus==1){
          int resposta= JOptionPane.showConfirmDialog(this, "Você esta no processo de inclusão de Funcionario, deseja cancelar?","Atenção!",JOptionPane.YES_NO_OPTION);
          if(resposta==JOptionPane.YES_OPTION){
              setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          }else{
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
          }
       }
    }//GEN-LAST:event_formWindowClosing

    private void PanelGuiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelGuiaMouseClicked
        AbilidaOuDesabilitaBotoes();
               preencheTabela();
                if(PanelGuia.getSelectedIndex()==1){
                    PanelGuia.setEnabled(false);
                }
    }//GEN-LAST:event_PanelGuiaMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpaCampos();
        JOptionPane.showMessageDialog(this,"Cadastro Cancelado");
        PanelGuia.setSelectedIndex(0);
        PanelGuia.setEnabled(true);
        btnAlterarStatus=0;
        btnIncluirStatus=0;
        AbilidaOuDesabilitaBotoes();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        if(btnAlterarStatus==0){//0 indica que nao foi clicado
           btnAlterarStatus=1;//1 indica que foi clicado
       }
        PanelGuia.setSelectedIndex(1);
        AbilidaOuDesabilitaBotoes();
        alterar();
       PanelGuia.setEnabled(false);
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void ComboCidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboCidadeItemStateChanged
        // TODO add your handling code here:
        Cidade cidade = (Cidade)ComboCidade.getSelectedItem();
        edtCep.setText(cidade.getCep());
    }//GEN-LAST:event_ComboCidadeItemStateChanged

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
            java.util.logging.Logger.getLogger(FormCadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCadFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboCidade;
    private javax.swing.JComboBox ComboPermissao;
    private javax.swing.JTabbedPane PanelGuia;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnIncluir;
    private com.toedter.calendar.JDateChooser dataAdm;
    private com.toedter.calendar.JDateChooser dataDm;
    private javax.swing.JTextField edtBairro;
    private javax.swing.JFormattedTextField edtCPF;
    private javax.swing.JFormattedTextField edtCep;
    private javax.swing.JTextField edtCodigo;
    private javax.swing.JTextField edtEnd;
    private javax.swing.JTextField edtNome;
    private javax.swing.JTextField edtNumero;
    private javax.swing.JFormattedTextField edtRG;
    private javax.swing.JFormattedTextField edtSalario;
    private javax.swing.JTextField edtSenha;
    private javax.swing.JFormattedTextField edtTel;
    private javax.swing.JTextField edtUsuario;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JTable tabelaFuncionario;
    // End of variables declaration//GEN-END:variables
}
