/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author USUARIO
 */
public class ProdutoNfe {
    String descricao;
    Double valorunt;
    Double qtd;
    String undComercial;
    String cod;
    Double total;

    public ProdutoNfe() {
    }

    public Double getQtd() {
        return qtd;
    }

    public void setQtd(Double qtd) {
        this.qtd = qtd;
    }

    public ProdutoNfe(String descricao, Double valorunt, Double qtd, String undComercial, String cod) {
        this.descricao = descricao;
        this.valorunt = valorunt;
        this.qtd = qtd;
        this.undComercial = undComercial;
        this.cod = cod;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorunt() {
        return valorunt;
    }

    public void setValorunt(Double valorunt) {
        this.valorunt = valorunt;
    }

   

    public String getUndComercial() {
        return undComercial;
    }

    public void setUndComercial(String undComercial) {
        this.undComercial = undComercial;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
    
    public Produto getProduto(){
        Produto produto = new Produto();
        produto.setDescricao(descricao);
        produto.setPreco_compra(valorunt);
        produto.setQtd_estoque(qtd);
        
        
        return produto;
    }
    
}
