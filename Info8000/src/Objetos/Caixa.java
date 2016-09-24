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
public class Caixa {
   private int id;
   private String descricao;
   private Double valorinicial;
   private Double valoratual;
   private Date data;
   private String Status;
    public Caixa() {
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    public Double getValorinicial() {
        return valorinicial;
    }

    public void setValorinicial(Double valorinicial) {
        this.valorinicial = valorinicial;
    }

    public Double getValoratual() {
        return valoratual;
    }

    public void setValoratual(Double valoratual) {
        this.valoratual = valoratual;
    }

   
   
   
}


