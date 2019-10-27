package com.tads.dac.beans;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cidade")
public class Cidade implements Serializable {
    private int id;
    private String nome;
    private Estado estado;

    @Id
    @Column(name = "id_cidade", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) {
        if(id > 0)
            this.id = id;
        else throw
            new RuntimeException("Erro: ID da Cidade deve ser maior do que 0!");
    }

    @Column(name = "nome_cidade", length =30, nullable = false)
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome != null)
            this.nome = nome;
        else throw
            new RuntimeException("Erro: Nome da Cidade não pode ser nulo!");
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estado")
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) {
        if (estado != null)
            this.estado = estado;
        else throw 
            new RuntimeException("Erro: Estado não pode ser nulo!");
    }
}
