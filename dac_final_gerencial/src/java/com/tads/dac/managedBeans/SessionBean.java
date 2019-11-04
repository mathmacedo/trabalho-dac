package com.tads.dac.managedBeans;

import com.tads.dac.beans.Funcionario;
import com.tads.dac.facade.FuncionarioFacade;
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
    private char tipo;
    
    public SessionBean() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getSenha(){ return this.senha; }
    public void setSenha(String senha) throws NoSuchAlgorithmException{ 
        this.senha = StringToMD5.toMD5(senha); 
    }
        
    public char getTipo() { return tipo; }
    public void setTipo(char tipo) { this.tipo = tipo; }
    
    public String authenticate(){
        Funcionario f = FuncionarioFacade.authenticate(this.email, this.senha);
        if (f != null){
            this.id = f.getId();
            this.tipo = f.getTipo();
            
            if (this.tipo == 'F')
                return "menu_funcionario";
            else return "menu_gerente";
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Erro: Email ou senha são inválidos! Tente novamente...", null));
        
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


