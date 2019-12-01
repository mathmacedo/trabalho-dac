package com.tads.dac.managedBeans;

import com.tads.dac.beans.Cliente;
import com.tads.dac.facade.ClienteFacade;
import com.tads.dac.util.StringToMD5;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable{
    private int id;
    private String email;
    private String senha;
    
    public SessionBean() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) throws NoSuchAlgorithmException {
        this.senha = StringToMD5.toMD5(senha);
    }
    
    public String authenticate(){
        System.out.println(email + " " + senha);
        Cliente c = ClienteFacade.authenticate(this.email, this.senha);
        if (c != null){
            this.id = c.getId();
            return "menu_cliente";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao realizar login! Senha ou email incorretos.", null));

            FacesContext.getCurrentInstance().getExternalContext()
                    .getFlash().setKeepMessages(true);

            return "index";
        }
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        return "index?faces-redirect=true";
    }
}