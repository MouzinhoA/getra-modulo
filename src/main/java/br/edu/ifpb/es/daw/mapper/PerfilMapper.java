package br.edu.ifpb.es.daw.mapper;

import br.edu.ifpb.es.daw.model.Perfil;
import br.edu.ifpb.es.daw.rest.dto.PerfilResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.PerfilSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class PerfilMapper {

    public Perfil from(PerfilSalvarRequestDTO from) {
        return Perfil.builder()
                .nome(from.nome())
                .permissoes(from.permissoes())
                .build();
    }

    public PerfilResponseDTO from(Perfil from) {
        return PerfilResponseDTO.builder()
                .lookupId(from.getLookupId())
                .nome(from.getNome())
                .permissoes(from.getPermissoes())
                .build();
    }
}
