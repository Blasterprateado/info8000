/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.ProdutoLinha;
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
public class LinhaBD {


    
   public List<ProdutoLinha> getListaProdutoLinha() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<ProdutoLinha> classes = new ArrayList<ProdutoLinha>();
         PreparedStatement stmt = connection.prepareStatement("select * from produtolinha");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             ProdutoLinha produtolinha = new ProdutoLinha();
             produtolinha.setId(resultado.getLong("id"));
             produtolinha.setDescricao(resultado.getString("descricao"));             
             
             
           // adicionando o objeto à lista
           classes.add(produtolinha);
         }
         resultado.close();
         stmt.close();
         return classes;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarProdutoLinha (ProdutoLinha produtolinha) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into produtolinha " + "(descricao)" + " values (?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,produtolinha.getDescricao());
    
       
         
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
     
     public void AtualizaProdutoLinha (ProdutoLinha produtolinha) throws Exception {
         //   System.out.println(CaixaBD.getId()); // ID N RECONHECE !!!!!!!!!!!
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         

     String UPDATE = "UPDATE produtolinha SET descricao=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
           stmt.setString(1,produtolinha.getDescricao());
  
         
         stmt.setLong(2,produtolinha.getId());
         
         System.out.println(produtolinha.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(produtolinha);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "ProdutoLinha invalida" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerProdutoLinha(int id) {
       
         String DELETE = "DELETE FROM produtolinha WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir ProdutoLinha do banco de" + "dados " + e.getMessage()); } }

public ProdutoLinha getEmpresaById(int id) {
    
    String LISTBYID = "SELECT * FROM produtolinha WHERE id=?";
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
   ProdutoLinha produtolinha = new ProdutoLinha();
             produtolinha.setId(rs.getLong("id"));
             produtolinha.setDescricao(rs.getString("descricao"));
  
             
          System.out.println(produtolinha.getId());
    return produtolinha;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar ProdutoLinha" + e.getMessage()); 
    } 
        return null;
     }
public ProdutoLinha getProdutoLinhaByNome(String nome) {
    
    String LISTBYID = "SELECT * FROM produtolinha WHERE descricao=?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
   
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
    pstm.setString(1, nome);
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
   ProdutoLinha produtolinha = new ProdutoLinha();
             produtolinha.setId(rs.getLong("id"));
             produtolinha.setDescricao(rs.getString("descricao"));
  
             
          System.out.println(produtolinha.getId());
    return produtolinha;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar ProdutoLinha" + e.getMessage()); 
    } 
        return null;
     }

    
}
