/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.dac.beans;

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
    private int id;
    private String nome;
    private String senha;
    private String cpf;
    private String telefone;
    private Endereco endereco;
    private String email;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id > 0) {
            this.id = id;            
        } else {
            throw new RuntimeException(
                    "Erro: Id do Cliente não pode ser nulo!");
        }
    }

    @Column(name = "nome", length = 70, nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome != null) {
            this.nome = nome;
        } else {
            throw new RuntimeException("Nome do cliente não pode ser nulo!");
        }
    }

    @Column(name = "senha", length = 64, nullable = false)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha){
        if(senha != null) {
            this.senha = senha;
        } else {
            throw new RuntimeException("Senha do cliente não pode ser nula!");
        }
    }

    @Column(name = "cpf", length = 11, nullable = false)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if(ValidaCpf.validate(cpf)) {
            this.cpf = cpf;
        } else {
            throw new RuntimeException("CPF não validado!");
        }
    }

    @Column(name = "telefone", length = 11, nullable = false)
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if(telefone != null) {
            this.telefone = telefone;
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

    @Column(name = "email", length = 100, nullable = false)
    public String getEmail() {
        return nome;
    }

    public void setEmail(String email) {
        if(email != null) {
            this.email = email;
        } else {
            throw new RuntimeException("Email do cliente não pode ser nulo!");
        }
    }
    
}
