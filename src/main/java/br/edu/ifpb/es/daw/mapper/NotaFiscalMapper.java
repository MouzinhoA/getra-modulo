package br.edu.ifpb.es.daw.mapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import br.edu.ifpb.es.daw.model.Fatura;
import br.edu.ifpb.es.daw.model.NotaFiscal;
import br.edu.ifpb.es.daw.rest.dto.NotaFiscalResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.NotaFiscalSalvarRequestDTO;

@Component
public class NotaFiscalMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public NotaFiscal from(NotaFiscalSalvarRequestDTO from) {
        return NotaFiscal.builder()
                .numero(from.numero())
                .dataEmissao(from.dataEmissao())
                .valorTotal(from.valorTotal())
                .statusApi(from.statusApi())
                .idExtGovApi(from.idExtGovApi())
                .linkXml(from.linkXml())
                .linkPdf(from.linkPdf())
                .fatura(entityManager.getReference(Fatura.class, from.idFatura()))
                .build();
    }

    public NotaFiscalResponseDTO from(NotaFiscal from) {
        return NotaFiscalResponseDTO.builder()
                .lookupId(from.getLookupId())
                .numero(from.getNumero())
                .dataEmissao(from.getDataEmissao())
                .valorTotal(from.getValorTotal())
                .statusApi(from.getStatusApi())
                .linkPdf(from.getLinkPdf())
                .idFatura(from.getFatura() != null ? from.getFatura().getId() : null)
                .build();
    }
}
