package br.edu.ifpb.es.daw.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_razao_social")
    private String nomeRazaoSocial;

    @Column(name = "cpf_cnpj", unique = true, nullable = false)
    private String cpfCnpj;

    private String email;
    private String telefone;
    private String endereco;

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    public void setNomeRazaoSocial(String nome) {
        this.nomeRazaoSocial = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Cliente))
            return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) || Objects.equals(cpfCnpj, cliente.cpfCnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpfCnpj);
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nomeRazaoSocial=" + nomeRazaoSocial + ", cpfCnpj=" + cpfCnpj + ", email="
                + email + ", telefone=" + telefone + ", endereco=" + endereco + "]";
    }

}