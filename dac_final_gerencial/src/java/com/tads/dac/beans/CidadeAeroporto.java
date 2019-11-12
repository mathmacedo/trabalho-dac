package com.tads.dac.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cidade_aeroporto")
public class CidadeAeroporto implements Serializable {
    private int id;
    private String nome;
    private Cidade cidade;
    private String sigla;

    @Id
    @Column(name = "id_cidade_aeroporto", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) {
        if(id > 0)
            this.id = id;
        else throw new RuntimeException(
            "Erro: ID da Cidade com Aeroporto deve ser maior do que 0!");
    }

    @Column(name = "nome_cidade_aeroporto", length =30, nullable = false)
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome != null)
            this.nome = nome;
        else throw new RuntimeException(
            "Erro: Nome da Cidade com Aeroporto não pode ser nulo!");
    }

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    public Cidade getCidade() { return cidade; }
    public void setCidade(Cidade cidade) {
        if (cidade != null)
            this.cidade = cidade;
        else throw 
            new RuntimeException(
                "Erro: Cidade da Cidade com Aeroporto não pode ser nulo!");
    }
    
    @Column(name = "sigla_cidade_aeroporto", nullable = false, length = 3)
    public String getSigla(){ return this.sigla; }
    public void setSigla(String sigla){
        if (sigla != null)
            this.sigla = sigla;
        else throw new RuntimeException(
            "Erro: Sigla da Cidade com Aeroporto não pode ser nula!");
    }
}
