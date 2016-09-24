/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.ProdutoGenero;
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
public class GeneroBD {


    
   public List<ProdutoGenero> getListaProdutoGenero() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<ProdutoGenero> classes = new ArrayList<ProdutoGenero>();
         PreparedStatement stmt = connection.prepareStatement("select * from produtogenero");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             ProdutoGenero produtogenero = new ProdutoGenero();
             produtogenero.setId(resultado.getLong("id"));
             produtogenero.setDescricao(resultado.getString("descricao"));             
             
             
           // adicionando o objeto à lista
           classes.add(produtogenero);
         }
         resultado.close();
         stmt.close();
         return classes;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarProdutoGenero (ProdutoGenero produtogenero) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into produtogenero " + "(descricao)" + " values (?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,produtogenero.getDescricao());
    
       
         
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
     
     public void AtualizaProdutoGenero (ProdutoGenero produtogenero) throws Exception {
         //   System.out.println(CaixaBD.getId()); // ID N RECONHECE !!!!!!!!!!!
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         

     String UPDATE = "UPDATE produtogenero SET descricao=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
           stmt.setString(1,produtogenero.getDescricao());
  
         
         stmt.setLong(2,produtogenero.getId());
         
         System.out.println(produtogenero.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(produtogenero);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "ProdutoGenero invalida" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerProdutoGenero(int id) {
       
         String DELETE = "DELETE FROM produtogenero WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir ProdutoGenero do banco de" + "dados " + e.getMessage()); } }

public ProdutoGenero getEmpresaById(int id) {
    
    String LISTBYID = "SELECT * FROM produtogenero WHERE id=?";
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
   ProdutoGenero produtogenero = new ProdutoGenero();
             produtogenero.setId(rs.getLong("id"));
             produtogenero.setDescricao(rs.getString("descricao"));
  
             
          System.out.println(produtogenero.getId());
    return produtogenero;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar ProdutoGenero" + e.getMessage()); 
    } 
        return null;
     }
public ProdutoGenero getProdutoGeneroByNome(String nome) {
    
    String LISTBYID = "SELECT * FROM produtogenero WHERE descricao=?";
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
   ProdutoGenero produtogenero = new ProdutoGenero();
             produtogenero.setId(rs.getLong("id"));
             produtogenero.setDescricao(rs.getString("descricao"));
  
             
          System.out.println(produtogenero.getId());
    return produtogenero;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar ProdutoGenero" + e.getMessage()); 
    } 
        return null;
     }

    
}
