/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;

import Objetos.Itens_Os;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos
 */
public class Itens_OsBD {
      public List<Itens_Os> getListaItens_Os() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<Itens_Os> itens = new ArrayList<Itens_Os>();
         PreparedStatement stmt = connection.prepareStatement("select * from itens_os)");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             Itens_Os itensos;
              itensos = new Itens_Os();
             itensos.setId(resultado.getInt("id"));
             itensos.setDesc(resultado.getString("descricao"));
             itensos.setValor(resultado.getDouble("valor"));
             itensos.setId_os(resultado.getInt("id_os"));
            
           // adicionando o objeto à lista
            itens.add(itensos);
         }
         resultado.close();
         stmt.close();
         return itens;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarItem(Itens_Os item) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into itens_os (descricao,valor,id_os) values(?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,item.getDesc());
         stmt.setDouble(2,item.getValor());
         stmt.setInt(3,item.getId_os());
         
         
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
     
     public void AtualizaItem(Itens_Os item) throws Exception {
            System.out.println(item.getId());
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     
      String UPDATE = "UPDATE itens_os SET descricao=?,valor=?,id_os=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
        stmt.setString(1,item.getDesc());
         stmt.setDouble(2,item.getValor());
         stmt.setInt(3, item.getId_os());
         stmt.setDouble(4,item.getId());
         
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
       
         String DELETE = "DELETE FROM itens_os WHERE ID =?";
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
       public void removerItemByIdOs(int id) {
       
         String DELETE = "DELETE FROM itens_os WHERE id_os =?";
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

public Itens_Os getItemById(int id) {
    
    String LISTBYID = "SELECT * FROM itens_os WHERE id=?";
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
      Itens_Os itensos;
              itensos = new Itens_Os();
             itensos.setId(rs.getInt("id"));
             itensos.setDesc(rs.getString("descricao"));
             itensos.setValor(rs.getDouble("valor"));
             itensos.setId_os(rs.getInt("id_os"));
    return itensos;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar itens" + e.getMessage()); 
    } 
        return null;
     }

public List<Itens_Os> getItemByIdOS(int id) {
    List<Itens_Os> itens = new ArrayList<>();
    String LISTBYID = "SELECT * FROM itens_os WHERE id_os=?";
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
      Itens_Os itensos;
              itensos = new Itens_Os();
             itensos.setId(rs.getInt("id"));
             itensos.setDesc(rs.getString("descricao"));
             itensos.setValor(rs.getDouble("valor"));
             itensos.setId_os(rs.getInt("id_os"));
   
           itens.add(itensos);
    } 
     return itens;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar itens" + e.getMessage()); 
    } 
        return null;
     }


    
    
}
