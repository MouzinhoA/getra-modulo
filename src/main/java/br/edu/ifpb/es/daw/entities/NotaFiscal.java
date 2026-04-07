package br.edu.ifpb.es.daw.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private LocalDate dataEmissao;

    private BigDecimal valorTotal;

    private String statusApi;

    private String idExtGovApi;

    private String linkXml;

    private String linkPdf;

    @ManyToOne
    @JoinColumn(name = "idFatura")
    private Fatura fatura;

    public NotaFiscal() {
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatusApi() {
        return statusApi;
    }

    public void setStatusApi(String statusApi) {
        this.statusApi = statusApi;
    }

    public String getIdExtGovApi() {
        return idExtGovApi;
    }

    public void setIdExtGovApi(String idExtGovApi) {
        this.idExtGovApi = idExtGovApi;
    }

    public String getLinkXml() {
        return linkXml;
    }

    public void setLinkXml(String linkXml) {
        this.linkXml = linkXml;
    }

    public String getLinkPdf() {
        return linkPdf;
    }

    public void setLinkPdf(String linkPdf) {
        this.linkPdf = linkPdf;
    }

    public Fatura getFatura() {
        return fatura;
    }

    public void setFatura(Fatura fatura) {
        this.fatura = fatura;
    }
}