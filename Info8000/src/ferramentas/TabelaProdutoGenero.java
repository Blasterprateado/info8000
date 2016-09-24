/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;




import Objetos.ProdutoGenero;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TabelaProdutoGenero extends AbstractTableModel{
    private static final int COL_COD = 0; 
    private static final int COL_DESCRICAO = 1;
    
    


    List<ProdutoGenero> linhas;
    private final String[] colunas = new String[]{"CÓDIGO","DESCRIÇÃO"};
    public TabelaProdutoGenero(List<ProdutoGenero> produtos) {
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
        ProdutoGenero m = linhas.get(row);
        if (column == COL_COD) {
            return m.getId();
        } else if (column == COL_DESCRICAO) {
            return m.getDescricao(); 
        } 
            
            return "";
        } 
     
     @Override
     public void setValueAt(Object aValue, int row, int column) {
        ProdutoGenero u = linhas.get(row);
        if (column == COL_COD) {
            u.setId((Long) aValue);
        } else if (column == COL_DESCRICAO) {
            u.setDescricao(aValue.toString());
        } 
        
        } 
    public ProdutoGenero getProduto(int indiceLinha) {
        return linhas.get(indiceLinha); 
    } 
    public void addProduto(ProdutoGenero produto) {
        linhas.add(produto);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    public void updateProduto(int indiceLinha, ProdutoGenero marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    } public void removeProduto(int indiceLinha) {
        
        linhas.remove(indiceLinha);
            
        fireTableRowsDeleted(indiceLinha, indiceLinha); 
        
    }
    

}
