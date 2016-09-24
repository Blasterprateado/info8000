/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;



import Objetos.ClasseProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class ClasseProdutoBD {
    
    public List<ClasseProduto> GetListaClasseProduto(){
            
            
            
            ConectionSingleton conexaosingleton;
            List<ClasseProduto> listaClasses = new ArrayList<ClasseProduto>();
    try {
        conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         PreparedStatement stmt = connection.prepareStatement("select * from classeproduto");
         ResultSet resultado = stmt.executeQuery();
         System.out.println(stmt);
         System.out.println(resultado);
         
       
  while (resultado.next()){
       ClasseProduto classe= new ClasseProduto();
      classe.setId(resultado.getInt("id"));
      classe.setDescricao(resultado.getString("descricao"));
      
      
      listaClasses.add(classe);
   
    
        
    }
 stmt.close();
         resultado.close();
    } catch (Exception ex) {
//        Logger.getLogger(FormVendaAcai2.class.getName()).log(Level.SEVERE, null, ex);
    }
        return listaClasses;
}
    
    public void adicionarClasseProduto(ClasseProduto classeproduto) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();

         String sql = "insert into classeproduto " +
             "(descricao)" +
             " values (?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,classeproduto.getDescricao());
        stmt.execute();
         stmt.close();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }
    
    public void AtualizaClasseProduto(ClasseProduto classeproduto) throws Exception {
            System.out.println(classeproduto.getId());
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
   
      String UPDATE = "UPDATE classeproduto SET descricao=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
         stmt.setString(1,classeproduto.getDescricao());
         stmt.setInt(2,classeproduto.getId());
         System.out.println(classeproduto.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(classeproduto);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Classe Produto invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerClasseProduto(int id) {
       
         String DELETE = "DELETE FROM classeproduto WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir Classe Produto do banco de" + "dados " + e.getMessage()); } }
    
    
    
    
    public ResultSet GetResultSetClasseProduto(){
            
            
            
            ConectionSingleton conexaosingleton;
            List<ClasseProduto> listaClasses = new ArrayList<ClasseProduto>();
            ResultSet resultado= null;
    try {
        conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         PreparedStatement stmt = connection.prepareStatement("select * from classeproduto order by descricao ");
          resultado = stmt.executeQuery();
         System.out.println(stmt);
         System.out.println(resultado);
         
       
  while (resultado.next()){
       ClasseProduto classe= new ClasseProduto();
      classe.setId(resultado.getInt("id"));
      classe.setDescricao(resultado.getString("descricao"));
      
      listaClasses.add(classe);
   
    
        
    }
 stmt.close();
        
    } catch (Exception ex) {
//        Logger.getLogger(FormVendaAcai2.class.getName()).log(Level.SEVERE, null, ex);
    }
        return resultado;
        
}
    
    
    public ClasseProduto getClasseProdutoById(int id) {
    
    String LISTBYID = "SELECT * FROM classeproduto WHERE id=?";
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
        System.out.println(rs.getInt("id"));
        System.out.println(rs.getString("descricao"));
   
         ClasseProduto produto = new ClasseProduto();
         
             produto.setId(rs.getInt("id"));
            System.out.println(produto.getId()); 
             produto.setDescricao(rs.getString("descricao"));
         
          System.out.println(produto.getDescricao());
    return produto;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Classes" + e.getMessage()); 
    } 
        return null;
     }


    
    
    public ClasseProduto getClasseProdutoByDesc(String desc) {
    
    String LISTBYID = "SELECT * FROM classeproduto WHERE descricao=?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
   
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
    pstm.setString(1, desc);
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
        System.out.println(rs.getInt("id"));
        System.out.println(rs.getString("descricao"));
   
         ClasseProduto produto = new ClasseProduto();
         
             produto.setId(rs.getInt("id"));
            System.out.println(produto.getId()); 
             produto.setDescricao(rs.getString("descricao"));
         
          System.out.println(produto.getDescricao());
    return produto;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Classes" + e.getMessage()); 
    } 
        return null;
     }

    
    
    
    
}