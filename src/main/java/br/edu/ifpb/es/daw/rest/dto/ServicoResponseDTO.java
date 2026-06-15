package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.util.UUID;

@Builder
public record ServicoResponseDTO(
    @Schema(description = "Lookup ID do serviço.")
    UUID lookupId,

    @Schema(description = "Nome do serviço.")
    String nome,

    @Schema(description = "Descrição do serviço.")
    String descrição,

    @Schema(description = "Valor padrão do serviço.")
    Double valorPadrao
) {}
