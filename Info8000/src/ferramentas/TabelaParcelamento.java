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
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PauloHenrique
 */
public class TabelaParcelamento extends AbstractTableModel{
     private static final int COL_ID = 0; 
     private static final int COL_COD_VENDA = 1;
     private static final int COL_CLIENTE = 2;
     private static final int COL_VALOR = 3;
     private static final int COL_DATA = 4;
    private static final int COL_DOCUMENTO=5;
    private static final int COL_SITUACAO=6;
   

     List<Parcelamento> linhas;
    private final String[] colunas = new String[]{"CODIGO", "COD VENDA", "CLIENTE", "VALOR","DATA DE VENCIMENTO", "DOCUMENTO", "SITUAÇÃO"};
    public TabelaParcelamento(List<Parcelamento> parcelamentos) {
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
        if (columnIndex == COL_ID) {
            return Integer.class;
        } return String.class;
    } 
     
    
    @Override
    public Object getValueAt(int row, int column) {
        Parcelamento m = linhas.get(row);
        if (column == COL_ID) {
            return m.getId();
        } else if (column == COL_CLIENTE) {
            ClienteBD clientebd = new ClienteBD();
            Cliente cliente = clientebd.getClienteById(m.getId_cliente());
            return cliente.getNome(); 
        } else if (column == COL_DOCUMENTO) {
            return m.getDocumento(); 
        } else if (column == COL_COD_VENDA) {
            return m.getId_venda();
        } else if (column == COL_VALOR){
            return m.getValor();
        }
         else if (column == COL_DATA){
            String dataz = "dd/MM/yyyy";
                SimpleDateFormat formatas = new SimpleDateFormat(dataz );
                String data = formatas.format(m.getData() );
            return data;
        }else if(column==COL_SITUACAO){
            return m.getStatus();
        }
            
            return "";
        } 
    
    
}
