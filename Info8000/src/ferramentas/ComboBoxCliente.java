/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Conection.ClienteBD;
import Objetos.Cliente;
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
public class ComboBoxCliente extends AbstractListModel implements ComboBoxModel{
    ClienteBD bd= new ClienteBD();
      List<Cliente> lista= new ArrayList<Cliente>();
      Cliente produto ;

    public ComboBoxCliente() {
        try {
            this.lista = bd.getLista();
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComboBoxCliente.class.getName()).log(Level.SEVERE, null, ex);
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
        if(o!=null){
            ClienteBD bd = new ClienteBD();
                  System.out.println(o.toString());
        Cliente cliente = bd.getClienteByNome(o.toString());
        produto=  (Cliente) cliente;
        }

          
    }

    @Override
    public Object getSelectedItem() {
        return produto;
       
    }
    
}
