/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;




import Conection.GeneroBD;
import Objetos.ProdutoGenero;
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
public class ComboBoxGenero extends AbstractListModel implements ComboBoxModel{
    
      List<ProdutoGenero> lista= new ArrayList<>();
      ProdutoGenero produtoGenero ;

    public ComboBoxGenero() {
        try {
            this.lista= new GeneroBD().getListaProdutoGenero();
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComboBoxGenero.class.getName()).log(Level.SEVERE, null, ex);
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
       
        produtoGenero=  (ProdutoGenero) o;
       }

    @Override
    public Object getSelectedItem() {
        return produtoGenero;
       
    }
    
}
