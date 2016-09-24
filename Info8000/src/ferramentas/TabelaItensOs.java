/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Objetos.Cliente;
import Objetos.Itens_Os;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PauloHenrique
 */
public class TabelaItensOs extends AbstractTableModel{
     private static final int COL_ID = 2; 
     private static final int COL_DESC = 0;
     private static final int COL_VALOR = 1;
     

     List<Itens_Os> linhas;
    private final String[] colunas = new String[]{"DESCRIÇÃO", "VALOR"};
    public TabelaItensOs(List<Itens_Os> itens) {
        this.linhas = new ArrayList<>(itens);
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    } 

         @Override
    public Class getColumnClass(int columnIndex) {
        if (columnIndex == COL_ID) {
            return Integer.class;
        } return String.class;
    } 
     
    
    @Override
    public Object getValueAt(int row, int column) {
        Itens_Os m = linhas.get(row);
        if (column == COL_ID) {
            return m.getId();
        } else if (column == COL_DESC) {
            return m.getDesc(); 
        } else if (column == COL_VALOR) {
            return m.getValor(); 
        } 
            
            return "";
        } 
    
    
}
