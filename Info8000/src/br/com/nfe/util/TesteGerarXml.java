/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nfe.util;


import br.com.nfe.entity.ObjectFactory;
import br.com.nfe.entity.TEnderEmi;
import br.com.nfe.entity.TEndereco;
import br.com.nfe.entity.TNFe;
import br.com.nfe.entity.TNFe.InfNFe;
import br.com.nfe.entity.TNFe.InfNFe.Dest;
import br.com.nfe.entity.TNFe.InfNFe.Det;
import br.com.nfe.entity.TNFe.InfNFe.Det.Imposto;
import br.com.nfe.entity.TNFe.InfNFe.Det.Imposto.COFINS;
import br.com.nfe.entity.TNFe.InfNFe.Det.Imposto.COFINS.COFINSNT;
import br.com.nfe.entity.TNFe.InfNFe.Det.Imposto.ICMS;
import br.com.nfe.entity.TNFe.InfNFe.Det.Imposto.PIS;
import br.com.nfe.entity.TNFe.InfNFe.Det.Imposto.PIS.PISNT;
import br.com.nfe.entity.TNFe.InfNFe.Det.Prod;
import br.com.nfe.entity.TNFe.InfNFe.Emit;
import br.com.nfe.entity.TNFe.InfNFe.Ide;
import br.com.nfe.entity.TNFe.InfNFe.InfAdic;
import br.com.nfe.entity.TNFe.InfNFe.Total;
import br.com.nfe.entity.TNFe.InfNFe.Total.ICMSTot;
import br.com.nfe.entity.TNFe.InfNFe.Transp;
import br.com.nfe.entity.TNFe.InfNFe.Transp.Transporta;
import br.com.nfe.entity.TNFe.InfNFe.Transp.Vol;
import br.com.nfe.entity.TUf;
import br.com.nfe.entity.TUfEmi;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;



/**
 *
 * @author USUARIO
 */
public class TesteGerarXml {
 static String verificador = "";
    /**
     * @param args the command line arguments
     */
   
  public void gerarXmlTeste(){
      try {  
             String cUF = "31"; // Código da UF do emitente do Documento Fiscal.  
            String dataAAMM = "1609"; // Ano e Mês de emissão da NF-e.  
            String cnpjCpf = "09.688.632/0001-64"; // CNPJ do emitente.  
            String mod = "65"; // Modelo do Documento Fiscal.  
            String serie = "001"; // Série do Documento Fiscal.  
            String tpEmis = "1"; // Forma de emissão da NF-e  
            String nNF = "9"; // Número do Documento Fiscal.  
            String cNF = "12345678"; // Código Numérico que compõe a Chave de Acesso.  
              
            //Chave c = new Chave();
         // c.criarChave(cUF, tpEmis, nNF, cNF, serie, dataAAMM, cnpjCpf);
            ///c.start();
           
            
            TNFe nFe = new TNFe();  
           
            InfNFe infNFe = new InfNFe();  
      
            StringBuilder chavee =Chave.geraChave(cUF, dataAAMM, cnpjCpf, mod, serie, tpEmis, nNF, cNF);
            verificador = String.valueOf(Chave.modulo11(chavee.toString()));
              System.out.println("verificador" + verificador);
            chavee.append(Chave.modulo11(chavee.toString()));    
    
            chavee.insert(0, "NFe");   
              // info("Chave NF-e: " + chavee.toString());  
      infNFe.setId(chavee.toString());  
    
            infNFe.setVersao("3.10");  
      
            infNFe.setIde(dadosDeIdentificacao());  
            infNFe.setEmit(dadosDoEmitente());  
            //infNFe.setDest(dadosDoDestinatario());  
           
              
            infNFe.getDet().add(dadosDoProduto("1","123","calça jeans","","","64061000","5102","und","2.0000","30","60","und","2.0000","30","1","0","102","2","12.96","90","90","Produto de teste 1","0.00")); 
           // infNFe.getDet().add(dadosDoProduto("2","12334","calça jeans 2","","","99","5102","und","2.0000","30","60","und","2.0000","30","1","0","500","2",".00","7","7","Produto de teste 2","0.00")); 

            infNFe.setTotal(totaisDaNFe());  
            infNFe.setTransp(dadosDoTransporte());  
            infNFe.setInfAdic(informacoesAdicionais());  
             
            nFe.setInfNFe(infNFe);
            
      
            info("XML NF-e: " + strValueOf(nFe));  
            FileWriter fstream = new FileWriter("C:\\ACBrMonitorPLUS\\Arqs\\18320519000112\\NFe\\201609\\NFe\\Assinar\\1321312-nfe.xml");
  BufferedWriter out = new BufferedWriter(fstream);
  out.write(strValueOf(nFe));
  //Close the output stream
  out.close();
        } catch (Exception e) {  
            error(e.toString());  
        } 
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
  private static String retornaDataNFCe(Date dataASerFormatada) {  
    GregorianCalendar calendar = new GregorianCalendar();    
    calendar.setTime(dataASerFormatada);    
    XMLGregorianCalendar xmlCalendar = null;  
    try {  
        xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);  
        xmlCalendar.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);  
      
        return(xmlCalendar.toString());  
    } catch (DatatypeConfigurationException ex) {  
        
    }  
    return null;  
}  
    /** 
     * B - Identificação da Nota Fiscal eletrônica 
     * @return 
     */  
    private static Ide dadosDeIdentificacao() {  
        Ide ide = new Ide();  
       ide.setCUF("31");  
        ide.setCNF("12345678");  
        ide.setNatOp("5102");  
        ide.setIndPag("0");  
        ide.setMod("65");  
        ide.setSerie("1");  
        
        ide.setNNF("9");  
        ide.setDhEmi(retornaDataNFCe(new Date()));  
       // ide.setDhSaiEnt(retornaDataNFCe(new Date()));
       
       // ide.setHSaiEn("15:03:56");  
        ide.setTpNF("1");  
        ide.setIdDest("1");
        ide.setCMunFG("3145208");  
        
        ide.setTpImp("1");  
        ide.setTpEmis("1");  
        ide.setCDV(verificador);  
        ide.setTpAmb("2");  
        ide.setFinNFe("1");  
        ide.setProcEmi("0");  
        ide.setVerProc("3.10");  
        ide.setIndFinal("1");
        
        ide.setIndPres("9");
        
        return ide;  
    }  
  
    /** 
     * C - Identificação do Emitente da Nota Fiscal eletrônica 
     * @return 
     */  
    private static Emit dadosDoEmitente() {  
          Emit emit = new Emit(); 
        
        emit.setCNPJ("09688632000164");  
        emit.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");  
        emit.setXFant("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");  
  
        TEnderEmi enderEmit = new TEnderEmi();   
        enderEmit.setXLgr("AVENIDA DOM CRISTIANO");  
        enderEmit.setNro("492");  
        enderEmit.setXBairro("CENTRO");  
        enderEmit.setCMun("3149705");  
        enderEmit.setXMun("NOVA SERRANA");  
        enderEmit.setUF(TUfEmi.valueOf("MG"));  
        enderEmit.setCEP("35545000");  
        enderEmit.setCPais("1058");  
        enderEmit.setXPais("Brasil");  
        enderEmit.setFone("3732871305");  
        emit.setEnderEmit(enderEmit);  
    emit.setIE("0010774860081");  
        emit.setCRT("1");               
        return emit;  
    }  
  
    /** 
     * E - Identificação do Destinatário da Nota Fiscal eletrônica 
     * @return 
     */  
    private static Dest dadosDoDestinatario() {  
         Dest dest = new Dest();  
        dest.setCNPJ("18320519000112");  
        dest.setXNome("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");  
          dest.setIndIEDest("9");
        TEndereco enderDest = new TEndereco();   
        enderDest.setXLgr("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");  
        enderDest.setNro("10");  
        enderDest.setXBairro("WEB");  
        enderDest.setCMun("3145208");  
        enderDest.setXMun("Java");  
        enderDest.setUF(TUf.valueOf("MG"));  
        enderDest.setCEP("35519000");  
        enderDest.setCPais("1058");  
        enderDest.setXPais("Brasil");  
        enderDest.setFone("4845454545");  
        dest.setEnderDest(enderDest);  
  
        dest.setIE("0021686610050");  
        dest.setEmail("forum@javac.com.br");  
          
        return dest;  
    }  
  
    /** 
     * H - Detalhamento de Produtos e Serviços da NF-e 
     * I - Produtos e Serviços da NF-e 
     * M - Tributos incidentes no Produto ou Serviço 
     * V - Informações adicionais 
     * @return 
     */  
    // @param xProd - Descricao
    private static Det dadosDoProduto(String nItem, String cProd, String xProd,String CEAM,String CeamTrib, String NCM,String CFOP,String uCom,String qCom,
   String vUnCom,String vProd,String uTrib, String qTrib,String VUnTrib, String indTot, String icmsOrig,
   String icmsCSOSN,String icmsVBCSTRet,String vICMSSTRet, String pisCST,String cofisCST,String infoAdcProduto,String vTotTrib) {  
        Det det = new Det();  
        det.setNItem(nItem);  
  
        /** 
         * Dados do Produro 
         */  
        Prod prod = new Prod();  
        prod.setCProd(cProd);  //2
        prod.setCEAN(CEAM);
        prod.setXProd(xProd);  // exemplo geração xml JAXB
        prod.setNCM(NCM);  // 99
        prod.setCFOP(CFOP);  //5102
        prod.setUCom(uCom);  // UND
        prod.setQCom(qCom); // 2.0000 
        prod.setVUnCom(vUnCom);  //90.0000
        prod.setVProd(vProd);  //180.00
        prod.setCEANTrib(CeamTrib);
        prod.setUTrib(uTrib);  //und
        prod.setQTrib(qTrib);  //2.0000
        prod.setVUnTrib(VUnTrib);  //90.0000
        prod.setIndTot(indTot);  //1
        
        det.setProd(prod);  
        
        /** 
         * Impostos da NF-e 
         */  
        Imposto imposto = new Imposto();  
         
        ICMS icms = new ICMS();  
        ICMS.ICMSSN102 icmssn500 = new ICMS.ICMSSN102();  
        icmssn500.setOrig("0");  //0
        icmssn500.setCSOSN(icmsCSOSN);  //500
      //  icmssn500.set(icmsVBCSTRet);  //2.00
       // icmssn500.setVCredICMSSN(vICMSSTRet);  //.00
        icms.setICMSSN102(icmssn500);  
          
        PIS pis = new PIS();   
        PISNT pisnt = new PISNT();  
        pisnt.setCST("04");  //07
        pis.setPISNT(pisnt);  
  
        COFINS cofins = new COFINS();  
        COFINSNT cofinsnt = new COFINSNT();  
        cofinsnt.setCST("04");  //07
        cofins.setCOFINSNT(cofinsnt);  
        
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoVTotTrib(vTotTrib));  
        imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoICMS(icms));  
imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoPIS(pis));  
imposto.getContent().add(new ObjectFactory().createTNFeInfNFeDetImpostoCOFINS(cofins));  
        

  
det.setImposto(imposto);    
  
        /** 
         * Informações adicionais do Produto. 
         */  
        det.setInfAdProd(infoAdcProduto);  //NF ELETRONICA DE EXEMPLO
  
        return det;  
    }  
  
    /** 
     * W - Valores Totais da NF-e 
     * @return 
     */  
    private static Total totaisDaNFe() {  
        Total total = new Total();  
  
        ICMSTot icmstot = new ICMSTot();  
        icmstot.setVICMSDeson("0");
        icmstot.setVBC("0.00");  
        icmstot.setVICMS("0.00");  
        icmstot.setVBCST("0.00");  
        icmstot.setVST("0.00");  
        icmstot.setVProd("60.00");  
        icmstot.setVFrete("0.00");  
        icmstot.setVSeg("0.00");  
        icmstot.setVDesc("0.00");  
        icmstot.setVII("0.00");  
        icmstot.setVIPI("0.00");  
        icmstot.setVPIS("0.00");  
        icmstot.setVCOFINS("0.00");  
        icmstot.setVOutro("0.00");  
        icmstot.setVNF("60.00");  
        total.setICMSTot(icmstot);  
  
        return total;  
    }  
  
    /** 
     * X - Informações do Transporte da NF-e 
     * @return 
     */  
    private static Transp dadosDoTransporte() {  
        Transp transp = new Transp();  
        transp.setModFrete("1");  
          
        /** 
         * Dados da Transportadora. 
         */  
        Transporta transporta = new Transporta();  
        transporta.setCNPJ("15307254000124");  
        transporta.setXNome("ADRIANA LUIZ DE SOUZA 03904716662");  
        transporta.setIE("0019404420069");  
        transporta.setXEnder("NOVE, 679 - ITAPUA");  
        transporta.setXMun("NOVA SERRANA");  
        transporta.setUF(TUf.valueOf("MG"));          
        transp.setTransporta(transporta);  
          
        /** 
         * Dados de Volumes. 
         */  
        Vol vol = new Vol();  
        vol.setQVol("1");  
        //vol.setNVol("0");  
        vol.setEsp("VOL");
        vol.setPesoL("14.000");  
        vol.setPesoB("14.000");  
        transp.getVol().add(vol);  
  
        return transp;  
    }  
  
    /** 
     * Z - Informações Adicionais da NF-e 
     * @return 
     */  
    private static InfAdic informacoesAdicionais() {  
        InfAdic infAdic = new InfAdic();  
        infAdic.setInfCpl("Informações Adicionais da NF-e.");  
        
        return infAdic;  
    }  
  
    /** 
     * Método que Converte o Objeto em String. 
     * @param consStatServ 
     * @return 
     * @throws JAXBException 
     */  
    private static String strValueOf(TNFe nfe) throws JAXBException {  
        JAXBContext context = JAXBContext.newInstance(TNFe.class);  
        Marshaller marshaller = context.createMarshaller();  
        JAXBElement<TNFe> element = new ObjectFactory().createNFe(nfe);  
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);  
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);  
  
        StringWriter sw = new StringWriter();  
        marshaller.marshal(element, sw);  
  
        String xml = sw.toString();  
        xml = xml.replaceAll("xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\"", "");  
        xml = xml.replaceAll("<NFe>", "<NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\">");  
          
        return xml;  
    }  
  
   
}
