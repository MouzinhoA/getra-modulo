package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContaPagarSalvarRequestDTO(
    @Schema(description = "Descrição ou título da conta a pagar.")
    @NotBlank
    String descricao,

    @Schema(description = "Valor da conta.")
    @NotNull
    Double valor,

    @Schema(description = "Status atual (PAGO, PENDENTE, VENCIDO).")
    @NotBlank
    String status,

    @Schema(description = "Forma de pagamento pretendia.")
    String formaPagamento,

    @Schema(description = "Data de vencimento.")
    @NotBlank
    String dataVencimento,

    @Schema(description = "Data em que foi paga.")
    String dataPagamento,

    @Schema(description = "ID do Parceiro/Fornecedor vinculado.")
    @NotNull
    Long idParceiro,

    @Schema(description = "ID do Usuário responsável.")
    @NotNull
    Long idUsuario
) {}
