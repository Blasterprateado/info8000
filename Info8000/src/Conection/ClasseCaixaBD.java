/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;



import Objetos.ClasseCaixa;
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
public class ClasseCaixaBD {
    
    public List<ClasseCaixa> GetListaClasseCaixa(){
            
            
            
            ConectionSingleton conexaosingleton;
            List<ClasseCaixa> listaClasses = new ArrayList<ClasseCaixa>();
    try {
        conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         PreparedStatement stmt = connection.prepareStatement("select * from classecaixa");
         ResultSet resultado = stmt.executeQuery();
         System.out.println(stmt);
         System.out.println(resultado);
         
       
  while (resultado.next()){
       ClasseCaixa classe= new ClasseCaixa();
      classe.setId(resultado.getInt("id"));
      classe.setDescricao(resultado.getString("descricao"));
      classe.setOperador(resultado.getString("operador"));
      listaClasses.add(classe);
   
    
        
    }
 stmt.close();
         resultado.close();
    } catch (Exception ex) {
//        Logger.getLogger(FormVendaAcai2.class.getName()).log(Level.SEVERE, null, ex);
    }
        return listaClasses;
}
    
    public void adicionarClasseCaixa(ClasseCaixa classecaixa) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();

         String sql = "insert into classecaixa " +
             "(descricao,operador)" +
             " values (?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,classecaixa.getDescricao());
         stmt.setString(2,classecaixa.getOperador());
        stmt.execute();
         stmt.close();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }
    
    public void AtualizaClasseCaixa(ClasseCaixa classecaixa) throws Exception {
            System.out.println(classecaixa.getId());
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         
         
      String UPDATE = "UPDATE classecaixa SET descricao=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
         stmt.setString(1,classecaixa.getDescricao());
         stmt.setInt(2,classecaixa.getId());
         System.out.println(classecaixa.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(classecaixa);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Classe Caixa invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerClasseCaixa(int id) {
       
         String DELETE = "DELETE FROM classecaixa WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir Classe Caixa do banco de" + "dados " + e.getMessage()); 
         } 
     }
    
    
    
    
    public ResultSet GetResultSetClasseCaixa(){
            
            
            
            ConectionSingleton conexaosingleton;
            List<ClasseCaixa> listaClasses = new ArrayList<ClasseCaixa>();
            ResultSet resultado= null;
    try {
        conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         PreparedStatement stmt = connection.prepareStatement("select * from classecaixa order by descricao ");
          resultado = stmt.executeQuery();
         System.out.println(stmt);
         System.out.println(resultado);
         
       
  while (resultado.next()){
       ClasseCaixa classe= new ClasseCaixa();
      classe.setId(resultado.getInt("id"));
      classe.setDescricao(resultado.getString("descricao"));
      
      listaClasses.add(classe);
   
    
        
    }
 stmt.close();
        
    } catch (Exception ex) {
//        Logger.getLogger(FormVendaAcai2.class.getName()).log(Level.SEVERE, null, ex);
    }
        return resultado;
        
}
    
    
    public ClasseCaixa getClasseCaixaById(int id) {
    
    String LISTBYID = "SELECT * FROM classecaixa WHERE id=?";
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
        System.out.println(rs.getInt("id"));
        System.out.println(rs.getString("descricao"));
   
         ClasseCaixa caixa = new ClasseCaixa();
         
             caixa.setId(rs.getInt("id"));
            System.out.println(caixa.getId()); 
             caixa.setDescricao(rs.getString("descricao"));
         
          System.out.println(caixa.getDescricao());
    return caixa;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Classes" + e.getMessage()); 
    } 
        return null;
     }


    
    
    public ClasseCaixa getClasseCaixaByDesc(String desc) {
    
    String LISTBYID = "SELECT * FROM classecaixa WHERE descricao=?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
   
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
    pstm.setString(1, desc);
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
        System.out.println(rs.getInt("id"));
        System.out.println(rs.getString("descricao"));
   
         ClasseCaixa caixa = new ClasseCaixa();
         
             caixa.setId(rs.getInt("id"));
            System.out.println(caixa.getId()); 
             caixa.setDescricao(rs.getString("descricao"));
         
          System.out.println(caixa.getDescricao());
    return caixa;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Classes" + e.getMessage()); 
    } 
        return null;
     }

    
    
    
    
}