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
@ToString(exclude = "contaPagar")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "parceiro")
public class Parceiro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "lookup_id", unique = true, nullable = false)
    private UUID lookupId;

    @Column(name = "nome_razao_social")
    private String nomeRazaoSocial;

    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "email_contato")
    private String emailContato;

    @Column(name = "dados_bancarios_pix")
    private String dadosBancariosPix;

    @OneToMany(mappedBy = "parceiro", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ContaPagar> contaPagar;

    @PrePersist
    public void gerarLookupId() {
        if (lookupId == null) {
            lookupId = UUID.randomUUID();
        }
    }

}
