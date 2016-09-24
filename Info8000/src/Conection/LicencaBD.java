/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;


import Objetos.Licenca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcos
 */
public class LicencaBD {
    public int getDataLicenca() {
        
            int resultado=0;
                PreparedStatement pstm = null;
Licenca licenca = new Licenca();
            ConectionSingleton conexaosingleton;
            try {
                conexaosingleton = ConectionSingleton.getInstancia();
                 Connection conn = conexaosingleton.conectar();
     pstm = conn.prepareStatement("select * from licenca");
            ResultSet rs= pstm.executeQuery();
            while(rs.next()){
                licenca.setData(rs.getDate("data"));
                licenca.setId(rs.getInt("id"));
            }
            java.util.Date dataUtil = new java.util.Date();
            java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
            
            String data_Banco=licenca.getData().toString();
            String data_Agora=dataSql.toString();
            int teste=data_Agora.compareTo(data_Banco);
            
            
            System.out.println("Data maior "+teste);
            
            return teste;
            } catch (Exception ex) {
                Logger.getLogger(LicencaBD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
         
        return 0;
    }
}
    

