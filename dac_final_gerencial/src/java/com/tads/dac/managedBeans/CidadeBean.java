package com.tads.dac.managedBeans;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "cidadeBean")
@RequestScoped
public class CidadeBean {
    private String nome;
    
    public CidadeBean() {}

    public String getNome() { return nome; } 
    public void setNome(String nome) { this.nome = nome; }
    
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
