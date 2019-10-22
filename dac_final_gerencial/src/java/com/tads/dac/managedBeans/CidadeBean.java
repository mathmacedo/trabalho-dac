package com.tads.dac.managedBeans;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "cidadeBean")
@RequestScoped
public class CidadeBean {
    private int id;
    private String nome;
    private EstadoBean estado;
    
    public CidadeBean() {}

    public int getId(){ return this.id; }
    public void setId(int id){
        if (id > 0) this.id = id;
        else throw new RuntimeException(
            "Erro: Id da cidade não pode ser nulo!");
    }
    
    public String getNome() { return nome; } 
    public void setNome(String nome) { 
        if (nome != null)
            this.nome = nome; 
        else throw new RuntimeException(
            "Erro: Nome da Cidade não pode ser nulo!");
    }
    
    public EstadoBean getEstado(){ return this.estado; }
    public void setEstado(EstadoBean estado){
        if (estado != null && estado.getId() > 0 && estado.getNome() != null
                && estado.getSigla() != null) this.estado = estado;
        else throw new RuntimeException(
            "Erro: Estado inválido na criação de Cidade!");
    }
    
    public void cadastrar() throws IOException{
        FacesContext.getCurrentInstance().addMessage(null, new
            FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Cidade cadastrada com sucesso!", null));
        
        FacesContext.getCurrentInstance().getExternalContext()
            .getFlash().setKeepMessages(true);
        
        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("pesquisar_cidades.xhtml"); 
    }
}
