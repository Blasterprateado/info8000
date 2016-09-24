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
public class Configuracao {
    int id;
    Boolean contasaPagar;
    Boolean contasaReceber;
    Boolean produtosEstoqueMinimo;
    Boolean valorCaixa;
     Boolean cartao;
    Boolean dinheiro;
    Boolean cheque;
    Boolean notinha;
    String corGeralFundo;
    String corGeralLetra;
    String corLetrasCancelado;
    String corLetrasCondicional;
    String corLetrasNormal;
    String corFundoCancelado;
     String corFundoCondicional;
      String corFundoNormal;
      int id_vendedor, id_cliente;

    public Configuracao(Boolean contasaPagar, Boolean contasaReceber, Boolean produtosEstoqueMinimo, Boolean valorCaixa, Boolean cartao, Boolean dinheiro, Boolean cheque, Boolean notinha, String corGeralFundo, String corGeralLetra, String corLetrasCancelado, String corLetrasCondicional, String corLetrasNormal, String corFundoCancelado, String corFundoCondicional, String corFundoNormal) {
        this.contasaPagar = contasaPagar;
        this.contasaReceber = contasaReceber;
        this.produtosEstoqueMinimo = produtosEstoqueMinimo;
        this.valorCaixa = valorCaixa;
        this.cartao = cartao;
        this.dinheiro = dinheiro;
        this.cheque = cheque;
        this.notinha = notinha;
        this.corGeralFundo = corGeralFundo;
        this.corGeralLetra = corGeralLetra;
        this.corLetrasCancelado = corLetrasCancelado;
        this.corLetrasCondicional = corLetrasCondicional;
        this.corLetrasNormal = corLetrasNormal;
        this.corFundoCancelado = corFundoCancelado;
        this.corFundoCondicional = corFundoCondicional;
        this.corFundoNormal = corFundoNormal;
    }

      
      
    public Boolean getCartao() {
        return cartao;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    

    public void setCartao(Boolean cartao) {
        this.cartao = cartao;
    }

    public Boolean getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(Boolean dinheiro) {
        this.dinheiro = dinheiro;
    }

    public Boolean getCheque() {
        return cheque;
    }

    public void setCheque(Boolean cheque) {
        this.cheque = cheque;
    }

    public Boolean getNotinha() {
        return notinha;
    }

    public void setNotinha(Boolean notinha) {
        this.notinha = notinha;
    }

      
      
      
    public Configuracao() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getContasaPagar() {
        return contasaPagar;
    }

    public void setContasaPagar(Boolean contasaPagar) {
        this.contasaPagar = contasaPagar;
    }

    public Boolean getContasaReceber() {
        return contasaReceber;
    }

    public void setContasaReceber(Boolean contasaReceber) {
        this.contasaReceber = contasaReceber;
    }

    public Boolean getProdutosEstoqueMinimo() {
        return produtosEstoqueMinimo;
    }

    public void setProdutosEstoqueMinimo(Boolean produtosEstoqueMinimo) {
        this.produtosEstoqueMinimo = produtosEstoqueMinimo;
    }

    public Boolean getValorCaixa() {
        return valorCaixa;
    }

    public void setValorCaixa(Boolean valorCaixa) {
        this.valorCaixa = valorCaixa;
    }

    public String getCorGeralFundo() {
        return corGeralFundo;
    }

    public void setCorGeralFundo(String corGeralFundo) {
        this.corGeralFundo = corGeralFundo;
    }

    public String getCorGeralLetra() {
        return corGeralLetra;
    }

    public void setCorGeralLetra(String corGeralLetra) {
        this.corGeralLetra = corGeralLetra;
    }

    public String getCorLetrasCancelado() {
        return corLetrasCancelado;
    }

    public void setCorLetrasCancelado(String corLetrasCancelado) {
        this.corLetrasCancelado = corLetrasCancelado;
    }

    public String getCorLetrasCondicional() {
        return corLetrasCondicional;
    }

    public void setCorLetrasCondicional(String corLetrasCondicional) {
        this.corLetrasCondicional = corLetrasCondicional;
    }

    public String getCorLetrasNormal() {
        return corLetrasNormal;
    }

    public void setCorLetrasNormal(String corLetrasNormal) {
        this.corLetrasNormal = corLetrasNormal;
    }

    public String getCorFundoCancelado() {
        return corFundoCancelado;
    }

    public void setCorFundoCancelado(String corFundoCancelado) {
        this.corFundoCancelado = corFundoCancelado;
    }

    public String getCorFundoCondicional() {
        return corFundoCondicional;
    }

    public void setCorFundoCondicional(String corFundoCondicional) {
        this.corFundoCondicional = corFundoCondicional;
    }

    public String getCorFundoNormal() {
        return corFundoNormal;
    }

    public void setCorFundoNormal(String corFundoNormal) {
        this.corFundoNormal = corFundoNormal;
    }
    
    
    
    
    
    
}
