package br.edu.ifpb.es.daw.mapper;

import br.edu.ifpb.es.daw.model.Parceiro;
import br.edu.ifpb.es.daw.rest.dto.ParceiroResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.ParceiroSalvarRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class ParceiroMapper {

    public Parceiro from(ParceiroSalvarRequestDTO from) {
        return Parceiro.builder()
                .nomeRazaoSocial(from.nomeRazaoSocial())
                .cpfCnpj(from.cpfCnpj())
                .emailContato(from.emailContato())
                .dadosBancariosPix(from.dadosBancariosPix())
                .build();
    }

    public ParceiroResponseDTO from(Parceiro from) {
        return ParceiroResponseDTO.builder()
                .lookupId(from.getLookupId())
                .nomeRazaoSocial(from.getNomeRazaoSocial())
                .cpfCnpj(from.getCpfCnpj())
                .emailContato(from.getEmailContato())
                .dadosBancariosPix(from.getDadosBancariosPix())
                .build();
    }
}
