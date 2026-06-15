package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioSalvarRequestDTO(
    @Schema(description = "Nome do usuário.")
    @NotBlank
    String nome,

    @Schema(description = "E-mail único de acesso do usuário.")
    @NotBlank
    String email,

    @Schema(description = "Senha em texto limpo.")
    @NotBlank
    String senha,

    @Schema(description = "Status se o usuário está ativo ou não.")
    @NotNull
    Boolean ativo,

    @Schema(description = "ID do Perfil de acesso associado.")
    @NotNull
    Long idPerfil
) {}
