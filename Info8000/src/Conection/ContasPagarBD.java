/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.ContasPagar;
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
public class ContasPagarBD {
    public List<ContasPagar> getListaContasPagar() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<ContasPagar> classes = new ArrayList<ContasPagar>();
         PreparedStatement stmt = connection.prepareStatement("select * from contaspagar");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             ContasPagar contasPagar = new ContasPagar();
             contasPagar.setId(resultado.getInt("id"));
             contasPagar.setDescricao(resultado.getString("descricao"));
            contasPagar.setData(resultado.getDate("data"));
            contasPagar.setSituacao(resultado.getInt("situacao"));
            contasPagar.setValor(resultado.getDouble("valor"));
             
           // adicionando o objeto à lista
           classes.add(contasPagar);
         }
         resultado.close();
         stmt.close();
         return classes;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
    public List<ContasPagar> getListaContasPagarAberto() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<ContasPagar> classes = new ArrayList<ContasPagar>();
         PreparedStatement stmt = connection.prepareStatement("select * from contaspagar where situacao=0");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             ContasPagar contasPagar = new ContasPagar();
             contasPagar.setId(resultado.getInt("id"));
             contasPagar.setDescricao(resultado.getString("descricao"));
            contasPagar.setData(resultado.getDate("data"));
            contasPagar.setSituacao(resultado.getInt("situacao"));
            contasPagar.setValor(resultado.getDouble("valor"));
             
           // adicionando o objeto à lista
           classes.add(contasPagar);
         }
         resultado.close();
         stmt.close();
         return classes;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarContasPagar(ContasPagar contasPagar) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into contaspagar " +
             "(descricao,valor,data,situacao)" +
             " values (?,?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,contasPagar.getDescricao());
         stmt.setDouble(2, contasPagar.getValor());
         stmt.setDate(3, contasPagar.getData());
         stmt.setInt(4, contasPagar.getSituacao());
    
       
         
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
     
     public void AtualizaConta(ContasPagar contasPagar) throws Exception {
            System.out.println(contasPagar.getId());
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     
      String UPDATE = "UPDATE contaspagar SET descricao=?,valor=?,situacao=?,data=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
           stmt.setString(1,contasPagar.getDescricao());
           stmt.setDouble(2, contasPagar.getValor());
           stmt.setInt(3, contasPagar.getSituacao());
         stmt.setDate(4,contasPagar.getData());
         stmt.setInt(5, contasPagar.getId());
         
         System.out.println(contasPagar.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(contasPagar);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "ContasPagar invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerContasPagar(int id) {
       
         String DELETE = "DELETE FROM contaspagar WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir ContasPagar do banco de" + "dados " + e.getMessage()); } }

public ContasPagar getContasPagarById(int id) {
    
    String LISTBYID = "SELECT * FROM contaspagar WHERE id=?";
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
   ContasPagar contasPagar = new ContasPagar();
                     
           contasPagar.setId(rs.getInt("id"));
             contasPagar.setDescricao(rs.getString("descricao"));
            contasPagar.setData(rs.getDate("data"));
            contasPagar.setSituacao(rs.getInt("situacao"));
            contasPagar.setValor(rs.getDouble("valor"));

          System.out.println(contasPagar.getId());
    return contasPagar;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar ContasPagars" + e.getMessage()); 
    } 
        return null;
     }


    
}
