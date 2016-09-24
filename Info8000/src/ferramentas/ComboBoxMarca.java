/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Conection.MarcaBD;
import Objetos.Marca;
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
public class ComboBoxMarca extends AbstractListModel implements ComboBoxModel{
    MarcaBD bd= new MarcaBD();
      List<Marca> lista= new ArrayList<Marca>();
      Marca produto ;

    public ComboBoxMarca() {
        try {
            this.lista = bd.getListaMarca();
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComboBoxMarca.class.getName()).log(Level.SEVERE, null, ex);
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
       
        produto=  (Marca) o;
       }

    @Override
    public Object getSelectedItem() {
        return produto;
       
    }
    
}
