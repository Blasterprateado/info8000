/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;



import Objetos.ProdutoNfe;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TabelaProdutosNfe extends AbstractTableModel{
    private static final int COL_COD = 0; 
    private static final int COL_DESCRICAO = 1;
        private static final int COL_UNDCOMERCIAL = 2;
    private static final int COL_VALORUNT = 3;
    private static final int COL_QUANTIDADE = 4;
    private static final int COL_TOTAL = 5;
    


    List<ProdutoNfe> linhas;
    private final String[] colunas = new String[]{"CÓDIGO","DESCRIÇÃO","UND","VALOR UND","QUANTIDADE", "TOTAL"};
    public TabelaProdutosNfe(List<ProdutoNfe> produtos) {
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
        if (columnIndex == COL_COD) {
            return Integer.class;
        } return String.class;
    } 
     @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    } 
     @Override
    public Object getValueAt(int row, int column) {
        ProdutoNfe m = linhas.get(row);
        if (column == COL_COD) {
            return m.getCod();
        } else if (column == COL_DESCRICAO) {
            return m.getDescricao(); 
        } else if (column == COL_VALORUNT) {
            return m.getValorunt(); 
        } else if (column == COL_QUANTIDADE) {
            return m.getQtd();
        } 
         else if (column == COL_TOTAL){
            return m.getTotal();
        }else if (column == COL_UNDCOMERCIAL){
            return m.getUndComercial();
        }
            
            return "";
        } 
     
     @Override
     public void setValueAt(Object aValue, int row, int column) {
        ProdutoNfe u = linhas.get(row);
        if (column == COL_COD) {
            u.setCod(aValue.toString());
        } else if (column == COL_DESCRICAO) {
            u.setDescricao(aValue.toString());
        } else if (column == COL_VALORUNT) {
            u.setValorunt((Double) aValue);
                    } else if (column == COL_QUANTIDADE) {
                        u.setQtd((Double) aValue);
                    } else if (column == COL_TOTAL) {
                        u.setTotal((Double) aValue);
                    }
        else if (column == COL_UNDCOMERCIAL) {
                        u.setUndComercial(aValue.toString());
                    }
        
        } 
    public ProdutoNfe getProduto(int indiceLinha) {
        return linhas.get(indiceLinha); 
    } 
    public void addProduto(ProdutoNfe produto) {
        linhas.add(produto);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    public void updateProduto(int indiceLinha, ProdutoNfe marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    } public void removeProduto(int indiceLinha) {
        
        linhas.remove(indiceLinha);
            
        fireTableRowsDeleted(indiceLinha, indiceLinha); 
        
    }
    

}
