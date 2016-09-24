/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Objetos.ItensVenda;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TabelaItensVendasVenda extends AbstractTableModel{
     private static final int COL_ID = 4; 
    private static final int COL_DESCRICAO = 0;
  private static final int COL_PRECOVENDA = 1;
   private static final int COL_QTD=2;
    private static final int COL_TOTAL=3;
   


    List<ItensVenda> linhas;
    private final String[] colunas = new String[]{"DESCRIÇÃO", "PREÇO UNITARIO", "QUANTIDADE","SUBTOTAL"};
    public TabelaItensVendasVenda(List<ItensVenda> produtos) {
        this.linhas = new ArrayList<>(produtos);
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
        ItensVenda m = linhas.get(row);
        if (column == COL_ID) {
            return m.getId();
        } else if (column == COL_DESCRICAO) {
            return m.getDescProduto(); 
        } else if (column == COL_PRECOVENDA) {
            return "R$ "+m.getValorUnt(); 
        } else if (column == COL_QTD) {
            return m.getQtd();
        } else if (column == COL_TOTAL){
            return "R$ "+m.getTotal();
        }
         
            
            return "";
        } 
     
     @Override
     public void setValueAt(Object aValue, int row, int column) {
        ItensVenda u = linhas.get(row);
        if (column == COL_ID) {
            u.setId((Long) aValue);
        } else if (column == COL_DESCRICAO) {
            u.setDescProduto(aValue.toString());
        } else if (column == COL_PRECOVENDA) {
                        u.setValorUnt((Double) aValue);
                    }else if (column == COL_QTD) {
                        u.setQtd((int) aValue);
                    } else if (column == COL_TOTAL) {
                        u.setTotal((Double)aValue);
                    }
        
        
} 
    public ItensVenda getItensVenda(int indiceLinha) {
        return linhas.get(indiceLinha); 
    } 
    public void addItensVenda(ItensVenda produto) {
        linhas.add(produto);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    public void updateItensVenda(int indiceLinha, ItensVenda marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    } public void removeItensVenda(int indiceLinha) {
        
        linhas.remove(indiceLinha);
            
        fireTableRowsDeleted(indiceLinha, indiceLinha); 
        
    }
    

}
