package com.tads.dac.managedBeans;

import com.tads.dac.beans.Assento;
import com.tads.dac.facade.AssentoFacade;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

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
        this.listaAssentos = AssentoFacade.getAssentosByVoo(id);
        return "consultar_assentos";
    }
}
