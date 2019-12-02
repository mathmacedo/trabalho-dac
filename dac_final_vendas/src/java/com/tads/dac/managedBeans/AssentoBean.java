package com.tads.dac.managedBeans;

import com.tads.dac.beans.Assento;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

@Named(value = "assentoBean")
@SessionScoped
public class AssentoBean implements Serializable{
    private List<Assento> listaAssentos;
    private List<String> selecionados;
    
    public AssentoBean() {}

    public List<Assento> getListaAssentos() {
        return listaAssentos;
    }

    public void setListaAssentos(List<Assento> listaAssentos) {
        this.listaAssentos = listaAssentos;
    }

    public List<String> getSelecionados() {
        return selecionados;
    }

    public void setSelecionados(List<String> selecionados) {
        this.selecionados = selecionados;
    }
    
    public String getAssentos(int id){
        Client c = ClientBuilder.newClient();
        
        GenericType<List<Assento>> genericType = new GenericType<List<Assento>>(){};
        this.listaAssentos = c
                .target("http://localhost:8080/dac_final_gerencial/webresources/assentos/" 
                        + Integer.toString(id))
                .request(MediaType.APPLICATION_JSON+";charset=utf-8")
                .get(genericType);
        
        return "consultar_assentos";
    }
}
