/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author PauloHenrique
 */
public class Servicos {
    private int id;
    private String descricao;
    private Double valor;
    private Double pcusto;

    public Double getPcusto() {
        return pcusto;
    }

    public void setPcusto(Double pcusto) {
        this.pcusto = pcusto;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Servicos{" + "id=" + id + ", descricao=" + descricao + ", valor=" + valor + '}';
    }
    
}
