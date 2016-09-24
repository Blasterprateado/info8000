/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;


import java.util.Date;





/**
 *
 * @author user
 */
public class Funcionario {
    private int id;
    private String nome;
    private Double salario;
    private String endereco;
    private String permissao;
    private String usuario;
    private String senha;
    private String cepCidade;
    private String cpf;

   
    
  

    
    private String rg;
    private String tel;
    private int cidade;

    public int getCidade() {
        return cidade;
    }

    public void setCidade(int cidade) {
        this.cidade = cidade;
    }

   

  

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    private String numero;

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    private String bairro;
    
   private Date dataAdm;
   private Date dataDm;
    public String getUsuario() {
        return usuario;
    }

    public Date getDataAdm() {
        return dataAdm;
    }

    public void setDataAdm(Date dataAdm) {
        this.dataAdm = dataAdm;
    }

    public Date getDataDm() {
        return dataDm;
    }

    public void setDataDm(Date dataDm) {
        this.dataDm = dataDm;
    }

    

    public String getCepCidade() {
        return cepCidade;
    }

    public void setCepCidade(String cepCidade) {
        this.cepCidade = cepCidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    

    

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Funcionario() {
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
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

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return  nome ;
    }
   
}
