package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ServicoSalvarRequestDTO(
    @Schema(description = "Nome único do serviço.")
    @NotBlank
    String nome,

    @Schema(description = "Descrição detalhada do serviço.")
    String descricao,

    @Schema(description = "Valor padrão cobrado pelo serviço.")
    @NotNull
    Double valorPadrao
) {}
