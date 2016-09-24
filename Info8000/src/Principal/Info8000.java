/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import fronteira.FormLogin;
import javax.swing.UIManager;

/**
 *
 * @author user
 */
public class Info8000 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Windows".equals(info.getName())) {
            UIManager.setLookAndFeel(info.getClassName());
            break;
        }
    }
} catch (Exception e) {}
        FormLogin form = new FormLogin();
      
        form.setVisible(true);
    }
    
}
