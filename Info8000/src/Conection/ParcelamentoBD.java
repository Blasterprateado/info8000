/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.Parcelamento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class ParcelamentoBD {
  
    public List<Parcelamento> getListaParcelamento() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<Parcelamento> parcelas = new ArrayList<Parcelamento>();
         PreparedStatement stmt = connection.prepareStatement("select * from parcelamento");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             Parcelamento parcelamento;
              parcelamento = new Parcelamento();
             parcelamento.setId(resultado.getInt("id"));
             parcelamento.setId_cliente(resultado.getInt("id_cliente"));
             parcelamento.setId_venda(resultado.getInt("id_venda"));
             parcelamento.setValor(resultado.getDouble("valor"));
             parcelamento.setData(resultado.getTimestamp("data"));
             parcelamento.setDocumento(resultado.getString("documento"));
             parcelamento.setStatus(resultado.getString("status"));
            
           // adicionando o objeto à lista
            parcelas.add(parcelamento);
         }
         resultado.close();
         stmt.close();
         return parcelas;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarItem(Parcelamento item) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     String sql = "insert into parcelamento (id_cliente,id_venda,valor,data,documento,status) values (?,?,?,?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setInt(1,item.getId_cliente());
         stmt.setInt(2,item.getId_venda());
         stmt.setDouble(3,item.getValor());
         
          java.sql.Date dataSql = new java.sql.Date(item.getData().getTime()); 
         stmt.setDate(4, dataSql);
         stmt.setString(5, item.getDocumento());
         stmt.setString(6, item.getStatus());
        
     
         
         
         stmt.execute();
        
         stmt.close();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }
     public int AdicionaListaParcelamento(List<Parcelamento> list) {
        int i = 0;
        Iterator<Parcelamento> it = list.iterator();
        try {
            while (it.hasNext()) {
                Parcelamento parcelamento = it.next();

                adicionarItem(parcelamento);

            }
            i=1;
        } catch (Exception e) {
            i=0;
            JOptionPane.showMessageDialog(null,"Erro ao Adicionar Lista De Parcelamento " +e);
        }
        return i;
    }
     public static java.sql.Timestamp getCurrentTimeStamp() {
 
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
 
	}
     
     public void AtualizaItem(Parcelamento item) throws Exception {
            System.out.println(item.getId());
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     
      String UPDATE = "UPDATE parcelamento SET id_cliente=?,id_venda=?,valor=?,documento=?,status=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
         stmt.setInt(1,item.getId_cliente());
         stmt.setInt(2,item.getId_venda());
         stmt.setDouble(3,item.getValor());
         //stmt.setDate(4, (Date) item.getData());
         stmt.setString(4, item.getDocumento());
         stmt.setString(5, item.getStatus());
         stmt.setInt(6, item.getId());
         
         stmt.execute();
        
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "item invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerItem(int id) {
       
         String DELETE = "DELETE FROM parcelamento WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir Item do banco de" + "dados " + e.getMessage()); 
         }
     }

     public void removerParcelamentoByIdVenda(int id) {
       
         String DELETE = "DELETE FROM parcelamento WHERE id_venda =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir Item do banco de" + "dados " + e.getMessage()); 
         }
     }
public Parcelamento getItemById(int id) {
    
    String LISTBYID = "SELECT * FROM parcelamento WHERE id=?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
     List<Parcelamento> lista = new ArrayList<>();
   
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
    pstm.setInt(1, id);
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
      
         Parcelamento parcelamento = new Parcelamento();
         
            parcelamento.setId(rs.getInt("id"));
             parcelamento.setId_cliente(rs.getInt("id_cliente"));
             parcelamento.setId_venda(rs.getInt("id_venda"));
             parcelamento.setValor(rs.getDouble("valor"));
             parcelamento.setData(rs.getTimestamp("data"));
             parcelamento.setDocumento(rs.getString("documento"));
             parcelamento.setStatus(rs.getString("status"));
             
          
    
            return parcelamento;
    } 
   
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar itens" + e.getMessage()); 
    } 
        return null;
     }
public List<Parcelamento> getItemByIdCliente(int id) {
    
    String LISTBYID = "SELECT * FROM parcelamento WHERE id_cliente=?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
     List<Parcelamento> lista = new ArrayList<>();
   
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
    pstm.setInt(1, id);
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
      
         Parcelamento parcelamento = new Parcelamento();
         
            parcelamento.setId(rs.getInt("id"));
             parcelamento.setId_cliente(rs.getInt("id_cliente"));
             parcelamento.setId_venda(rs.getInt("id_venda"));
             parcelamento.setValor(rs.getDouble("valor"));
             parcelamento.setData(rs.getTimestamp("data"));
              parcelamento.setDocumento(rs.getString("documento"));
              parcelamento.setStatus(rs.getString("status"));
             
          
    lista.add(parcelamento);
           
    } 
    return lista;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar itens" + e.getMessage()); 
    } 
        return null;
     }
public  List<Parcelamento> getItemByVenda(int id) {
    
    String LISTBYID = "SELECT * FROM parcelamento WHERE id_venda=?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
    List<Parcelamento> lista = new ArrayList<>();
   
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
    pstm.setInt(1, id);
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
      
         Parcelamento parcelamento = new Parcelamento();
         
            parcelamento.setId(rs.getInt("id"));
             parcelamento.setId_cliente(rs.getInt("id_cliente"));
             parcelamento.setId_venda(rs.getInt("id_venda"));
             parcelamento.setValor(rs.getDouble("valor"));
             parcelamento.setData(rs.getTimestamp("data"));
              parcelamento.setDocumento(rs.getString("documento"));
              parcelamento.setStatus(rs.getString("status"));
          
   lista.add(parcelamento);
           
    } 
    return lista;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar itens" + e.getMessage()); 
    } 
        return null;
     }
public List<Parcelamento> getItemByData(java.util.Date data) {
    
    String LISTBYID = "SELECT * FROM parcelamento WHERE data like ? ";
    PreparedStatement pstm = null;
    ResultSet rs = null;
   List<Parcelamento> lista = new ArrayList<>();
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
   java.sql.Date datasql = new java.sql.Date(data.getTime());
    pstm.setString(1, ""+datasql.toString()+"%");
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
      
         Parcelamento parcelamento = new Parcelamento();
         
            parcelamento.setId(rs.getInt("id"));
             parcelamento.setId_cliente(rs.getInt("id_cliente"));
             parcelamento.setId_venda(rs.getInt("id_venda"));
             parcelamento.setValor(rs.getDouble("valor"));
             parcelamento.setData(rs.getTimestamp("data"));
              parcelamento.setDocumento(rs.getString("documento"));
              parcelamento.setStatus(rs.getString("status"));
             
    lista.add(parcelamento);
           
    } 
    return lista;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar itens" + e.getMessage()); 
    } 
        return null;
     }

public List<Parcelamento> getItemByDataEIDCliente(int id , java.util.Date data){
    
    String LISTBYID = "SELECT * FROM parcelamento WHERE id=? and data=?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
    List<Parcelamento> lista = new ArrayList<>();
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
    pstm.setInt(1, id);
    java.sql.Date datasql = new java.sql.Date(data.getTime());
    pstm.setDate(2, datasql);
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
      
         Parcelamento parcelamento = new Parcelamento();
         
            parcelamento.setId(rs.getInt("id"));
             parcelamento.setId_cliente(rs.getInt("id_cliente"));
             parcelamento.setId_venda(rs.getInt("id_venda"));
             parcelamento.setValor(rs.getDouble("valor"));
             parcelamento.setData(rs.getTimestamp("data"));
              parcelamento.setDocumento(rs.getString("documento"));
             parcelamento.setStatus(rs.getString("status"));
          
    lista.add(parcelamento);
           
    } 
    return lista;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar parcelamento" + e.getMessage()); 
    } 
        return null;
     }


    
    
public List<Parcelamento> getParcelamentosVencidos() {
    java.util.Date data = new java.util.Date();
    String LISTBYID = "SELECT * FROM parcelamento WHERE parcelamento.status='A RECEBER' and  data  between ? and ?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
   List<Parcelamento> lista = new ArrayList<>();
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
   java.sql.Date datasql = new java.sql.Date(data.getTime());
    pstm.setString(1,"1990/01/01");
    pstm.setString(2,datasql.toString());
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
      
         Parcelamento parcelamento = new Parcelamento();
         
            parcelamento.setId(rs.getInt("id"));
             parcelamento.setId_cliente(rs.getInt("id_cliente"));
             parcelamento.setId_venda(rs.getInt("id_venda"));
             parcelamento.setValor(rs.getDouble("valor"));
             parcelamento.setData(rs.getTimestamp("data"));
              parcelamento.setDocumento(rs.getString("documento"));
              parcelamento.setStatus(rs.getString("status"));
             
    lista.add(parcelamento);
           
    } 
    return lista;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar itens" + e.getMessage()); 
    } 
        return null;
     }
    public List<Parcelamento> getParcelamentosByDatas(java.util.Date datainicial, java.util.Date dataFinal) {
   
    String LISTBYID = "SELECT * FROM parcelamento WHERE data  between ? and ?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
   List<Parcelamento> lista = new ArrayList<>();
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
   java.sql.Date datasqlInicial = new java.sql.Date(datainicial.getTime());
   java.sql.Date datasqlFinal = new java.sql.Date(dataFinal.getTime());
    pstm.setString(1,datasqlInicial.toString());
    pstm.setString(2,datasqlFinal.toString());
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
      
         Parcelamento parcelamento = new Parcelamento();
         
            parcelamento.setId(rs.getInt("id"));
             parcelamento.setId_cliente(rs.getInt("id_cliente"));
             parcelamento.setId_venda(rs.getInt("id_venda"));
             parcelamento.setValor(rs.getDouble("valor"));
             parcelamento.setData(rs.getTimestamp("data"));
              parcelamento.setDocumento(rs.getString("documento"));
              parcelamento.setStatus(rs.getString("status"));
             
    lista.add(parcelamento);
           
    } 
    return lista;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Não são permitidos Campos Nulos, Verifique!"); 
    } 
        return null;
     }
    
    
    
}
