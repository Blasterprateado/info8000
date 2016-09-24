/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Relatorios;


import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;

import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;



/**
 *
 * @author user
 */
public class ReportUtilsDireto {
     public static void openReport(
            String titulo,
            InputStream inputStream,
            Map parametros,
            Connection conexao) throws JRException {
 
        /*
         * Cria um JasperPrint, que é a versão preenchida do relatório,
         * usando uma conexão.
         */
        JasperPrint print = JasperFillManager.fillReport(
                inputStream, parametros, conexao );

        // abre o JasperPrint em um JFrame
        //viewReportFrame( titulo, print);
 Thread t;
         t = new Thread(new Runnable() {
             
             @Override
             public void run() {
                 try {
                     imprimir(titulo, print); //To change body of generated methods, choose Tools | Templates.
                 } catch (JRException ex) {
                     Logger.getLogger(ReportUtilsDireto.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         });
         t.start();
    }
 
    /**
     * Abre um relatório usando um datasource genérico.
     *
     * @param titulo Título usado na janela do relatório.
     * @param inputStream InputStream que contém o relatório.
     * @param parametros Parâmetros utilizados pelo relatório.
     * @param dataSource Datasource a ser utilizado pelo relatório.
     * @throws JRException Caso ocorra algum problema na execução do relatório
     */
    public static void openReport(
            String titulo,
            InputStream inputStream,
            Map parametros,
            JRDataSource dataSource ) throws JRException {
 
        /*
         * Cria um JasperPrint, que é a versão preenchida do relatório,
         * usando um datasource genérico.
         */
        JasperPrint print = JasperFillManager.fillReport(
                inputStream, parametros, dataSource );
 
        // abre o JasperPrint em um JFrame
        //viewReportFrame( titulo, print );
         Thread t;
         t = new Thread(new Runnable() {
             
             @Override
             public void run() {
                 try {
                     imprimir(titulo, print); //To change body of generated methods, choose Tools | Templates.
                 } catch (JRException ex) {
                     Logger.getLogger(ReportUtilsDireto.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         });
         t.start();
        
 
    }
 
    /**
     * Cria um JFrame para exibir o relatório representado pelo JasperPrint.
     *
     * @param titulo Título do JFrame.
     * @param print JasperPrint do relatório.
     */
    private static void viewReportFrame( String titulo, JasperPrint print ) {
        //retirar caso queira so imprimir 
        try {
        
        //caso queira somente imprimir  // 
            JasperPrintManager.printReport(print, true);
             /*
             * Cria um JRViewer para exibir o relatório.
             * Um JRViewer é uma JPanel.
             */
            // JRViewer viewer = new JRViewer( print );
             
             // cria o JFrame
             // JFrame frameRelatorio = new JFrame( titulo );
             
             // adiciona o JRViewer no JFrame
              //frameRelatorio.add( viewer, BorderLayout.CENTER );
             
             // configura o tamanho padrão do JFrame
              //frameRelatorio.setSize( 500, 500 );
             
             // maximiza o JFrame para ocupar a tela toda.
             // frameRelatorio.setExtendedState( JFrame.MAXIMIZED_BOTH );
             
             // configura a operação padrão quando o JFrame for fechado.
            // frameRelatorio.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
             
             // exibe o JFrame
             // frameRelatorio.setVisible( true );
         } catch (JRException ex) {
             Logger.getLogger(ReportUtils.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null, "Erro ao imprimir"+ex);
         }
 
    }
    
     public static void imprimir( String titulo, JasperPrint print) throws JRException
  {
      
      
      long start = 0;
      try {
          start = System.currentTimeMillis();
       
          PrintService impressora = PrintServiceLookup.lookupDefaultPrintService();
          System.out.println(impressora);
          PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();          
          printRequestAttributeSet.add(MediaSizeName.NA_8X10);          
          
          PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();          
          printServiceAttributeSet.add(new PrinterName(impressora.getName(), null));          
         
          JRPrintServiceExporter exporter = new JRPrintServiceExporter();          
          exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);          
         exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);          
          exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);          
          //exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);          
          //exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);          
          
          exporter.exportReport();
      } catch (JRException jRException) {
          
      }
 


    System.err.println("Printing time : " + (System.currentTimeMillis() - start));
   
   
  }
     public static String ler(PrintService impressora){
         
         String nome ="C:\\config.txt"; 
         
         String linhaa="nada";
         /*
        try{
             FileReader arq = new FileReader(nome);
        
         BufferedReader lerArq = new BufferedReader(arq);
         String linha = lerArq.readLine(); // lê a primeira linha
         linhaa=linha;
            System.out.println(linha);
         
      arq.close();
        }catch(IOException io){
          System.err.printf("Erro na abertura do arquivo: %s.\n", io.getMessage());
          JOptionPane.showMessageDialog(null,"Erro no arquivo de configuração "+ io);
        }*/
         linhaa = impressora.getName();
         System.out.println(linhaa);
 return linhaa;
         
     }

    
}
