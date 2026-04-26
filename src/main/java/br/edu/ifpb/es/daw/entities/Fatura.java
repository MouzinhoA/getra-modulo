package br.edu.ifpb.es.daw.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRecorrencia")
    private Recorrencia recorrencia;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "fatura")
    private List<NotaFiscal> notaFiscals;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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
                + qrCodePix + ", idExternoGateway=" + idExternoGateway + "]";
    }

    public Recorrencia getRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(Recorrencia recorrencia) {
        this.recorrencia = recorrencia;
    }

    public List<NotaFiscal> getNotaFiscals() {
        return notaFiscals;
    }

    public void setNotaFiscals(List<NotaFiscal> notaFiscals) {
        this.notaFiscals = notaFiscals;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}