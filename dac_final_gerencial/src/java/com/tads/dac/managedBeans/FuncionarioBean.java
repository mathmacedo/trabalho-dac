package com.tads.dac.managedBeans;

import com.tads.dac.beans.Cidade;
import com.tads.dac.beans.Endereco;
import com.tads.dac.beans.Estado;
import com.tads.dac.beans.Funcionario;
import com.tads.dac.facade.CidadeFacade;
import com.tads.dac.facade.EnderecoFacade;
import com.tads.dac.facade.EstadoFacade;
import com.tads.dac.facade.FuncionarioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "funcionarioBean")
@RequestScoped
public class FuncionarioBean implements Serializable{
    private List<Funcionario> funcionarios;
    private List<Estado> listaEstados;
    private List<Cidade> listaCidades;
    private Cidade cidade;
    private Estado estado;
    private String nome;
    private String cpf;
    private String email;
    private char cargo;
    private String senha;
    private String confSenha;
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;
    private String telefone;
    private Funcionario funcionario;
    private char function;
    
    @PostConstruct
    public void init(){
        this.funcionario = new Funcionario();
        this.funcionarios = FuncionarioFacade.listFuncionarios();
        
        if (this.listaEstados == null)
            this.listaEstados = EstadoFacade.listEstados();
        
        this.listaCidades = CidadeFacade.listCidades();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
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

    public String getConfSenha(){ 
        return this.confSenha; 
    }
    
    public void setConfSenha(String senha){
        this.confSenha = senha;
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

    public char getFunction() {
        return function;
    }

    public void setFunction(char function) {
        this.function = function;
    }
    
    public void cadastrar() throws IOException, NoSuchAlgorithmException{
        Funcionario f = new Funcionario();
        Endereco e = new Endereco();
        
        if (senha.equals(confSenha))
            f.setSenha(senha);
        else {
            FacesContext.getCurrentInstance().addMessage(null, new
            FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas não coincidem!", null));
        
            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("pesquisar_funcionarios.xhtml"); 
        }
        
        e.setCidade(cidade);
        e.setComplemento(complemento);
        e.setNumero(numero);
        e.setRua(rua);
        
        int idEndereco = EnderecoFacade.insertEndereco(e);
        if (idEndereco != 0) e.setId(idEndereco);
        else {
            FacesContext.getCurrentInstance().addMessage(null, new
            FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir endereço!", null));
        
            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("pesquisar_funcionarios.xhtml"); 
        }
        
        f.setEndereco(e);
        f.setCpf(cpf);
        f.setEmail(email);
        f.setNome(nome);
        f.setTipo(cargo);
        
        int idFuncionario = FuncionarioFacade.insertFuncionario(f);
        if (idFuncionario != 0) {
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro Realizado!", null));

            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("pesquisar_funcionarios.xhtml"); 
        }
        
        else {
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar funcionário!", null));

            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

            FacesContext.getCurrentInstance()
                    .getExternalContext().redirect("pesquisar_funcionarios.xhtml"); 
        }
    }
    
    public String viewFuncionario(int id){
        this.function = 'v';
        
        Funcionario func = FuncionarioFacade.getFuncionarioById(id);
        
        this.estado = func.getEndereco().getCidade().getEstado();
        
        this.listaCidades = CidadeFacade.getCidadesByEstado(this.estado.getId());
        this.cidade = func.getEndereco().getCidade();
        
        this.cargo = func.getTipo();
        //this.bairro = funcionario.getEndereco().getBairro();
        this.complemento = func.getEndereco().getComplemento();
        this.cpf = func.getCpf();
        this.email = func.getEmail();
        this.nome = func.getNome();
        this.numero = func.getEndereco().getNumero();
        this.rua = func.getEndereco().getRua();
        //this.telefone = funcionario.getTelefone();
     
        return "manter_funcionarios.xhtml";
    }
}
