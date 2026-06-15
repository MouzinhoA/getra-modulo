package br.edu.ifpb.es.daw.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@ToString(exclude = {"cliente", "recorrencia", "notaFiscals", "usuario"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "FATURA")
public class Fatura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "lookup_id", unique = true, nullable = false)
    private UUID lookupId;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "fatura")
    private List<NotaFiscal> notaFiscals;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @PrePersist
    public void gerarLookupId() {
        if (lookupId == null) {
            lookupId = UUID.randomUUID();
        }
    }

}
