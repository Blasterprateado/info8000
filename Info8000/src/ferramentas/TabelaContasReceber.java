/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Objetos.ContasReceber;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TabelaContasReceber extends AbstractTableModel{
    private static final int COL_ID = 0; 
    private static final int COL_DESCRICAO = 1;
     private static final int COL_VALOR = 2;
      private static final int COL_SITUACAO = 3;
      private static final int COL_DATA=4;
    


    List<ContasReceber> linhas;
    private String[] colunas = new String[]{"CÓDIGO", "DESCRIÇÃO","VALOR","SITUAÇÃO","DATA VENCIMENTO"};
    public TabelaContasReceber(List<ContasReceber> classeProdutos) {
        this.linhas = new ArrayList<>(classeProdutos);
    } 
    @Override
    public int getRowCount() {
        return linhas.size();
    } 
    @Override
    public int getColumnCount() {
        return colunas.length; 
    } 
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    } 
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == COL_ID) {
            return Integer.class;
        } return String.class;
    } 
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    } 
    public Object getValueAt(int row, int column) {
        ContasReceber m = linhas.get(row);
        if (column == COL_ID) {
            return m.getId();
        } else if (column == COL_DESCRICAO) {
            return m.getDescricao(); 
        } else if (column == COL_VALOR) {
            return "R$ "+m.getValor(); 
            
        }else if (column == COL_SITUACAO) {
            if(m.getSituacao()==0){
                return "Em Aberto";
            }else if(m.getSituacao()==1){
                return "Fechado";
            }
        
            return m.getSituacao(); 
        }else if (column == COL_DATA) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
            
            return format.format(m.getData()); 
        }
            
            return "";
        } 
     
public void setValueAt(Object aValue, int row, int column) {
        ContasReceber u = linhas.get(row);
        if (column == COL_ID) {
            u.setId((Integer) aValue);
        } else if (column == COL_DESCRICAO) {
            u.setDescricao(aValue.toString());
        }else if (column == COL_SITUACAO) {
            u.setSituacao((Integer)aValue);
        } else if (column == COL_VALOR) {
            u.setValor((Double) aValue);
        } else if (column == COL_DATA) {
            u.setData((Date) aValue);
        } 
        
} 
    public ContasReceber getContasReceber(int indiceLinha) {
        return linhas.get(indiceLinha); 
    } 
    public void addContasReceber(ContasReceber classeProduto) {
        linhas.add(classeProduto);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    public void updateContasReceber(int indiceLinha, ContasReceber marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    } public void removeContasReceber(int indiceLinha) {
        
        linhas.remove(indiceLinha);
            
        fireTableRowsDeleted(indiceLinha, indiceLinha); 
        
    }

}
