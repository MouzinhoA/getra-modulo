package br.edu.ifpb.es.daw.mapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import br.edu.ifpb.es.daw.model.Perfil;
import br.edu.ifpb.es.daw.model.Usuario;
import br.edu.ifpb.es.daw.rest.dto.UsuarioResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.UsuarioSalvarRequestDTO;

@Component
public class UsuarioMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public Usuario from(UsuarioSalvarRequestDTO from) {
        return Usuario.builder()
                .nome(from.nome())
                .email(from.email())
                .senhaHash(from.senha())
                .ativo(from.ativo())
                .perfil(entityManager.getReference(Perfil.class, from.idPerfil()))
                .build();
    }

    public UsuarioResponseDTO from(Usuario from) {
        return UsuarioResponseDTO.builder()
                .lookupId(from.getLookupId())
                .nome(from.getNome())
                .email(from.getEmail())
                .ativo(from.getAtivo())
                .idPerfil(from.getPerfil() != null ? from.getPerfil().getId() : null)
                .nomePerfil(from.getPerfil() != null ? from.getPerfil().getNome() : null)
                .build();
    }
}
