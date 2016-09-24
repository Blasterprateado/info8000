/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;



import Conection.UnidadeComercialBD;
import Objetos.Unidadecomercial;
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
public class ComboBoxUndComercial extends AbstractListModel implements ComboBoxModel{
  
      List<Unidadecomercial> lista= new ArrayList<>();
      Unidadecomercial produtoColecao ;

    public ComboBoxUndComercial() {
        try {
            this.lista= new UnidadeComercialBD().getListaUnidadecomercial();
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComboBoxUndComercial.class.getName()).log(Level.SEVERE, null, ex);
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
       
        produtoColecao=  (Unidadecomercial) o;
       }

    @Override
    public Object getSelectedItem() {
        return produtoColecao;
       
    }
    
}
