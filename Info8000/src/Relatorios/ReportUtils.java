/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Relatorios;

import java.awt.BorderLayout;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author user
 */
public class ReportUtils {
     public static void openReport(
            String titulo,
            InputStream inputStream,
            Map parametros,
            Connection conexao,
             JFrame frame
     ) throws JRException {
 
        /*
         * Cria um JasperPrint, que é a versão preenchida do relatório,
         * usando uma conexão.
         */
        JasperPrint print = JasperFillManager.fillReport(
                inputStream, parametros, conexao );

                   viewReportFrame( titulo, print , frame ); //To change body of generated methods, choose Tools | Templates.
        
        
       
 
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
            JRDataSource dataSource,
            JFrame frame
    ) throws JRException {
 
        /*
         * Cria um JasperPrint, que é a versão preenchida do relatório,
         * usando um datasource genérico.
         */
        JasperPrint print = JasperFillManager.fillReport(
                inputStream, parametros, dataSource );
 
        Thread t;
         t = new Thread(new Runnable() {
             
             @Override
             public void run() {
                   viewReportFrame( titulo, print, frame ); //To change body of generated methods, choose Tools | Templates.
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
    private static void viewReportFrame( String titulo, JasperPrint print, JFrame frame ) {
        //retirar caso queira so imprimir// try {
        
        //caso queira somente imprimir  // JasperPrintManager.printPage(print, 0, true);
             /*
             * Cria um JRViewer para exibir o relatório.
             * Um JRViewer é uma JPanel.
             */
             JRViewer viewer = new JRViewer( print );
             
             // cria o JFrame
              JFrame frameRelatorio = new JFrame( titulo );
             
             // adiciona o JRViewer no JFrame
              frameRelatorio.add( viewer, BorderLayout.CENTER );
             
             // configura o tamanho padrão do JFrame
              frameRelatorio.setSize( 500, 500 );
             
             // maximiza o JFrame para ocupar a tela toda.
              frameRelatorio.setExtendedState( JFrame.MAXIMIZED_BOTH );
             
             // configura a operação padrão quando o JFrame for fechado.
             frameRelatorio.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
             
             // exibe o JFrame
              frameRelatorio.setVisible( true );
              frame.dispose();
        // } catch (JRException ex) {
             //Logger.getLogger(ReportUtils.class.getName()).log(Level.SEVERE, null, ex);
             //JOptionPane.showMessageDialog(null, "Erro ao imprimir"+ex);
        // }
 
    }
}
