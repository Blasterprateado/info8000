/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.Empresas;
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
public class EmpresasBD {


    
   public List<Empresas> getListaEmpresas() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<Empresas> classes = new ArrayList<Empresas>();
         PreparedStatement stmt = connection.prepareStatement("select * from empresa");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             Empresas empresa = new Empresas();
             empresa.setId(resultado.getInt("id"));
             empresa.setNome(resultado.getString("nome"));             
            empresa.setEndereco(resultado.getString("end"));
             empresa.setBairro(resultado.getString("bairro"));
             empresa.setNumero(resultado.getInt("num"));
             empresa.setTelefone(resultado.getString("tel"));
             empresa.setCpf(resultado.getString("cnpj"));
             empresa.setRG(resultado.getString("ie"));
             empresa.setCidade(resultado.getString("cidade"));
             
             
           // adicionando o objeto à lista
           classes.add(empresa);
         }
         resultado.close();
         stmt.close();
         return classes;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarEmpresas (Empresas empresa) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into empresa " + "(nome,end,bairro,num,tel,cnpj,cidade,ie)" +
             " values (?,?,?,?,?,?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,empresa.getNome());
         stmt.setString(2,empresa.getEndereco());
         stmt.setString(3,empresa.getBairro());
         stmt.setInt(4,empresa.getNumero());
         stmt.setString(5,empresa.getTelefone());
         stmt.setString(6,empresa.getCpf());
         stmt.setString(7,empresa.getCidade());
         stmt.setString(8,empresa.getRG());
         
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
     
     public void AtualizaEmpresas (Empresas empresa) throws Exception {
         //   System.out.println(CaixaBD.getId()); // ID N RECONHECE !!!!!!!!!!!
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         

     String UPDATE = "UPDATE empresa SET nome=?,end=?,bairro=?,num=?,tel=?,cnpj=?,cidade=?,ie=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
           stmt.setString(1,empresa.getNome());
           
           stmt.setString(2,empresa.getEndereco());
           stmt.setString(3,empresa.getBairro());
           stmt.setInt(4,empresa.getNumero());
           stmt.setString(5,empresa.getTelefone());
           stmt.setString(6,empresa.getCpf());
           stmt.setString(7,empresa.getCidade());
           stmt.setString(8,empresa.getRG());
           stmt.setInt(9,empresa.getId());
           
         System.out.println(empresa.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(empresa);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Empresa invalida" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerEmpresas(int id) {
       
         String DELETE = "DELETE FROM empresa WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir Empresas do banco de" + "dados " + e.getMessage()); } }

public Empresas getEmpresaById(int id) {
    
    String LISTBYID = "SELECT * FROM empresa WHERE id=?";
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
   Empresas empresa = new Empresas();
             empresa.setId(rs.getInt("id"));
             empresa.setNome(rs.getString("nome"));             
            empresa.setEndereco(rs.getString("end"));
             empresa.setBairro(rs.getString("bairro"));
             empresa.setNumero(rs.getInt("num"));
             empresa.setTelefone(rs.getString("tel"));
             empresa.setCpf(rs.getString("cnpj"));
             empresa.setRG(rs.getString("ie"));
             empresa.setCidade(rs.getString("cidade"));
             
          System.out.println(empresa.getId());
    return empresa;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Empresas" + e.getMessage()); 
    } 
        return null;
     }


    
}
