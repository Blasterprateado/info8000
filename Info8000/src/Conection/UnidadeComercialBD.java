/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.Unidadecomercial;
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
public class UnidadeComercialBD {


    
   public List<Unidadecomercial> getListaUnidadecomercial() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<Unidadecomercial> classes = new ArrayList<Unidadecomercial>();
         PreparedStatement stmt = connection.prepareStatement("select * from unidadecomercial");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             Unidadecomercial unidadecomercial = new Unidadecomercial();
             unidadecomercial.setIdUnidadeComercial(resultado.getInt("idUnidadeComercial"));
             unidadecomercial.setSigla(resultado.getString("sigla"));     
             unidadecomercial.setValor(resultado.getDouble("valor"));
             
             
           // adicionando o objeto à lista
           classes.add(unidadecomercial);
         }
         resultado.close();
         stmt.close();
         return classes;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarUnidadecomercial (Unidadecomercial unidadecomercial) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into unidadecomercial " + "(sigla,valor)" + " values (?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,unidadecomercial.getSigla());
         stmt.setDouble(2, unidadecomercial.getValor());
    
       
         
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
     
     public void AtualizaUnidadecomercial (Unidadecomercial unidadecomercial) throws Exception {
         //   System.out.println(CaixaBD.getId()); // ID N RECONHECE !!!!!!!!!!!
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         

     String UPDATE = "UPDATE unidadecomercial SET sigla=?, valor=? WHERE idUnidadeComercial=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
           stmt.setString(1,unidadecomercial.getSigla());
           stmt.setDouble(2, unidadecomercial.getValor());
  
         
         stmt.setInt(3,unidadecomercial.getIdUnidadeComercial());
      
         stmt.execute();
       
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Unidadecomercial invalida" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerUnidadecomercial(int id) {
       
         String DELETE = "DELETE FROM unidadecomercial WHERE idUnidadeComercial=?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir Unidadecomercial do banco de" + "dados " + e.getMessage()); } }

public Unidadecomercial getEmpresaById(int id) {
    
    String LISTBYID = "SELECT * FROM unidadecomercial WHERE idUnidadeComercial=?";
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
   Unidadecomercial unidadecomercial = new Unidadecomercial();
            unidadecomercial.setIdUnidadeComercial(rs.getInt("idUnidadeComercial"));
             unidadecomercial.setSigla(rs.getString("sigla"));     
             unidadecomercial.setValor(rs.getDouble("valor"));
  
             
    return unidadecomercial;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Unidadecomercial" + e.getMessage()); 
    } 
        return null;
     }
public Unidadecomercial getUnidadecomercialByNome(String nome) {
    
    String LISTBYID = "SELECT * FROM unidadecomercial WHERE descricao=?";
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
   Unidadecomercial unidadecomercial = new Unidadecomercial();
             unidadecomercial.setIdUnidadeComercial(rs.getInt("idUnidadeComercial"));
             unidadecomercial.setSigla(rs.getString("sigla"));     
             unidadecomercial.setValor(rs.getDouble("valor"));
          
    return unidadecomercial;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Unidadecomercial" + e.getMessage()); 
    } 
        return null;
     }

    
}
