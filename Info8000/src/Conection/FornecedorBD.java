/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.Fornecedor;
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
public class FornecedorBD {
 
    public List<Fornecedor> GetListaFornecedor(){
            
            
            
            ConectionSingleton conexaosingleton;
            List<Fornecedor> listaFornecedores = new ArrayList<Fornecedor>();
    try {
        conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         PreparedStatement stmt = connection.prepareStatement("select * from fornecedor");
         ResultSet resultado = stmt.executeQuery();
         System.out.println(stmt);
         System.out.println(resultado);
         
       
  while (resultado.next()){
       Fornecedor fornecedor= new Fornecedor();
      fornecedor.setId(resultado.getInt("id"));
      fornecedor.setNome(resultado.getString("nome"));
      fornecedor.setEndereco(resultado.getString("endereco"));
      fornecedor.setBairro(resultado.getString("bairro"));
      fornecedor.setNumero(resultado.getInt("numero"));
      fornecedor.setCidade(resultado.getString("cidade"));
      fornecedor.setCnpj(resultado.getString("cnpj"));
      fornecedor.setTelefone(resultado.getString("telefone"));
      listaFornecedores.add(fornecedor);
   
    
        
    }
 stmt.close();
         resultado.close();
    } catch (Exception ex) {
///        Logger.getLogger(FormVendaAcai2.class.getName()).log(Level.SEVERE, null, ex);
    }
        return listaFornecedores;
}
    
    public void adicionarFornecedor(Fornecedor fornecedor) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();

         String sql = "insert into fornecedor " +
             "(nome,endereco,bairro,numero,cidade,cnpj,telefone,ie)" +
             " values (?,?,?,?,?,?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,fornecedor.getNome());
         stmt.setString(2,fornecedor.getEndereco());
         stmt.setString(3,fornecedor.getBairro());
         stmt.setInt(4,fornecedor.getNumero());
         stmt.setString(5,fornecedor.getCidade());
         stmt.setString(6, fornecedor.getCnpj());
         stmt.setString(7,fornecedor.getTelefone());
         stmt.setString(8,fornecedor.getIe());
        stmt.execute();
         stmt.close();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }
    
    public void AtualizaFornecedor(Fornecedor fornecedor) throws Exception {
            System.out.println(fornecedor.getId());
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         
      String UPDATE = "UPDATE fornecedor SET nome=?,endereco=?,bairro=?,numero=?,cidade=?,cnpj=?,telefone=?,ie=?  WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
         stmt.setString(1,fornecedor.getNome());
         stmt.setString(2,fornecedor.getEndereco());
         stmt.setString(3,fornecedor.getBairro());
         stmt.setInt(4,fornecedor.getNumero());
         stmt.setString(5,fornecedor.getCidade());
         stmt.setString(6, fornecedor.getCnpj());
         stmt.setString(7, fornecedor.getTelefone());
         stmt.setString(8, fornecedor.getIe());
         stmt.setInt(9,fornecedor.getId());
         System.out.println(fornecedor.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(fornecedor);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Fornecedor invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerFornecedor(int id) {
       
         String DELETE = "DELETE FROM fornecedor WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir Fornecedor do banco de" + "dados " + e.getMessage()); } }
    
    
    
    
    public ResultSet GetResultSetFornecedor(){
            
            
            
            ConectionSingleton conexaosingleton;
            List<Fornecedor> listaClasses = new ArrayList<Fornecedor>();
            ResultSet resultado= null;
    try {
        conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         PreparedStatement stmt = connection.prepareStatement("select * from fornecedor order by nome ");
          resultado = stmt.executeQuery();
         System.out.println(stmt);
         System.out.println(resultado);
         
       
  while (resultado.next()){
       Fornecedor fornecedor= new Fornecedor();
      fornecedor.setId(resultado.getInt("id"));
      fornecedor.setNome(resultado.getString("nome"));
      fornecedor.setEndereco(resultado.getString("endereco"));
      fornecedor.setBairro(resultado.getString("bairro"));
      fornecedor.setNumero(resultado.getInt("numero"));
      fornecedor.setCidade(resultado.getString("cidade"));
      fornecedor.setCnpj(resultado.getString("cnpj"));
      fornecedor.setTelefone(resultado.getString("telefone"));
      
      listaClasses.add(fornecedor);
   
    
        
    }
 stmt.close();
        
    } catch (Exception ex) {
//        Logger.getLogger(FormVendaAcai2.class.getName()).log(Level.SEVERE, null, ex);
    }
        return resultado;
        
}
    
    
    public Fornecedor getFornecedorById(int id) {
    
    String LISTBYID = "SELECT * FROM fornecedor WHERE id=?";
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
       
   Fornecedor fornecedor= new Fornecedor();
      fornecedor.setId(rs.getInt("id"));
      fornecedor.setNome(rs.getString("nome"));
      fornecedor.setEndereco(rs.getString("endereco"));
      fornecedor.setBairro(rs.getString("bairro"));
      fornecedor.setNumero(rs.getInt("numero"));
      fornecedor.setCidade(rs.getString("cidade"));
      fornecedor.setCnpj(rs.getString("cnpj"));
      fornecedor.setTelefone(rs.getString("telefone"));
      fornecedor.setIe(rs.getString("ie"));
          System.out.println(fornecedor.getNome());
    return fornecedor;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Fornecedores" + e.getMessage()); 
    } 
        return null;
     }
public Fornecedor getFornecedorByNome(String nome) {
    
    String LISTBYNOME = "SELECT * FROM fornecedor WHERE nome=?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
   
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYNOME);
    pstm.setString(1, nome);
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
       
   Fornecedor fornecedor= new Fornecedor();
      fornecedor.setId(rs.getInt("id"));
      fornecedor.setNome(rs.getString("nome"));
      fornecedor.setEndereco(rs.getString("endereco"));
      fornecedor.setBairro(rs.getString("bairro"));
      fornecedor.setNumero(rs.getInt("numero"));
      fornecedor.setCidade(rs.getString("cidade"));
      fornecedor.setCnpj(rs.getString("cnpj"));
      fornecedor.setTelefone(rs.getString("telefone"));
      fornecedor.setIe(rs.getString("ie"));
      
          System.out.println(fornecedor.getNome());
    return fornecedor;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Fornecedores" + e.getMessage()); 
    } 
        return null;
     }

    
}
