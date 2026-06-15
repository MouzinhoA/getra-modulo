package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.util.UUID;

@Builder
public record PerfilResponseDTO(
    @Schema(description = "Lookup ID do perfil.")
    UUID lookupId,

    @Schema(description = "Nome do perfil.")
    String nome,

    @Schema(description = "Permissões do perfil.")
    String permissoes
) {}
