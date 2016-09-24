/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;

import Objetos.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PauloHenrique
 */
public class TabelaFuncionario extends AbstractTableModel{
     private static final int COL_ID = 0; 
     private static final int COL_NOME = 1;
     private static final int COL_SALARIO = 2;
     private static final int COL_ENDERECO = 3;
     private static final int COL_PERMISSAO = 5;
     private static final int COL_USUARIO = 4;
     private static final int COL_SENHA=6;
     private static final int COL_ADMISSAO=7;
     private static final int COL_DEMISSAO=8;
     
     

    List<Funcionario> linhas;
    private final String[] colunas = new String[]{"CODIGO", "NOME", "SALÁRIO", "ENDEREÇO", "PERMISSAO","USUARIO","SENHA", "DATA DE ADMISSAO", "DATA DE DEMISSÃO"};
    public TabelaFuncionario(List<Funcionario> funcionario) {
        this.linhas = new ArrayList<>(funcionario);
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
        Funcionario m = linhas.get(row);
        if (column == COL_ID) {
            return m.getId();
        } else if (column == COL_NOME) {
            return m.getNome(); 
        }  else if (column == COL_SALARIO) {
            return m.getSalario(); 
        }  else if (column == COL_ENDERECO) {
            return m.getEndereco(); 
        } else if (column == COL_PERMISSAO) {
            return m.getPermissao(); 
        } else if (column == COL_USUARIO) {
            return m.getUsuario();
        } else if (column == COL_SENHA){
            return m.getSenha();
        }else if (column == COL_ADMISSAO){
            return m.getDataAdm();
        }else if (column == COL_DEMISSAO){
            return m.getDataDm();
        }
            
            return "";
        } 
    
    
}
