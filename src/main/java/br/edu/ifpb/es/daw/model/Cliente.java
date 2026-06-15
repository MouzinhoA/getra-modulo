package br.edu.ifpb.es.daw.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;


@Getter
@Setter
@ToString(exclude = {"faturas", "recorrencias"})
@EqualsAndHashCode(of = {"id", "cpfCnpj"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "lookup_id", unique = true, nullable = false)
    private UUID lookupId;

    @Column(name = "nome_razao_social")
    private String nomeRazaoSocial;

    @Column(name = "cpf_cnpj", unique = true, nullable = false)
    private String cpfCnpj;

    private String email;
    private String telefone;
    private String endereco;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "cliente")
    private List<Fatura> faturas;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "cliente")
    private List<Recorrencia> recorrencias;

    @PrePersist
    public void gerarLookupId() {
        if (lookupId == null) {
            lookupId = UUID.randomUUID();
        }
    }

}
