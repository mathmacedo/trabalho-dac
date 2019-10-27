package com.tads.dac.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_estado")
public class Estado implements Serializable {
    private int id;
    private String nome;
    private String sigla;

    @Id
    @Column(name = "id_estado", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) {
        if (id > 0)
            this.id = id;
        else throw new RuntimeException(
            "Erro! ID do estado deve ser maior do que 0!");
    }

    @Column(length = 30, nullable = false, name = "nome_estado")
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome != null)
            this.nome = nome;
        else throw new RuntimeException(
            "Erro: Nome do Estado não pode ser nulo!");
    }

    @Column(length = 2, nullable = false, name = "sigla_estado")
    public String getSigla() { return sigla; }
    public void setSigla(String sigla) {
        if(sigla != null)
            this.sigla = sigla; 
        else throw new RuntimeException(
            "Erro: Sigla do Estado da não pode ser nula!");
    }
}
