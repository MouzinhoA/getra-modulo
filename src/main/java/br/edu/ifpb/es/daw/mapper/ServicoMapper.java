package br.edu.ifpb.es.daw.mapper;

import br.edu.ifpb.es.daw.model.Servico;
import br.edu.ifpb.es.daw.rest.dto.ServicoResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.ServicoSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class ServicoMapper {

    public Servico from(ServicoSalvarRequestDTO from) {
        return Servico.builder()
                .nome(from.nome())
                .descricao(from.descrição())
                .valorPadrao(from.valorPadrao())
                .build();
    }

    public ServicoResponseDTO from(Servico from) {
        return ServicoResponseDTO.builder()
                .id(from.getId())
                .lookupId(from.getLookupId())
                .nome(from.getNome())
                .descrição(from.getDescricao())
                .valorPadrao(from.getValorPadrao())
                .build();
    }
}
