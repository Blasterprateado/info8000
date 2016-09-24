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
public class Produto {
    private int _id;
    private String descricao;
    private Double preco_compra;
    private Double preco_venda;
    private Double qtd_estoque;
    private String Classe;
    private Double estoque_minimo;
    private String fornecedor;
    private String marca;
    private long codBarras;

    public long getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(long codBarras) {
        this.codBarras = codBarras;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getClasse() {
        return Classe;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setClasse(String Classe) {
        this.Classe = Classe;
    }

    public Double getEstoque_minimo() {
        return estoque_minimo;
    }

    public void setEstoque_minimo(Double estoque_minimo) {
        this.estoque_minimo = estoque_minimo;
    }

    

    

    public Produto() {
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }



    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(Double preco_compra) {
        this.preco_compra = preco_compra;
    }

    public Double getPreco_venda() {
        return preco_venda;
    }

    public void setPreco_venda(Double preco_venda) {
        this.preco_venda = preco_venda;
    }

    public Double getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(Double qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    @Override
    public String toString() {
        return descricao ;
    }

    
    public boolean equals(Produto o) {
        if(this==o){
            return true;
            
        }
        if(o==null){
            return false;
            
        }
        return o.descricao==this.descricao ||(this.descricao!=null&& this.descricao.equals(o.descricao));
    }

   
    
}
