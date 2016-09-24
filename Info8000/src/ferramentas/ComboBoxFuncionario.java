/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Conection.FuncionarioBD;
import Objetos.Funcionario;
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
public class ComboBoxFuncionario extends AbstractListModel implements ComboBoxModel{
    FuncionarioBD bd= new FuncionarioBD();
      List<Funcionario> lista= new ArrayList<Funcionario>();
      Funcionario produto ;

    public ComboBoxFuncionario() {
        try {
            this.lista = bd.getListaFuncionario();
            
            
        } catch (Exception ex) {
            Logger.getLogger(ComboBoxFuncionario.class.getName()).log(Level.SEVERE, null, ex);
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
        
        produto=  (Funcionario) o;
        

          
    }

    @Override
    public Object getSelectedItem() {
        return produto;
       
    }
    
}
