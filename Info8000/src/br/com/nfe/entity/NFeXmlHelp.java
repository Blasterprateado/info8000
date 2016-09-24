/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfe.entity;


import Objetos.ProdutoNfe;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author USUARIO
 */
public class NFeXmlHelp {
    Double versao;
    Long numeroNfe;
String xmlFilePathNFe3;
JAXBContext context = null;
TNfeProc tNfeProc = null;
    public NFeXmlHelp(String xmlFilePathNFe3) {
        this.xmlFilePathNFe3=xmlFilePathNFe3;
    

    try {

        // Realizando o parser do XML da NFe para Objeto Java
        context = JAXBContext.newInstance(TNfeProc.class.getPackage().getName());
        Unmarshaller unmarshaller1 = context.createUnmarshaller();
           // File file=new File(xmlFilePathNFe3);
            InputStream fileIn = new FileInputStream(xmlFilePathNFe3);
            Reader file = new InputStreamReader(fileIn, "UTF-8");
           tNfeProc = (TNfeProc) JAXBIntrospector.getValue(unmarshaller1.unmarshal(file)); 
        // Este é o seu Objeto Java da NFe (tNfeProc)
        //tNfeProc = (TNfeProc) unmarshaller1.unmarshal(new File(xmlFilePathNFe3));

        
       
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Arquivo não Suportado, se realmente for uma nota fiscal, solicite suporte!");
    }
    }
    
    
    public double getVersao(){
        
        versao = Double.valueOf(tNfeProc.getNFe().getInfNFe().getVersao());
        
        return versao;
    }
    
     public String getNumeroNfe(){
        
        return tNfeProc.getNFe().getInfNFe().ide.nnf;
    }
    
    
    public List<ProdutoNfe> getProdutos (){
        List<ProdutoNfe> listaProdutos = new ArrayList<>();
        try {
            for (TNFe.InfNFe.Det item : tNfeProc.getNFe().getInfNFe().getDet()) {
                ProdutoNfe produto = new ProdutoNfe();
                produto.setCod(item.getProd().cProd);
                produto.setDescricao(item.getProd().xProd);
                produto.setQtd(Double.valueOf(item.getProd().qCom));
                produto.setValorunt(Double.valueOf(item.getProd().vUnCom));
                produto.setUndComercial(item.getProd().uCom);
                produto.setTotal(Double.valueOf(item.getProd().vProd));
                
                listaProdutos.add(produto);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
      return listaProdutos;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void lerxml(){
        // Caminho do arquivo XML da NFe
    String xmlFilePathNFe3 = "src//31160713377077000127550010000241311270427736-NFe-protocolo.xml";
    JAXBContext context = null;
    TNfeProc tNfeProc = null;

    try {

        // Realizando o parser do XML da NFe para Objeto Java
        context = JAXBContext.newInstance(TNfeProc.class.getPackage().getName());
        Unmarshaller unmarshaller1 = context.createUnmarshaller();

        // Este é o seu Objeto Java da NFe (tNfeProc)
        tNfeProc = (TNfeProc) unmarshaller1.unmarshal(new File(xmlFilePathNFe3));

        // Iterando na lista de produtos da NFe
        for (TNFe.InfNFe.Det item : tNfeProc.getNFe().getInfNFe().getDet()){
            System.out.println(
                    "Item: "+item.getNItem()+
                            " Codigo: "+item.getProd().getCProd()+
                            " Descricao: "+item.getProd().getXProd()+
                            " Valor: "+item.getProd().getVProd()+
                            " qtd: "+item.getProd().getQCom()+
                            " Valor: "+item.getProd().getVUnCom()
            );
        }
        System.out.println(tNfeProc.getNFe().getInfNFe().getVersao());
    } catch (JAXBException e) {
        e.printStackTrace();
    }
    }
}
