package br.edu.ifpb.es.daw.model;

import jakarta.persistence.*;
import java.io.Serializable;
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
@ToString(exclude = {"parceiro", "usuario"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contapagar")
public class ContaPagar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "lookup_id", unique = true, nullable = false)
    private UUID lookupId;

    private String descricao;
    private Double valor;
    private String status;

    @Column(name = "forma_pagamento")
    private String formaPagamento;

    @Column(name = "data_vencimento")
    private String dataVencimento;

    @Column(name = "data_pagamento")
    private String dataPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idParceiro")
    private Parceiro parceiro;

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
