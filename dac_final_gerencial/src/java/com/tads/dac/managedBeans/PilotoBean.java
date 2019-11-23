package com.tads.dac.managedBeans;

import com.tads.dac.beans.Cidade;
import com.tads.dac.beans.Endereco;
import com.tads.dac.beans.Estado;
import com.tads.dac.beans.Piloto;
import com.tads.dac.facade.CidadeFacade;
import com.tads.dac.facade.EnderecoFacade;
import com.tads.dac.facade.EstadoFacade;
import com.tads.dac.facade.PilotoFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "pilotoBean")
@SessionScoped
public class PilotoBean implements Serializable{
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private Estado estado;
    private Cidade cidade;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;
    private String telefone;
    private char method;
    
    private List<Piloto> listaPilotos;
    private List<Estado> listaEstados;
    private List<Cidade> listaCidades;
    
    public PilotoBean() {}

    @PostConstruct
    public void init(){
        this.listaPilotos = PilotoFacade.listPilotos();
        this.listaEstados = EstadoFacade.listEstados();
        this.listaCidades = CidadeFacade.listCidades();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public char getMethod() {
        return method;
    }

    public void setMethod(char method) {
        this.method = method;
    }

    public List<Piloto> getListaPilotos() {
        return listaPilotos;
    }

    public void setListaPilotos(List<Piloto> listaPilotos) {
        this.listaPilotos = listaPilotos;
    }

    public List<Estado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<Estado> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<Cidade> getListaCidades() {
        return listaCidades;
    }

    public void setListaCidades(List<Cidade> listaCidades) {
        this.listaCidades = listaCidades;
    }
    
    public void buscarCidades(){
        this.listaCidades = CidadeFacade.getCidadesByEstado(this.estado.getId());
    }
    
    public String cadastrar() {
        Endereco e = new Endereco();
        Piloto p = new Piloto();
        
        e.setBairro(bairro);
        e.setCidade(cidade);
        e.setComplemento(complemento);
        e.setNumero(numero);
        e.setRua(rua);
        
        e.setId(EnderecoFacade.insertEndereco(e));
        
        if (e.getId() > 0){
            p.setEndereco(e);

            p.setCpf(cpf);
            p.setEmail(email);
            p.setNome(nome);
            p.setTelefone(telefone);

            p.setId(PilotoFacade.insertPiloto(p));
           
            if (p.getId() > 0){
                setListaPilotos(PilotoFacade.listPilotos());
               
                FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Piloto inserido com sucesso!", null));

                FacesContext.getCurrentInstance().getExternalContext()
                        .getFlash().setKeepMessages(true);
                
                return "pesquisar_pilotos"; 
           }
           else {
                FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Erro ao inserir piloto!", null));
           }
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Erro ao inserir endereço do piloto!", null));
        }
        
        FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

        return "manter_pilotos"; 
    }
    
    public String atualizar(){
        Piloto p = PilotoFacade.getPilotoById(this.id);
        Endereco e = p.getEndereco();
        
        e.setBairro(bairro);
        e.setCidade(cidade);
        e.setComplemento(complemento);
        e.setNumero(numero);
        e.setRua(rua);
        
        p.setEndereco(e);
        
        p.setCpf(cpf);
        p.setEmail(email);
        p.setNome(nome);
        p.setTelefone(telefone);
        
        if (EnderecoFacade.updateEndereco(e)){
            if (PilotoFacade.updatePiloto(p)){
                setListaPilotos(PilotoFacade.listPilotos());
               
                FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Piloto atualizado com sucesso!", null));

                FacesContext.getCurrentInstance().getExternalContext()
                        .getFlash().setKeepMessages(true);
                
                return "pesquisar_pilotos"; 
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Erro ao atualizar Piloto!", null));
            }    
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao atualizar Endereço!", null));
        }
        FacesContext.getCurrentInstance().getExternalContext()
                        .getFlash().setKeepMessages(true);
                
        return "manter_pilotos"; 
    }
    
    public void remover(int id){
        if (PilotoFacade.deletePiloto(id)){
            setListaPilotos(PilotoFacade.listPilotos());
            
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Piloto removido com sucesso!", null));
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao remover o Piloto!", null));
        }
    }
    
    public String openView(Piloto p, char method){
        clearData();
        
        setMethod(method);
        
        if (method != 'n') {
            Endereco e = p.getEndereco();
            
            this.id = p.getId();
            
            this.bairro = e.getBairro();
            this.rua = e.getRua();
            this.numero = e.getNumero();
            this.cidade = e.getCidade();
            this.estado = cidade.getEstado();
            this.complemento = e.getComplemento();
            
            this.nome = p.getNome();
            this.cpf = p.getCpf();
            this.email = p.getEmail();
            this.telefone = p.getTelefone();
        }
        
        if (this.estado == null) setEstado(this.listaEstados.get(0));
        
        if(method != 'v')
            setListaCidades(CidadeFacade.getCidadesByEstado(estado.getId()));
        
        return "manter_pilotos";
    }
    
    public void clearData(){
        this.bairro = null;
        this.cidade = null;
        this.estado = null;
        this.complemento = null;
        this.cpf = null;
        this.email = null;
        this.id = 0;
        this.method = 0;
        this.nome = null;
        this.nome = null;
        this.numero = 0;
        this.rua = null;
        this.telefone = null;
    }
}
