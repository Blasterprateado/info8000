/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fronteira;

import Conection.CaixaBD;
import Conection.CaixaDiarioBD;
import Conection.ConectionSingleton;
import Conection.ItensVendaBD;
import Conection.LancamentoCaixaBD;
import Conection.ParcelamentoBD;
import Conection.ProdutoBD;
import Conection.VendaBD;
import Objetos.Caixa;
import Objetos.CaixaDiario;
import Objetos.Cliente;
import Objetos.Funcionario;
import Objetos.ItensVenda;
import Objetos.LancamentoCaixa;
import Objetos.Parcelamento;
import Objetos.Produto;
import Objetos.Venda;
import ferramentas.TabelaQtdParcelas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class FormFinalizaVenda extends javax.swing.JFrame {

    List<ItensVenda> itens;
    double valorVenda = 0.0, desconto = 0.0, valorRecebido = 0.0, troco = 0.0, valorAReceber = 0.0;
    VendaBD vendaBd = new VendaBD();
    ProdutoBD produtoBd = new ProdutoBD();
    ItensVendaBD itensBd = new ItensVendaBD();
    Venda venda;
    List<Parcelamento> lista = new ArrayList<>();
    Cliente cliente;
    Funcionario vendedor;
    int ultimaid;

    /**
     * Creates new form FormFinalizaVenda
     */
    public FormFinalizaVenda() {
        initComponents();

        TabelaQtdParcelas model = new TabelaQtdParcelas(lista);
        comboParcelamento.setEnabled(false);
        tableParcelamento.setModel(model);
    }

    private void preencheTabela(List<Parcelamento> list) {
        TabelaQtdParcelas model = new TabelaQtdParcelas(list);
        tableParcelamento.setModel(model);
        this.lista = list;

    }

    public FormFinalizaVenda(List<ItensVenda> listaItens, Double valorVenda, Integer numeroVenda) {
        initComponents();
        this.itens = listaItens;
        this.ultimaid = numeroVenda;

        TabelaQtdParcelas model = new TabelaQtdParcelas(lista);
        tableParcelamento.setModel(model);
        this.valorVenda = valorVenda;
        valorAReceber = valorVenda;
        comboParcelamento.setEnabled(false);
        edtValorDaVenda.setText("R$ " + valorVenda.toString());
        labelNumeroVenda.setText(numeroVenda.toString());
        edtDesconto.setText(String.valueOf(desconto));
//         edtValorAReceber.setText(String.valueOf(valorVenda));
        atalhos();
    }

    public FormFinalizaVenda(List<ItensVenda> listaItens, Double valorVenda, Integer numeroVenda, Venda venda, Cliente cliente, Funcionario funcionario) {
        initComponents();
        this.itens = listaItens;
        comboParcelamento.setEnabled(false);
        this.cliente = cliente;
        this.vendedor = funcionario;
        this.ultimaid = numeroVenda;
        List<Parcelamento> lista = new ArrayList<>();
        TabelaQtdParcelas model = new TabelaQtdParcelas(lista);
        tableParcelamento.setModel(model);
        this.valorVenda = valorVenda;
        this.venda = venda;
        valorAReceber = valorVenda;
        edtValorDaVenda.setText("R$ " + valorVenda.toString());
        labelNumeroVenda.setText(numeroVenda.toString());
        edtDesconto.setText(String.valueOf(desconto));
//         edtValorAReceber.setText(String.valueOf(valorVenda));
        atalhos();
    }

    public int verifica() {
        CaixaDiarioBD bd = new CaixaDiarioBD();
        CaixaBD caixabd = new CaixaBD();
        Caixa caixa = new Caixa();
        try {
            caixa = caixabd.getUltimoCaixaById(0);
            int ultimoidCaixa = caixa.getId();
            if (caixa.getStatus().equals("FECHADO")) {
                return 2;
            } else if (caixa.getStatus().equals("ABERTO")) {
                return 1;
            }
        } catch (Exception ex) {
            Logger.getLogger(FormCaixaDiario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erro Ao Obter Dados " + ex);
        }
        return 0;

    }

    public void lancaEmCaixa(int idVenda) {
        int resposta = verifica();
        if (resposta == 1) {
            LancamentoCaixa lancamento = new LancamentoCaixa();
            LancamentoCaixaBD bd = new LancamentoCaixaBD();
            CaixaDiario caixadiario = new CaixaDiario();
            CaixaDiarioBD diariobd = new CaixaDiarioBD();

            lancamento.setTipo("Receitas diversas");
            lancamento.setId_venda(idVenda);

            caixadiario.setTipolancamento("Receitas diversas");
            lancamento.setObs("Venda");

            caixadiario.setValor(valorAReceber);
            lancamento.setValor(valorAReceber);

            try {
                bd.adicionarlancamentoCaixa(lancamento);
                diariobd.adicionarClasseCaixa(caixadiario);
            } catch (Exception ex) {
                Logger.getLogger(FormFinalizaVenda.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Erro ao realizar lancamento " + ex);
            }

            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Caixa Fechado Abra-o");
        }

    }

    public final void atalhos() {
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), "gravar");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "continuar");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        this.getRootPane().getActionMap().put("gravar", new AbstractAction() {
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
                btnContinuar.doClick();
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        edtValorDaVenda = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        labelNumeroVenda = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        edtValorEmDinheiro = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        edtTroco = new javax.swing.JTextField();
        btnContinuar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        edtDesconto = new javax.swing.JFormattedTextField();
        rbDinheiro = new javax.swing.JRadioButton();
        rbCartao = new javax.swing.JRadioButton();
        rbCheque = new javax.swing.JRadioButton();
        rbNotinha = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        comboParcelamento = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableParcelamento = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        rb30Dias = new javax.swing.JRadioButton();
        rb15Dias = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Finalizar Venda");
        setBackground(new java.awt.Color(153, 153, 153));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Finalizar Venda");

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Total a pagar");

        edtValorDaVenda.setEditable(false);
        edtValorDaVenda.setBackground(new java.awt.Color(255, 229, 149));
        edtValorDaVenda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Venda número :");

        labelNumeroVenda.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelNumeroVenda.setText("Numero da venda");

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

        btnContinuar.setText("Continuar essa venda (ESC)");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        btnFinalizar.setText("Finalizar Venda (F12)");
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

        rbDinheiro.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(rbDinheiro);
        rbDinheiro.setSelected(true);
        rbDinheiro.setText("Dinheiro");
        rbDinheiro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbDinheiroItemStateChanged(evt);
            }
        });

        rbCartao.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(rbCartao);
        rbCartao.setText("Cartão");
        rbCartao.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbCartaoItemStateChanged(evt);
            }
        });

        rbCheque.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(rbCheque);
        rbCheque.setText("Cheque");
        rbCheque.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbChequeItemStateChanged(evt);
            }
        });

        rbNotinha.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(rbNotinha);
        rbNotinha.setText("Notinha");
        rbNotinha.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbNotinhaItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Forma Recebimento");

        comboParcelamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A Vista", "2  vezes", "3  vezes", "4  vezes", "5  vezes", "6  vezes", "7  vezes", "8  vezes", "9  vezes", "10 vezes" }));
        comboParcelamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboParcelamentoItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Quantidade De Parcelas");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Especificação de parcelas");

        tableParcelamento.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableParcelamento);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Intervalo Parcelamento");

        rb30Dias.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup2.add(rb30Dias);
        rb30Dias.setSelected(true);
        rb30Dias.setText("30 Dias");

        rb15Dias.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup2.add(rb15Dias);
        rb15Dias.setText("15 Dias");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel14))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(edtValorEmDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNumeroVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(comboParcelamento, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(edtValorDaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(edtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(rbDinheiro)
                            .addGap(28, 28, 28)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(rbCartao)
                                    .addGap(33, 33, 33)
                                    .addComponent(rbCheque))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(jLabel5)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbNotinha))
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(rb30Dias)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(rb15Dias))
                                .addComponent(jLabel6))
                            .addGap(123, 123, 123)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnContinuar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelNumeroVenda))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtValorDaVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbDinheiro)
                    .addComponent(rbCartao)
                    .addComponent(rbCheque)
                    .addComponent(rbNotinha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb30Dias)
                    .addComponent(rb15Dias))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(0, 0, 0)
                .addComponent(comboParcelamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtValorEmDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar)
                    .addComponent(btnFinalizar))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {edtDesconto, edtTroco, edtValorDaVenda, edtValorEmDinheiro});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (edtDesconto.getText().isEmpty() || edtDesconto.getText() == "") {
                edtDesconto.setText("0");
                desconto = Double.valueOf(edtDesconto.getText());
                Double Valor = valorVenda - desconto;
                valorAReceber = Valor;
                //edtValorAReceber.setText(Valor.toString());
            } else {
                desconto = Double.valueOf(edtDesconto.getText());
                Double Valor = valorVenda - desconto;
                //edtValorAReceber.setText(Valor.toString());
                valorAReceber = Valor;
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_edtDescontoKeyReleased
    public final java.sql.Date pegaDataAtual() {
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

    public void relatorio(int id) {

        InputStream inputStream = getClass().getResourceAsStream("/Relatorios/RelatorioVendaFiltro.jasper");
        Map parametros = new HashMap();
        parametros.put("id", id);

        try {
            ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
            Connection connection = conexaosingleton.conectar();

            // abre o relatório
            //ReportUtils.openReport("VENDA", inputStream, parametros,
            //  connection);
            FormProgressDialog pro = new FormProgressDialog("Venda", inputStream, parametros,
                    connection);
            pro.setVisible(true);

        } catch (SQLException exc) {
            exc.printStackTrace();
            //JOptionPane.showMessageDialog(this, "erro" +exc+edtObservacao.getText());
        } catch (JRException exc) {
            exc.printStackTrace();
            JOptionPane.showMessageDialog(this, "erro" + exc);
        } catch (Exception ex) {
//            Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "erro" + ex);
        }
    }
    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        int i = verifica();

        int idVenda = 0;
        ParcelamentoBD parcelamentoBD = new ParcelamentoBD();

        if (i == 1) {// testar se caixa esta aberto

            Double qtdEstoqueProduto;
            Iterator<ItensVenda> it = itens.iterator();

            if (this.venda == null) {// testar se a venda esta vindo de uma venda rapida
                Venda vend = new Venda();
                vend.setCaixa("Venda Rapida");
                vend.setCliente("Venda Rapida");
                vend.setFuncionario("Venda Rapida");
                vend.setValor_venda(valorAReceber);
                venda.setSituacao(0);//  0 representa venda normal  // 1 representa condicional
                vend.setData_venda(pegaDataAtual());
                this.venda = vend;

            } else if (venda.getId() != 0) {// caso esteja editando uma venda
                try {
                    vendaBd.AtualizaVenda(venda);
                    JOptionPane.showMessageDialog(this, "Venda Atualizada com Sucesso.");
                } catch (Exception ex) {
                    Logger.getLogger(FormFinalizaVenda.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Erro ao Atualizar Venda " + ex);
                }

                try {//devolver estoque Produtos

                    List<ItensVenda> listaDevolverEstoque = itensBd.getListaItensVendaByIdVenda(venda.getId());

                    Iterator<ItensVenda> iterator = listaDevolverEstoque.iterator();
                    while (iterator.hasNext()) {
                        ItensVenda itensVenda = iterator.next();
                        int qtd = itensVenda.getQtd();
                        Produto produto = produtoBd.getProdutoById(itensVenda.getId_produto());
                        qtdEstoqueProduto = produto.getQtd_estoque();
                        qtdEstoqueProduto += qtd;
                        produto.setQtd_estoque(qtdEstoqueProduto);
                        produtoBd.AtualizaProduto(produto);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(FormFinalizaVenda.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Erro ao devolver estoques produtos venda " + ex);
                }
                // devolver estoque produtos

                itensBd.removerItemByIdVenda(venda.getId());//remover itens da venda;

                while (it.hasNext()) {//reinserir itens na venda
                    ItensVenda itensVenda = it.next();
                    int qtd = itensVenda.getQtd();
                    Produto produto = produtoBd.getProdutoById(itensVenda.getId_produto());
                    qtdEstoqueProduto = produto.getQtd_estoque();
                    qtdEstoqueProduto -= qtd;
                    produto.setQtd_estoque(qtdEstoqueProduto);
                    itensVenda.setId_venda(venda.getId());
                    try {

                        itensBd.adicionarItem(itensVenda);
                        produtoBd.AtualizaProduto(produto);

                    } catch (Exception ex) {
                        Logger.getLogger(FormFinalizaVenda.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, "Erro ao finalizar venda " + ex);

                    }

                }
                //buscar parcelamentos da venda;

                parcelamentoBD.removerParcelamentoByIdVenda(venda.getId());// apagar
                LancamentoCaixaBD lancamentoCaixaBD = new LancamentoCaixaBD();
                if (venda.getSituacao() == 0) {//testa se é uma venda normal, porque se nao for, nao da entrada em financeiro.
                    Iterator<Parcelamento> itParcelamento = lista.iterator();

                    while (itParcelamento.hasNext()) { //inserir novamente
                        Parcelamento par = itParcelamento.next();
                        par.setId_venda(venda.getId());
                    }
                    parcelamentoBD.AdicionaListaParcelamento(lista);//gravar no banco

                    if (venda.getId() != 0) {
                        if (rbDinheiro.isSelected()) {
                            lancaEmCaixa(venda.getId());
                        }

                        relatorio(venda.getId());

                        //JOptionPane.showMessageDialog(this, "Sucesso ao realizar Venda");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao realizar Venda");
                    }
                } else if (venda.getSituacao() == 1) {

                    relatorioCondicional(venda);

                }

                this.dispose();

            }//caso esteja editando uma venda;
            else {//caso esteja adicionando uma venda

                try {

                    idVenda = vendaBd.adicionarVenda(venda);

                    if (venda.getSituacao() == 0) {
                        Iterator<Parcelamento> itParcelamento = lista.iterator();

                        while (itParcelamento.hasNext()) { //atualizar
                            Parcelamento par = itParcelamento.next();
                            par.setId_venda(idVenda);
                        }
                        parcelamentoBD.AdicionaListaParcelamento(lista);//gravar no banco

                    }

                } catch (Exception ex) {
                    Logger.getLogger(FormFinalizaVenda.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(this, "Erro ao finalizar venda " + ex);
                }

                while (it.hasNext()) {//percorrer lista, dar baixa nos estoques e gravar os itens da venda;
                    ItensVenda itensVenda = it.next();
                    int qtd = itensVenda.getQtd();
                    Produto produto = produtoBd.getProdutoById(itensVenda.getId_produto());
                    qtdEstoqueProduto = produto.getQtd_estoque();
                    qtdEstoqueProduto -= qtd;
                    produto.setQtd_estoque(qtdEstoqueProduto);
                    itensVenda.setId_venda(idVenda);
                    try {

                        itensBd.adicionarItem(itensVenda);
                        produtoBd.AtualizaProduto(produto);

                    } catch (Exception ex) {
                        Logger.getLogger(FormFinalizaVenda.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, "Erro ao finalizar venda " + ex);

                    }
                }

                if (venda.getSituacao() == 0) {
                    if (idVenda != 0) {
                        if (rbDinheiro.isSelected()) {
                            lancaEmCaixa(idVenda);
                        }
                        relatorio(idVenda);
                        //JOptionPane.showMessageDialog(this, "Sucesso ao realizar Venda");
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao realizar Venda");
                    }
                } else {
                    relatorioCondicional(venda);
                }

            }
        } else if (i == 2) {
            JOptionPane.showMessageDialog(this, "Caixa Fechado!!  Abra-o");
            FormCaixaDiario form = new FormCaixaDiario();
            form.setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void edtValorEmDinheiroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtValorEmDinheiroKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (edtValorEmDinheiro.getText().isEmpty() || edtValorEmDinheiro.getText() == "") {
                edtValorEmDinheiro.setText("0");
                valorRecebido = 0;
                troco = valorRecebido - valorAReceber;
                edtTroco.setText(String.valueOf("R$ " + troco));
            } else {
                valorRecebido = Double.valueOf(edtValorEmDinheiro.getText());
                troco = valorRecebido - valorAReceber;
                edtTroco.setText(String.valueOf("R$ " + troco));
            }
        }
    }//GEN-LAST:event_edtValorEmDinheiroKeyReleased

    private void edtValorEmDinheiroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtValorEmDinheiroKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtValorEmDinheiroKeyPressed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void edtDescontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edtDescontoFocusLost
        if (edtDesconto.getText().isEmpty() || edtDesconto.getText() == "") {
            edtDesconto.setText("0");
            desconto = Double.valueOf(edtDesconto.getText());
            Double Valor = valorVenda - desconto;
            valorAReceber = Valor;
            //edtValorAReceber.setText(Valor.toString());
        } else {
            desconto = Double.valueOf(edtDesconto.getText());
            Double Valor = valorVenda - desconto;
            //edtValorAReceber.setText(Valor.toString());
            valorAReceber = Valor;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_edtDescontoFocusLost

    private void edtValorEmDinheiroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edtValorEmDinheiroFocusLost
        if (edtValorEmDinheiro.getText().isEmpty() || edtValorEmDinheiro.getText() == "") {
            edtValorEmDinheiro.setText("0");
            valorRecebido = 0;
            troco = valorRecebido - valorAReceber;
            edtTroco.setText(String.valueOf("R$ " + troco));
        } else {
            valorRecebido = Double.valueOf(edtValorEmDinheiro.getText());
            troco = valorRecebido - valorAReceber;
            edtTroco.setText(String.valueOf("R$ " + troco));
        }        // TODO add your handling code here:
    }//GEN-LAST:event_edtValorEmDinheiroFocusLost
    private void comboSelect(int qtd) {
        String documento = null;

        int cont = 0;
        List<Parcelamento> lista = new ArrayList<Parcelamento>();
        java.util.Date date = new java.util.Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        if (rbDinheiro.isSelected()) {
            documento = "Dinheiro";
        } else if (rbCartao.isSelected()) {
            documento = "Cartão";
        } else if (rbCheque.isSelected()) {
            documento = "Cheque";
        } else if (rbNotinha.isSelected()) {
            documento = "Notinha";
        }

        while (cont < qtd) {
            if (rb30Dias.isSelected()) {
                calendar.add(Calendar.MONTH, 1);
            } else if (rb15Dias.isSelected()) {
                calendar.add(Calendar.DAY_OF_MONTH, 15);
            }
            Parcelamento parcelamento = new Parcelamento();
            parcelamento.setData(calendar.getTime());
            parcelamento.setDocumento(documento);
            parcelamento.setId_cliente(cliente.getId());
            parcelamento.setStatus("A RECEBER");
            BigDecimal decimal = new BigDecimal(valorAReceber / qtd).setScale(3, RoundingMode.HALF_EVEN);
            parcelamento.setValor(decimal.doubleValue());
            parcelamento.setId_venda(ultimaid);
            lista.add(parcelamento);
            cont++;

        }
        preencheTabela(lista);
    }
    private void comboParcelamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboParcelamentoItemStateChanged

        if (comboParcelamento.getSelectedIndex() == 0) {
            comboSelect(1);
        } else if (comboParcelamento.getSelectedIndex() == 1) {
            comboSelect(2);
        } else if (comboParcelamento.getSelectedIndex() == 2) {
            comboSelect(3);
        } else if (comboParcelamento.getSelectedIndex() == 3) {
            comboSelect(4);
        } else if (comboParcelamento.getSelectedIndex() == 4) {
            comboSelect(5);
        } else if (comboParcelamento.getSelectedIndex() == 5) {
            comboSelect(6);
        } else if (comboParcelamento.getSelectedIndex() == 6) {
            comboSelect(7);
        } else if (comboParcelamento.getSelectedIndex() == 7) {
            comboSelect(8);
        } else if (comboParcelamento.getSelectedIndex() == 8) {
            comboSelect(9);
        } else if (comboParcelamento.getSelectedIndex() == 9) {
            comboSelect(10);
        }
    }//GEN-LAST:event_comboParcelamentoItemStateChanged

    private void rbDinheiroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbDinheiroItemStateChanged
        TesteRadio();
    }//GEN-LAST:event_rbDinheiroItemStateChanged

    private void rbCartaoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbCartaoItemStateChanged
        // TODO add your handling code here:
        TesteRadio();
    }//GEN-LAST:event_rbCartaoItemStateChanged

    private void rbChequeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbChequeItemStateChanged
        TesteRadio();        // TODO add your handling code here:
    }//GEN-LAST:event_rbChequeItemStateChanged

    private void rbNotinhaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbNotinhaItemStateChanged
        TesteRadio();        // TODO add your handling code here:
    }//GEN-LAST:event_rbNotinhaItemStateChanged
    private void TesteRadio() {
        if (rbDinheiro.isSelected()) {
            comboParcelamento.setEnabled(false);
            List<Parcelamento> list = new ArrayList<Parcelamento>();
            preencheTabela(list);
            comboParcelamento.setSelectedIndex(0);
        } else if (rbCartao.isSelected()) {

            comboParcelamento.setEnabled(true);
            comboSelect(comboParcelamento.getSelectedIndex() + 1);

        } else if (rbCheque.isSelected()) {

            comboParcelamento.setEnabled(true);
            comboSelect(comboParcelamento.getSelectedIndex() + 1);
        } else if (rbNotinha.isSelected()) {

            comboParcelamento.setEnabled(true);
            comboSelect(comboParcelamento.getSelectedIndex() + 1);
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
            java.util.logging.Logger.getLogger(FormFinalizaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormFinalizaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormFinalizaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormFinalizaVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormFinalizaVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox comboParcelamento;
    private javax.swing.JFormattedTextField edtDesconto;
    private javax.swing.JTextField edtTroco;
    private javax.swing.JTextField edtValorDaVenda;
    private javax.swing.JTextField edtValorEmDinheiro;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel labelNumeroVenda;
    private javax.swing.JRadioButton rb15Dias;
    private javax.swing.JRadioButton rb30Dias;
    private javax.swing.JRadioButton rbCartao;
    private javax.swing.JRadioButton rbCheque;
    private javax.swing.JRadioButton rbDinheiro;
    private javax.swing.JRadioButton rbNotinha;
    private javax.swing.JTable tableParcelamento;
    // End of variables declaration//GEN-END:variables

    private void relatorioCondicional(Venda venda) {
        InputStream inputStream = getClass().getResourceAsStream("/Relatorios/RelatorioVendaCondicional.jasper");
        Map parametros = new HashMap();
        parametros.put("id", venda.getId());

        try {
            ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
            Connection connection = conexaosingleton.conectar();

            // abre o relatório
            //ReportUtils.openReport("VENDA", inputStream, parametros,
            //  connection);
            FormProgressDialog pro = new FormProgressDialog("Venda", inputStream, parametros,
                    connection);
            pro.setVisible(true);

        } catch (SQLException exc) {
            exc.printStackTrace();
            //JOptionPane.showMessageDialog(this, "erro" +exc+edtObservacao.getText());
        } catch (JRException exc) {
            exc.printStackTrace();
            JOptionPane.showMessageDialog(this, "erro" + exc);
        } catch (Exception ex) {
//            Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "erro" + ex);
        }
    }
}
