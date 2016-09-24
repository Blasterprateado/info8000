/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;

import Objetos.Cidade;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TabelaCidades extends AbstractTableModel{
    
   private static final int COL_ID = 5; 
   private static final int COL_NOME = 0;
   private static final int COL_CEP = 1;
   private static final int COL_UF = 2;
   private static final int COL_COD_UF = 3;
   private static final int COL_IBGE = 4;


    List<Cidade> linhas;
    private String[] colunas = new String[]{"NOME CIDADE", "CEP","UF","COD UF","COD IBGE"};
    public TabelaCidades(List<Cidade> caixas) {
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
        Cidade m = linhas.get(row);
       switch (column) {
           case COL_CEP:
               return (m.getCep());
           case COL_NOME:
               return m.getNome();
           case COL_COD_UF:
               return m.getCodUf();
           case COL_IBGE:
               return m.getCodMunicipio();
           case COL_UF:
               return m.getUf();
           case COL_ID:
               return m.getId();
           default:
               break;
       }
            return "";
        } 
    public Object getValor(int row, int column) {
        Cidade m = linhas.get(row);
        
         if (column == 4) {
            return m.getId();
        }
            return "";
        }
   @Override
   public void setValueAt(Object aValue, int row, int column) {
        Cidade u = linhas.get(row);
       switch (column) {
           case COL_CEP:
               u.setCep(aValue.toString());
               break;
           case COL_NOME:
               u.setNome(aValue.toString());
               break;
           case COL_COD_UF:
               u.setCodUf(aValue.toString());
               break;
           case COL_IBGE:
               u.setCodMunicipio(aValue.toString());
               break;
           case COL_ID:
               u.setId((int) aValue);
               break;
           case COL_UF:
               u.setUf(aValue.toString());
               break;
           default:
               break;
       }
        
} 
    public Cidade getCidade(int indiceLinha) {
        return linhas.get(indiceLinha); 
    } 
    public void addCidade(Cidade cidade) {
        linhas.add(cidade);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    public void updateCidade(int indiceLinha, Cidade marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    } 
    public void removeCidade(int indiceLinha) {
        
        linhas.remove(indiceLinha);
            
        fireTableRowsDeleted(indiceLinha, indiceLinha); 
        
    } 
    
}
