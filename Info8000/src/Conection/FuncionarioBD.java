/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.Funcionario;
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
public class FuncionarioBD {
 
    public List<Funcionario> getListaFuncionario() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<Funcionario> funcionarios = new ArrayList<Funcionario>();
         PreparedStatement stmt = connection.prepareStatement("select * from funcionario");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             Funcionario funcionario = new Funcionario();
             funcionario.setId(resultado.getInt("id"));
             funcionario.setNome(resultado.getString("nome"));
             funcionario.setEndereco(resultado.getString("endereco"));
             funcionario.setSalario(resultado.getDouble("salario"));
             funcionario.setPermissao(resultado.getString("permissao"));
             funcionario.setUsuario(resultado.getString("usuario"));
             funcionario.setSenha(resultado.getString("senha"));
             funcionario.setDataAdm(resultado.getDate("data_ad"));
             funcionario.setDataDm(resultado.getDate("data_d"));
             funcionario.setBairro(resultado.getString("bairro"));
             funcionario.setNumero(resultado.getString("numero"));
             funcionario.setCidade(resultado.getInt("id_cidade"));
             funcionario.setRg(resultado.getString("rg"));
             funcionario.setCpf(resultado.getString("cpf"));
             funcionario.setTel(resultado.getString("tel"));
             
           // adicionando o objeto à lista
           funcionarios.add(funcionario);
         }
         resultado.close();
         stmt.close();
         return funcionarios;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarFuncionario(Funcionario funcionario) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into funcionario " +
             "(nome,endereco,salario,permissao,usuario,senha,data_d,data_ad,bairro,numero,id_cidade,rg,cpf,tel)" +
             " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,funcionario.getNome());
         stmt.setString(2,funcionario.getEndereco());
         stmt.setDouble(3,funcionario.getSalario());
         stmt.setString(4,funcionario.getPermissao());
         stmt.setString(5,funcionario.getUsuario());
          stmt.setString(6,funcionario.getSenha());
          stmt.setDate(7, (Date) funcionario.getDataDm());
          stmt.setDate(8, (Date) funcionario.getDataAdm());
       stmt.setString(9,funcionario.getBairro());
       stmt.setString(10,funcionario.getNumero());
       stmt.setInt(11,funcionario.getCidade());
       stmt.setString(12,funcionario.getRg());
       stmt.setString(13,funcionario.getCpf());
       stmt.setString(14, funcionario.getTel());
         
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
     
     public void AtualizaFuncionario(Funcionario funcionario) throws Exception {
            System.out.println(funcionario.getId());
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     
      String UPDATE = "UPDATE funcionario SET nome=?,endereco=?,salario=?,permissao=?,usuario=?,"
              + "senha=?,data_d=?,data_ad=?,bairro=?,numero=?,id_cidade=?,rg=?,cpf=?,tel=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores 1 representa nome pq é o primeiro parametro, e assim por diante
         
         stmt.setString(1,funcionario.getNome());
         stmt.setString(2,funcionario.getEndereco());
         stmt.setDouble(3,funcionario.getSalario());
         stmt.setString(4,funcionario.getPermissao());
         stmt.setString(5,funcionario.getUsuario());
         stmt.setString(6,funcionario.getSenha());
         stmt.setDate(7, (Date) funcionario.getDataDm());
         stmt.setDate(8, (Date) funcionario.getDataAdm());
         stmt.setString(9,funcionario.getBairro());
         stmt.setString(10, funcionario.getNumero());
         stmt.setInt(11, funcionario.getCidade());
         stmt.setString(12, funcionario.getRg());
         stmt.setString(13, funcionario.getCpf());
         stmt.setString(14, funcionario.getTel());
         stmt.setInt(15,funcionario.getId());
         
         System.out.println(funcionario.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(funcionario);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Funcionario invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerFuncionario(int id) {
       
         String DELETE = "DELETE FROM funcionario WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir Funcionario do banco de" + "dados " + e.getMessage()); } }

public Funcionario getFuncionarioById(int id) {
    
    String LISTBYID = "SELECT * FROM funcionario WHERE id=?";
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
   Funcionario funcionario = new Funcionario();
             funcionario.setId(rs.getInt("id"));
             funcionario.setNome(rs.getString("nome"));
             funcionario.setEndereco(rs.getString("endereco"));
             funcionario.setSalario(rs.getDouble("salario"));
             funcionario.setPermissao(rs.getString("permissao"));
             funcionario.setUsuario(rs.getString("usuario"));
             funcionario.setSenha(rs.getString("senha"));
             funcionario.setDataAdm(rs.getDate("data_ad"));
             funcionario.setDataDm(rs.getDate("data_d"));
             funcionario.setBairro(rs.getString("bairro"));
             funcionario.setNumero(rs.getString("numero"));
             funcionario.setCidade(rs.getInt("id_cidade"));
             funcionario.setCpf(rs.getString("cpf"));
             funcionario.setRg(rs.getString("rg"));
             funcionario.setTel(rs.getString("tel"));
             
          System.out.println(funcionario.getNome());
    return funcionario;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Funcionario" + e.getMessage()); 
    } 
        return null;
     }


public Funcionario getFuncionarioLogin(String user, String senha) throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         Funcionario funcionario = new Funcionario();
         PreparedStatement stmt = connection.prepareStatement("select * from funcionario where usuario=? and senha=?");
         stmt.setString(1, user);
         stmt.setString(2, senha);
         System.out.println("sql"+stmt);
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             
            
             funcionario.setId(resultado.getInt("id"));
             funcionario.setNome(resultado.getString("nome"));
             funcionario.setEndereco(resultado.getString("endereco"));
             funcionario.setSalario(resultado.getDouble("salario"));
             funcionario.setPermissao(resultado.getString("permissao"));
             funcionario.setUsuario(resultado.getString("usuario"));
             funcionario.setSenha(resultado.getString("senha"));
             funcionario.setDataAdm(resultado.getDate("data_ad"));
             funcionario.setDataDm(resultado.getDate("data_d"));
             funcionario.setBairro(resultado.getString("bairro"));
             funcionario.setNome(resultado.getString("numero"));
             funcionario.setCidade(resultado.getInt("id_cidade"));
             funcionario.setCpf(resultado.getString("cpf"));
             funcionario.setRg(resultado.getString("rg"));
             funcionario.setTel(resultado.getString("tel"));
         }
         resultado.close();
         stmt.close();
         if(funcionario!=null){
         return funcionario;
         }
         else{
             return null;
         }
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}

}
