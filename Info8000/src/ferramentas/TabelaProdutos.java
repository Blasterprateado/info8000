/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Objetos.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TabelaProdutos extends AbstractTableModel{
     private static final int COL_ID = 0; 
    private static final int COL_DESCRICAO = 1;
    private static final int COL_PRECOCOMPRA = 2;
    private static final int COL_PRECOVENDA = 3;
    private static final int COL_QTDESTOQUE = 4;
    private static final int COL_CLASSE = 6;
    private static final int COL_FORNECEDOR=5;
    private static  final int COL_MARCA=7;


    List<Produto> linhas;
    private final String[] colunas = new String[]{"CODIGO", "DESCRICAO", "PREÇO DE COMPRA", "PREÇO DE VENDA","ESTOQUE","FORNECEDOR", "CLASSE", "MARCA"};
    public TabelaProdutos(List<Produto> produtos) {
        this.linhas = new ArrayList<>(produtos);
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
        Produto m = linhas.get(row);
        if (column == COL_ID) {
            return m.getId();
        } else if (column == COL_DESCRICAO) {
            return m.getDescricao(); 
        } else if (column == COL_PRECOCOMPRA) {
            return m.getPreco_compra(); 
        } else if (column == COL_PRECOVENDA) {
            return m.getPreco_venda();
        } else if (column == COL_QTDESTOQUE){
            return m.getQtd_estoque();
        }
         else if (column == COL_CLASSE){
            return m.getClasse();
        }else if (column == COL_FORNECEDOR){
            return m.getFornecedor();
        }else if (column == COL_MARCA){
            return m.getMarca();
        }
            
            return "";
        } 
     
     @Override
     public void setValueAt(Object aValue, int row, int column) {
        Produto u = linhas.get(row);
        if (column == COL_ID) {
            u.setId((Integer) aValue);
        } else if (column == COL_DESCRICAO) {
            u.setDescricao(aValue.toString());
        } else if (column == COL_PRECOCOMPRA) {
            u.setPreco_compra((Double) aValue);
                    } else if (column == COL_PRECOVENDA) {
                        u.setPreco_venda((Double) aValue);
                    }else if (column == COL_QTDESTOQUE) {
                        u.setQtd_estoque((Double) aValue);
                    } else if (column == COL_CLASSE) {
                        u.setClasse(aValue.toString());
                    }
        else if (column == COL_FORNECEDOR) {
                        u.setFornecedor(aValue.toString());
                    }
        else if (column == COL_MARCA) {
                        u.setMarca(aValue.toString());
                    }
        
} 
    public Produto getProduto(int indiceLinha) {
        return linhas.get(indiceLinha); 
    } 
    public void addProduto(Produto produto) {
        linhas.add(produto);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }
    public void updateProduto(int indiceLinha, Produto marca) {
        linhas.set(indiceLinha, marca);
        fireTableRowsUpdated(indiceLinha, indiceLinha);
    } public void removeProduto(int indiceLinha) {
        
        linhas.remove(indiceLinha);
            
        fireTableRowsDeleted(indiceLinha, indiceLinha); 
        
    }
    

}
