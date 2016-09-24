/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfe.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author USUARIO
 */
public class Chave extends Thread {

    private String pasta = "18320519000112"; // pegar de uma configuração no bd posteriormente
    private String cNf;
    public static String chave;
    boolean allDone = false;

    public Chave() {
    }

    @Override
    public void run() {
        
        while(true){
        String chave = "";
        Document doc = null;
        SAXBuilder builder = new SAXBuilder();

        Path path = Paths.get("c:/unimake/uninfe/" + pasta + "/Retorno");
        WatchService watchService = null;
        try {
            watchService = FileSystems.getDefault().newWatchService();
            path.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);

        } catch (IOException io) {
            io.printStackTrace();
        }
        WatchKey key = null;

        while (true) {
            try {
                key = watchService.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    Kind<?> kind = event.kind();
                    System.out.println("Evento em " + event.context().toString() + " " + kind);
                    try {

                        doc = builder.build("c:/unimake/uninfe/" + pasta + "/Retorno/" + cNf + "-ret-gerar-chave.xml");

                        Element retorno = doc.getRootElement();

                        List<Element> lista = retorno.getChildren();

                        for (Element e : lista) {

                            chave = e.getAttributeValue("chaveNFe");
                            chave = e.getText();

                        }
                        mudaChave(chave);
                        if (chave.isEmpty() == false) {
                            allDone=true;
                        }
                    } catch (Exception e) {

                        e.printStackTrace();

                    }

                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            boolean reset = key.reset();
            if (!reset) {
                break;
            }
            if (allDone) {
                    return;
                }
        }

    }
    }

    public void criarChave(String uf, String tpEmis, String nNfe, String cNf, String serie, String AnoMes, String cnpj) {
        this.cNf = cNf;
        Element gerarChave = new Element("gerarChave");
        Document documento = new Document(gerarChave);
        Element UF = new Element("UF");
        UF.setText(uf);

        Element NNFE = new Element("nNF");
        NNFE.setText(nNfe);

        Element CNF = new Element("cNF");
        CNF.setText(cNf);

        Element TPEMIS = new Element("tpEmis");
        TPEMIS.setText(tpEmis);

        Element SERIE = new Element("serie");
        SERIE.setText(serie);

        Element ANOMES = new Element("AAMM");
        ANOMES.setText(AnoMes);

        Element CNPJ = new Element("CNPJ");
        CNPJ.setText(cnpj);

        gerarChave.addContent(UF);
       gerarChave.addContent(TPEMIS);
        gerarChave.addContent(NNFE);
        //gerarChave.addContent(CNF);
        gerarChave.addContent(SERIE);
        gerarChave.addContent(ANOMES);
        gerarChave.addContent(CNPJ);
        XMLOutputter xout = new XMLOutputter();
        try {

            FileWriter arquivo = new FileWriter(new File("c:/unimake/uninfe/" + pasta + "/envio/" + cNf + "-gerar-chave.xml"));

            xout.output(documento, arquivo);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void pegaChave() {

    }

    public String getChave() {
        return chave;
    }

    public static void setChave(String chave) {
        Chave.chave = chave;
    }

    public static void mudaChave(String chavee) {
        chave = chavee;
        System.out.println("chave" + chavee);

    }

    
      public static StringBuilder geraChave(String cUF,String dataAAMM,String cnpjCpf,String mod,String serie,String tpEmis, String nNF,String cNF)   
    {    
        try   
        {    
                      
            StringBuilder chavee = new StringBuilder();    
            chavee.append(lpadTo(cUF, 2, '0'));    
            chavee.append(lpadTo(dataAAMM, 4, '0'));    
            chavee.append(lpadTo(cnpjCpf.replaceAll("\\D",""), 14, '0'));    
            chavee.append(lpadTo(mod, 2, '0'));    
            chavee.append(lpadTo(serie, 3, '0'));    
            chavee.append(lpadTo(String.valueOf(nNF), 9, '0'));    
            chavee.append(lpadTo(tpEmis, 1, '0'));    
            chavee.append(lpadTo(cNF, 8, '0'));    
           
            return chavee;  
        } catch (Exception e) {    
            error(e.toString());  
            return null;  
        }    
    }    
        
    public static int modulo11(String chave) {    
        int total = 0;    
        int peso = 2;    
                
        for (int i = 0; i < chave.length(); i++) {    
            total += (chave.charAt((chave.length()-1) - i) - '0') * peso;    
            peso ++;    
            if (peso == 10)    
                peso = 2;    
        }    
        int resto = total % 11;    
        return (resto == 0 || resto == 1) ? 0 : (11 - resto);    
    }    
    
    public static String lpadTo(String input, int width, char ch) {    
        String strPad;    
    
        StringBuilder sb = new StringBuilder(input.trim());    
        while (sb.length() < width)    
            sb.insert(0,ch);    
        strPad = sb.toString();    
            
        if (strPad.length() > width) {    
            strPad = strPad.substring(0,width);    
        }    
        return strPad;    
    }    
    
    /**  
     * Log ERROR.  
     * @param error  
     */    
    private static void error(String error) {    
        System.out.println("| ERROR: " + error);    
    }    
    
    /**  
     * Log INFO.  
     * @param info  
     */    
    private static void info(String info) {    
        System.out.println("| INFO: " + info);    
    }    
      
}
