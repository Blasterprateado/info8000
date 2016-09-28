/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author user
 */
public class ConectionSingleton {
private static final String NOME_SERVIDOR = "localhost";//CINHO DO SERVIDOR DE BANCO DE DADOS
    private static final String NOME_DA_BASE = "infofiscal";//NOME DO SEU BANCO DE DADOS
    private static final String URL_CONEXAO_BANCO = "jdbc:mysql://" + NOME_SERVIDOR + "/" + NOME_DA_BASE;//conexao com o banco
    private static final String NOME_DE_USUARIO = "root"; //nome de usuario do banco de dados
    private static final String SENHA_BANCO = ""; //Senha do banco de dados
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private Connection connection;
    private static ConectionSingleton conexaoSingleton;
public static ConectionSingleton getInstancia() throws Exception {
        try {
            if (conexaoSingleton == null) {
               conexaoSingleton =new ConectionSingleton();
                //conectar no banco
                System.out.println("Carregando Driver");
                Class.forName(DRIVER_NAME);
                System.out.println("Criando Conexao com o banco");
                conexaoSingleton.connection = DriverManager.getConnection(URL_CONEXAO_BANCO , NOME_DE_USUARIO ,SENHA_BANCO);
            } 
                    
                    //"jdbc:mysql://localhost/posto", "root", "root"
            return conexaoSingleton;
        } catch (Exception e) {
            System.out.println(e);
            throw new Exception(e);
            
        }
    }

    public void fecharConexao() throws Exception {
        try {
            if (connection != null) {
                connection.close();

            }

        } catch (Exception e) {
            throw new Exception(e);

        }

    }
public Connection conectar(){
    return connection;
    
}
}

