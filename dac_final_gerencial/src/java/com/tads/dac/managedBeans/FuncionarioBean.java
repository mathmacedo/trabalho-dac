package com.tads.dac.managedBeans;

import com.tads.dac.beans.Cidade;
import com.tads.dac.beans.Estado;
import com.tads.dac.beans.Funcionario;
import com.tads.dac.facade.CidadeFacade;
import com.tads.dac.facade.EstadoFacade;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "funcionarioBean")
@RequestScoped
public class FuncionarioBean {
    private Funcionario funcionario;
    private List<Estado> listaEstados;
    private List<Cidade> listaCidades;
    private Cidade cidade;
    private Estado estado;
    private String nome;
    private String cpf;
    private String email;
    private char cargo;
    private String senha;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;
    private String telefone;
    
    @PostConstruct
    public void init(){
        this.funcionario = new Funcionario();
        this.listaEstados = EstadoFacade.listEstados();
        this.estado = listaEstados.get(0);
        this.listaCidades = CidadeFacade.getCidadesByEstado(estado.getId());
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    public char getCargo() {
        return cargo;
    }

    public void setCargo(char cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
    
    public void buscarCidades(){
        this.listaCidades = CidadeFacade.getCidadesByEstado(this.estado.getId());
    }
    
    public void cadastrar() throws IOException{
        Funcionario f = new Funcionario();
        
        FacesContext.getCurrentInstance().addMessage(null, new
            FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro Realizado!", null));
        
        FacesContext.getCurrentInstance().getExternalContext()
            .getFlash().setKeepMessages(true);
        
        FacesContext.getCurrentInstance()
                .getExternalContext().redirect("pesquisar_funcionarios.xhtml"); 
    }
}
