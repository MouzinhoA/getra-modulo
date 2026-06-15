package br.edu.ifpb.es.daw.mapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import br.edu.ifpb.es.daw.model.Cliente;
import br.edu.ifpb.es.daw.model.Fatura;
import br.edu.ifpb.es.daw.model.Recorrencia;
import br.edu.ifpb.es.daw.model.Usuario;
import br.edu.ifpb.es.daw.rest.dto.FaturaResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.FaturaSalvarRequestDTO;

@Component
public class FaturaMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public Fatura from(FaturaSalvarRequestDTO from) {
        var builder = Fatura.builder()
                .valorTotal(from.valorTotal())
                .dataVencimento(from.dataVencimento())
                .dataPagamento(from.dataPagamento())
                .status("PAGO".equalsIgnoreCase(from.status()))
                .tipoPagamentoPreferencial(from.meioPagamento())
                .cliente(entityManager.getReference(Cliente.class, from.idCliente()))
                .usuario(entityManager.getReference(Usuario.class, from.idUsuario()));
        if (from.idRecorrencia() != null) {
            builder.recorrencia(entityManager.getReference(Recorrencia.class, from.idRecorrencia()));
        }
        return builder.build();
    }

    public FaturaResponseDTO from(Fatura from) {
        return FaturaResponseDTO.builder()
                .lookupId(from.getLookupId())
                .valorTotal(from.getValorTotal())
                .dataVencimento(from.getDataVencimento())
                .dataPagamento(from.getDataPagamento())
                .meioPagamento(from.getTipoPagamentoPreferencial())
                .status(from.getStatus() != null && from.getStatus() ? "PAGO" : "PENDENTE")
                .nomeCliente(from.getCliente() != null ? from.getCliente().getNomeRazaoSocial() : null)
                .nomeUsuario(from.getUsuario() != null ? from.getUsuario().getNome() : null)
                .build();
    }
}
