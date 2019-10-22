package com.tads.dac.managedBeans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "estadoBean")
@RequestScoped
public class EstadoBean  implements Serializable{
    private int id;
    private String nome;
    private String sigla;
    
    public EstadoBean() {}

    public int getId() { return id; }
    public void setId(int id) {
        if (id > 0)
            this.id = id;
        else throw new RuntimeException(
                "Erro: Id do Estado não pode ser menor ou igual a 0!");
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome != null)
            this.nome = nome;
        else throw new RuntimeException(
                "Erro: Nome do Estado não pode ser nulo!");
    }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) {
        if (sigla != null)
            this.sigla = sigla;
        else throw new RuntimeException(
                "Erro: Sigla do Estado não pode ser nula!");
    }
}
