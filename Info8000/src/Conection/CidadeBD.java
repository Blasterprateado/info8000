/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;

import Objetos.Cidade;
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
 * @author Marcos
 */
public class CidadeBD {
    public List<Cidade> getListaCidade() throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
         List<Cidade> classes = new ArrayList<Cidade>();
         PreparedStatement stmt = connection.prepareStatement("select * from cidades");
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             // criando o objeto Produto
             Cidade cidades = new Cidade();
             cidades.setId(resultado.getInt("id"));
             cidades.setNome(resultado.getString("NOME_MUNICIPIO"));
             cidades.setUf(resultado.getString("NOME_UF"));
             cidades.setCodUf(resultado.getString("UF"));
                    cidades.setCep(resultado.getString("cep"));
                    cidades.setCodMunicipio(resultado.getString("COD_MUNICIPIO_COMPLETO"));
            classes.add(cidades);
         }
         resultado.close();
         stmt.close();
         return classes;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
    public Cidade getListaCidadeBYID(int id) throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
        Cidade cidades = new Cidade();
         PreparedStatement stmt = connection.prepareStatement("select * from cidades where id=?");
         stmt.setInt(1, id);
         ResultSet resultado = stmt.executeQuery();
         System.out.println(stmt);
         while (resultado.next()) {
             
            cidades.setId(resultado.getInt("id"));
             cidades.setNome(resultado.getString("NOME_MUNICIPIO"));
             cidades.setUf(resultado.getString("NOME_UF"));
             cidades.setCodUf(resultado.getString("UF"));
                    cidades.setCep(resultado.getString("cep"));
                    cidades.setCodMunicipio(resultado.getString("COD_MUNICIPIO_COMPLETO"));
           
         }
         resultado.close();
         stmt.close();
         return cidades;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
    public Cidade getListaCidadeBYNOME(String nome) throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
        Cidade cidades = new Cidade();
         PreparedStatement stmt = connection.prepareStatement("select * from cidades where NOME_MUNICIPIO=?");
         stmt.setString(1, nome);
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             
            cidades.setId(resultado.getInt("id"));
             cidades.setNome(resultado.getString("NOME_MUNICIPIO"));
             cidades.setUf(resultado.getString("NOME_UF"));
             cidades.setCodUf(resultado.getString("UF"));
                    cidades.setCep(resultado.getString("cep"));
                    cidades.setCodMunicipio(resultado.getString("COD_MUNICIPIO_COMPLETO"));
           
         }
         resultado.close();
         stmt.close();
         return cidades;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
    public Cidade getListaCidadeBYCEP(String CEP) throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
        Cidade cidades = new Cidade();
         PreparedStatement stmt = connection.prepareStatement("select * from cidades where cep=?");
         stmt.setString(1, CEP);
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             
            cidades.setId(resultado.getInt("id"));
             cidades.setNome(resultado.getString("NOME_MUNICIPIO"));
             cidades.setUf(resultado.getString("NOME_UF"));
             cidades.setCodUf(resultado.getString("UF"));
                    cidades.setCep(resultado.getString("cep"));
                    cidades.setCodMunicipio(resultado.getString("COD_MUNICIPIO_COMPLETO"));
           
         }
         resultado.close();
         stmt.close();
         return cidades;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
    public List<Cidade> getListaCidadeBYCODUF(int CODUF) throws Exception {
     try {
         List<Cidade> lista = new ArrayList<>();
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
            PreparedStatement stmt = connection.prepareStatement("select * from cidades where uf=?");
         stmt.setInt(1, CODUF);
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             Cidade cidades = new Cidade();
            cidades.setId(resultado.getInt("id"));
             cidades.setNome(resultado.getString("NOME_MUNICIPIO"));
             cidades.setUf(resultado.getString("NOME_UF"));
             cidades.setCodUf(resultado.getString("UF"));
                    cidades.setCep(resultado.getString("cep"));
                    cidades.setCodMunicipio(resultado.getString("COD_MUNICIPIO_COMPLETO"));
           lista.add(cidades);
         }
         resultado.close();
         stmt.close();
         return lista;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
    public Cidade getListaCidadeBYCODIBGE(String CODIBGE) throws Exception {
     try {
          ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();
        Cidade cidades = new Cidade();
         PreparedStatement stmt = connection.prepareStatement("select * from cidades where COD_MUNICIPIO_COMPLETO=?");
         stmt.setString(1, CODIBGE);
         ResultSet resultado = stmt.executeQuery();
 
         while (resultado.next()) {
             
            cidades.setId(resultado.getInt("id"));
             cidades.setNome(resultado.getString("NOME_MUNICIPIO"));
             cidades.setUf(resultado.getString("NOME_UF"));
             cidades.setCodUf(resultado.getString("UF"));
                    cidades.setCep(resultado.getString("cep"));
                    cidades.setCodMunicipio(resultado.getString("COD_MUNICIPIO_COMPLETO"));
           
         }
         resultado.close();
         stmt.close();
         return cidades;
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
}
    
    
    public void adicionarCidade(Cidade cidade) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();

         String sql = "insert into cidades " +
             "(NOME_MUNICIPIO,NOME_UF,UF,cep,COD_MUNICIPIO_COMPLETO)" +
             " values (?,?,?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
        
         stmt.setString(1,cidade.getNome());
         stmt.setString(2,cidade.getUf());
         stmt.setString(3,cidade.getCodUf());
         stmt.setString(4,cidade.getCep());
         stmt.setString(5,cidade.getCodMunicipio());
        
         stmt.execute();
         stmt.close();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }
    
    public void updateCidade(Cidade cidade) throws Exception {
        ConectionSingleton conexaosingleton = ConectionSingleton.getInstancia();
         Connection connection = conexaosingleton.conectar();

         String sql = "UPDATE cidades SET NOME_MUNICIPIO=?,NOME_UF=?,UF=?,cep=?,COD_MUNICIPIO_COMPLETO=? WHERE cidades.id=?";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
        
         stmt.setString(1,cidade.getNome());
         stmt.setString(2,cidade.getUf());
         stmt.setString(3,cidade.getCodUf());
         stmt.setString(4,cidade.getCep());
         stmt.setString(5,cidade.getCodMunicipio());
         stmt.setInt(6, cidade.getId());
        
         stmt.execute();
         stmt.close();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }
    
  public void removerCidade(int id) {
       
         String DELETE = "DELETE FROM CIDADES WHERE ID =?";
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
}
