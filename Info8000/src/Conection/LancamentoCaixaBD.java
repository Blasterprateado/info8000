/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;

import Objetos.LancamentoCaixa;
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
public class LancamentoCaixaBD {
     public List<LancamentoCaixa> getListalancamentoCaixa() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<LancamentoCaixa> lancamentos = new ArrayList<LancamentoCaixa>();
         PreparedStatement stmt = connection.prepareStatement("select * from lancamentocaixa");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             LancamentoCaixa lancamento = new LancamentoCaixa();
             lancamento.setId(resultado.getInt("id"));
             lancamento.setTipo(resultado.getString("classelancamento"));
             lancamento.setObs(resultado.getString("observacao"));
             lancamento.setValor(resultado.getDouble("valor"));
             lancamento.setData(resultado.getTimestamp("data"));
             lancamento.setId_venda(resultado.getInt("id_venda"));
             
           // adicionando o objeto à lista
           lancamentos.add(lancamento);
         }
         resultado.close();
         stmt.close();
         return lancamentos;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarlancamentoCaixa(LancamentoCaixa lancamento) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into lancamentocaixa " +
             "(classelancamento,observacao,valor,id_venda)" +
             " values (?,?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,lancamento.getTipo());
         stmt.setString(2,lancamento.getObs());
         stmt.setDouble(3,lancamento.getValor());
         stmt.setInt(4,lancamento.getId_venda());
    
       
         
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
     
  
     public void AtualizaLancamento(LancamentoCaixa lancamento) throws Exception {
            System.out.println(lancamento.getId());
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     
      String UPDATE = "UPDATE lancamentocaixa SET classelancamento=?,observacao=?,valor=?,id_venda=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
           stmt.setString(1,lancamento.getTipo());
         stmt.setString(2,lancamento.getObs());
         stmt.setDouble(3,lancamento.getValor());
          stmt.setInt(4,lancamento.getId_venda());
         stmt.setInt(5,lancamento.getId());
         
         System.out.println(lancamento.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(lancamento);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "lancamentoCaixa invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerlancamentoCaixa(int id) {
       
         String DELETE = "DELETE FROM lancamentocaixa WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir lancamentoCaixa do banco de" + "dados " + e.getMessage()); 
         } 
     }
     public void removerlancamentoCaixaByIdVenda(int id_venda) {
       
         String DELETE = "DELETE FROM lancamentocaixa WHERE id_venda=?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id_venda); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir lancamentoCaixa do banco de" + "dados " + e.getMessage()); 
         } 
     }

public LancamentoCaixa getlancamentoCaixaById(int id) {
    
    String LISTBYID = "SELECT * FROM lancamentocaixa WHERE id=?";
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
   LancamentoCaixa lancamento = new LancamentoCaixa();
             lancamento.setId(rs.getInt("id"));
             lancamento.setTipo(rs.getString("classelancamento"));
             lancamento.setObs(rs.getString("observacao"));
             lancamento.setValor(rs.getDouble("valor"));
             lancamento.setData(rs.getTimestamp("data"));
              lancamento.setId_venda(rs.getInt("id_venda"));
          System.out.println(lancamento.getId());
    return lancamento;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar lancamentoCaixas" + e.getMessage()); 
    } 
        return null;
     }



public LancamentoCaixa getlancamentoCaixaByidVenda(int id) {
    
    String LISTBYID = "SELECT * FROM lancamentocaixa WHERE id_venda=?";
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
   LancamentoCaixa lancamento = new LancamentoCaixa();
             lancamento.setId(rs.getInt("id"));
             lancamento.setTipo(rs.getString("classelancamento"));
             lancamento.setObs(rs.getString("observacao"));
             lancamento.setValor(rs.getDouble("valor"));
             lancamento.setData(rs.getTimestamp("data"));
              lancamento.setId_venda(rs.getInt("id_venda"));
          System.out.println(lancamento.getId());
    return lancamento;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar lancamentoCaixas" + e.getMessage()); 
    } 
        return null;
     }


}









