package com.tads.dac.managedBeans;

import com.tads.dac.beans.CidadeAeroporto;
import com.tads.dac.beans.Piloto;
import com.tads.dac.beans.Voo;
import com.tads.dac.facade.CidadeAeroportoFacade;
import com.tads.dac.facade.PilotoFacade;
import com.tads.dac.facade.VooFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "vooBean")
@SessionScoped
public class VooBean implements Serializable {
    private int id;
    private CidadeAeroporto origem;
    private CidadeAeroporto destino;
    private Piloto piloto;
    private double precoPrimeiraClasse;
    private double precoEconomica;
    private Date saida;
    private Date chegada;
    private char method;
    private List<Voo> listaVoos;
    private List<CidadeAeroporto> listaAeroportos;
    private List<Piloto> listaPilotos;
    
    public VooBean() {}
    
    @PostConstruct
    public void init(){
        this.listaAeroportos = CidadeAeroportoFacade.listCidadeAeroportos();
        this.listaPilotos = PilotoFacade.listPilotos();
        this.listaVoos = VooFacade.listVoos();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CidadeAeroporto getOrigem() {
        return origem;
    }

    public void setOrigem(CidadeAeroporto origem) {
        this.origem = origem;
    }

    public CidadeAeroporto getDestino() {
        return destino;
    }

    public void setDestino(CidadeAeroporto destino) {
        this.destino = destino;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public double getPrecoPrimeiraClasse() {
        return precoPrimeiraClasse;
    }

    public void setPrecoPrimeiraClasse(double precoPrimeiraClasse) {
        this.precoPrimeiraClasse = precoPrimeiraClasse;
    }

    public double getPrecoEconomica() {
        return precoEconomica;
    }

    public void setPrecoEconomica(double precoEconomica) {
        this.precoEconomica = precoEconomica;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public Date getChegada() {
        return chegada;
    }

    public void setChegada(Date chegada) {
        this.chegada = chegada;
    }
    
    public char getMethod() {
        return method;
    }

    public void setMethod(char method) {
        this.method = method;
    }

    public List<Voo> getListaVoos() {
        return listaVoos;
    }

    public void setListaVoos(List<Voo> listaVoos) {
        this.listaVoos = listaVoos;
    }

    public List<CidadeAeroporto> getListaAeroportos() {
        return listaAeroportos;
    }

    public void setListaAeroportos(List<CidadeAeroporto> listaAeroportos) {
        this.listaAeroportos = listaAeroportos;
    }

    public List<Piloto> getListaPilotos() {
        return listaPilotos;
    }

    public void setListaPilotos(List<Piloto> listaPilotos) {
        this.listaPilotos = listaPilotos;
    }
    
    
    
    public String cadastrar() throws IOException{
        Voo v = new Voo();
        
        v.setCidadeDestino(destino);
        v.setCidadeOrigem(origem);
        v.setDataHoraChegada(chegada);
        v.setDataHoraSaida(saida);

        v.setPiloto(piloto);
        v.setPrecoClasseEconomica(precoEconomica);
        v.setPrecoPrimeiraClasse(precoPrimeiraClasse);
        
        v.setId(VooFacade.insertVoo(v));
        
        if (v.getId() > 0){
            this.listaVoos = VooFacade.listVoos();
            
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Voo cadastrado com sucesso!", null));

            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
         
            return "pesquisar_voos";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erro ao cadastrar voo!", null));

            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
         
            return "manter_voos";
        }
    }
    
    public String atualizar(){
        Voo v = new Voo();
        
        v.setCidadeDestino(destino);
        v.setCidadeOrigem(origem);
        v.setDataHoraChegada(chegada);
        v.setDataHoraSaida(saida);
        v.setId(id);
        v.setPiloto(piloto);
        v.setPrecoClasseEconomica(precoEconomica);
        v.setPrecoPrimeiraClasse(precoPrimeiraClasse);
        
        if (VooFacade.updateVoo(v)){
            this.listaVoos = VooFacade.listVoos();
            
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Voo atualizado com sucesso!", null));

            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
         
            return "pesquisar_voos";
        }
        else{
            this.listaVoos = VooFacade.listVoos();
            
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Erro ao atualizar voo!", null));

            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
         
            return "manter_voos";
        }
    }
    
    public void remover(int id){
        if (VooFacade.deleteVoo(id)){
            this.listaVoos = VooFacade.listVoos();
            
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Voo removido com sucesso!", null));
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR, 
                        "Erro ao remover voo!", null));
        }
    }
    
    public String openView(Voo v, char method){
        clearData();
        
        this.listaAeroportos = CidadeAeroportoFacade.listCidadeAeroportos();
        this.listaPilotos = PilotoFacade.listPilotos();
        
        this.method = method;
        
        if (method != 'n'){
            this.destino = v.getCidadeDestino();
            this.origem = v.getCidadeOrigem();
            this.chegada = v.getDataHoraChegada();
            this.saida = v.getDataHoraSaida();
            this.id = v.getId();
            this.piloto = v.getPiloto();
            this.precoEconomica = v.getPrecoClasseEconomica();
            this.precoPrimeiraClasse = v.getPrecoPrimeiraClasse();
        }
        else{
            this.destino = listaAeroportos.get(0);
            this.origem = listaAeroportos.get(0);
        }
        
        return "manter_voos";
    }
    
    public void clearData(){
        this.destino = null;
        this.chegada = null;
        this.saida = null;
        this.chegada = null;
        this.saida = null;
        this.id = 0;
        this.method = 0;
        this.piloto = null;
        this.precoEconomica = 0;
        this.precoPrimeiraClasse = 0;
    }
}
