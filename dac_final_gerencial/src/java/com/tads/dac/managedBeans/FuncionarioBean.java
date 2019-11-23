package com.tads.dac.managedBeans;

import com.tads.dac.beans.Cidade;
import com.tads.dac.beans.Endereco;
import com.tads.dac.beans.Estado;
import com.tads.dac.beans.Funcionario;
import com.tads.dac.facade.CidadeFacade;
import com.tads.dac.facade.EnderecoFacade;
import com.tads.dac.facade.EstadoFacade;
import com.tads.dac.facade.FuncionarioFacade;
import com.tads.dac.util.StringToMD5;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "funcionarioBean")
@SessionScoped
public class FuncionarioBean implements Serializable{
    private List<Funcionario> funcionarios;
    private List<Estado> listaEstados;
    private List<Cidade> listaCidades;
    private Cidade cidade;
    private Estado estado;
    private int id;
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
    private char method;
    
    @PostConstruct
    public void init(){
        this.funcionarios = FuncionarioFacade.listFuncionarios();
        this.listaEstados = EstadoFacade.listEstados();
        this.listaCidades = CidadeFacade.listCidades();
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
    
    public void setConfSenha(String senha) throws NoSuchAlgorithmException{
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

    public char getMethod() {
        return method;
    }

    public void setMethod(char method) {
        this.method = method;
    }
    
    public String cadastrar() throws NoSuchAlgorithmException{
        Funcionario f = new Funcionario();
        Endereco e = new Endereco();
        
        if (senha.equals(confSenha))
            f.setSenha(StringToMD5.toMD5(senha));
        else {
            FacesContext.getCurrentInstance().addMessage(null, new
            FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas não coincidem!", null));
        
            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

            return "manter_funcionarios";
        }
        
        e.setCidade(cidade);
        e.setComplemento(complemento);
        e.setNumero(numero);
        e.setRua(rua);
        e.setBairro(bairro);
        
        int idEndereco = EnderecoFacade.insertEndereco(e);
        if (idEndereco != 0) e.setId(idEndereco);
        else {
            FacesContext.getCurrentInstance().addMessage(null, new
            FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir endereço!", null));
        
            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

            return "manter_funcionarios";
        }
        
        f.setEndereco(e);
        f.setCpf(cpf);
        f.setEmail(email);
        f.setNome(nome);
        f.setTipo(cargo);
        f.setTelefone(telefone);
        
        int idFuncionario = FuncionarioFacade.insertFuncionario(f);
        
        if (idFuncionario > 0) {
            setFuncionarios(FuncionarioFacade.listFuncionarios());
            
            clearData();
            
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro Realizado!", null));

            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

            return "pesquisar_funcionarios";
        }
        
        else {
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar funcionário!", null));

            FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);

            return "manter_funcionarios";
        }
    }
    
    public String atualizar() throws NoSuchAlgorithmException {
        Funcionario f = FuncionarioFacade.getFuncionarioById(this.id);
        Endereco e = f.getEndereco();
        
        if (senha != null){
            if (senha.equals(confSenha))
                f.setSenha(StringToMD5.toMD5(senha));
            else {
                FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR, "Senhas não coincidem!", null));

                FacesContext.getCurrentInstance().getExternalContext()
                    .getFlash().setKeepMessages(true);

                return "manter_funcionarios";
            }
        }
        
        e.setBairro(bairro);
        e.setCidade(cidade);
        e.setComplemento(complemento);
        e.setNumero(numero);
        e.setRua(rua);
        
        f.setCpf(cpf);
        f.setEmail(email);
        f.setEndereco(e);
        f.setNome(nome);
        f.setTelefone(telefone);
        f.setTipo(cargo);
        
        if (EnderecoFacade.updateEndereco(e)){
            if (FuncionarioFacade.updateFuncionario(f)){
                setFuncionarios(FuncionarioFacade.listFuncionarios());
                
                clearData();
                
                FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_INFO, "Funcionário atualizado com sucesso!", null));

                FacesContext.getCurrentInstance().getExternalContext()
                    .getFlash().setKeepMessages(true);

                return "pesquisar_funcionarios";
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar funcionário!", null));
            }
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar endereço!", null));
        }
        FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
        
        return "manter_funcionarios";
    }
    
    public void remover(int id){
        if (FuncionarioFacade.deleteFuncionario(id)){
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_INFO, "Funcionário excluído com sucesso!", null));
            
            setFuncionarios(FuncionarioFacade.listFuncionarios());
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir funcionário!", null));
        }
        FacesContext.getCurrentInstance().getExternalContext()
                .getFlash().setKeepMessages(true);
    }
    
    public String openView(Funcionario func, char method){
        clearData();
        
        this.method = method;
        
        if (this.estado == null) setEstado(this.listaEstados.get(0));
        
        if(method != 'v')
            setListaCidades(CidadeFacade.getCidadesByEstado(estado.getId()));
        
        if (method != 'n'){
            this.id = func.getId();

            this.estado = func.getEndereco().getCidade().getEstado();

            this.listaCidades = CidadeFacade.getCidadesByEstado(this.estado.getId());
            this.cidade = func.getEndereco().getCidade();

            this.cargo = func.getTipo();
            this.bairro = func.getEndereco().getBairro();
            this.complemento = func.getEndereco().getComplemento();
            this.cpf = func.getCpf();
            this.email = func.getEmail();
            this.nome = func.getNome();
            this.numero = func.getEndereco().getNumero();
            this.rua = func.getEndereco().getRua();
            this.telefone = func.getTelefone();
        }
        return "manter_funcionarios";
    }

    private void clearData() {
        this.bairro = null;
        this.cargo = 0;
        this.cidade = null;
        this.complemento = null;
        this.confSenha = null;
        this.cpf = null;
        this.email= null;
        this.estado = null;
        this.id = 0;
        this.method = 0;
        this.nome = null;
        this.numero = 0;
        this.rua = null;
        this.senha = null;
        this.telefone = null;
        
        setListaEstados(EstadoFacade.listEstados());
        setListaCidades(CidadeFacade.listCidades());
    }
}   