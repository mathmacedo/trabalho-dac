package com.tads.dac.managedBeans;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "pilotoBean")
@RequestScoped
public class PilotoBean {
    private String nome;
    
    public PilotoBean() {}

    public String getNome() { return nome; } 
    public void setNome(String nome) { this.nome = nome; }
    
    public void cadastrar() throws IOException{
        FacesContext.getCurrentInstance().addMessage(null, new
            FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Cadastro Realizado com sucesso!", null));
        
        FacesContext.getCurrentInstance().getExternalContext()
            .getFlash().setKeepMessages(true);
        
        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("pesquisar_pilotos.xhtml"); 
    }
}
