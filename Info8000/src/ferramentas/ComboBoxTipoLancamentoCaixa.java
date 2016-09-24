/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ferramentas;


import Conection.ClasseCaixaBD;
import Objetos.ClasseCaixa;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author user
 */
public class ComboBoxTipoLancamentoCaixa extends AbstractListModel implements ComboBoxModel {
    ClasseCaixaBD bd= new ClasseCaixaBD();
      List<ClasseCaixa> lista= new ArrayList<ClasseCaixa>();
     ClasseCaixa classe ;

    public ComboBoxTipoLancamentoCaixa() {
        try {
            this.lista = bd.GetListaClasseCaixa();
        } catch (Exception ex) {
           // Logger.getLogger(ComboBoxAdicional.class.getName()).log(Level.SEVERE, null, ex);
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
      classe=(ClasseCaixa) o;
    }

    @Override
    public Object getSelectedItem() {
        return classe;
       
    }
}
