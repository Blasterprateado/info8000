/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.ItensVenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class ItensVendaBD {
  
    public List<ItensVenda> getListaItensVenda() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<ItensVenda> itens = new ArrayList<ItensVenda>();
         PreparedStatement stmt = connection.prepareStatement("select * from itens_venda)");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             ItensVenda itensvenda;
              itensvenda = new ItensVenda();
             itensvenda.setId(resultado.getLong("id"));
             itensvenda.setId_produto(resultado.getInt("id_produto"));
             itensvenda.setId_venda(resultado.getInt("id_venda"));
             itensvenda.setDescProduto(resultado.getString("descricao"));
             itensvenda.setQtd(resultado.getInt("qtd"));
             itensvenda.setValorUnt(resultado.getDouble("valor_unt"));
             itensvenda.setTotal(resultado.getDouble("total"));
            
           // adicionando o objeto à lista
            itens.add(itensvenda);
         }
         resultado.close();
         stmt.close();
         return itens;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
    public List<ItensVenda> getListaItensVendaByIdVenda(int idVenda) throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<ItensVenda> itens = new ArrayList<ItensVenda>();
         PreparedStatement stmt = connection.prepareStatement("select * from itens_venda where id_venda=?");
         stmt.setInt(1, idVenda);
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             ItensVenda itensvenda;
              itensvenda = new ItensVenda();
             itensvenda.setId(resultado.getLong("id"));
             itensvenda.setId_produto(resultado.getInt("id_produto"));
             itensvenda.setId_venda(resultado.getInt("id_venda"));
             itensvenda.setDescProduto(resultado.getString("descricao"));
             itensvenda.setQtd(resultado.getInt("qtd"));
             itensvenda.setValorUnt(resultado.getDouble("valor_unt"));
             itensvenda.setTotal(resultado.getDouble("total"));
             
            
           // adicionando o objeto à lista
            itens.add(itensvenda);
         }
         resultado.close();
         stmt.close();
         return itens;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarItem(ItensVenda item) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into itens_venda (id_produto,id_venda,descricao,qtd,valor_unt,total) values (?,?,?,?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setInt(1,item.getId_produto());
         stmt.setInt(2,item.getId_venda());
         stmt.setString(3,item.getDescProduto());
         stmt.setInt(4,item.getQtd());
         stmt.setDouble(5,item.getValorUnt());
         stmt.setDouble(6,item.getTotal());
     
         
         
         stmt.execute();
        
         stmt.close();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }
     public static java.sql.Timestamp getCurrentTimeStamp() {
 
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
 
	}
     
     public void AtualizaItem(ItensVenda item) throws Exception {
            System.out.println(item.getId());
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     
      String UPDATE = "UPDATE itens_venda SET id_produto=?,id_venda=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
         stmt.setInt(1,item.getId_produto());
         stmt.setInt(2,item.getId_venda());
         stmt.setDouble(3,item.getId());
         
         System.out.println(item.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(item);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "item invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerItem(int id) {
       
         String DELETE = "DELETE FROM itens_venda WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir Item do banco de" + "dados " + e.getMessage()); 
         } 
     }
     public void removerItemByIdVenda(int id) {
       
         String DELETE = "DELETE FROM itens_venda WHERE id_venda =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir Item do banco de" + "dados " + e.getMessage()); 
         } 
     }

public ItensVenda getItemById(int id) {
    
    String LISTBYID = "SELECT * FROM itens_venda WHERE id=?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
   
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
    pstm.setInt(1, id);
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
      
         ItensVenda produto = new ItensVenda();
         
             produto.setId(rs.getLong("id"));
            System.out.println(produto.getId()); 
             produto.setId_produto(rs.getInt("id_produto"));
             produto.setId_venda(rs.getInt("id_venda"));
             
          System.out.println(produto.getId_venda());
    return produto;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar itens" + e.getMessage()); 
    } 
        return null;
     }


    
    

    
    
    
}
