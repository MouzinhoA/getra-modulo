package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record FaturaSalvarRequestDTO(
    @Schema(description = "Valor total da fatura.")
    @NotNull
    Double valorTotal,

    @Schema(description = "Data de vencimento da fatura.")
    @NotNull
    LocalDate dataVencimento,

    @Schema(description = "Data em que foi paga.")
    LocalDate dataPagamento,

    @Schema(description = "Meio de pagamento utilizado.")
    String meioPagamento,

    @Schema(description = "Status atual da fatura (ex: PAGO, PENDENTE).")
    String status,

    @Schema(description = "ID do Cliente.")
    @NotNull
    Long idCliente,

    @Schema(description = "ID do Usuário que gerou a fatura.")
    @NotNull
    Long idUsuario,

    @Schema(description = "ID da Recorrência geradora.")
    Long idRecorrencia
) {}
