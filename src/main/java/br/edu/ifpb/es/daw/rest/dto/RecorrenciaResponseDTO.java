package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.util.UUID;

@Builder
public record RecorrenciaResponseDTO(
    @Schema(description = "ID interno da recorrência.")
    Long id,

    @Schema(description = "Lookup ID da recorrência.")
    UUID lookupId,

    @Schema(description = "Valor cobrado.")
    Double valorCobrado,

    @Schema(description = "Periodicidade.")
    String periodicidade,

    @Schema(description = "Dia de vencimento.")
    Integer diaVencimento,

    @Schema(description = "Status da assinatura.")
    Boolean status,

    @Schema(description = "Nome do Cliente.")
    String nomeCliente,

    @Schema(description = "Nome do Serviço.")
    String nomeServico
) {}
