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
public class GrupoUsuario {
    int id;
    String nome;
    Boolean cadCliente;
    Boolean cadProduto;
    Boolean cadFornecedor;
    Boolean cadEmpresa;
    Boolean cadFuncionario;
    Boolean cadServico;
    Boolean caixa;
    Boolean contaPagar;
   Boolean contaReceber;
   Boolean venda;
   Boolean os;
   Boolean relatorio;
   Boolean contaContabil;
   Boolean configuracao;

    public GrupoUsuario() {
    }

    public GrupoUsuario(String nome, Boolean cadCliente, Boolean cadProduto, Boolean cadFornecedor, Boolean cadEmpresa, Boolean cadFuncionario, Boolean cadServico, Boolean caixa, Boolean contaPagar, Boolean contaReceber, Boolean venda, Boolean os, Boolean relatorio, Boolean contaContabil, Boolean configuracao) {
        this.nome = nome;
        this.cadCliente = cadCliente;
        this.cadProduto = cadProduto;
        this.cadFornecedor = cadFornecedor;
        this.cadEmpresa = cadEmpresa;
        this.cadFuncionario = cadFuncionario;
        this.cadServico = cadServico;
        this.caixa = caixa;
        this.contaPagar = contaPagar;
        this.contaReceber = contaReceber;
        this.venda = venda;
        this.os = os;
        this.relatorio = relatorio;
        this.contaContabil = contaContabil;
        this.configuracao = configuracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getCadCliente() {
        return cadCliente;
    }

    public void setCadCliente(Boolean cadCliente) {
        this.cadCliente = cadCliente;
    }

    public Boolean getCadProduto() {
        return cadProduto;
    }

    public void setCadProduto(Boolean cadProduto) {
        this.cadProduto = cadProduto;
    }

    public Boolean getCadFornecedor() {
        return cadFornecedor;
    }

    public void setCadFornecedor(Boolean cadFornecedor) {
        this.cadFornecedor = cadFornecedor;
    }

    public Boolean getCadEmpresa() {
        return cadEmpresa;
    }

    public void setCadEmpresa(Boolean cadEmpresa) {
        this.cadEmpresa = cadEmpresa;
    }

    public Boolean getCadFuncionario() {
        return cadFuncionario;
    }

    public void setCadFuncionario(Boolean cadFuncionario) {
        this.cadFuncionario = cadFuncionario;
    }

    public Boolean getCadServico() {
        return cadServico;
    }

    public void setCadServico(Boolean cadServico) {
        this.cadServico = cadServico;
    }

    public Boolean getCaixa() {
        return caixa;
    }

    public void setCaixa(Boolean caixa) {
        this.caixa = caixa;
    }

    public Boolean getContaPagar() {
        return contaPagar;
    }

    public void setContaPagar(Boolean contaPagar) {
        this.contaPagar = contaPagar;
    }

    public Boolean getContaReceber() {
        return contaReceber;
    }

    public void setContaReceber(Boolean contaReceber) {
        this.contaReceber = contaReceber;
    }

    public Boolean getVenda() {
        return venda;
    }

    public void setVenda(Boolean venda) {
        this.venda = venda;
    }

    public Boolean getOs() {
        return os;
    }

    public void setOs(Boolean os) {
        this.os = os;
    }

    public Boolean getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Boolean relatorio) {
        this.relatorio = relatorio;
    }

    public Boolean getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(Boolean contaContabil) {
        this.contaContabil = contaContabil;
    }

    public Boolean getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(Boolean configuracao) {
        this.configuracao = configuracao;
    }

   
    
    
    
}
