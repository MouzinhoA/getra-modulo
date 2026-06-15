package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.util.UUID;

@Builder
public record UsuarioResponseDTO(
    @Schema(description = "Lookup ID seguro do usuário.")
    UUID lookupId,

    @Schema(description = "Nome do usuário.")
    String nome,

    @Schema(description = "E-mail do usuário.")
    String email,

    @Schema(description = "Status se está ativo.")
    Boolean ativo,

    @Schema(description = "Nome do Perfil associado.")
    String nomePerfil
) {}
