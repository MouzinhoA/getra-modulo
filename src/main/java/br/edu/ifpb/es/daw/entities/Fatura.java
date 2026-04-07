package br.edu.ifpb.es.daw.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FATURA")
public class Fatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valorTotal;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private Boolean status;
    private String tipoPagamentoPreferencial;
    private String linhaDigitavelBoleto;
    private String qrCodePix;

    @Column(unique = true)
    private String idExternoGateway;

    private Long idCliente;
    private Long idRecorrencia;
    private Long idUsuario;

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Fatura [id=" + id + ", valorTotal=" + valorTotal + ", dataVencimento=" + dataVencimento
                + ", dataPagamento=" + dataPagamento + ", status=" + status + ", tipoPagamentoPreferencial="
                + tipoPagamentoPreferencial + ", linhaDigitavelBoleto=" + linhaDigitavelBoleto + ", qrCodePix="
                + qrCodePix + ", idExternoGateway=" + idExternoGateway + ", idCliente=" + idCliente + ", idRecorrencia="
                + idRecorrencia + ", idUsuario=" + idUsuario + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Fatura other = (Fatura) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getTipoPagamentoPreferencial() {
        return tipoPagamentoPreferencial;
    }

    public void setTipoPagamentoPreferencial(String tipoPagamentoPreferencial) {
        this.tipoPagamentoPreferencial = tipoPagamentoPreferencial;
    }

    public String getLinhaDigitavelBoleto() {
        return linhaDigitavelBoleto;
    }

    public void setLinhaDigitavelBoleto(String linhaDigitavelBoleto) {
        this.linhaDigitavelBoleto = linhaDigitavelBoleto;
    }

    public String getQrCodePix() {
        return qrCodePix;
    }

    public void setQrCodePix(String qrCodePix) {
        this.qrCodePix = qrCodePix;
    }

    public String getIdExternoGateway() {
        return idExternoGateway;
    }

    public void setIdExternoGateway(String idExternoGateway) {
        this.idExternoGateway = idExternoGateway;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdRecorrencia() {
        return idRecorrencia;
    }

    public void setIdRecorrencia(Long idRecorrencia) {
        this.idRecorrencia = idRecorrencia;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

}