/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import util.Gerador;
/**
 *
 * @author helton
 */
@Entity
@Table(name = "PessoaFisica")
@PrimaryKeyJoinColumn
public class PessoaFisica extends Pessoa implements Serializable{
     private static final long serialVersionUID = 1L;
    
    private String cpf;
    private String rg;

    public PessoaFisica() {
    }

    public PessoaFisica(Long id, String cpf, String rg, String nome, 
            String telefone, String email, Endereco endereco) {
        super(id, nome, telefone, email, endereco);
        this.cpf = cpf;
        this.rg = rg;
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

    @Override
    public String toString() {
        return "PessoaFisica{" + "cpf=" + cpf + ", rg=" + rg + '}';
    }
}
