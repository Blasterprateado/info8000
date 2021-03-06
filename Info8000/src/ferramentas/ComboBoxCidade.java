/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Conection.CidadeBD;
import Objetos.Cidade;
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
public class ComboBoxCidade extends AbstractListModel implements ComboBoxModel{
    
      List<Cidade> lista= new ArrayList<Cidade>();
      Cidade produto ;

    public ComboBoxCidade(List<Cidade> lista) {
        
            this.lista =lista;
            
        
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
      
          try {
              produto=new CidadeBD().getListaCidadeBYNOME(o.toString());
          } catch (Exception ex) {
              Logger.getLogger(ComboBoxCidade.class.getName()).log(Level.SEVERE, null, ex);
          }
              
    }

    @Override
    public Object getSelectedItem() {
        return produto;
       
    }
    
}
