/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.Servicos;
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
 * @author PauloHenrique
 */
public class ServicosBD {


    
   public List<Servicos> getListaServicos() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<Servicos> classes = new ArrayList<Servicos>();
         PreparedStatement stmt = connection.prepareStatement("select * from servicos");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             Servicos servicos = new Servicos();
             servicos.setId(resultado.getInt("id"));
             servicos.setDescricao(resultado.getString("descricao"));             
             servicos.setValor(resultado.getDouble("valor"));
             servicos.setPcusto(resultado.getDouble("pcusto"));
             
           // adicionando o objeto à lista
           classes.add(servicos);
         }
         resultado.close();
         stmt.close();
         return classes;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarServicos (Servicos servicos) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into servicos " + "(descricao, valor, pcusto)" + " values (?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,servicos.getDescricao());
         stmt.setDouble(2,servicos.getValor());
         stmt.setDouble(3,servicos.getPcusto());
       
         
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
     
     public void AtualizaServicos (Servicos servicos) throws Exception {
         //   System.out.println(CaixaBD.getId()); // ID N RECONHECE !!!!!!!!!!!
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         

     String UPDATE = "UPDATE servicos SET descricao=?, valor=?,pcusto=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
           stmt.setString(1,servicos.getDescricao());
           stmt.setDouble(2,servicos.getValor());
           stmt.setDouble(3,servicos.getPcusto());
         
         stmt.setInt(4,servicos.getId());
         
         System.out.println(servicos.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(servicos);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Servico invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerServicos(int id) {
       
         String DELETE = "DELETE FROM servicos WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir Servicos do banco de" + "dados " + e.getMessage()); } }

public Servicos getServicosSById(int id) {
    
    String LISTBYID = "SELECT * FROM servicos WHERE id=?";
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
   Servicos servicos = new Servicos();
             servicos.setId(rs.getInt("id"));
             servicos.setDescricao(rs.getString("descricao"));
             servicos.setValor(rs.getDouble("valor"));
             servicos.setPcusto(rs.getDouble("pcusto"));
             
          System.out.println(servicos.getId());
    return servicos;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Servicos" + e.getMessage()); 
    } 
        return null;
     }


    
}
