/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.dac.beans;

import com.tads.dac.util.StringToMD5;
import com.tads.dac.util.ValidaCpf;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author macedo
 */
@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable {
    private int id_cliente;
    private String nome_cliente;
    private String senha_cliente;
    private String cpf_cliente;
    private String telefone_cliente;
    private Endereco endereco;
    private String email_cliente;

    @Id
    @Column(name = "id_cliente", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        if(id_cliente > 0) {
            this.id_cliente = id_cliente;            
        } else {
            throw new RuntimeException("Erro: Nome do Funcionário não pode ser nulo!");
        }
    }

    @Column(name = "nome_cliente", length = 70, nullable = false)
    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        if(nome_cliente != null) {
            this.nome_cliente = nome_cliente;
        } else {
            throw new RuntimeException("Nome do cliente não pode ser nulo!");
        }
    }

    @Column(name = "senha_cliente", length = 64, nullable = false)
    public String getSenha_cliente() {
        return senha_cliente;
    }

    public void setSenha_cliente(String senha_cliente) throws NoSuchAlgorithmException {
        if(senha_cliente != null) {
            this.senha_cliente = StringToMD5.toMD5(senha_cliente);
        } else {
            throw new RuntimeException("Senha do cliente não pode ser nula!");
        }
    }

    @Column(name = "cpf_cliente", length = 11, nullable = false)
    public String getCpf_cliente() {
        return cpf_cliente;
    }

    public void setCpf_cliente(String cpf_cliente) {
        if(ValidaCpf.validate(cpf_cliente)) {
            this.cpf_cliente = cpf_cliente;
        } else {
            throw new RuntimeException("CPF não validado!");
        }
    }

    @Column(name = "telefone_cliente", length = 11, nullable = false)
    public String getTelefone_cliente() {
        return telefone_cliente;
    }

    public void setTelefone_cliente(String telefone_cliente) {
        if(telefone_cliente != null) {
            this.telefone_cliente = telefone_cliente;
        } else {
            throw new RuntimeException("Telefone do cliente está inválido !");
        }
    }

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        if(endereco != null) {
            this.endereco = endereco;
        } else {
            throw new RuntimeException("Endereço inválido!");
        }
    }

    @Column(name = "email_cliente", length = 100, nullable = false)
    public String getEmail_cliente() {
        return nome_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        if(email_cliente != null) {
            this.email_cliente = email_cliente;
        } else {
            throw new RuntimeException("Email do cliente não pode ser nulo!");
        }
    }
    
}
