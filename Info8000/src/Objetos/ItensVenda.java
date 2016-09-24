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
public class ItensVenda {
    private Long id;
    private int id_produto;
    private int id_venda;
    private String descProduto;
    private int qtd;
    private Double valorUnt;
    private Double total;

    public Double getValorUnt() {
        return valorUnt;
    }

    public void setValorUnt(Double valorUnt) {
        this.valorUnt = valorUnt;
    }
    

    public ItensVenda(int id_produto, int id_venda) {
        this.id_produto = id_produto;
        this.id_venda = id_venda;
    }

    public String getDescProduto() {
        return descProduto;
    }

    public void setDescProduto(String descProduto) {
        this.descProduto = descProduto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ItensVenda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    @Override
    public String toString() {
        
      return  descProduto ;
    }
    
}
