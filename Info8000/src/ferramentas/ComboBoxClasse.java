/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Conection.ClasseProdutoBD;
import Objetos.ClasseProduto;
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
public class ComboBoxClasse extends AbstractListModel implements ComboBoxModel{
    ClasseProdutoBD bd= new ClasseProdutoBD();
      List<ClasseProduto> lista= new ArrayList<ClasseProduto>();
      ClasseProduto produto ;

    public ComboBoxClasse() {
        try {
            this.lista = bd.GetListaClasseProduto();
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComboBoxClasse.class.getName()).log(Level.SEVERE, null, ex);
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
       
        produto=  (ClasseProduto) o;
       }

    @Override
    public Object getSelectedItem() {
        return produto;
       
    }
    
}
