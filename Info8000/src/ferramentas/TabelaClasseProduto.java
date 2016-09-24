/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;

import Objetos.ClasseProduto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 */
public class TabelaClasseProduto extends AbstractTableModel{
     private static final int COL_ID = 0; 
     private static final int COL_DESCRICAO = 1;

   
     List<ClasseProduto> linhas;
      private final String[] colunas = new String[]{"CODIGO", "DESCRIÇÃO"};
      public TabelaClasseProduto(List<ClasseProduto> marca) {
        this.linhas = new ArrayList<>(marca);
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
        ClasseProduto m = linhas.get(row);
        if (column == COL_ID) {
            return m.getId();
        } else if (column == COL_DESCRICAO) {
            return m.getDescricao(); 
        }
            
            return "";
        } 
    
    
}
