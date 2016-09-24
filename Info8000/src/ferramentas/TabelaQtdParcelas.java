/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Conection.ClienteBD;
import Objetos.Cliente;
import Objetos.Parcelamento;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PauloHenrique
 */
public class TabelaQtdParcelas extends AbstractTableModel{
     
     private static final int COL_VALOR = 0;
     private static final int COL_DATA = 1;
private static final int COL_DOCUMENTO=2;
   

     List<Parcelamento> linhas;
    private final String[] colunas = new String[]{"VALOR","DATA", "DOCUMENTO"};
    public TabelaQtdParcelas(List<Parcelamento> parcelamentos) {
        this.linhas = new ArrayList<>(parcelamentos);
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
        if (columnIndex == COL_DATA) {
            return Integer.class;
        } return String.class;
    } 
     
    
    @Override
    public Object getValueAt(int row, int column) {
        Parcelamento m = linhas.get(row);
        if (column == COL_DOCUMENTO) {
            return m.getDocumento(); 
        }  else if (column == COL_VALOR){
            return "R$ "+m.getValor();
        }
         else if (column == COL_DATA){
             String dataz = "dd/MM/yyyy";
                SimpleDateFormat formatas = new SimpleDateFormat(dataz );
                String data = formatas.format(m.getData() );
            return data;
        }
            
            return "";
        } 
    
    
}
