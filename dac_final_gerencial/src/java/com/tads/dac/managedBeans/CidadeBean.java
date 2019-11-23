package com.tads.dac.managedBeans;

import com.tads.dac.beans.Cidade;
import com.tads.dac.beans.CidadeAeroporto;
import com.tads.dac.beans.Estado;
import com.tads.dac.facade.CidadeAeroportoFacade;
import com.tads.dac.facade.CidadeFacade;
import com.tads.dac.facade.EstadoFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "cidadeBean")
@SessionScoped
public class CidadeBean implements Serializable {
    private int id;
    private String nome;
    private String sigla;
    private Estado estado;
    private Cidade cidade;
    private char method;
    private List<CidadeAeroporto> listaAeroportos;
    private List<Cidade> listaCidades;
    private List<Estado> listaEstados;
    
    public CidadeBean() {}

    @PostConstruct
    public void init(){
        this.listaAeroportos = CidadeAeroportoFacade.listCidadeAeroportos();
        this.listaCidades = CidadeFacade.listCidades();
        this.listaEstados = EstadoFacade.listEstados();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public char getMethod() {
        return method;
    }

    public void setMethod(char method) {
        this.method = method;
    }

    public List<CidadeAeroporto> getListaAeroportos() {
        return listaAeroportos;
    }

    public void setListaAeroportos(List<CidadeAeroporto> listaAeroportos) {
        this.listaAeroportos = listaAeroportos;
    }

    public List<Cidade> getListaCidades() {
        return listaCidades;
    }

    public void setListaCidades(List<Cidade> listaCidades) {
        this.listaCidades = listaCidades;
    }

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }
    
    public void buscarCidades(){
        this.listaCidades = CidadeFacade.getCidadesByEstado(this.estado.getId());
    }
    
    public String cadastrar() throws IOException{
        CidadeAeroporto c = new CidadeAeroporto();
        c.setCidade(cidade);
        c.setNome(nome);
        c.setSigla(sigla);
        
        int idAeroporto = CidadeAeroportoFacade.insertCidadeAeroporto(c);
        
        if (idAeroporto > 0){
            FacesContext.getCurrentInstance().addMessage(null, new
            FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Aeroporto cadastrado com sucesso!", null));
        
            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

            this.listaAeroportos = CidadeAeroportoFacade.listCidadeAeroportos();
            
            return "pesquisar_cidades";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new
            FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Erro ao cadastrar Aeroporto!", null));
        
            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

            return "pesquisar_cidades";
        }
    }
    
    public String atualizar(){
        CidadeAeroporto c = CidadeAeroportoFacade.getCidadeAeroportoById(id);
        
        c.setCidade(cidade);
        c.setNome(nome);
        c.setSigla(sigla);
        
        if(CidadeAeroportoFacade.updateCidadeAeroporto(c)){
            this.listaAeroportos = CidadeAeroportoFacade.listCidadeAeroportos();
            
            FacesContext.getCurrentInstance().addMessage(null, new
            FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Aeroporto alterado com sucesso!!", null));
        
            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

            return "pesquisar_cidades";
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Erro ao atualizar Aeroporto!", null));
        
            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

            return "manter_cidades";
        }
    }
    
    public void remover(int id){
        if (CidadeAeroportoFacade.deleteCidadeAeroporto(id)){
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_INFO, 
                    "Aeroporto removido com sucesso!", null));
            this.listaAeroportos = CidadeAeroportoFacade.listCidadeAeroportos();
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR, 
                    "Erro ao remover Aeroporto!", null));
        }
    }
    
    public String openView(CidadeAeroporto aero, char method){
        clearData();
        
        if (this.estado == null) setEstado(getListaEstados().get(0));
        
        this.method = method;
        
        if (method != 'n'){
            this.id = aero.getId();
            this.cidade = aero.getCidade();
            this.estado = aero.getCidade().getEstado();
            this.nome = aero.getNome();
            this.sigla = aero.getSigla();
        }
        
        return "manter_cidades";
    }
    
    public void clearData(){
        this.cidade = null;
        this.estado = null;
        this.nome = null;
        this.id = 0;
        this.sigla = null;
        this.method = 0;
    }
}
