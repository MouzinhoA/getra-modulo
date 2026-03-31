package br.edu.ifpb.es.daw.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "parceiro")
public class Parceiro {

    @Id
    private Long id;

    private String nome_razao_social;

    private String cpf_cnpj;

    private String email_contato;

    private String dados_bancarios_pix;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_razao_social() {
        return nome_razao_social;
    }

    public void setNome_razao_social(String nome_razao_social) {
        this.nome_razao_social = nome_razao_social;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getEmail_contato() {
        return email_contato;
    }

    public void setEmail_contato(String email_contato) {
        this.email_contato = email_contato;
    }

    public String getDados_bancarios_pix() {
        return dados_bancarios_pix;
    }

    public void setDados_bancarios_pix(String dados_bancarios_pix) {
        this.dados_bancarios_pix = dados_bancarios_pix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parceiro)) return false;
        Parceiro parceiro = (Parceiro) o;
        return Objects.equals(id, parceiro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Parceiro{" +
                "id=" + id +
                ", nome_razao_social='" + nome_razao_social + '\'' +
                ", cpf_cnpj='" + cpf_cnpj + '\'' +
                ", email_contato='" + email_contato + '\'' +
                ", dados_bancarios_pix='" + dados_bancarios_pix + '\'' +
                '}';
    }
}