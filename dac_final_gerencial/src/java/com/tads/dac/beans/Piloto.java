package com.tads.dac.beans;

import com.tads.dac.util.ValidaCpf;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_piloto")
public class Piloto implements Serializable {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Endereco endereco;

    @Id
    @Column(name = "id_piloto", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() { return id; }
    public void setId(int id) {
        if (id > 0)
            this.id = id;
        else throw new RuntimeException(
            "Erro: ID do Piloto deve ser maior do que 0!");
    }

    @Column(name = "nome_piloto", length = 70, nullable = false)
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome != null)
            this.nome = nome;
        else throw new RuntimeException(
            "Erro: Nome do Piloto não pode ser nulo!");
    }

    @Column(name = "cpf_piloto", length = 11, nullable = false)
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) {
        if (ValidaCpf.validate(cpf))
            this.cpf = cpf;
        else throw new RuntimeException(
            "Erro: CPF do Piloto é inválido!");
    }

    @Column(name = "email_piloto", length = 100, nullable = false)
    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email != null)
            this.email = email;
        else throw new RuntimeException(
            "Erro: Email do Piloto não pode ser nulo!");
    }

    @Column(name = "telefone_piloto", length = 11, nullable = false)
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) {
        if (telefone != null)
            this.telefone = telefone;
        else throw new RuntimeException(
            "Erro: Telefone do Piloto não pode ser nulo!");
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) {
        if(endereco != null)
            this.endereco = endereco;
        else throw new RuntimeException(
            "Erro: Endereço do Piloto não pode ser nulo!");
    }
}
