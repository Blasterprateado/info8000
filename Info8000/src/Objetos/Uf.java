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
public class Uf {
    int codUF;
    String sigla;

    public int getCodUF() {
        return codUF;
    }

    public void setCodUF(int codUF) {
        this.codUF = codUF;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return  sigla ;
    }
    
    
    
}
