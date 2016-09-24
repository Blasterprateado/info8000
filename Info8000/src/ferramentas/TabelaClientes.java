/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Objetos.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PauloHenrique
 */
public class TabelaClientes extends AbstractTableModel{
     private static final int COL_ID = 0; 
     private static final int COL_NOME = 1;
     private static final int COL_ENDERECO = 2;
     private static final int COL_BAIRRO = 3;
     private static final int COL_TELEFONE = 5;
     private static final int COL_NUMERO = 4;
     private static final int COL_CPF=6;
   

     List<Cliente> linhas;
    private final String[] colunas = new String[]{"CODIGO", "NOME", "RUA", "BAIRRO","NUMERO","TELEFONE", "CPF"};
    public TabelaClientes(List<Cliente> clientes) {
        this.linhas = new ArrayList<>(clientes);
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
        Cliente m = linhas.get(row);
        if (column == COL_ID) {
            return m.getId();
        } else if (column == COL_NOME) {
            return m.getNome(); 
        } else if (column == COL_ENDERECO) {
            return m.getEndereco(); 
        } else if (column == COL_BAIRRO) {
            return m.getBairro();
        } else if (column == COL_TELEFONE){
            return m.getTelefone();
        }
         else if (column == COL_NUMERO){
            return m.getNumero();
        }else if (column == COL_CPF){
            return m.getCpf();
        }
            
            return "";
        } 
    
    
}
