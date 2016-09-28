/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Conection.UfBD;
import Objetos.Uf;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author user
 */
public class ComboBoxUF extends AbstractListModel implements ComboBoxModel{
    UfBD bd= new UfBD();
      List<Uf> lista= new ArrayList<Uf>();
      Uf produto ;

    public ComboBoxUF() {
        try {
            this.lista = bd.getlistUf();
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComboBoxUF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
       return lista.get(index);
    }

    @Override
    public void setSelectedItem(Object o) {
      
      produto=(Uf) o;
              
    }

    @Override
    public Object getSelectedItem() {
        return produto;
       
    }
    
}
