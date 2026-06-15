package br.edu.ifpb.es.daw.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@ToString(exclude = "fatura")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "NOTAFISCAL")
public class NotaFiscal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "lookup_id", unique = true, nullable = false)
    private UUID lookupId;

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

    @PrePersist
    public void gerarLookupId() {
        if (lookupId == null) {
            lookupId = UUID.randomUUID();
        }
    }

}
