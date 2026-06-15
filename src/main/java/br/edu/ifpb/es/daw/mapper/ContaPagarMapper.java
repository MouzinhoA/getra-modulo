package br.edu.ifpb.es.daw.mapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import br.edu.ifpb.es.daw.model.ContaPagar;
import br.edu.ifpb.es.daw.model.Parceiro;
import br.edu.ifpb.es.daw.model.Usuario;
import br.edu.ifpb.es.daw.rest.dto.ContaPagarResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.ContaPagarSalvarRequestDTO;

@Component
public class ContaPagarMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public ContaPagar from(ContaPagarSalvarRequestDTO from) {
        return ContaPagar.builder()
                .descricao(from.descricao())
                .valor(from.valor())
                .status(from.status())
                .formaPagamento(from.formaPagamento())
                .dataVencimento(from.dataVencimento())
                .dataPagamento(from.dataPagamento())
                .parceiro(entityManager.getReference(Parceiro.class, from.idParceiro()))
                .usuario(entityManager.getReference(Usuario.class, from.idUsuario()))
                .build();
    }

    public ContaPagarResponseDTO from(ContaPagar from) {
        return ContaPagarResponseDTO.builder()
                .id(from.getId())
                .lookupId(from.getLookupId())
                .descricao(from.getDescricao())
                .valor(from.getValor())
                .status(from.getStatus())
                .formaPagamento(from.getFormaPagamento())
                .dataVencimento(from.getDataVencimento())
                .dataPagamento(from.getDataPagamento())
                .nomeParceiro(from.getParceiro() != null ? from.getParceiro().getNomeRazaoSocial() : null)
                .nomeUsuario(from.getUsuario() != null ? from.getUsuario().getNome() : null)
                .build();
    }
}
