package br.edu.ifpb.es.daw.mapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import br.edu.ifpb.es.daw.model.Cliente;
import br.edu.ifpb.es.daw.model.Recorrencia;
import br.edu.ifpb.es.daw.model.Servico;
import br.edu.ifpb.es.daw.rest.dto.RecorrenciaResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.RecorrenciaSalvarRequestDTO;

@Component
public class RecorrenciaMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public Recorrencia from(RecorrenciaSalvarRequestDTO from) {
        return Recorrencia.builder()
                .valorCobrado(from.valorCobrado())
                .periodicidade(Recorrencia.Periodicidade.valueOf(from.periodicidade()))
                .diaVencimento(from.diaVencimento())
                .status(from.status())
                .cliente(entityManager.getReference(Cliente.class, from.idCliente()))
                .servico(entityManager.getReference(Servico.class, from.idServico()))
                .build();
    }

    public RecorrenciaResponseDTO from(Recorrencia from) {
        return RecorrenciaResponseDTO.builder()
                .lookupId(from.getLookupId())
                .valorCobrado(from.getValorCobrado())
                .periodicidade(from.getPeriodicidade() != null ? from.getPeriodicidade().name() : null)
                .diaVencimento(from.getDiaVencimento())
                .status(from.getStatus())
                .nomeCliente(from.getCliente() != null ? from.getCliente().getNomeRazaoSocial() : null)
                .nomeServico(from.getServico() != null ? from.getServico().getNome() : null)
                .build();
    }
}
