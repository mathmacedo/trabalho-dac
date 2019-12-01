package com.tads.dac.managedBeans;

import com.tads.dac.beans.Cidade;
import com.tads.dac.beans.Cliente;
import com.tads.dac.beans.Endereco;
import com.tads.dac.beans.Estado;
import com.tads.dac.facade.ClienteFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {
    private List<Cidade> listaCidades;
    private List<Estado> listaEstados;

    private int id;
    private String nome;
    private String cpf;
    private String senha;
    private String confSenha;
    private String email;
    private Estado estado;
    private Cidade cidade;
    
    private String bairro;
    private String rua;
    private int numero;
    private String complemento;
    private String telefone;
    
    public ClienteBean() {}

    @PostConstruct
    public void init(){
        //this.getEstados();
        //this.estado = listaEstados.get(0);
        //this.getCidades();
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfSenha() {
        return confSenha;
    }

    public void setConfSenha(String confSenha) {
        this.confSenha = confSenha;
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
    
    public void getCidades(){
        Client c = ClientBuilder.newClient();
        this.listaCidades = c
                .target("http://localhost:8080/dac_final_gerencial/webresources/cidade/" 
                        + Integer.toString(this.estado.getId()))
                .request(MediaType.APPLICATION_JSON)
                .get(List.class);
    }   
    
    public void getEstados(){
        Client c = ClientBuilder.newClient();
        this.listaEstados = c
                .target("http://localhost:8080/dac_final_gerencial/webresources/estado")
                .request(MediaType.APPLICATION_JSON)
                .get(List.class);
    }
    
    public String cadastrar(){
        if (!senha.equals(confSenha)){
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Senhas não coincidem!", null));

            FacesContext.getCurrentInstance().getExternalContext()
                    .getFlash().setKeepMessages(true);

            return "cadastrar_cliente";
        }
        
        Cliente c = new Cliente();
        Endereco e = new Endereco();
        
        e.setBairro(bairro);
        e.setCidade(cidade);
        e.setComplemento(complemento);
        e.setNumero(numero);
        e.setRua(rua);
        
        Client client = ClientBuilder.newClient();
        int idEndereco = client
            .target("http://localhost:8080/dac_final_gerencial/webresources/endereco")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.json(e), Integer.class);
        
        if (idEndereco > 0){
            c.setCpf(cpf);
            c.setEmail(email);
            c.setEndereco(e);
            c.setNome(nome);
            c.setSenha(senha);
            c.setTelefone(telefone);
            
            if (ClienteFacade.insertCliente(c) > 0){
                FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Cadastro realizado com sucesso!", null));

                FacesContext.getCurrentInstance().getExternalContext()
                        .getFlash().setKeepMessages(true);

                return "index";
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new
                    FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Erro ao inserir cliente!!", null));

                FacesContext.getCurrentInstance().getExternalContext()
                        .getFlash().setKeepMessages(true);

                return "cadastrar_cliente";
            }
        }
        else {
            FacesContext.getCurrentInstance().addMessage(null, new
                FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro ao inserir endereço!", null));

            FacesContext.getCurrentInstance().getExternalContext()
                    .getFlash().setKeepMessages(true);

            return "cadastrar_cliente";
        }
    }
}
