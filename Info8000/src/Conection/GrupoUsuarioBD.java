/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;

import Objetos.GrupoUsuario;
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
public class GrupoUsuarioBD {

    private static GrupoUsuario grupoUsuario;
   ConectionSingleton conectionSingleton;
 static Connection connection;

    public GrupoUsuarioBD() {
        try {
            conectionSingleton = ConectionSingleton.getInstancia();
           connection = conectionSingleton.conectar();
        } catch (Exception ex) {
            Logger.getLogger(GrupoUsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
   
   
   
   
public List<GrupoUsuario> getGrupos() throws Exception {
      List<GrupoUsuario> list = new ArrayList<>();
            
                 PreparedStatement stmt = connection.prepareStatement("select * from gruposusuarios");
                  ResultSet resultado = stmt.executeQuery();
                  
                  while(resultado.next()){
                      grupoUsuario = new GrupoUsuario();
                      
                      grupoUsuario.setId(resultado.getInt("id"));
                      grupoUsuario.setNome(resultado.getString("nome"));
                      grupoUsuario.setCadCliente(resultado.getBoolean("cadCliente"));
                      grupoUsuario.setCadProduto(resultado.getBoolean("cadProduto"));
                      grupoUsuario.setCadFornecedor(resultado.getBoolean("cadFornecedor"));
                      grupoUsuario.setCadEmpresa(resultado.getBoolean("cadEmpresa"));
                      grupoUsuario.setCadFuncionario(resultado.getBoolean("cadFuncionario"));
                      grupoUsuario.setCadServico(resultado.getBoolean("cadServico"));
                      grupoUsuario.setCaixa(resultado.getBoolean("caixa"));
                      grupoUsuario.setContaPagar(resultado.getBoolean("contaPagar"));
                      grupoUsuario.setContaReceber(resultado.getBoolean("contaReceber"));
                      grupoUsuario.setVenda(resultado.getBoolean("venda"));
                      grupoUsuario.setOs(resultado.getBoolean("os"));
                      grupoUsuario.setRelatorio(resultado.getBoolean("relatorio"));
                      grupoUsuario.setContaContabil(resultado.getBoolean("contaContabil"));
                      grupoUsuario.setConfiguracao(resultado.getBoolean("configuracao"));
                      
                      list.add(grupoUsuario);
                      
                      
                  }
                     resultado.close();
         stmt.close();
                
            
               
      
    return list;

}
public GrupoUsuario getGruposById(int id) throws Exception {
      
            
                 PreparedStatement stmt = connection.prepareStatement("select * from gruposusuarios where id=?");
                 stmt.setInt(1, id);
                  ResultSet resultado = stmt.executeQuery();
                  
                  while(resultado.next()){
                      grupoUsuario = new GrupoUsuario();
                      
                      grupoUsuario.setId(resultado.getInt("id"));
                      grupoUsuario.setNome(resultado.getString("nome"));
                      grupoUsuario.setCadCliente(resultado.getBoolean("cadCliente"));
                      grupoUsuario.setCadProduto(resultado.getBoolean("cadProduto"));
                      grupoUsuario.setCadFornecedor(resultado.getBoolean("cadFornecedor"));
                      grupoUsuario.setCadEmpresa(resultado.getBoolean("cadEmpresa"));
                      grupoUsuario.setCadFuncionario(resultado.getBoolean("cadFuncionario"));
                      grupoUsuario.setCadServico(resultado.getBoolean("cadServico"));
                      grupoUsuario.setCaixa(resultado.getBoolean("caixa"));
                      grupoUsuario.setContaPagar(resultado.getBoolean("contaPagar"));
                      grupoUsuario.setContaReceber(resultado.getBoolean("contaReceber"));
                      grupoUsuario.setVenda(resultado.getBoolean("venda"));
                      grupoUsuario.setOs(resultado.getBoolean("os"));
                      grupoUsuario.setRelatorio(resultado.getBoolean("relatorio"));
                      grupoUsuario.setContaContabil(resultado.getBoolean("contaContabil"));
                      grupoUsuario.setConfiguracao(resultado.getBoolean("configuracao"));
                      
                  
                      
                      
                  }
                     resultado.close();
         stmt.close();
                
            
               
      
    return grupoUsuario;

}













public GrupoUsuario getGrupoUsuario(){
    return grupoUsuario;
    
}

public void adicionarGrupoUsuario (GrupoUsuario grupoUsuario) throws Exception {
        
     String sql = "insert into gruposusuarios " + "(nome,cadCliente,cadProduto,cadFornecedor,cadEmpresa,cadFuncionario,cadServico,caixa,contaPagar,contaReceber,venda,os,relatorio,contaContabil,configuracao)" +
             " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,grupoUsuario.getNome());
         stmt.setBoolean(2,grupoUsuario.getCadCliente());
         stmt.setBoolean(3,grupoUsuario.getCadProduto());
         stmt.setBoolean(4,grupoUsuario.getCadFornecedor());
         
         stmt.setBoolean(5,grupoUsuario.getCadEmpresa());
         stmt.setBoolean(6,grupoUsuario.getCadFuncionario());
         stmt.setBoolean(7,grupoUsuario.getCadServico());
         stmt.setBoolean(8,grupoUsuario.getCaixa());
         stmt.setBoolean(9,grupoUsuario.getContaPagar());
         stmt.setBoolean(10,grupoUsuario.getContaReceber());
         
          stmt.setBoolean(11,grupoUsuario.getVenda());
         stmt.setBoolean(12,grupoUsuario.getOs());
         stmt.setBoolean(13,grupoUsuario.getRelatorio());
          stmt.setBoolean(14,grupoUsuario.getContaContabil());
           stmt.setBoolean(15,grupoUsuario.getConfiguracao());
         
         
         
         stmt.execute();
        
         stmt.close();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }
public void updateGrupoUsuario (GrupoUsuario grupoUsuario) throws Exception {
        
    String sql = "update gruposusuarios set " + "nome=?,cadCliente=?,cadProduto=?,cadFornecedor=?,cadEmpresa=?,cadFuncionario=?,cadServico=?,caixa=?,contaPagar=?,contaReceber=?,venda=?,os=?,relatorio=?,contaContabil=?,configuracao=? where id=?";
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
        stmt.setString(1,grupoUsuario.getNome());
         stmt.setBoolean(2,grupoUsuario.getCadCliente());
         stmt.setBoolean(3,grupoUsuario.getCadProduto());
         stmt.setBoolean(4,grupoUsuario.getCadFornecedor());
         
         stmt.setBoolean(5,grupoUsuario.getCadEmpresa());
         stmt.setBoolean(6,grupoUsuario.getCadFuncionario());
         stmt.setBoolean(7,grupoUsuario.getCadServico());
         stmt.setBoolean(8,grupoUsuario.getCaixa());
         stmt.setBoolean(9,grupoUsuario.getContaPagar());
         stmt.setBoolean(10,grupoUsuario.getContaReceber());
         
          stmt.setBoolean(11,grupoUsuario.getVenda());
         stmt.setBoolean(12,grupoUsuario.getOs());
         stmt.setBoolean(13,grupoUsuario.getRelatorio());
          stmt.setBoolean(14,grupoUsuario.getContaContabil());
           stmt.setBoolean(15,grupoUsuario.getConfiguracao());
         stmt.setInt(16,grupoUsuario.getId());
         
         
         stmt.execute();
        
         stmt.close();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }


public void deletarGrupo(int id){
    
   String sql ="DELETE FROM gruposusuarios WHERE id=?"; 
  
        try {
              PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GrupoUsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir Grupo de Usuarios");
        }
}



}



