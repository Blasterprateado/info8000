/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;




import Conection.LinhaBD;
import Objetos.ProdutoLinha;
import static com.jaspersoft.ireport.designer.palette.PaletteUtils.controller;
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
public class ComboBoxLinha extends AbstractListModel implements ComboBoxModel{
   
      List<ProdutoLinha> lista= new ArrayList<>();
      ProdutoLinha produtoLinha ;

    public ComboBoxLinha() {
        try {
            this.lista= new LinhaBD().getListaProdutoLinha();
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComboBoxLinha.class.getName()).log(Level.SEVERE, null, ex);
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
       
        produtoLinha=  (ProdutoLinha) o;
       }

    @Override
    public Object getSelectedItem() {
        return produtoLinha;
       
    }
    
}
