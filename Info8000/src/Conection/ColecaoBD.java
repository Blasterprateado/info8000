/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.ProdutoColecao;
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
public class ColecaoBD {


    
   public List<ProdutoColecao> getListaProdutoColecao() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<ProdutoColecao> classes = new ArrayList<ProdutoColecao>();
         PreparedStatement stmt = connection.prepareStatement("select * from produtocolecao");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             ProdutoColecao produtocolecao = new ProdutoColecao();
             produtocolecao.setId(resultado.getLong("id"));
             produtocolecao.setDescricao(resultado.getString("descricao"));             
             
             
           // adicionando o objeto à lista
           classes.add(produtocolecao);
         }
         resultado.close();
         stmt.close();
         return classes;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarProdutoColecao (ProdutoColecao produtocolecao) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into produtocolecao " + "(descricao)" + " values (?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,produtocolecao.getDescricao());
    
       
         
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
     
     public void AtualizaProdutoColecao (ProdutoColecao produtocolecao) throws Exception {
         //   System.out.println(CaixaBD.getId()); // ID N RECONHECE !!!!!!!!!!!
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         

     String UPDATE = "UPDATE produtocolecao SET descricao=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
           stmt.setString(1,produtocolecao.getDescricao());
  
         
         stmt.setLong(2,produtocolecao.getId());
         
         System.out.println(produtocolecao.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(produtocolecao);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "ProdutoColecao invalida" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerProdutoColecao(int id) {
       
         String DELETE = "DELETE FROM produtocolecao WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir ProdutoColecao do banco de" + "dados " + e.getMessage()); } }

public ProdutoColecao getEmpresaById(int id) {
    
    String LISTBYID = "SELECT * FROM produtocolecao WHERE id=?";
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
   ProdutoColecao produtocolecao = new ProdutoColecao();
             produtocolecao.setId(rs.getLong("id"));
             produtocolecao.setDescricao(rs.getString("descricao"));
  
             
          System.out.println(produtocolecao.getId());
    return produtocolecao;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar ProdutoColecao" + e.getMessage()); 
    } 
        return null;
     }
public ProdutoColecao getProdutoColecaoByNome(String nome) {
    
    String LISTBYID = "SELECT * FROM produtocolecao WHERE descricao=?";
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
   ProdutoColecao produtocolecao = new ProdutoColecao();
             produtocolecao.setId(rs.getLong("id"));
             produtocolecao.setDescricao(rs.getString("descricao"));
  
             
          System.out.println(produtocolecao.getId());
    return produtocolecao;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar ProdutoColecao" + e.getMessage()); 
    } 
        return null;
     }

    
}
