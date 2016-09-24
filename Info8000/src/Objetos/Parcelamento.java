/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.Date;

/**
 *
 * @author Marcos
 */
public class Parcelamento {
    int id;
    int id_venda;
    int id_cliente;
    Double valor;
    Date data;
    String documento;
    String Status;

    @Override
    public String toString() {
        return "Parcelamento{" + "id=" + id + ", id_venda=" + id_venda + ", id_cliente=" + id_cliente + ", valor=" + valor + ", data=" + data + ", documento=" + documento + ", Status=" + Status + '}';
    }
    

    public String getDocumento() {
        return documento;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
}
