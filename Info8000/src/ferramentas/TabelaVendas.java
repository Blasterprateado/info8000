/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Objetos.Venda;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TabelaVendas extends AbstractTableModel{
     private static final int COL_ID = 0; 
    private static final int COL_CLIENTE = 1;
    private static final int COL_VENDEDOR = 2;
    private static final int COL_VALOR = 3;
    private static final int COL_DATA = 4;
    private static final int COL_SITUACAO = 5;
   


    List<Venda> linhas;
    private final String[] colunas = new String[]{"Nº VENDA", "CLIENTE", "VENDEDOR", "VALOR","DATA","SITUAÇÃO"};
    public TabelaVendas(List<Venda> vendas) {
        this.linhas = new ArrayList<>(vendas);
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
        Venda m = linhas.get(row);
        if (column == COL_ID) {
            return m.getId();
        } else if (column == COL_CLIENTE) {
            return m.getCliente(); 
        } else if (column == COL_VENDEDOR) {
            return m.getFuncionario(); 
        } else if (column == COL_VALOR) {
            return m.getValor_venda();
        } else if (column == COL_DATA){
              DateFormat f = DateFormat.getDateInstance(DateFormat.MEDIUM);
            return f.format(m.getData_venda());
        }else if (column == COL_SITUACAO){
          if( m.getSituacao()==0){
              return "Venda Normal";
          }else if( m.getSituacao()==1){
              return "Condicional";
          }else if( m.getSituacao()==2){
              return "Cancelada";
          }
           
        }
         
            
            return "";
        } 
     
     @Override
     public void setValueAt(Object aValue, int row, int column) {
        Venda u = linhas.get(row);
        if (column == COL_ID) {
            u.setId((Integer) aValue);
        } else if (column == COL_CLIENTE) {
            u.setCliente(aValue.toString());
        } else if (column == COL_VENDEDOR) {
            u.setFuncionario( aValue.toString());
                    } else if (column == COL_VALOR) {
                        u.setValor_venda((Double) aValue);
                    }else if (column == COL_DATA) {
                        u.setData_venda((Date) aValue);
                    } else if (column == COL_SITUACAO) {
                        u.setSituacao((Integer) aValue);
                    }
        
        
} 
    public Venda getVenda(int indiceLinha) {
        return linhas.get(indiceLinha); 
    } 
    public void addVenda(Venda venda) {
        linhas.add(venda);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    public void updateVenda(int indiceLinha, Venda venda) {
        linhas.set(indiceLinha, venda);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    } public void removeVenda(int indiceLinha) {
        
        linhas.remove(indiceLinha);
            
        fireTableRowsDeleted(indiceLinha, indiceLinha); 
        
    }
    

}
