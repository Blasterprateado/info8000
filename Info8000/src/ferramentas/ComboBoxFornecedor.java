/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Conection.FornecedorBD;
import Objetos.Fornecedor;
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
public class ComboBoxFornecedor extends AbstractListModel implements ComboBoxModel{
    FornecedorBD bd= new FornecedorBD();
      List<Fornecedor> lista= new ArrayList<Fornecedor>();
      Fornecedor produto ;

    public ComboBoxFornecedor() {
        try {
            this.lista = bd.GetListaFornecedor();
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComboBoxFornecedor.class.getName()).log(Level.SEVERE, null, ex);
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
      
      produto=(Fornecedor) o;
              
    }

    @Override
    public Object getSelectedItem() {
        return produto;
       
    }
    
}
