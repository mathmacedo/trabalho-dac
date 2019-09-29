package com.tads.dac.managedBeans;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "funcionarioBean")
@RequestScoped
public class FuncionarioBean {
    private String nome;
    
    public FuncionarioBean() {}

    public String getNome() { return nome; } 
    public void setNome(String nome) { this.nome = nome; }
    
    public void cadastrar() throws IOException{
        FacesContext.getCurrentInstance().addMessage(null, new
            FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro Realizado!", null));
        
        FacesContext.getCurrentInstance().getExternalContext()
            .getFlash().setKeepMessages(true);
        
        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("pesquisar_funcionarios.xhtml"); 
    }
}
