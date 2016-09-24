/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Objetos.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

 /*
 * @author PauloHenrique
 */

public class TabelaFornecedor extends AbstractTableModel{
     private static final int COL_ID = 0; 
     private static final int COL_NOME = 1;
     private static final int COL_ENDERECO = 2;
     private static final int COL_BAIRRO = 4;
     private static final int COL_CIDADE = 5;
     private static final int COL_TELEFONE = 6;
     private static final int COL_NUMERO = 3;
     private static final int COL_CNPJ=7;
   
     
          List<Fornecedor> linhas;
    private final String[] colunas = new String[]{"CODIGO", "NOME", "RUA", "NUMERO", "BAIRRO","CIDADE", "TELEFONE", "CNPJ"};
    public TabelaFornecedor(List<Fornecedor> fornecedor) {
        this.linhas = new ArrayList<>(fornecedor);
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
        Fornecedor f = linhas.get(row);
        if (column == COL_ID) {
            return f.getId();
        } else if (column == COL_NOME) {
            return f.getNome(); 
        } else if (column == COL_ENDERECO) {
            return f.getEndereco(); 
        } else if (column == COL_BAIRRO) {
            return f.getBairro();
        } else if (column == COL_TELEFONE){
            return f.getTelefone();
        } else if (column == COL_CIDADE){
            return f.getCidade();
        }
         else if (column == COL_NUMERO){
            return f.getNumero();
        }else if (column == COL_CNPJ){
            return f.getCnpj();
        }
            
            return "";
        } 
     
}
