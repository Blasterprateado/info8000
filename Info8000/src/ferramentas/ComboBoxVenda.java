/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Conection.VendaBD;
import Conection.VendaBD;
import Objetos.Venda;
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
public class ComboBoxVenda extends AbstractListModel implements ComboBoxModel{
   VendaBD bd= new VendaBD();
      List<Integer> lista= new ArrayList<Integer>();
      Integer produto ;

    public ComboBoxVenda() {
        try {
            this.lista = bd.getListaVendaID();
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComboBoxVenda.class.getName()).log(Level.SEVERE, null, ex);
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
      
        produto=  (Integer) o;
        

          
    }

    @Override
    public Object getSelectedItem() {
        return produto;
       
    }
    
}
