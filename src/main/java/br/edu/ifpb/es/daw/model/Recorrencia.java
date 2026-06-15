package br.edu.ifpb.es.daw.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
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
@ToString(exclude = {"cliente", "servico", "faturas"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "RECORRENCIA")
public class Recorrencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "lookup_id", unique = true, nullable = false)
    private UUID lookupId;

    private Double valorCobrado;
    private Integer diaVencimento;
    private Boolean status;

    public enum Periodicidade {
        MENSAL,
        BIMESTRAL,
        TRIMESTRAL,
        SEMESTRAL,
        ANUAL
    }

    @Enumerated(EnumType.STRING)
    private Periodicidade periodicidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idServico")
    private Servico servico;

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, orphanRemoval = true, mappedBy = "recorrencia")
    private List<Fatura> faturas;

    @PrePersist
    public void gerarLookupId() {
        if (lookupId == null) {
            lookupId = UUID.randomUUID();
        }
    }

}
