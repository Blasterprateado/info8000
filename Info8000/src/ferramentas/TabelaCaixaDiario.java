/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;

import Objetos.CaixaDiario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TabelaCaixaDiario extends AbstractTableModel{
    
     private static final int COL_ID = 3; 
  private static final int COL_VALOR = 0;
  private static final int COL_CLASSE = 1;
   
    private static final int COL_DATA = 2;
   


    List<CaixaDiario> linhas;
    private String[] colunas = new String[]{"VALOR", "TIPO LANÇAMENTO","DATA"};
    public TabelaCaixaDiario(List<CaixaDiario> caixas) {
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
        CaixaDiario m = linhas.get(row);
        if (column == COL_VALOR) {
            return ("R$ "+m.getValor()); 
        } else if (column == COL_CLASSE) {
            return m.getTipolancamento(); 
        } else if (column == COL_DATA) {
            return m.getData_lancamento();
        } else if (column == COL_ID) {
            return m.getId();
        }
            return "";
        } 
    public Object getValor(int row, int column) {
        CaixaDiario m = linhas.get(row);
        
         if (column == 4) {
            return m.getId();
        }
            return "";
        }
public void setValueAt(Object aValue, int row, int column) {
        CaixaDiario u = linhas.get(row);
         if (column == COL_VALOR) {
            u.setValor((Double) aValue);
        } else if (column == COL_CLASSE) {
            u.setTipolancamento(aValue.toString());
                    } else if (column == COL_DATA) {
                        u.setData_lancamento((Date) aValue);
                    } 
        
} 
    public CaixaDiario getCaixa(int indiceLinha) {
        return linhas.get(indiceLinha); 
    } 
    public void addCaixa(CaixaDiario caixa) {
        linhas.add(caixa);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    public void updateCaixa(int indiceLinha, CaixaDiario marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    } public void removeCaixa(int indiceLinha) {
        
        linhas.remove(indiceLinha);
            
        fireTableRowsDeleted(indiceLinha, indiceLinha); 
        
    } 
    
}
