package br.edu.ifpb.es.daw.entities;

import java.util.Objects;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "parceiro")
public class Parceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeRazaoSocial;

    private String cpfCnpj;

    private String emailContato;

    private String dadosBancariosPix;

    @OneToMany(mappedBy = "parceiro", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ContaPagar> conta_pagar;

    public Long getId() {
        return id;
    }

    public List<ContaPagar> getConta_pagar() {
        return conta_pagar;
    }

    public void setConta_pagar(List<ContaPagar> conta_pagar) {
        this.conta_pagar = conta_pagar;
    }

    public Parceiro() {
        
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_razao_social() {
        return nomeRazaoSocial;
    }

    public void setNome_razao_social(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }

    public String getCpf_cnpj() {
        return cpfCnpj;
    }

    public void setCpf_cnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail_contato() {
        return emailContato;
    }

    public void setEmail_contato(String emailContato) {
        this.emailContato = emailContato;
    }

    public String getDados_bancarios_pix() {
        return dadosBancariosPix;
    }

    public void setDados_bancarios_pix(String dadosBancariosPix) {
        this.dadosBancariosPix = dadosBancariosPix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Parceiro))
            return false;
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
                ", nomeRazaoSocial='" + nomeRazaoSocial + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", emailContato='" + emailContato + '\'' +
                ", dadosBancariosPix='" + dadosBancariosPix + '\'' +
                '}';
    }
}