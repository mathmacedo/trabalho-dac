package com.tads.dac.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_assento")
public class Assento implements Serializable {
    private int id;
    private String nome;
    private char status;
    private char tipo;
    private double valor;
    private Voo voo;    

    @Id
    @Column(name = "id_assento", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) {
        if(id > 0)
            this.id = id;
        else throw new RuntimeException(
            "Erro: ID do Assento deve ser maior do que 0!");
    }

    @Column(name = "nome_assento", length=3, nullable = false)
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome != null)
            this.nome = nome;
        else throw new RuntimeException(
            "Erro: Nome do Assento não pode ser nulo!");
    }

    @Column(name = "status_assento", length=1, nullable = false)
    public char getStatus() { return status; }
    public void setStatus(char status) {
        if (status == 'L' || status == 'R' || status == 'C')
            this.status = status;
        else throw new RuntimeException(
            "Erro: Status do assento inválido!");
    }
    
    @Column(name = "tipo_assento", length=1, nullable = false)
    public char getTipo() { return tipo; }
    public void setTipo(char tipo) {
        if (tipo == 'P' || tipo == 'E')
            this.tipo = tipo;
        else throw new RuntimeException(
            "Erro: Tipo do Assento inválido!");
    }
    
    @Column(name = "valor_assento", nullable = false)
    public double getValor(){ return this.valor; }
    public void setValor(double valor){
        if (valor > 0)
            this.valor = valor;
        else throw new RuntimeException(
            "Erro: Valor do assento deve ser maior do que 0!");
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_voo")
    public Voo getVoo() { return voo; }
    public void setVoo(Voo voo) {
        if (voo != null)
            this.voo = voo;
        else throw new RuntimeException(
            "Erro: Voo não pode ser nulo!");
    }
}
