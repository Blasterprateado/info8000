/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.OrdemServico;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class OrdemServicoBD {


public int getUltimaID() throws Exception {
    int id=0;
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
       
         PreparedStatement stmt = connection.prepareStatement("select MAX(id) as 'id' from ordem_servico");
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


    
   public List<OrdemServico> getListaOrdemServico() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<OrdemServico> classes = new ArrayList<OrdemServico>();
         PreparedStatement stmt = connection.prepareStatement("select * from ordem_servico");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             OrdemServico ordem_servico = new OrdemServico();
             ordem_servico.setId(resultado.getInt("id"));
              ordem_servico.setCliente(resultado.getString("cliente"));
               ordem_servico.setFuncionario(resultado.getString("funcionario"));
                ordem_servico.setData_entrada(resultado.getTimestamp("data_entrada"));
                 ordem_servico.setValor(resultado.getDouble("valor"));
                  ordem_servico.setId_caixa(resultado.getInt("id_caixa"));
                   ordem_servico.setDescricao_equipamento(resultado.getString("desc_equipamento"));
                    ordem_servico.setDescricao_problema(resultado.getString("desc_problema"));
                    ordem_servico.setObservacao(resultado.getString("observacao"));
                    ordem_servico.setData_saida(resultado.getTimestamp("data_saida"));
                    ordem_servico.setStatus(resultado.getString("status"));
                    ordem_servico.setTipo_Atendimento(resultado.getString("tipo_atendimento"));
                    ordem_servico.setGarantia(resultado.getString("garantia"));
             
//             ordem_servico.setId_ordem(resultado.getInt("id_ordem"));             
             //ordem_servico.setId_servicos(resultado.getInt("id_servicos"));
             
             
           // adicionando o objeto à lista
           classes.add(ordem_servico);
         }
         resultado.close();
         stmt.close();
         return classes;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public int adicionarOrdemServico (OrdemServico ordem_servico) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         int id=0;
     String sql = "insert into ordem_servico "
     + "(cliente, funcionario,data_entrada, valor,id_caixa, desc_equipamento,desc_problema, observacao,data_saida, status,tipo_atendimento, garantia)" 
     + " values (?,?,?,?,?,?,?,?,?,?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
 
       
         stmt.setString(1,ordem_servico.getCliente());
         stmt.setString(2,ordem_servico.getFuncionario());
         stmt.setTimestamp(3,ordem_servico.getData_entrada());
         stmt.setDouble(4,ordem_servico.getValor());
         stmt.setInt(5,ordem_servico.getId_caixa());
         stmt.setString(6,ordem_servico.getDescricao_equipamento());
         stmt.setString(7,ordem_servico.getDescricao_problema());
         stmt.setString(8,ordem_servico.getObservacao());
         stmt.setTimestamp(9,ordem_servico.getData_saida());
         stmt.setString(10,ordem_servico.getStatus());
         stmt.setString(11,ordem_servico.getTipo_Atendimento());
         stmt.setString(12,ordem_servico.getGarantia());
         
    
       
         
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
     
     public void AtualizaOrdemServico (OrdemServico ordem_servico) throws Exception {
         //   System.out.println(CaixaBD.getId()); // ID N RECONHECE !!!!!!!!!!!
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         

     String UPDATE = "UPDATE ordem_servico "
             + "SET "
             + "cliente=?,funcionario=?,data_entrada=?,valor=?,id_caixa=?,desc_equipamento=?,desc_problema=?,observacao=?,data_saida=?,status=?,tipo_atendimento=?,garantia=?"
             + " WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
        stmt.setString(1,ordem_servico.getCliente());
         stmt.setString(2,ordem_servico.getFuncionario());
         stmt.setTimestamp(3,ordem_servico.getData_entrada());
         stmt.setDouble(4,ordem_servico.getValor());
         stmt.setInt(5,ordem_servico.getId_caixa());
         stmt.setString(6,ordem_servico.getDescricao_equipamento());
         stmt.setString(7,ordem_servico.getDescricao_problema());
         stmt.setString(8,ordem_servico.getObservacao());
         stmt.setTimestamp(9,ordem_servico.getData_saida());
         stmt.setString(10,ordem_servico.getStatus());
         stmt.setString(11,ordem_servico.getTipo_Atendimento());
         stmt.setString(12,ordem_servico.getGarantia());
         stmt.setInt(13,ordem_servico.getId());
         
         System.out.println(ordem_servico.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(ordem_servico);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "OS invalida" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerOrdemServico(int id) {
       
         String DELETE = "DELETE FROM ordem_servico WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir OrdemServico do banco de" + "dados " + e.getMessage()); } }

public OrdemServico getOrdemSById(int id) {
    
    String LISTBYID = "SELECT * FROM ordem_servico WHERE id=?";
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
   OrdemServico ordem_servico = new OrdemServico();
             ordem_servico.setId(rs.getInt("id"));
//           ordem_servico.setId(resultado.getInt("id"));
              ordem_servico.setCliente(rs.getString("cliente"));
               ordem_servico.setFuncionario(rs.getString("funcionario"));
                ordem_servico.setData_entrada(rs.getTimestamp("data_entrada"));
                 ordem_servico.setValor(rs.getDouble("valor"));
                  ordem_servico.setId_caixa(rs.getInt("id_caixa"));
                   ordem_servico.setDescricao_equipamento(rs.getString("desc_equipamento"));
                    ordem_servico.setDescricao_problema(rs.getString("desc_problema"));
                    ordem_servico.setObservacao(rs.getString("observacao"));
                    ordem_servico.setData_saida(rs.getTimestamp("data_saida"));
                    ordem_servico.setStatus(rs.getString("status"));
                    ordem_servico.setTipo_Atendimento(rs.getString("tipo_atendimento"));
                    ordem_servico.setGarantia(rs.getString("garantia"));
  
             
          System.out.println(ordem_servico.getId());
    return ordem_servico;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar OrdemServico" + e.getMessage()); 
    } 
        return null;
     }


    
}
