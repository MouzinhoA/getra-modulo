package br.edu.ifpb.es.daw.mapper;

import br.edu.ifpb.es.daw.model.Cliente;
import br.edu.ifpb.es.daw.rest.dto.ClienteResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.ClienteSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente from(ClienteSalvarRequestDTO from) {
        return Cliente.builder()
                .nomeRazaoSocial(from.nomeRazaoSocial())
                .cpfCnpj(from.cpfCnpj())
                .email(from.email())
                .telefone(from.telefone())
                .endereco(from.endereco())
                .build();
    }

    public ClienteResponseDTO from(Cliente from) {
        return ClienteResponseDTO.builder()
                .id(from.getId())
                .lookupId(from.getLookupId())
                .nomeRazaoSocial(from.getNomeRazaoSocial())
                .cpfCnpj(from.getCpfCnpj())
                .email(from.getEmail())
                .telefone(from.getTelefone())
                .endereco(from.getEndereco())
                .build();
    }
}
