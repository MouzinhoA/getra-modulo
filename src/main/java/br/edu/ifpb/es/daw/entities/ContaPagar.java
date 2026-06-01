package br.edu.ifpb.es.daw.entities;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "contapagar")
public class ContaPagar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private Double valor;

    private String formaPagamento;

    private String status;

    private String dataVencimento;

    private String dataPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idParceiro")
    private Parceiro parceiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Parceiro getParceiro() {
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ContaPagar() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getForma_pagamento() {
        return formaPagamento;
    }

    public void setForma_pagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData_vencimento() {
        return dataVencimento;
    }

    public void setData_vencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getData_pagamento() {
        return dataPagamento;
    }

    public void setData_pagamento(String dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ContaPagar))
            return false;
        ContaPagar that = (ContaPagar) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ContaPagar [id=" + id + ", descricao=" + descricao + ", valor=" + valor + ", formaPagamento="
                + formaPagamento + ", status=" + status + ", dataVencimento=" + dataVencimento + ", dataPagamento="
                + dataPagamento + "]";
    }

}