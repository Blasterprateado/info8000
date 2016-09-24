package br.com.nfe.comunicador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Edson Moretti - www.edsonmoretti.com.br
 */
public class TextUtils {

    public static String lerTagIni(String ler, String deOnde, String chaveTitulo) {
        if (chaveTitulo.endsWith("]") && chaveTitulo.startsWith("[")) {
            chaveTitulo = chaveTitulo.toLowerCase(); //deixando minuscula pra ignorar o case e adicionado o =
        } else {
            chaveTitulo = "[" + chaveTitulo.toLowerCase() + "]"; //deixando minuscula pra ignorar o case e adicionado o =
        }
        String original = deOnde; //guarando a string deOnde original para pegar os dados no formato normal
        deOnde = deOnde.toLowerCase() + "\n"; //deixando minuscula o conteudo do ini pra poder usar a tag minuscula e dando um enter no final para pegar a ultima tag caso n tenha \n, pois o char \n eh usado para saber o final da tag ini
        original = deOnde = original.substring(deOnde.indexOf(chaveTitulo) + 1);
        try {
            original = "[" + original.substring(0, deOnde.indexOf("\n["));
        } catch (Exception e) {

        }
        return lerTagIni(ler, original);
    }

    public static String lerTagIni(String ler, String deOnde) {
        ler = ler.toLowerCase() + "="; //deixando minuscula pra ignorar o case e adicionado o =
        String original = deOnde; //guarando a string deOnde original para pegar os dados no formato normal
        deOnde = deOnde.toLowerCase() + "\n"; //deixando minuscula o conteudo do ini pra poder usar a tag minuscula e dando um enter no final para pegar a ultima tag caso n tenha \n, pois o char \n eh usado para saber o final da tag ini
        int index = deOnde.indexOf(ler); //pegando o index no conteudo INI minusculo
        String temp = original.substring(index + ler.length()); //usando o (index + variavelIni lenght) em minusculo para pegar o conteúdo do INI original

        if (!temp.endsWith("\n")) {
            temp = temp + "\n";
        }

        String retorno = "";
        if (index > -1) {
            int i = 0;
            while (temp.charAt(i) != '\n') { //ler o conteúdo até o achar o \n (final da String da variavel do INI)
                retorno += temp.charAt(i++);
            }
//            System.out.println(ler + retorno);
        }
        return retorno.trim().replace("\r", "");
    }

    public static String lpadZero(int tamanho, int numero) {
        return String.format("%0" + tamanho + "d", numero);
    }

}
