/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

import Conection.ClienteBD;
import Conection.FuncionarioBD;
import Conection.Itens_OsBD;
import Conection.OrdemServicoBD;
import Objetos.Cliente;
import Objetos.Funcionario;
import Objetos.Itens_Os;
import Objetos.OrdemServico;
import Objetos.Produto;
import Objetos.Servicos;
import ferramentas.TabelaItensOs;
import java.awt.AWTKeyStroke;
import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author user
 */
public class FormOrdemServico extends javax.swing.JFrame {

    TabelaItensOs model;
    List<Itens_Os> listOrdem = new ArrayList<>();
    OrdemServicoBD osBD = new OrdemServicoBD();
    int ultimaid = 1;
    Cliente cliente;
    Funcionario funcionario;
    int iditem = 0;
    OrdemServico os =null;
    Double ValorOS=0.0;

    /**
     * Creates new form FormOrdemServico
     */
    public FormOrdemServico() {
        initComponents();
        pegaDataAtual();
        considera();
        atalhos();
        bloquearCampos();
        listOrdem = new ArrayList<>();
        model = new TabelaItensOs(listOrdem);
        TabelaItensOs.setModel(model);
        try {
            ultimaid = osBD.getUltimaID();
            ultimaid++;
            //edtCodigo.setText(String.valueOf(ultimaid));
        } catch (Exception ex) {
            Logger.getLogger(FormOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClienteBD clientebd = new ClienteBD();
        FuncionarioBD funcionarioBd = new FuncionarioBD();
        try {
            AutoCompleteDecorator.decorate(edtCliente, clientebd.getLista(), false);
            AutoCompleteDecorator.decorate(edtTecnico, funcionarioBd.getListaFuncionario(), false);
        } catch (Exception ex) {
            Logger.getLogger(FormOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void atalhos() {
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "finalizar");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelar");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "incluir");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "excluir");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        this.getRootPane().getActionMap().put("finalizar", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {

                btnGravar.doClick();
            }
        });
        this.getRootPane().getActionMap().put("cancelar", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {

                btnCancelar.doClick();
            }
        });
        this.getRootPane().getActionMap().put("incluir", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {

                btnIncluir.doClick();
            }
        });
        this.getRootPane().getActionMap().put("excluir", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent e) {

                //excluirItem();
            }
        });

    }

    public void atualizaListas() {
        ClienteBD clientebd = new ClienteBD();
        FuncionarioBD funcionarioBd = new FuncionarioBD();
        try {
            AutoCompleteDecorator.decorate(edtCliente, clientebd.getLista(), false);
            AutoCompleteDecorator.decorate(edtTecnico, funcionarioBd.getListaFuncionario(), false);
            model = new TabelaItensOs(listOrdem);
            TabelaItensOs.setModel(model);
        } catch (Exception ex) {
            Logger.getLogger(FormOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public OrdemServico PreencheOs() {
        OrdemServico os = new OrdemServico();

        os.setCliente(edtCliente.getText());
        os.setFuncionario(edtTecnico.getText());
        os.setData_entrada(new java.sql.Timestamp(new java.util.Date().getTime()));
        os.setDescricao_equipamento(edtDescEquipamento.getText());
        os.setDescricao_problema(edtDefeitoEquipamento.getText());
        os.setObservacao(edtObservacao.getText());
        os.setStatus(ComboSituacaoOs.getSelectedItem().toString());
        os.setTipo_Atendimento(tipoAtendimento.getSelection().getActionCommand());
        if (rbGNossa.isSelected()) {
            os.setGarantia("nossa");
        } else if (rbGSem.isSelected()) {
            os.setGarantia("sem garantia");
        } else if (rbGTerceiros.isSelected()) {
            os.setGarantia("terceiros");
        }

        Double valor = 0.0;
        Iterator<Itens_Os> it = listOrdem.iterator();
        while (it.hasNext()) {
            Itens_Os item = it.next();
            valor += item.getValor();
        }
        os.setValor(valor);
        ValorOS=valor;
        if (edtCodigo.getText().isEmpty()) {

        } else {
            os.setId(Integer.valueOf(edtCodigo.getText()));
        }
        System.out.println("Ordem de serviço " + os);
        return os;
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
        tipoAtendimento = new javax.swing.ButtonGroup();
        garantia = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        edtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        edtTecnico = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        edtData = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        rbAtendimentoInterno = new javax.swing.JRadioButton();
        rbAtendimentoExterno = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        edtTelefone = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        rbGNossa = new javax.swing.JRadioButton();
        rbGSem = new javax.swing.JRadioButton();
        rbGTerceiros = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        edtDescEquipamento = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        edtDefeitoEquipamento = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        TabelaItensOs = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        edtObservacao = new javax.swing.JTextPane();
        ComboSituacaoOs = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        btnADDProdutos = new javax.swing.JButton();
        btnADDServicos = new javax.swing.JButton();
        btnExcluirItem = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        edtCliente = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnIncluir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnSelecionarOs = new javax.swing.JButton();
        btnFecharOs = new javax.swing.JButton();
        btnEntregarOs = new javax.swing.JButton();
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        jScrollPane1.setViewportView(jTextPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordem de Serviço");
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Código:");

        edtCodigo.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Cliente:");

        edtTecnico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtTecnicoKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Data:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Tecnico Responsavel");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Descrição do defeito aparente (Sintomas informados pelo cliente)");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Descrição do Equipamento");

        edtData.setEditable(false);
        edtData.setNextFocusableComponent(edtCliente);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de Atendimento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        tipoAtendimento.add(rbAtendimentoInterno);
        rbAtendimentoInterno.setSelected(true);
        rbAtendimentoInterno.setText("Interno - Recebido no balcão");
        rbAtendimentoInterno.setActionCommand("Interno");

        tipoAtendimento.add(rbAtendimentoExterno);
        rbAtendimentoExterno.setText("Externo - Deslocamento ao local");
        rbAtendimentoExterno.setActionCommand("Externo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbAtendimentoInterno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbAtendimentoExterno, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbAtendimentoInterno)
                    .addComponent(rbAtendimentoExterno)))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Telefone:");

        edtTelefone.setNextFocusableComponent(edtTecnico);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Equipamento em Garantia")));

        buttonGroup1.add(rbGNossa);
        rbGNossa.setText("Nossa");
        rbGNossa.setName("Nossa"); // NOI18N

        buttonGroup1.add(rbGSem);
        rbGSem.setSelected(true);
        rbGSem.setText("Sem Garantia");
        rbGSem.setName("Sem Garantia"); // NOI18N

        buttonGroup1.add(rbGTerceiros);
        rbGTerceiros.setText("Terceiros");
        rbGTerceiros.setName("Terceiros"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbGNossa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbGSem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbGTerceiros)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rbGNossa)
                .addComponent(rbGSem)
                .addComponent(rbGTerceiros))
        );

        jScrollPane2.setViewportView(edtDescEquipamento);

        jScrollPane3.setViewportView(edtDefeitoEquipamento);

        TabelaItensOs.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(TabelaItensOs);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Produtos E Serviços incluidos");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Observação");

        jScrollPane5.setViewportView(edtObservacao);

        ComboSituacaoOs.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aberta - Em analise", "Aberta - Aguardando Aprovação", "Aprovada - Em Execução", "Fechada - Aguardando Retirada do Cliente", "Entregue" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Situação Atual");

        btnADDProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/produto_1.png"))); // NOI18N
        btnADDProdutos.setText("Produtos");
        btnADDProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDProdutosActionPerformed(evt);
            }
        });

        btnADDServicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/serviços.png"))); // NOI18N
        btnADDServicos.setText("Serviços");
        btnADDServicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnADDServicosActionPerformed(evt);
            }
        });

        btnExcluirItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        btnExcluirItem.setText("Excluir");
        btnExcluirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirItemActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel11.setText("Pressione  F8 para selecionar Cliente");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel12.setText(" F8 para selecionar Tecnico");

        edtCliente.setNextFocusableComponent(edtTelefone);
        edtCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edtClienteFocusLost(evt);
            }
        });
        edtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edtClienteKeyReleased(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/adicionar.png"))); // NOI18N
        btnIncluir.setText("Incluir (F2)");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnSelecionarOs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/busca.png"))); // NOI18N
        btnSelecionarOs.setText("Selecionar");
        btnSelecionarOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarOsActionPerformed(evt);
            }
        });

        btnFecharOs.setText("Fechamento");
        btnFecharOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharOsActionPerformed(evt);
            }
        });

        btnEntregarOs.setText("Entrega");
        btnEntregarOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregarOsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSelecionarOs, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFecharOs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEntregarOs, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnEntregarOs, btnFecharOs});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnIncluir)
                                .addComponent(btnSelecionarOs))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEntregarOs)
                        .addComponent(btnFecharOs, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnEntregarOs, btnFecharOs, btnSelecionarOs});

        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/confirma.png"))); // NOI18N
        btnGravar.setText("Gravar (F12)");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar(ESC)");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(0, 0, 0)
                                            .addComponent(jLabel12))
                                        .addComponent(edtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(5, 5, 5)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel6)
                                .addComponent(jLabel5)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(edtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(edtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
                                .addComponent(jScrollPane3)
                                .addComponent(jScrollPane2)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(edtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel1))
                                            .addGap(27, 27, 27)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(edtData, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(3, 3, 3)
                                            .addComponent(jLabel11)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                    .addComponent(btnGravar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnADDProdutos)
                                .addGap(5, 5, 5)
                                .addComponent(btnADDServicos)
                                .addGap(5, 5, 5)
                                .addComponent(btnExcluirItem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboSituacaoOs, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel11))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(edtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(edtCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel12))
                        .addComponent(edtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel7)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnADDProdutos)
                    .addComponent(btnADDServicos)
                    .addComponent(btnExcluirItem)
                    .addComponent(jLabel10)
                    .addComponent(ComboSituacaoOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel9)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGravar)
                        .addGap(2, 2, 2)
                        .addComponent(btnCancelar)))
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnADDProdutos, btnADDServicos, btnExcluirItem});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void edtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtClienteKeyReleased

    }//GEN-LAST:event_edtClienteKeyReleased

    private void edtClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtClienteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F8 || KeyEvent.VK_ENTER == evt.getKeyCode()) {
            FormSelecionaCliente form = new FormSelecionaCliente(this);
            form.setVisible(true);
        }
    }//GEN-LAST:event_edtClienteKeyPressed

    private void edtClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edtClienteFocusLost
        ClienteBD bd = new ClienteBD();

        Cliente client = bd.getClienteByNome(edtCliente.getText());
        if (client.getTelefone() != null && client.getTelefone() != "") {
            edtTelefone.setText(client.getTelefone());
        } else {
            JOptionPane.showMessageDialog(this, "Cliente " + edtCliente.getText() + " não encontrado, utilize Tecle F8 ou ENTER para selecionar um cliente");
        }
    }//GEN-LAST:event_edtClienteFocusLost

    private void edtTecnicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtTecnicoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F8 || KeyEvent.VK_ENTER == evt.getKeyCode()) {
            FormSelecionaFuncionario form = new FormSelecionaFuncionario(this);
            form.setVisible(true);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_edtTecnicoKeyPressed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
       // TODO add your handling code here:
        if (edtCodigo.getText().isEmpty()) {
            salvar();
            if(os.getStatus().equals("Fechada - Aguardando Retirada do Cliente")){
            bloquearCampos();
            btnEntregarOs.setEnabled(true);
        }else if(os.getStatus().equals("Entregue")){
            //JOptionPane.showMessageDialog(this, "Ordem de serviço ja Entregue!!");
            bloquearCampos();
        }
        } else {
            atualiza();
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void salvar() {
        OrdemServicoBD bd = new OrdemServicoBD();
        Itens_OsBD itensBD = new Itens_OsBD();
        OrdemServico os = PreencheOs();
        try {
            if(os.getStatus().equals("Entregue")){
                int resposta = JOptionPane.showConfirmDialog(this, "Confirma Entrega? ", "Confirma entrega da OS?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resposta == JOptionPane.YES_OPTION) {
            ComboSituacaoOs.setSelectedIndex(4);
           // atualiza();
            // JOptionPane.showMessageDialog(this, "Ordem de serviço fechada com sucesso.");
            bloquearCampos();
            FormFinalizaOS form = new FormFinalizaOS(listOrdem, ValorOS, ultimaid,os);
            form.setVisible(true);
            this.dispose();
        }
            }else{
            ultimaid = bd.adicionarOrdemServico(os);
            JOptionPane.showMessageDialog(this, "Ordem de Serviço gravada com sucesso!");
            Iterator<Itens_Os> it = listOrdem.iterator();
            while (it.hasNext()) {
                Itens_Os item = it.next();
                item.setId_os(ultimaid);
                itensBD.adicionarItem(item);

            }
            this.os=os;
            edtCodigo.setText(String.valueOf(ultimaid));
            }
        } catch (Exception ex) {
            Logger.getLogger(FormOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erro ao gravar ordem de servico! " + ex);

        }
    }
     public static void considerarEnterComoTab (Component comp) {
		Set<AWTKeyStroke> keystrokes = comp.getFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS);
		Set<AWTKeyStroke> newKeystrokes = new HashSet<AWTKeyStroke> (keystrokes);
		newKeystrokes.add (AWTKeyStroke.getAWTKeyStroke(KeyEvent.VK_ENTER, 0));
		comp.setFocusTraversalKeys (KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, newKeystrokes); 
	}

    private void atualiza() {
        OrdemServicoBD bd = new OrdemServicoBD();
        Itens_OsBD itensBD = new Itens_OsBD();
        OrdemServico os = PreencheOs();
        try {
             if(os.getStatus().equals("Entregue")){
                int resposta = JOptionPane.showConfirmDialog(this, "Confirma Entrega? ", "Confirma entrega da OS?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resposta == JOptionPane.YES_OPTION) {
            ComboSituacaoOs.setSelectedIndex(4);
           // atualiza();
            // JOptionPane.showMessageDialog(this, "Ordem de serviço fechada com sucesso.");
            bloquearCampos();
            FormFinalizaOS form = new FormFinalizaOS(listOrdem, ValorOS, ultimaid,os);
            form.setVisible(true);
            this.dispose();
        }
            }else{
            bd.AtualizaOrdemServico(os);
            Iterator<Itens_Os> it = listOrdem.iterator();
            itensBD.removerItemByIdOs(ultimaid);
            while (it.hasNext()) {
                Itens_Os item = it.next();
                item.setId_os(ultimaid);
                itensBD.adicionarItem(item);

            }
            JOptionPane.showMessageDialog(this, "Ordem de Serviço modificada com sucesso!");
this.os=os;
             }
        } catch (Exception ex) {
            Logger.getLogger(FormOrdemServico.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erro ao gravar ordem de servico! " + ex);
        }
        
    }
    private void btnADDProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDProdutosActionPerformed
        FormSelecionaProduto form = new FormSelecionaProduto(this);
        form.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_btnADDProdutosActionPerformed

    private void btnADDServicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnADDServicosActionPerformed
        FormSelecionaServico form = new FormSelecionaServico(this);
        form.setVisible(true);
    }//GEN-LAST:event_btnADDServicosActionPerformed

    private void btnSelecionarOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarOsActionPerformed
        FormSelecionaOS form = new FormSelecionaOS(this);
        form.setVisible(true);
    }//GEN-LAST:event_btnSelecionarOsActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limparCampos();
        listOrdem.clear();
        atualizaListas();
        bloquearCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnFecharOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharOsActionPerformed
        if(os!=null){
        if(os.getStatus().equals("Fechada - Aguardando Retirada do Cliente")){
             JOptionPane.showMessageDialog(this, "Ordem de serviço ja Fechada!!");
        }else if(os.getStatus().equals("Entregue")){
            JOptionPane.showMessageDialog(this, "Ordem de serviço ja Entregue!!");
        }else{
            
        int resposta = JOptionPane.showConfirmDialog(this, "Após o fechamento não é possivel modificar as informações! ", "Confirma fechamento da OS?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resposta == JOptionPane.YES_OPTION) {
            ComboSituacaoOs.setSelectedIndex(3);
            atualiza();
            // JOptionPane.showMessageDialog(this, "Ordem de serviço fechada com sucesso.");
            bloquearCampos();
            btnEntregarOs.setEnabled(true);
        }
        }
        }else{
            JOptionPane.showMessageDialog(this, "Nenhuma OS selecionada ou criada para fechar!");
        }
// TODO add your handling code here:
    }//GEN-LAST:event_btnFecharOsActionPerformed

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
        desbloquearCampos();
        limparCampos();
        edtCliente.requestFocus();
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void btnEntregarOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregarOsActionPerformed
      
           if(os!=null){
        if(os.getStatus().equals("Entregue")){
            JOptionPane.showMessageDialog(this, "Ordem de serviço ja Entregue!!");
        }else{
        int resposta = JOptionPane.showConfirmDialog(this, "Confirma Entrega? ", "Confirma entrega da OS?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resposta == JOptionPane.YES_OPTION) {
            ComboSituacaoOs.setSelectedIndex(4);
           // atualiza();
            // JOptionPane.showMessageDialog(this, "Ordem de serviço fechada com sucesso.");
            bloquearCampos();
            FormFinalizaOS form = new FormFinalizaOS(listOrdem, ValorOS, ultimaid,os);
            form.setVisible(true);
        }
       }
           }
        else{
            JOptionPane.showMessageDialog(this, "Nenhuma OS selecionada ou criada para entregar!");
        }
    }//GEN-LAST:event_btnEntregarOsActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        atualizaListas();
    }//GEN-LAST:event_formWindowGainedFocus

    private void btnExcluirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirItemActionPerformed
        // TODO add your handling code here:
        excluir();
    }//GEN-LAST:event_btnExcluirItemActionPerformed

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
            java.util.logging.Logger.getLogger(FormOrdemServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormOrdemServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormOrdemServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormOrdemServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormOrdemServico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboSituacaoOs;
    private javax.swing.JTable TabelaItensOs;
    private javax.swing.JButton btnADDProdutos;
    private javax.swing.JButton btnADDServicos;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEntregarOs;
    private javax.swing.JButton btnExcluirItem;
    private javax.swing.JButton btnFecharOs;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnSelecionarOs;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField edtCliente;
    private javax.swing.JTextField edtCodigo;
    private javax.swing.JFormattedTextField edtData;
    private javax.swing.JTextPane edtDefeitoEquipamento;
    private javax.swing.JTextPane edtDescEquipamento;
    private javax.swing.JTextPane edtObservacao;
    private javax.swing.JTextField edtTecnico;
    private javax.swing.JFormattedTextField edtTelefone;
    private javax.swing.ButtonGroup garantia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JRadioButton rbAtendimentoExterno;
    private javax.swing.JRadioButton rbAtendimentoInterno;
    private javax.swing.JRadioButton rbGNossa;
    private javax.swing.JRadioButton rbGSem;
    private javax.swing.JRadioButton rbGTerceiros;
    private javax.swing.ButtonGroup tipoAtendimento;
    // End of variables declaration//GEN-END:variables

    void retornaCliente(Cliente cliente) {
        this.cliente = cliente;
        edtCliente.setText(cliente.getNome());
        edtTelefone.setText(cliente.getTelefone());
    }

    void retornaFuncionario(Funcionario cliente) {
        atualizaListas();
        this.funcionario = cliente;
        edtTecnico.setText(cliente.getNome());
    }

    void recuperaProduto(Produto produto) {

        Itens_Os item = new Itens_Os();
        item.setDesc(produto.getDescricao());
        item.setId_os(ultimaid);
        item.setId_produto(produto.getId());
        item.setValor(produto.getPreco_venda());
        listOrdem.add(item);
        atualizaListas();

    }

    void retornaServicos(Servicos servico) {
        Itens_Os item = new Itens_Os();
        item.setDesc(servico.getDescricao());
        item.setId_os(ultimaid);
        //item.setId_produto(produto.getId());
        item.setValor(servico.getValor());
        listOrdem.add(item);
        atualizaListas();
    }

    void retornaOrdemServico(OrdemServico os) {
this.os=os;
        if (os.getStatus().equals("Fechada - Aguardando Retirada do Cliente")) {
            bloquearCampos();
            JOptionPane.showMessageDialog(this, "Ordem de serviço ja fechada, não é permitida a edição.");
            btnEntregarOs.setEnabled(true);
        } else if(os.getStatus().equals("Entregue")) {
            bloquearCampos();
            JOptionPane.showMessageDialog(this, "Ordem de serviço ja entregue, não é permitida a edição.");
            btnEntregarOs.setEnabled(false);
        }else{
            desbloquearCampos();
        }
        //TODO IMPLEMENTAR RETORNO DE SELEÇÃO DE OS
        edtCodigo.setText(String.valueOf(os.getId()));
        edtCliente.setText(os.getCliente());
        edtTecnico.setText(os.getFuncionario());
        edtDefeitoEquipamento.setText(os.getDescricao_problema());
        edtDescEquipamento.setText(os.getDescricao_equipamento());
        edtObservacao.setText(os.getObservacao());
        DateFormat f = DateFormat.getDateInstance(DateFormat.MEDIUM);
        edtData.setText(f.format(os.getData_entrada()));
        ComboSituacaoOs.setSelectedItem(os.getStatus());
        if (os.getTipo_Atendimento().equals("interno")) {
            rbAtendimentoInterno.setSelected(true);
        } else {
            rbAtendimentoExterno.setSelected(true);
        }
        if (os.getGarantia().equals("nossa")) {
            rbGNossa.setSelected(true);
        } else if (os.getGarantia().equals("terceiros")) {
            rbGTerceiros.setSelected(true);
        } else {
            rbGSem.setSelected(true);
        }
        Itens_OsBD itensbd = new Itens_OsBD();
        listOrdem = itensbd.getItemByIdOS(os.getId());
        ultimaid = os.getId();
        ValorOS=os.getValor();
        atualizaListas();
    }
    public void excluir(){
        int linhaSelecionada = -1;
        Cliente cliente;
        linhaSelecionada = TabelaItensOs.getSelectedRow();
        if (linhaSelecionada >= 0) { 
            listOrdem.remove(linhaSelecionada);
            atualizaListas();
            
    }else{
            JOptionPane.showMessageDialog(this, "Selecione um produto ou serviço para excluir");
        }                                          
    }

   public void limparCampos() {
        edtCodigo.setText("");
        edtCliente.setText("");
        edtTecnico.setText("");
        edtDefeitoEquipamento.setText("");
        edtDescEquipamento.setText("");
        edtObservacao.setText("");
        edtTelefone.setText("");
        edtData.setText(pegaDataAtual());
        ComboSituacaoOs.setSelectedIndex(0);
        rbGSem.setSelected(true);
        rbAtendimentoInterno.setSelected(true);
        listOrdem.clear();
        atualizaListas();
    }
   private void considera(){
       considerarEnterComoTab(edtCodigo);
       considerarEnterComoTab(edtCliente);
       considerarEnterComoTab(edtTecnico);
       considerarEnterComoTab(edtDefeitoEquipamento);
       considerarEnterComoTab(edtDescEquipamento);
       considerarEnterComoTab(edtObservacao);
       considerarEnterComoTab(edtTelefone);
       considerarEnterComoTab(edtData);
       considerarEnterComoTab(edtCodigo);
       considerarEnterComoTab(TabelaItensOs);
       considerarEnterComoTab(rbAtendimentoExterno);
       considerarEnterComoTab(rbAtendimentoInterno);
       considerarEnterComoTab(rbGNossa);
       considerarEnterComoTab(rbGSem);
       considerarEnterComoTab(rbGTerceiros);
       considerarEnterComoTab(btnADDProdutos);
       considerarEnterComoTab(btnADDServicos);
       considerarEnterComoTab(ComboSituacaoOs);
       considerarEnterComoTab(btnFecharOs);
       considerarEnterComoTab(btnExcluirItem);
       considerarEnterComoTab(btnIncluir);
       considerarEnterComoTab(btnSelecionarOs);
       considerarEnterComoTab(btnGravar);
       considerarEnterComoTab(btnCancelar);
       considerarEnterComoTab(btnEntregarOs);
//       considerarEnterComoTab(btnExcluir1);
  //     considerarEnterComoTab(btnAlterar1);
   }

    private void bloquearCampos() {
        edtCodigo.setEnabled(false);
        edtCliente.setEnabled(false);
        edtTecnico.setEnabled(false);
        edtDefeitoEquipamento.setEnabled(false);
        edtDescEquipamento.setEnabled(false);
        edtObservacao.setEnabled(false);
        edtTelefone.setEnabled(false);
        edtData.setEnabled(false);

        rbAtendimentoExterno.setEnabled(false);
        rbAtendimentoInterno.setEnabled(false);
        rbGNossa.setEnabled(false);
        rbGSem.setEnabled(false);
        rbGTerceiros.setEnabled(false);
        btnADDProdutos.setEnabled(false);
        btnADDServicos.setEnabled(false);
        btnExcluirItem.setEnabled(false);
        btnGravar.setEnabled(false);
        btnCancelar.setEnabled(false);
//        btnAlterar1.setEnabled(false);
  //      btnExcluir1.setEnabled(false);
        btnFecharOs.setEnabled(false);
        btnEntregarOs.setEnabled(false);
        TabelaItensOs.setEnabled(false);
        ComboSituacaoOs.setEnabled(false);

    }

    public void desbloquearCampos() {
        edtCodigo.setEnabled(true);
//        btnAlterar1.setEnabled(true);
        btnGravar.setEnabled(true);
        btnCancelar.setEnabled(true);
  //      btnExcluir1.setEnabled(true);
        btnFecharOs.setEnabled(true);
        btnEntregarOs.setEnabled(true);
        edtCliente.setEnabled(true);
        edtTecnico.setEnabled(true);
        edtDefeitoEquipamento.setEnabled(true);
        edtDescEquipamento.setEnabled(true);
        edtObservacao.setEnabled(true);
        edtTelefone.setEnabled(true);
        edtData.setEnabled(true);

        rbAtendimentoExterno.setEnabled(true);
        rbAtendimentoInterno.setEnabled(true);
        rbGNossa.setEnabled(true);
        rbGSem.setEnabled(true);
        rbGTerceiros.setEnabled(true);
        btnADDProdutos.setEnabled(true);
        btnADDServicos.setEnabled(true);
        btnExcluirItem.setEnabled(true);
        TabelaItensOs.setEnabled(true);
        ComboSituacaoOs.setEnabled(true);

    }
}
