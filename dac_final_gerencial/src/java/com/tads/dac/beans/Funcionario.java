package com.tads.dac.beans;

import com.tads.dac.util.StringToMD5;
import com.tads.dac.util.ValidaCpf;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario implements Serializable {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private char tipo;
    private Endereco endereco;

    @Id
    @Column(name = "id_funcionario", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) {
        if(id > 0)
            this.id = id;
        else throw new RuntimeException(
            "Erro: ID do Funcionário deve ser maior do que 0!");
    }
    
    @Column(name = "nome_funcionario", length = 100, nullable = false)
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if(nome != null)
            this.nome = nome;
        else throw new RuntimeException(
            "Erro: Nome do Funcionário não pode ser nulo!");
    }
    
    @Column(name = "cpf_funcionario", length = 11, nullable = false)
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) {
        if(ValidaCpf.validate(cpf))
            this.cpf = cpf;
        else throw new RuntimeException(
            "Erro: CPF do Funcionário é inválido!");
    }

    @Column(name = "email_funcionario", length = 100, nullable = false)
    public String getEmail() { return email; }
    public void setEmail(String email) {
        if(email != null)
            this.email = email;
        else throw new RuntimeException(
            "Erro: Email do Funcionário não pode ser nulo!");
    }

    @Column(name = "senha_funcionario", length = 64, nullable = false)
    public String getSenha() { return senha; }
    public void setSenha(String senha) throws NoSuchAlgorithmException {
        if (senha != null)
            this.senha = StringToMD5.toMD5(senha);
        else throw new RuntimeException(
            "Erro: Senha do funcionário não pode ser nula!");
    }

    @Column(name = "tipo_funcionario", length = 1, nullable = false)
    public char getTipo() { return tipo; }
    public void setTipo(char tipo) {
        if(tipo == 'G' || tipo == 'F')
            this.tipo = tipo;
        else throw new RuntimeException(
            "Erro: Tipo de Funcionário inválido!");
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_endereco")
    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) {
        if (endereco != null)
            this.endereco = endereco;
        else throw new RuntimeException(
            "Erro: Endereço do Funcionário não pode ser nulo!");
    }    
}
