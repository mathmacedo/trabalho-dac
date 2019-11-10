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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_checkin")
public class Checkin implements Serializable {
    private int id;
    private Date data;
    private Time hora;
    private Assento assento;
    private Funcionario funcionario;
    
    @Id
    @Column(name = "id_checkin", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) {
        if (id > 0) 
            this.id = id;
        else throw new RuntimeException(
            "Erro: ID do Checkin deve ser maior do que 0!");
    }
    
    @OneToOne
    @JoinColumn(name = "id_assento", nullable = false)
    public Assento getAssento() { return assento; }
    public void setAssento(Assento assento) {
        if(assento != null) 
            this.assento = assento;
        else throw new RuntimeException(
            "Erro: Assento do Checkin não pode ser nulo!");
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "data_checkin", nullable = false)
    public Date getData() { return data; }
    public void setData(Date data) {
        if (data != null)
            this.data = data;
        else throw new RuntimeException(
            "Erro: Data de Checkin não pode ser nula!");
    }

    @Column(name = "hora_checkin", nullable = false)
    public Time getHora() { return hora; }
    public void setHora(String hora) {
        if(hora != null) 
            this.hora = Time.valueOf(hora);
        else throw new RuntimeException(
            "Erro: Hora do Checkin não pode ser nula!");
   }

    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) {
        if(funcionario != null)
            this.funcionario = funcionario;
        else throw new RuntimeException(
            "Erro: Funcionário do Checkin não pode ser nulo!");
    }
}
