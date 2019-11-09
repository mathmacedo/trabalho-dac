package com.tads.dac.beans;

import java.io.Serializable;
import java.util.Calendar;
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
@Table(name = "tb_reserva")
public class Reserva implements Serializable {
    private int id;
    private char status;
    private Calendar horaReserva;
    private Boleto boleto;
    private Cliente cliente; //waiting git pull
    private Assento assentos;
    
    @Id
    @Column(name = "id_reserva", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) {
        if (id > 0)
            this.id = id;
        else throw new RuntimeException(
            "Erro: ID da reserva deve ser maior do que 0!");
    }

    @Column(name = "status_reserva", length = 1, nullable = false)
    public char getStatus(){ return this.status; }
    public void setPago(char status){
        if(status == 'C' || status == 'P' || status == 'F')
            this.status = status;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_hora_reserva", nullable = false)
    public Calendar gethoraReserva(){ return this.horaReserva; }
    public void setHoraReserva(Calendar hora){
        if(hora != null){
            this.horaReserva = hora;
        }
        else throw new RuntimeException(
            "Erro: Data/Hora da reserva não pode ser nula!");
    }
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    public Cliente getCliente() { return this.cliente; }
    public void setCliente(Cliente cliente) {
        if(cliente != null)
            this.cliente = cliente;
        else throw new RuntimeException(
            "Erro: Cliente da reserva não pode ser nulo!");
    }
    
    @ManyToOne
    @JoinColumn(name = "id_boleto")
    public Boleto getBoleto() { return this.boleto; }
    public void setBoleto(Boleto boleto) {
        if(boleto != null)
            this.boleto = boleto;
        else throw new RuntimeException(
            "Erro: Boleto da reserva não pode ser nulo!");
    }
}
