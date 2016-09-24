/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class VendaBD {
    
    public int getUltimaID() throws Exception {
    int id=0;
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
       
         PreparedStatement stmt = connection.prepareStatement("select MAX(id) as 'id' from venda");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
           
             id=resultado.getInt("id");
         }
         resultado.close();
         stmt.close();
         return id;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
    
    
    public List<Venda> getListaVenda() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<Venda> vendas = new ArrayList<Venda>();
         PreparedStatement stmt = connection.prepareStatement("select * from venda");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             Venda venda = new Venda();
             venda.setId(resultado.getInt("id"));
             venda.setCliente(resultado.getString("cliente"));
             venda.setFuncionario(resultado.getString("funcionario"));
             venda.setCaixa(resultado.getString("caixa"));
             venda.setData_venda(resultado.getTimestamp("data"));
             venda.setValor_venda(resultado.getDouble("valor"));
             venda.setId_Cliente(resultado.getInt("id_cliente"));
             venda.setId_funcionario(resultado.getInt("id_vendedor"));
             venda.setSituacao(resultado.getInt("situacao"));
           // adicionando o objeto à lista
           vendas.add(venda);
         }
         resultado.close();
         stmt.close();
         return vendas;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
    public List<Integer> getListaVendaID() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<Integer> vendas = new ArrayList<Integer>();
         PreparedStatement stmt = connection.prepareStatement("select * from venda");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             Venda venda = new Venda();
             venda.setId(resultado.getInt("id"));
             
           // adicionando o objeto à lista
           vendas.add(venda.getId());
         }
         resultado.close();
         stmt.close();
         return vendas;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public Integer adicionarVenda(Venda venda) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
        
     int id = 0;  
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement("insert into venda (cliente,funcionario,caixa,data,valor,id_cliente,id_vendedor,situacao) values (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,venda.getCliente());
         stmt.setString(2,venda.getFuncionario());
         stmt.setString(3,venda.getCaixa());
         
         stmt.setDate(4, (Date) venda.getData_venda());
         stmt.setDouble(5,venda.getValor_venda());
         stmt.setInt(6, venda.getId_Cliente());
          stmt.setInt(7, venda.getId_funcionario());
          stmt.setInt(8, venda.getSituacao());
          stmt.execute();
         ResultSet rs = stmt.getGeneratedKeys();  
    
    if(rs.next()){  
        id = rs.getInt(1);  
    }   
        
         stmt.close();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
         return id;
 }
     public static java.sql.Timestamp getCurrentTimeStamp() {
 
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
 
	}
     
     public void AtualizaVenda(Venda venda) throws Exception {
            System.out.println(venda.getId());
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     
      String UPDATE = "UPDATE venda SET cliente=?,funcionario=?,caixa=?,valor=?,id_cliente=?,id_vendedor=?,situacao=? WHERE id=?";

     try {
         // prepared statement para atualizar
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
         stmt.setString(1,venda.getCliente());
         stmt.setString(2,venda.getFuncionario());
         stmt.setString(3,venda.getCaixa());
         stmt.setDouble(4,venda.getValor_venda());
         stmt.setInt(5, venda.getId_Cliente());
         stmt.setInt(6, venda.getId_funcionario());
         stmt.setInt(7, venda.getSituacao());
         stmt.setInt(8,venda.getId());
         
         
         System.out.println(venda.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(venda);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Venda invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerProduto(int id) {
       
         String DELETE = "DELETE FROM venda WHERE id =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir venda do banco de" + "dados " + e.getMessage()); } }

public Venda getVendaById(int id) {
    
    String LISTBYID = "SELECT * FROM venda WHERE id=?";
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
       
         Venda venda = new Venda();
         
             venda.setId(rs.getInt("id"));
            System.out.println(venda.getId()); 
             venda.setCliente(rs.getString("cliente"));
             venda.setFuncionario(rs.getString("funcionario"));
             venda.setValor_venda(rs.getDouble("valor"));
             venda.setData_venda(rs.getTimestamp("data"));
             venda.setCaixa(rs.getString("caixa"));
             venda.setId_Cliente(rs.getInt("id_cliente"));
             venda.setId_funcionario(rs.getInt("id_vendedor"));
             venda.setSituacao(rs.getInt("situacao"));
          System.out.println(venda.getCliente());
    return venda;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar vendas" + e.getMessage()); 
    } 
        return null;
     }

}
