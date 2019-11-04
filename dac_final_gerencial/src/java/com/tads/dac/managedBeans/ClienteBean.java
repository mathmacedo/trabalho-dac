package com.tads.dac.managedBeans;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "clienteBean")
@RequestScoped
public class ClienteBean {

    public ClienteBean() {}

    public void cadastrar() throws IOException{
        FacesContext.getCurrentInstance().addMessage(null, new
            FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro Realizado!", null));

        FacesContext.getCurrentInstance().getExternalContext()
            .getFlash().setKeepMessages(true);

        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("index.xhtml"); 
    }
}