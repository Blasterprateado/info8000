/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author user
 */
public class ClasseProduto {
    private Integer id;
    private String Desc;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return Desc ;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return Desc;
    }

    public void setDescricao(String Descricao) {
        this.Desc = Descricao;
    }
    
}
