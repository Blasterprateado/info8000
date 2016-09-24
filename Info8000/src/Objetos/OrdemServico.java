/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author PauloHenrique
 */
public class OrdemServico {
    private int id;
    private String cliente;
    private String funcionario;
    private String descricao_equipamento;
    private String descricao_problema;
    private String observacao;
    private String status;
    private Timestamp data_entrada;
    private Timestamp data_saida;
    private Double valor;
    private int id_caixa;
    private String tipo_Atendimento;
    private String garantia;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public String toString() {
        return "OrdemServico{" + "id=" + id + ", cliente=" + cliente + ", funcionario=" + funcionario + ", descricao_equipamento=" + descricao_equipamento + ", descricao_problema=" + descricao_problema + ", observacao=" + observacao + ", status=" + status + ", data_entrada=" + data_entrada + ", data_saida=" + data_saida + ", valor=" + valor + ", id_caixa=" + id_caixa + ", tipo_Atendimento=" + tipo_Atendimento + ", garantia=" + garantia + '}';
    }

    public String getTipo_Atendimento() {
        return tipo_Atendimento;
    }

    public void setTipo_Atendimento(String tipo_Atendimento) {
        this.tipo_Atendimento = tipo_Atendimento;
    }

    public String getGarantia() {
        return garantia;
    }

    public void setGarantia(String garantia) {
        this.garantia = garantia;
    }

    public String getDescricao_equipamento() {
        return descricao_equipamento;
    }

    public void setDescricao_equipamento(String descricao_equipamento) {
        this.descricao_equipamento = descricao_equipamento;
    }

    public String getDescricao_problema() {
        return descricao_problema;
    }

    public void setDescricao_problema(String descricao_problema) {
        this.descricao_problema = descricao_problema;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(Timestamp data_entrada) {
        this.data_entrada = data_entrada;
    }

    public Timestamp getData_saida() {
        return data_saida;
    }

    public void setData_saida(Timestamp data_saida) {
        this.data_saida = data_saida;
    }

    

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getId_caixa() {
        return id_caixa;
    }

    public void setId_caixa(int id_caixa) {
        this.id_caixa = id_caixa;
    }
    
    
   
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    
    
    
}
