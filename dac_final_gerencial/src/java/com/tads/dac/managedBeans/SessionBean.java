package com.tads.dac.managedBeans;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable{
    private int id;
    private String nome;
    private char tipo;
    
    public SessionBean() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public char getTipo() { return tipo; }
    public void setTipo(char tipo) { this.tipo = tipo; }
}
