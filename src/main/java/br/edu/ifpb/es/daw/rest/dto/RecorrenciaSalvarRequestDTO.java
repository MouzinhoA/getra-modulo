package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record RecorrenciaSalvarRequestDTO(
    @Schema(description = "Valor customizado cobrado nesta recorrência.")
    @NotNull
    Double valorCobrado,

    @Schema(description = "Periodicidade da cobrança: MENSAL, BIMESTRAL, TRIMESTRAL, SEMESTRAL, ANUAL.")
    @NotNull
    String periodicidade,

    @Schema(description = "Dia de vencimento da fatura (1 a 31).")
    @NotNull
    Integer diaVencimento,

    @Schema(description = "Status da recorrência (ativo/inativo).")
    @NotNull
    Boolean status,

    @Schema(description = "ID do Cliente associado.")
    @NotNull
    Long idCliente,

    @Schema(description = "ID do Serviço associado.")
    @NotNull
    Long idServico
) {}
