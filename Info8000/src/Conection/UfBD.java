/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;

import Objetos.Itens_Os;
import Objetos.Uf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class UfBD {
    public List<Uf> getlistUf() throws Exception {
       
        try {
             ConectionSingleton conexaosingleton;
        List<Uf> list = new ArrayList<>();
            conexaosingleton = ConectionSingleton.getInstancia();
             Connection connection = conexaosingleton.conectar();
         List<Itens_Os> itens = new ArrayList<Itens_Os>();
         PreparedStatement stmt = connection.prepareStatement("select * from uf");
         ResultSet resultado = stmt.executeQuery();
         
         while(resultado.next()){
             Uf uf = new Uf();
             uf.setCodUF(resultado.getInt("ufCod"));
             uf.setSigla(resultado.getString("ufSigla"));
             list.add(uf);
         }
          resultado.close();
         stmt.close();
         return list;
         
        } catch (Exception ex) {
            Logger.getLogger(UfBD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
}
