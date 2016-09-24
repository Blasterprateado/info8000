/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;

import Objetos.GrupoUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TabelaGrupoUsuario extends AbstractTableModel{
    
     private static final int COL_ID = 0; 
  private static final int COL_NOME = 1;
   


    List<GrupoUsuario> linhas;
    private String[] colunas = new String[]{"COD", "NOME"};
    public TabelaGrupoUsuario(List<GrupoUsuario> caixas) {
        this.linhas = new ArrayList<>(caixas);
    } 
    public int getRowCount() {
        return linhas.size();
    } 
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
        GrupoUsuario m = linhas.get(row);
        if (column == COL_ID) {
            return (m.getId()); 
        } else if (column == COL_NOME) {
            return m.getNome(); 
        }
            return "";
        } 
    public Object getValor(int row, int column) {
        GrupoUsuario m = linhas.get(row);
        
         if (column == 4) {
            return m.getId();
        }
            return "";
        }
public void setValueAt(Object aValue, int row, int column) {
        GrupoUsuario u = linhas.get(row);
         if (column == COL_ID) {
            u.setId((Integer) aValue);
        } else if (column == COL_NOME) {
            u.setNome(aValue.toString());
        }
        
} 
    public GrupoUsuario getCaixa(int indiceLinha) {
        return linhas.get(indiceLinha); 
    } 
    public void addCaixa(GrupoUsuario caixa) {
        linhas.add(caixa);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    public void updateCaixa(int indiceLinha, GrupoUsuario marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    } public void removeCaixa(int indiceLinha) {
        
        linhas.remove(indiceLinha);
            
        fireTableRowsDeleted(indiceLinha, indiceLinha); 
        
    } 
    
}
