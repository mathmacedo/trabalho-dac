package com.tads.dac.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_voo")
public class Voo implements Serializable {
    private int id;
    private CidadeAeroporto cidadeOrigem;
    private CidadeAeroporto cidadeDestino;
    private Piloto piloto;
    private Date dataHoraSaida;
    private Date dataHoraChegada;
    private double precoPrimeiraClasse;
    private double precoClasseEconomica;

    @Id
    @Column(name = "id_voo", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) {
        if (id > 0)
            this.id = id;
        else throw new RuntimeException(
            "Erro: ID do voo deve ser maior do que 0!");
    }

    @ManyToOne
    @JoinColumn(name = "id_cidade_origem")
    public CidadeAeroporto getCidadeOrigem() { return cidadeOrigem; }
    public void setCidadeOrigem(CidadeAeroporto cidadeOrigem) {
        if (cidadeOrigem != null)
            this.cidadeOrigem = cidadeOrigem;
        else throw new RuntimeException(
            "Erro: Cidade de Origem não pode ser nula!");
    }

    @ManyToOne
    @JoinColumn(name = "id_cidade_destino")
    public CidadeAeroporto getCidadeDestino() { return cidadeDestino; }
    public void setCidadeDestino(CidadeAeroporto cidadeDestino) {
        if (cidadeDestino != null)
            this.cidadeDestino = cidadeDestino;
        else throw new RuntimeException(
            "Erro: Cidade de Destino não pode ser nula!");
    }
    
    @ManyToOne
    @JoinColumn(name = "id_piloto")
    public Piloto getPiloto(){ return this.piloto; }
    public void setPiloto(Piloto piloto){
        if (piloto != null)
            this.piloto = piloto;
        else throw new RuntimeException(
            "Erro: Piloto do Voo não pode ser nulo!");
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora_saida", nullable = false)
    public Date getDataHoraSaida() { return dataHoraSaida; }
    public void setDataHoraSaida(Date dataHoraSaida) {
        if(dataHoraSaida != null)
            this.dataHoraSaida = dataHoraSaida;
        else throw new RuntimeException(
            "Erro: Data de Saída do Voo não pode ser nula!");
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora_chegada", nullable = false)
    public Date getDataHoraChegada() { return dataHoraChegada; }
    public void setDataHoraChegada(Date dataHoraChegada) {
        if (dataHoraChegada != null)
            this.dataHoraChegada = dataHoraChegada;
        else throw new RuntimeException(
            "Erro: Data de Chegada do Voo não pode ser nula!");
    }

    @Column(name = "preco_primeira_classe", nullable = false)
    public double getPrecoPrimeiraClasse() { return precoPrimeiraClasse; }
    public void setPrecoPrimeiraClasse(double precoPrimeiraClasse) {
        if (precoPrimeiraClasse > 0)
            this.precoPrimeiraClasse = precoPrimeiraClasse;
        else throw new RuntimeException(
            "Erro: Preço da Primeira Classe deve ser maior do que 0!");
    }

    @Column(name = "preco_classe_economica", nullable = false)
    public double getPrecoClasseEconomica() { return precoClasseEconomica; }
    public void setPrecoClasseEconomica(double precoClasseEconomica) {
        if (precoClasseEconomica > 0)
            this.precoClasseEconomica = precoClasseEconomica;
        else throw new RuntimeException(
            "Erro: Preço da Classe Econômica deve ser maior do que 0!");
    }
}
