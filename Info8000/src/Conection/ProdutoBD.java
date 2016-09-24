/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;




import Objetos.Produto;
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
public class ProdutoBD {
    
    public List<Produto> getListaProduto() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<Produto> produtoss = new ArrayList<Produto>();
         PreparedStatement stmt = connection.prepareStatement("select * from produtos");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             Produto produtos = new Produto();
             produtos.setId(resultado.getInt("id"));
             produtos.setDescricao(resultado.getString("descricao"));
             produtos.setPreco_compra(resultado.getDouble("preco_compra"));
             produtos.setPreco_venda(resultado.getDouble("preco_venda"));
             produtos.setQtd_estoque(resultado.getDouble("qtd_estoque"));
             produtos.setClasse(resultado.getString("classe"));
             produtos.setEstoque_minimo(resultado.getDouble("estoque_minimo"));
             produtos.setFornecedor(resultado.getString("fornecedor"));
             produtos.setMarca(resultado.getString("marca"));
             produtos.setCodBarras(resultado.getLong("codbarras"));
           // adicionando o objeto à lista
            produtoss.add(produtos);
         }
         resultado.close();
         stmt.close();
         return produtoss;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
     public void adicionarProduto(Produto produtos) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();

         String sql = "insert into produtos " +
             "(descricao,preco_compra,preco_venda,qtd_estoque,classe,estoque_minimo,fornecedor,marca,codbarras)" +
             " values (?,?,?,?,?,?,?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setString(1,produtos.getDescricao());
         stmt.setDouble(2,produtos.getPreco_compra());
         stmt.setDouble(3,produtos.getPreco_venda());
         stmt.setDouble(4,produtos.getQtd_estoque());
         stmt.setString(5,produtos.getClasse());
         stmt.setDouble(6,produtos.getEstoque_minimo());
         stmt.setString(7,produtos.getFornecedor());
         stmt.setString(8,produtos.getMarca());
         stmt.setLong(9,produtos.getCodBarras());
         
         
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
     
     public void AtualizaProduto(Produto produtos) throws Exception {
            System.out.println(produtos.getId());
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();

         
      String UPDATE = "UPDATE produtos SET descricao=?,preco_compra=?,preco_venda=?,qtd_estoque=?,classe=?,estoque_minimo=?, fornecedor=?,marca=?,codbarras=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
         stmt.setString(1,produtos.getDescricao());
         stmt.setDouble(2,produtos.getPreco_compra());
         stmt.setDouble(3,produtos.getPreco_venda());
         stmt.setDouble(4,produtos.getQtd_estoque());
         stmt.setString(5,produtos.getClasse());
         stmt.setDouble(6,produtos.getEstoque_minimo());
         stmt.setString(7,produtos.getFornecedor());
         stmt.setString(8, produtos.getMarca());
         stmt.setLong(9,produtos.getCodBarras());
         stmt.setInt(10,produtos.getId());
         System.out.println(produtos.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(produtos);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Produto invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
      public void AtualizaQtdEstoque(Produto produtos) throws Exception {
            System.out.println(produtos.getId());
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
     
      String UPDATE = "UPDATE produtos SET qtd_estoque=? WHERE id=?";

     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(UPDATE);
         
         // seta os valores
         
      
         stmt.setDouble(1,produtos.getQtd_estoque());
         stmt.setInt(2,produtos.getId());
         System.out.println(produtos.getId());
         System.out.println(stmt);
         stmt.execute();
         System.out.println(produtos);
         stmt.close();
     } catch (SQLException e) {
         JOptionPane.showMessageDialog(null, "Produto invalido" + e.getMessage()); 
         throw new RuntimeException(e);
     }
 }
     public void removerProduto(int id) {
       
         String DELETE = "DELETE FROM produtos WHERE ID =?";
         try {
         ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
             PreparedStatement pstm;
             pstm = connection.prepareStatement(DELETE);
             pstm.setInt(1, id); 
             pstm.execute();
             
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Erro ao excluir Produto do banco de" + "dados " + e.getMessage()); } }

public Produto getProdutoById(int id) {
    
    String LISTBYID = "SELECT * FROM produtos WHERE id=?";
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
        System.out.println(rs.getDouble("preco_compra"));
        System.out.println(rs.getDouble("preco_venda"));
        System.out.println(rs.getDouble("qtd_estoque"));
        System.out.println(rs.getString("classe"));
         Produto produtos = new Produto();
         
             produtos.setId(rs.getInt("id"));
            System.out.println(produtos.getId()); 
             produtos.setDescricao(rs.getString("descricao"));
             produtos.setPreco_compra(rs.getDouble("preco_compra"));
             produtos.setPreco_venda(rs.getDouble("preco_venda"));
             produtos.setQtd_estoque(rs.getDouble("qtd_estoque"));
             produtos.setClasse(rs.getString("classe"));
             produtos.setEstoque_minimo(rs.getDouble("estoque_minimo"));
             produtos.setFornecedor(rs.getString("fornecedor"));
              produtos.setMarca(rs.getString("marca"));
               produtos.setCodBarras(rs.getLong("codbarras"));
          System.out.println(produtos.getDescricao());
    return produtos;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Clientes" + e.getMessage()); 
    } 
        return null;
     }




public Produto getProdutoByNome(String descricao) {
    
    String LISTBYID = "SELECT * FROM produtos WHERE descricao=?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
   
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
    pstm.setString(1, descricao);
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
        System.out.println(rs.getInt("id"));
        System.out.println(rs.getString("descricao"));
        System.out.println(rs.getDouble("preco_compra"));
        System.out.println(rs.getDouble("preco_venda"));
        System.out.println(rs.getDouble("qtd_estoque"));
        System.out.println(rs.getString("classe"));
         Produto produtos = new Produto();
         
             produtos.setId(rs.getInt("id"));
            System.out.println(produtos.getId()); 
             produtos.setDescricao(rs.getString("descricao"));
             produtos.setPreco_compra(rs.getDouble("preco_compra"));
             produtos.setPreco_venda(rs.getDouble("preco_venda"));
             produtos.setQtd_estoque(rs.getDouble("qtd_estoque"));
             produtos.setClasse(rs.getString("classe"));
             produtos.setEstoque_minimo(rs.getDouble("estoque_minimo"));
             produtos.setFornecedor(rs.getString("fornecedor"));
              produtos.setMarca(rs.getString("marca"));
               produtos.setCodBarras(rs.getLong("codbarras"));
          System.out.println(produtos.getDescricao());
    return produtos;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos" + e.getMessage()); 
    } 
        return null;
     }

public List<Produto> getListaProdutosClasse(String classe) throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<Produto> produtoss = new ArrayList<Produto>();
         PreparedStatement stmt = connection.prepareStatement("select * from produtos where classe=? order by descricao ");
         stmt.setString(1, classe);
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             Produto produtos = new Produto();
             produtos.setId(resultado.getInt("id"));
             produtos.setDescricao(resultado.getString("descricao"));
             produtos.setPreco_compra(resultado.getDouble("preco_compra"));
             produtos.setPreco_venda(resultado.getDouble("preco_venda"));
             produtos.setQtd_estoque(resultado.getDouble("qtd_estoque"));
             produtos.setClasse(resultado.getString("classe"));
             produtos.setEstoque_minimo(resultado.getDouble("estoque_minimo"));
             produtos.setFornecedor(resultado.getString("fornecedor"));
              produtos.setMarca(resultado.getString("marca"));
               produtos.setCodBarras(resultado.getLong("codbarras"));
           // adicionando o objeto à lista
            produtoss.add(produtos);
         }
         resultado.close();
         stmt.close();
         return produtoss;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}

public Produto getProdutoByDescricaoETipo(String descricao, String classe) {
    
    String LISTBYID = "SELECT * FROM produtos WHERE descricao=? and classe=?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
   
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
    pstm.setString(1, descricao);
    pstm.setString(2, classe);
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
        System.out.println(rs.getInt("id"));
        System.out.println(rs.getString("descricao"));
        System.out.println(rs.getDouble("preco_compra"));
        System.out.println(rs.getDouble("preco_venda"));
        System.out.println(rs.getDouble("qtd_estoque"));
        System.out.println(rs.getString("classe"));
         Produto produtos = new Produto();
         
             produtos.setId(rs.getInt("id"));
            System.out.println(produtos.getId()); 
             produtos.setDescricao(rs.getString("descricao"));
             produtos.setPreco_compra(rs.getDouble("preco_compra"));
             produtos.setPreco_venda(rs.getDouble("preco_venda"));
             produtos.setQtd_estoque(rs.getDouble("qtd_estoque"));
             produtos.setClasse(rs.getString("classe"));
             produtos.setEstoque_minimo(rs.getDouble("estoque_minimo"));
             produtos.setFornecedor(rs.getString("fornecedor"));
              produtos.setMarca(rs.getString("marca"));
               produtos.setCodBarras(rs.getLong("codbarras"));
          System.out.println(produtos.getDescricao());
    return produtos;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Produtos" + e.getMessage()); 
    } 
        return null;
     }

public List<Produto> getProdutoByTipo(String classe) {
    
    String LISTBYID = "SELECT * FROM produtos WHERE classe=? order by descricao";
    PreparedStatement pstm = null;
    ResultSet rs = null;
   List<Produto> lista = new ArrayList<Produto>();
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
    
    pstm.setString(1, classe);
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
         Produto produtos = new Produto();
            produtos.setId(rs.getInt("id"));
             produtos.setDescricao(rs.getString("descricao"));
             produtos.setPreco_compra(rs.getDouble("preco_compra"));
             produtos.setPreco_venda(rs.getDouble("preco_venda"));
             produtos.setQtd_estoque(rs.getDouble("qtd_estoque"));
             produtos.setClasse(rs.getString("classe"));
             produtos.setEstoque_minimo(rs.getDouble("estoque_minimo"));
             produtos.setFornecedor(rs.getString("fornecedor"));
              produtos.setMarca(rs.getString("marca"));
               produtos.setCodBarras(rs.getLong("codbarras"));
          
    lista.add(produtos);
           
    } 
    return lista;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Produtos" + e.getMessage()); 
    } 
        return null;
     }



public Produto getProdutoByNomeLike(String descricao) {
    
    String LISTBYID = "SELECT * FROM produtos WHERE  descricao like ?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
   
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
    pstm.setString(1, descricao);
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
        System.out.println(rs.getInt("id"));
        System.out.println(rs.getString("descricao"));
        System.out.println(rs.getDouble("preco_compra"));
        System.out.println(rs.getDouble("preco_venda"));
        System.out.println(rs.getDouble("qtd_estoque"));
        System.out.println(rs.getString("classe"));
         Produto produtos = new Produto();
         
             produtos.setId(rs.getInt("id"));
            System.out.println(produtos.getId()); 
             produtos.setDescricao(rs.getString("descricao"));
             produtos.setPreco_compra(rs.getDouble("preco_compra"));
             produtos.setPreco_venda(rs.getDouble("preco_venda"));
             produtos.setQtd_estoque(rs.getDouble("qtd_estoque"));
             produtos.setClasse(rs.getString("classe"));
             produtos.setEstoque_minimo(rs.getDouble("estoque_minimo"));
             produtos.setFornecedor(rs.getString("fornecedor"));
              produtos.setMarca(rs.getString("marca"));
               produtos.setCodBarras(rs.getLong("codbarras"));
          System.out.println(produtos.getDescricao());
    return produtos;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Clientes" + e.getMessage()); 
    } 
        return null;
     }

public Produto getProdutoByCodBarras(Long CodBarras) {
    
    String LISTBYID = "SELECT * FROM produtos WHERE codbarras=?";
    PreparedStatement pstm = null;
    ResultSet rs = null;
   
    try { 
       
       ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
    pstm = connection.prepareStatement(LISTBYID);
    pstm.setLong(1, CodBarras);
        System.out.println(pstm);
    rs = pstm.executeQuery();
    
    while (rs.next()) {
     
         Produto produtos = new Produto();
         
             produtos.setId(rs.getInt("id"));
            System.out.println(produtos.getId()); 
             produtos.setDescricao(rs.getString("descricao"));
             produtos.setPreco_compra(rs.getDouble("preco_compra"));
             produtos.setPreco_venda(rs.getDouble("preco_venda"));
             produtos.setQtd_estoque(rs.getDouble("qtd_estoque"));
             produtos.setClasse(rs.getString("classe"));
             produtos.setEstoque_minimo(rs.getDouble("estoque_minimo"));
             produtos.setFornecedor(rs.getString("fornecedor"));
              produtos.setMarca(rs.getString("marca"));
               produtos.setCodBarras(rs.getLong("codbarras"));
          System.out.println(produtos.getDescricao());
    return produtos;
           
    } 
    
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar Clientes" + e.getMessage()); 
    } 
        return null;
     }


    
}
