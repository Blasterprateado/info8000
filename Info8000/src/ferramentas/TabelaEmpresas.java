/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;

import Objetos.Empresas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PauloHenrique
 */
public class TabelaEmpresas extends AbstractTableModel{
     private static final int COL_ID = 0; 
     private static final int COL_NOME = 1;


     List<Empresas> linhas;
    private final String[] colunas = new String[]{"CODIGO", "NOME"};
    public TabelaEmpresas(List<Empresas> empresas) {
        this.linhas = new ArrayList<>(empresas);
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
        Empresas e = linhas.get(row);
        if (column == COL_ID) {
            return e.getId();
        } else if (column == COL_NOME) {
            return e.getNome(); 
        }            
            return "";
        } 
    
    
}
