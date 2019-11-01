package com.tads.dac.beans;

import java.io.Serializable;
import java.sql.Time;
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
    private Time horarioPartida;
    private Time horarioChegada;
    private Date dataSaida;
    private Date dataChegada;
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
    
    @Column(name = "horario_partida", nullable = false)
    public Time getHorarioPartida() { return horarioPartida; }
    public void setHorarioPartida(Time horarioPartida) {
        if (horarioPartida != null)
            this.horarioPartida = horarioPartida;
        else throw new RuntimeException(
            "Erro: Horário de Partida do Voo não pode ser nulo!");
    }
    
    @Column(name = "horario_chegada", nullable = false)
    public Time getHorarioChegada() { return horarioChegada; }
    public void setHorarioChegada(Time horarioChegada) {
        if (horarioChegada != null)
            this.horarioChegada = horarioChegada;
        else throw new RuntimeException(
            "Erro: Horário de Chegada do Voo não pode ser nulo!");
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_saida", nullable = false)
    public Date getDataSaida() { return dataSaida; }
    public void setDataSaida(Date dataSaida) {
        if(dataSaida != null)
            this.dataSaida = dataSaida;
        else throw new RuntimeException(
            "Erro: Data de Saída do Voo não pode ser nula!");
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_chegada", nullable = false)
    public Date getDataChegada() { return dataChegada; }
    public void setDataChegada(Date dataChegada) {
        if (dataChegada != null)
            this.dataChegada = dataChegada;
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
