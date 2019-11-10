package com.tads.dac.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_boleto")
public class Boleto implements Serializable{
    private int id;
    private double valor;
    private Calendar emissao;
    private List<Reserva> reservas;

    @Id
    @Column(name = "id_boleto", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) {
        if (id > 0)
            this.id = id;
        else throw new RuntimeException(
            "Erro: ID do boleto deve ser maior do que 0!");
    }

    @Column(name = "valor_boleto", nullable = false)
    public double getValor() { return valor; }
    public void setValor(double valor) {
        if (valor > 0)
            this.valor = valor;
        else throw new RuntimeException(
            "Erro: Valor do boleto deve ser maior do que 0!");
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "data_emissao_boleto", nullable = false)
    public Calendar getEmissao() { return emissao; }
    public void setEmissao(Calendar emissao) {
        if (emissao != null)
            this.emissao = emissao; 
        else throw new RuntimeException(
            "Erro: Hora de emissão do boleto não pode ser nula!");
    }

    @OneToMany(mappedBy = "boleto")
    public List<Reserva> getReservas() { return reservas; }
    public void setReservas(List<Reserva> reservas){ this.reservas = reservas; } 
}
