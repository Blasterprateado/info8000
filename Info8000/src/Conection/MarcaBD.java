/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.Marca;
import java.sql.Connection;
import java.sql.Date;
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
public class MarcaBD {


    
   public List<Marca> getListaMarca() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<Marca> classes = new ArrayList<Marca>();
         PreparedStatement stmt = connection.prepareStatement("select * from marca");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             Marca marca = new Marca();
             marca.setId(resultado.getInt("id"));
             marca.setDescricao(resultado.getString("descricao"));             
             
             
           // adicionando o objeto à lista
           classes.add(marca);
         }
         resultado.close();
         stmt.close();
         return classes;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarMarca (Marca marca) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into marca " + "(descricao)" + " values (?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,marca.getDescricao());
    
       
         
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
     
     public void AtualizaMarca (Marca marca) throws Exception {
         //   System.out.println(CaixaBD.getId()); // ID N RECONHECE !!!!!!!!!!!
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         

     String UPDATE = "UPDATE marca SET descricao=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
           stmt.setString(1,marca.getDescricao());
  
         
         stmt.setInt(2,marca.getId());
         
         System.out.println(marca.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(marca);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Marca invalida" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerMarca(int id) {
       
         String DELETE = "DELETE FROM marca WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir Marca do banco de" + "dados " + e.getMessage()); } }

public Marca getEmpresaById(int id) {
    
    String LISTBYID = "SELECT * FROM marca WHERE id=?";
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
   Marca marca = new Marca();
             marca.setId(rs.getInt("id"));
             marca.setDescricao(rs.getString("descricao"));
  
             
          System.out.println(marca.getId());
    return marca;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Marca" + e.getMessage()); 
    } 
        return null;
     }
public Marca getMarcaByNome(String nome) {
    
    String LISTBYID = "SELECT * FROM marca WHERE descricao=?";
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
   Marca marca = new Marca();
             marca.setId(rs.getInt("id"));
             marca.setDescricao(rs.getString("descricao"));
  
             
          System.out.println(marca.getId());
    return marca;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Marca" + e.getMessage()); 
    } 
        return null;
     }

    
}
