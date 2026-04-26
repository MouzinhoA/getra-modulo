package br.edu.ifpb.es.daw.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "NOTAFISCAL")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFatura")
    private Fatura fatura;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NotaFiscal other = (NotaFiscal) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "NotaFiscal [id=" + id + ", numero=" + numero + ", dataEmissao=" + dataEmissao + ", valorTotal="
                + valorTotal + ", statusApi=" + statusApi + ", idExtGovApi=" + idExtGovApi + ", linkXml=" + linkXml
                + ", linkPdf=" + linkPdf + "]";
    }

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