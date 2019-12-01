package com.tads.dac.managedBeans;

import com.tads.dac.beans.Voo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

@Named(value = "vooBean")
@SessionScoped
public class VooBean implements Serializable {
    private List<Voo> lista;
    
    public VooBean(){}

    public List<Voo> getLista() {
        return lista;
    }

    public void setLista(List<Voo> lista) {
        this.lista = lista;
    }
    
    @PostConstruct
    public void init(){
        GenericType<List<Voo>> genericType = new GenericType<List<Voo>>(){};
        Client c = ClientBuilder.newClient();
        this.lista = c.target("http://localhost:8080/dac_final_gerencial/webresources/voo")
                .request(MediaType.APPLICATION_JSON+";charset=utf-8")
                .get(genericType);
    }
}
