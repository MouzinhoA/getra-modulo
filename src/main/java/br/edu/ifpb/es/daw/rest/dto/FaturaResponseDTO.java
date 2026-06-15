package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record FaturaResponseDTO(
    @Schema(description = "ID interno da fatura.")
    Long id,

    @Schema(description = "Lookup ID da fatura.")
    UUID lookupId,

    @Schema(description = "Valor total.")
    Double valorTotal,

    @Schema(description = "Data de vencimento.")
    LocalDate dataVencimento,

    @Schema(description = "Data de pagamento.")
    LocalDate dataPagamento,

    @Schema(description = "Meio de pagamento.")
    String meioPagamento,

    @Schema(description = "Status da fatura.")
    String status,

    @Schema(description = "Nome do Cliente.")
    String nomeCliente,

    @Schema(description = "Nome do Usuário criador.")
    String nomeUsuario
) {}
