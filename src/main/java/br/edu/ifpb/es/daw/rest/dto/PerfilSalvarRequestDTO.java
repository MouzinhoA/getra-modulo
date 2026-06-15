package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record PerfilSalvarRequestDTO(
    @Schema(description = "Nome do perfil.")
    @NotBlank
    String nome,

    @Schema(description = "Permissões associadas ao perfil separadas por vírgula.")
    @NotBlank
    String permissoes
) {}
