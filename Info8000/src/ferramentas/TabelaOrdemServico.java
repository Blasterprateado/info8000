/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;

import Objetos.OrdemServico;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PauloHenrique
 */
public class TabelaOrdemServico extends AbstractTableModel{
    private static final int COL_ID = 0;
    private static final int COL_CLIENTE = 1;
    private static final int COL_DESC_ESQUIPAMENTO = 2;
    private static final int COL_STATUS = 3;
    private static final int COL_VALOR = 4;
    private static final int COL_FUNCIONARIO = 5;
    private static final int COL_DATA_ENTRADA = 6;
    private static final int COL_DATA_SAIDA = 7;
     List<OrdemServico> linhas;
    private final String[] colunas = new String[]{"CODIGO", "CLIENTE", "DESC. EQUIPAMENTO", "STATUS", "VALOR", "FUNCIONARIO", "DATA ENTRADA", "DATA SAIDA"};
    public TabelaOrdemServico(List<OrdemServico> ordemservico) {
        this.linhas = new ArrayList<>(ordemservico);
    } 
@Override
    public int getRowCount() {
        return linhas.size();
    } 
     @Override
    public int getColumnCount() {
        return colunas.length; 
    } 
     @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    } 
     @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == COL_ID) {
            return Integer.class;
        } return String.class;
    } 
     @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    } 
    
    @Override
    public Object getValueAt(int row, int column) {
        OrdemServico os = linhas.get(row);
        if (column == COL_ID) {
            return os.getId();
        } else if (column == COL_CLIENTE) {
            return os.getCliente(); 
        } else if (column == COL_DATA_ENTRADA) {
            DateFormat f = DateFormat.getDateInstance(DateFormat.MEDIUM);
          
            
            return   f.format(os.getData_entrada()); 
        }else if (column == COL_DATA_SAIDA) {
             DateFormat f = DateFormat.getDateInstance(DateFormat.MEDIUM);
             if(os.getData_saida()!=null){
                  return f.format(os.getData_saida()); 
             }else{
                 return os.getData_saida();
             }
           
        } else if (column == COL_FUNCIONARIO) {
            return os.getFuncionario(); 
        }else if (column == COL_DESC_ESQUIPAMENTO) {
            return os.getDescricao_equipamento(); 
        } else if (column == COL_STATUS) {
            return os.getStatus(); 
        }else if (column == COL_VALOR) {
            return "R$ "+os.getValor(); 
        } 
            return "";
        } 
    
    
}
