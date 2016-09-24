/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;

import Objetos.Configuracao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ConfiguracaoBD {

    private static Configuracao configuracao;
static   ConectionSingleton conectionSingleton;
 static Connection connection;

    public ConfiguracaoBD() {
        try {
            conectionSingleton = ConectionSingleton.getInstancia();
           connection = conectionSingleton.conectar();
        } catch (Exception ex) {
            Logger.getLogger(ConfiguracaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
   
   
   
   
public static Configuracao getInstancia() throws Exception {
      try {
            conectionSingleton = ConectionSingleton.getInstancia();
           connection = conectionSingleton.conectar();
        } catch (Exception ex) {
            Logger.getLogger(ConfiguracaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
            if (configuracao == null){
                 PreparedStatement stmt = connection.prepareStatement("select * from configuracao");
                  ResultSet resultado = stmt.executeQuery();
                  
                  while(resultado.next()){
                      configuracao = new Configuracao();
                      
                      configuracao.setId(resultado.getInt("id"));
                      
                      configuracao.setProdutosEstoqueMinimo(resultado.getBoolean("produtosestoqueminimo"));
                      configuracao.setValorCaixa(resultado.getBoolean("valorcaixa"));
                      configuracao.setContasaPagar(resultado.getBoolean("contasapagar"));
                      configuracao.setContasaReceber(resultado.getBoolean("contasareceber"));
                      
                      configuracao.setCartao(resultado.getBoolean("cartao"));
                      configuracao.setCheque(resultado.getBoolean("cheque"));
                      configuracao.setDinheiro(resultado.getBoolean("dinheiro"));
                      configuracao.setNotinha(resultado.getBoolean("notinha"));
                      
                      
                      configuracao.setCorFundoCancelado(resultado.getString("corfundocancelado"));
                      configuracao.setCorFundoCondicional(resultado.getString("corfundocondicional"));
                      configuracao.setCorFundoNormal(resultado.getString("corfundonormal"));
                      
                      configuracao.setCorGeralFundo(resultado.getString("corgeralfundo"));
                      configuracao.setCorGeralLetra(resultado.getString("corgeralletra"));
                      
                      configuracao.setCorLetrasCancelado(resultado.getString("corletrascancelado"));
                      configuracao.setCorLetrasCondicional(resultado.getString("corletrascondicional"));
                      configuracao.setCorLetrasNormal(resultado.getString("corletrasnormal"));
                      
                      
                      
                      
                  }
                     resultado.close();
         stmt.close();
                
            } 
               
      
    return configuracao;

}
public Configuracao getConfiguracao(){
    return configuracao;
    
}

public void adicionarConfiguracao (Configuracao configuracao) throws Exception {
        
     String sql = "insert into configuracao " + "(produtosestoqueminimo,valorcaixa,contasapagar,contasareceber,corfundocancelado,corfundocondicional,corfundonormal,corgeralfundo,corgeralletra,corletrascancelado,corletrascondicional,corletrasnormal,cartao,cheque,dinheiro,notinha)" +
             " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setBoolean(1,configuracao.getProdutosEstoqueMinimo());
         stmt.setBoolean(2,configuracao.getValorCaixa());
         stmt.setBoolean(3,configuracao.getContasaPagar());
         stmt.setBoolean(4,configuracao.getContasaReceber());
         
         stmt.setString(5,configuracao.getCorFundoCancelado());
         stmt.setString(6,configuracao.getCorFundoCondicional());
         stmt.setString(7,configuracao.getCorFundoNormal());
         
         stmt.setString(8,configuracao.getCorGeralFundo());
         stmt.setString(9,configuracao.getCorGeralLetra());
         
          stmt.setString(10,configuracao.getCorLetrasCancelado());
         stmt.setString(11,configuracao.getCorLetrasCondicional());
         stmt.setString(12,configuracao.getCorLetrasNormal());
         
          stmt.setBoolean(13,configuracao.getCartao());
         stmt.setBoolean(14,configuracao.getCheque());
         stmt.setBoolean(15,configuracao.getDinheiro());
         stmt.setBoolean(16,configuracao.getNotinha());
         
         
         stmt.execute();
        
         stmt.close();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }
public void updateConfiguracao (Configuracao configuracao) throws Exception {
        
     String sql = "update configuracao set " +
     "produtosestoqueminimo=?,valorcaixa=?,contasapagar=?,contasareceber=?,corfundocancelado=?,corfundocondicional=?,"
     + "corfundonormal=?,corgeralfundo=?,corgeralletra=?,corletrascancelado=?,corletrascondicional=?,corletrasnormal=?,cartao=?,cheque=?,dinheiro=?,notinha=? where id=?" ;
 
     try {
         // prepared statement para inserção
         PreparedStatement stmt = connection.prepareStatement(sql);
 
         // seta os valores
         //stmt.setInt(1,ator.getAtor_id());
         stmt.setBoolean(1,configuracao.getProdutosEstoqueMinimo());
         stmt.setBoolean(2,configuracao.getValorCaixa());
         stmt.setBoolean(3,configuracao.getContasaPagar());
         stmt.setBoolean(4,configuracao.getContasaReceber());
         
         stmt.setString(5,configuracao.getCorFundoCancelado());
         stmt.setString(6,configuracao.getCorFundoCondicional());
         stmt.setString(7,configuracao.getCorFundoNormal());
         
         stmt.setString(8,configuracao.getCorGeralFundo());
         stmt.setString(9,configuracao.getCorGeralLetra());
         
          stmt.setString(10,configuracao.getCorLetrasCancelado());
         stmt.setString(11,configuracao.getCorLetrasCondicional());
         stmt.setString(12,configuracao.getCorLetrasNormal());
          
         stmt.setBoolean(13,configuracao.getCartao());
         stmt.setBoolean(14,configuracao.getCheque());
         stmt.setBoolean(15,configuracao.getDinheiro());
         stmt.setBoolean(16,configuracao.getNotinha());
         
         
         stmt.setInt(17,configuracao.getId());
         
         
         stmt.execute();
        
         stmt.close();
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
 }






}



