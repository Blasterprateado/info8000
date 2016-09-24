/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.ContasReceber;
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
public class ContasReceberBD {
    public List<ContasReceber> getListaContasReceber() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<ContasReceber> classes = new ArrayList<ContasReceber>();
         PreparedStatement stmt = connection.prepareStatement("select * from contasreceber");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             ContasReceber contasPagar = new ContasReceber();
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
     public void adicionarContasReceber(ContasReceber contasPagar) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into contasreceber " +
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
     
     public void AtualizaConta(ContasReceber contasPagar) throws Exception {
            System.out.println(contasPagar.getId());
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     
      String UPDATE = "UPDATE contasreceber SET descricao=?,valor=?,situacao=?,data=? WHERE id=?";

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
         JOptionPane.showMessageDialog(null, "ContasReceber invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerContasReceber(int id) {
       
         String DELETE = "DELETE FROM contasreceber WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir ContasReceber do banco de" + "dados " + e.getMessage()); } }

public ContasReceber getContasReceberById(int id) {
    
    String LISTBYID = "SELECT * FROM contasreceber WHERE id=?";
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
   ContasReceber contasPagar = new ContasReceber();
                     
           contasPagar.setId(rs.getInt("id"));
             contasPagar.setDescricao(rs.getString("descricao"));
            contasPagar.setData(rs.getDate("data"));
            contasPagar.setSituacao(rs.getInt("situacao"));
            contasPagar.setValor(rs.getDouble("valor"));

          System.out.println(contasPagar.getId());
    return contasPagar;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar ContasRecebers" + e.getMessage()); 
    } 
        return null;
     }


    
}
