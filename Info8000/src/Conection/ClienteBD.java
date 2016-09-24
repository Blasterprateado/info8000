/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.Cliente;
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
public class ClienteBD {

  
   
    public List<Cliente> getLista() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<Cliente> clientes = new ArrayList<Cliente>();
         PreparedStatement stmt = connection.prepareStatement("select * from cliente");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto cliente
             Cliente cliente = new Cliente();
             cliente.setId(resultado.getInt("id"));
             cliente.setNome(resultado.getString("nome"));
             cliente.setEndereco(resultado.getString("endereco"));
             cliente.setBairro(resultado.getString("bairro"));
             cliente.setNumero(resultado.getInt("numero"));
             cliente.setTelefone(resultado.getString("telefone"));
             cliente.setCpf(resultado.getString("cpf"));
             cliente.setRg(resultado.getString("rg"));
             cliente.setCidade(resultado.getInt("id_cidade"));
           // adicionando o objeto à lista
            clientes.add(cliente);
         }
         resultado.close();
         stmt.close();
         return clientes;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarCliente(Cliente cliente) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into cliente " +
             "(nome,endereco,bairro,numero,telefone,cpf,id_cidade,rg)" +
             " values (?,?,?,?,?,?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,cliente.getNome());
         stmt.setString(2,cliente.getEndereco());
         stmt.setString(3,cliente.getBairro());
         stmt.setInt(4,cliente.getNumero());
         stmt.setString(5,cliente.getTelefone());
         stmt.setString(6,cliente.getCpf());
         stmt.setInt(7,cliente.getCidade());
         stmt.setString(8,cliente.getRg());
         
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
     
     public void AtualizaCliente(Cliente cliente) throws Exception {
         System.out.println(cliente);
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();

      String UPDATE = "UPDATE cliente SET nome=?,endereco=?,bairro=?,numero=?,telefone=?,cpf=?,id_cidade=?,rg=? WHERE id=?";


 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
 
         // seta os valores
         System.out.println(cliente.getNome());
         stmt.setString(1,cliente.getNome());
         stmt.setString(2,cliente.getEndereco());
         stmt.setString(3,cliente.getBairro());
         stmt.setInt(4,cliente.getNumero());
         stmt.setString(5,cliente.getTelefone());
         stmt.setString(6,cliente.getCpf());
         stmt.setInt(7,cliente.getCidade());
         stmt.setString(8,cliente.getRg());
         stmt.setInt(9,cliente.getId());
         
         stmt.execute();
        
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Cliente invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerCliente(int id) {
       
         String DELETE = "DELETE FROM cliente WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir cliente do banco de" + "dados " + e.getMessage()); } }
     
     
     
 public Cliente getClienteByNomeTel(Cliente c) {
     final String LISTBYNOMEFONE = "SELECT * FROM cliente WHERE nome=? AND telefone=?";
     PreparedStatement pstm = null;
     ResultSet rs = null;
     Cliente cliente = new Cliente();
     try { 
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection conn = conexaosingleton.conectar();
     pstm = conn.prepareStatement(LISTBYNOMEFONE);
     pstm.setString(1, c.getNome());
     pstm.setString(2, c.getTelefone());
     rs = pstm.executeQuery();
     while (rs.next()) {
         cliente.setId(rs.getInt("id"));
         cliente.setNome(rs.getString("nome"));
             cliente.setEndereco(rs.getString("endereco"));
             cliente.setBairro(rs.getString("bairro"));
             cliente.setNumero(rs.getInt("numero"));
             cliente.setTelefone(rs.getString("telefone"));
             cliente.setCpf(rs.getString("cpf"));
             cliente.setRg(rs.getString("rg"));
             cliente.setCidade(rs.getInt("id_cidade"));
     } 
     conexaosingleton.fecharConexao();
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "Erro ao listar Clientes" + e.getMessage()); 
     }
     return cliente;
 }
 
  public Cliente getClienteByNome(String nome) {
     final String LISTBYNOMEFONE = "SELECT * FROM cliente WHERE nome like ?";
     PreparedStatement pstm = null;
     ResultSet rs = null;
     Cliente cliente = new Cliente();
     try { 
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection conn = conexaosingleton.conectar();
     pstm = conn.prepareStatement(LISTBYNOMEFONE);
     pstm.setString(1, ""+nome+"%");
     
     rs = pstm.executeQuery();
     while (rs.next()) {
         cliente.setId(rs.getInt("id"));
         cliente.setNome(rs.getString("nome"));
             cliente.setEndereco(rs.getString("endereco"));
             cliente.setBairro(rs.getString("bairro"));
             cliente.setNumero(rs.getInt("numero"));
             cliente.setTelefone(rs.getString("telefone"));
             cliente.setCpf(rs.getString("cpf"));
             cliente.setRg(rs.getString("rg"));
             cliente.setCidade(rs.getInt("id_cidade"));
     } 
     //conexaosingleton.fecharConexao();
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "Erro ao listar Clientes" + e.getMessage()); 
     }
     return cliente;
 }
public Cliente getClienteByCpf(String cpf) {
     final String LISTBYNOMEFONE = "SELECT * FROM cliente WHERE cpf=?";
     PreparedStatement pstm = null;
     ResultSet rs = null;
     Cliente cliente = new Cliente();
     try { 
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection conn = conexaosingleton.conectar();
     pstm = conn.prepareStatement(LISTBYNOMEFONE);
     pstm.setString(1, cpf);
     
     rs = pstm.executeQuery();
     while (rs.next()) {
         cliente.setId(rs.getInt("id"));
         cliente.setNome(rs.getString("nome"));
             cliente.setEndereco(rs.getString("endereco"));
             cliente.setBairro(rs.getString("bairro"));
             cliente.setNumero(rs.getInt("numero"));
             cliente.setTelefone(rs.getString("telefone"));
             cliente.setCpf(rs.getString("cpf"));
             cliente.setRg(rs.getString("rg"));
             cliente.setCidade(rs.getInt("id_cidade"));
     } 
     //conexaosingleton.fecharConexao();
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "Erro ao listar Clientes" + e.getMessage()); 
     }
     return cliente;
 }
public Cliente getClienteById(int id) {
    
    String LISTBYID = "SELECT * FROM cliente WHERE id=?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Cliente cliente = new Cliente();
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
    pstm.setInt(1, id);
    rs = pstm.executeQuery();
    while (rs.next()) {
        
             cliente.setId(rs.getInt("id"));
             cliente.setNome(rs.getString("nome"));
             cliente.setEndereco(rs.getString("endereco"));
             cliente.setBairro(rs.getString("bairro"));
             cliente.setNumero(rs.getInt("numero"));
             cliente.setTelefone(rs.getString("telefone"));
             cliente.setCpf(rs.getString("cpf"));
             cliente.setRg(rs.getString("rg"));
             cliente.setCidade(rs.getInt("id_cidade"));
           // adicionando o objeto à lista
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Clientes" + e.getMessage()); 
    } 
    return cliente; }


}


