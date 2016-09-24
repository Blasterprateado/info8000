/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.Caixa;
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
public class CaixaBD {
public Caixa getUltimoCaixaById(int id) {
    
    String LISTBYID = "SELECT * FROM caixa ORDER BY id DESC LIMIT 1";
    PreparedStatement pstm = null;
    ResultSet rs = null;
   
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
   
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
   Caixa caixa = new Caixa();
             caixa.setId(rs.getInt("id"));
             caixa.setDescricao(rs.getString("descricao"));
             caixa.setValorinicial(rs.getDouble("valor_inicial"));
             caixa.setValoratual(rs.getDouble("valor_final"));
             caixa.setData(rs.getTimestamp("data"));
             caixa.setStatus(rs.getString("status"));
          System.out.println(caixa.getDescricao());
    return caixa;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Caixas" + e.getMessage()); 
    } 
        return null;
     }
   /* private static boolean getId() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
   public List<Caixa> getListaClasseCaixa() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<Caixa> classes = new ArrayList<Caixa>();
         PreparedStatement stmt = connection.prepareStatement("select * from caixa");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             Caixa caixa = new Caixa();
             caixa.setId(resultado.getInt("id"));
             caixa.setDescricao(resultado.getString("descricao"));
             caixa.setValorinicial(resultado.getDouble("valor_inicial"));
             caixa.setValoratual(resultado.getDouble("valor_final"));
             caixa.setData(resultado.getDate("data"));
             caixa.setStatus(resultado.getString("status"));
             
             
             
           // adicionando o objeto à lista
           classes.add(caixa);
         }
         resultado.close();
         stmt.close();
         return classes;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarClasseCaixa(Caixa caixa) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into caixa " + "(descricao, valor_inicial, valor_final, data, status)" + " values (?,?,?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,caixa.getDescricao());
         stmt.setDouble(2,caixa.getValorinicial());
         stmt.setDouble(3,caixa.getValoratual());
         stmt.setDate(4, (Date) caixa.getData());
         stmt.setString(5,caixa.getStatus());
    
       
         
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
     
     public void AtualizaCAixa(Caixa caixa) throws Exception {
//            System.out.println(CaixaBD.getId());
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         

     String UPDATE = "UPDATE caixa SET descricao=?, valor_inicial=?, valor_final=?, data=?, status=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
           stmt.setString(1,caixa.getDescricao());
           stmt.setDouble(2, caixa.getValorinicial());
           stmt.setDouble(3, caixa.getValoratual());
           stmt.setDate(4, (Date) caixa.getData());
           stmt.setString(5, caixa.getStatus());
           
         
         stmt.setInt(6,caixa.getId());
         
         System.out.println(caixa.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(caixa);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Caixa invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerClasseCaixa(int id) {
       
         String DELETE = "DELETE FROM caixa WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir Caixa do banco de" + "dados " + e.getMessage()); } }

public Caixa getClasseCaixaById(int id) {
    
    String LISTBYID = "SELECT * FROM caixa WHERE id=?";
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
   Caixa caixa = new Caixa();
             caixa.setId(rs.getInt("id"));
             caixa.setDescricao(rs.getString("descricao"));
             caixa.setValorinicial(rs.getDouble("valor_inicial"));
             caixa.setValoratual(rs.getDouble("valor_final"));
             caixa.setData(rs.getDate("data"));
             caixa.setStatus(rs.getString("Status"));
             
          System.out.println(caixa.getId());
    return caixa;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Caixas" + e.getMessage()); 
    } 
        return null;
     }

    private String getDescricao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
