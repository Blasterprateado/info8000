/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.CaixaDiario;
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
public class CaixaDiarioBD {
public Double getCaixaDiarioSoma() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
        Double valor=0.0;
         PreparedStatement stmt = connection.prepareStatement("select SUM(valor) from caixa_diario");
         ResultSet resultado = stmt.executeQuery();
         
         while (resultado.next()) {
            
             valor=resultado.getDouble("SUM(valor)");
             
             System.out.println(resultado.getDouble("SUM(valor)"));
             
           
         }
         resultado.close();
         stmt.close();
         return valor;
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null,"Caixa Fechado, é necessario abri-lo"+ e);
         throw new RuntimeException(e);
     }
}

  

public void fecharcaixa() throws Exception{
    
    try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
       PreparedStatement stmt = connection.prepareStatement("TRUNCATE TABLE caixa_diario;");
        stmt.executeUpdate();
            
         stmt.close();
        
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}









public List<CaixaDiario> getListaClasseCaixa() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<CaixaDiario> classes = new ArrayList<CaixaDiario>();
         PreparedStatement stmt = connection.prepareStatement("select * from caixa_diario");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             CaixaDiario caixaDiario = new CaixaDiario();
             caixaDiario.setId(resultado.getInt("id"));
             caixaDiario.setValor(resultado.getDouble("valor"));
             caixaDiario.setData_lancamento(resultado.getDate("data_lancamento"));
             caixaDiario.setTipolancamento(resultado.getString("tipolancamento"));         
             
           // adicionando o objeto à lista
           classes.add(caixaDiario);
         }
         resultado.close();
         stmt.close();
         return classes;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarClasseCaixa(CaixaDiario caixaDiario) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into caixa_diario " + "(valor,tipolancamento)" + " values (?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setDouble(1,caixaDiario.getValor());
       
         stmt.setString(2,caixaDiario.getTipolancamento());

       
         
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
     
     public void AtualizaCAixa(CaixaDiario caixaDiario) throws Exception {
           /* System.out.println(CaixaDiario.getId);*/ //ESTE GET ID NÃO ESTÁ SENDO RECONHECIDO!!!!!!!!!!!!!!!!!!
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     
      String UPDATE = "UPDATE caixa_diario SET valor=?,data_lancamento=?,tipolancamento=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
         stmt.setDouble(1,caixaDiario.getValor());
         stmt.setDate(2, (Date) caixaDiario.getData_lancamento());
         stmt.setString(3,caixaDiario.getTipolancamento());
         
         stmt.setInt(4,caixaDiario.getId());
         
         System.out.println(caixaDiario.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(caixaDiario);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Caixa Diario invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerClasseCaixa(int id) {
       
         String DELETE = "DELETE FROM caixa_diario WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir CaixaDiario do banco de" + "dados " + e.getMessage()); } }

public CaixaDiario getClasseCaixaById(int id) {
    
    String LISTBYID = "SELECT * FROM caixa_diario WHERE id=?";
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
   CaixaDiario caixaDiario = new CaixaDiario();
             caixaDiario.setId(rs.getInt("id"));
             caixaDiario.setValor(rs.getDouble("valor"));
             caixaDiario.setData_lancamento(rs.getDate("data_lancamento"));
             caixaDiario.setTipolancamento(rs.getString("tipolancamento"));
             
          System.out.println(caixaDiario.getId());
    return caixaDiario;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar ClasseCaixas" + e.getMessage()); 
    } 
        return null;
     }

    private String getDescricao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
