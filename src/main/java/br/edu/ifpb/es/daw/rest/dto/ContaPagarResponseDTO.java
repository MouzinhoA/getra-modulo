package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.util.UUID;

@Builder
public record ContaPagarResponseDTO(
        @Schema(description = "Lookup ID seguro da conta a pagar.")
        UUID lookupId,

        @Schema(description = "Descrição da conta.")
        String descricao,

        @Schema(description = "Valor da conta.")
        Double valor,

        @Schema(description = "Status atual (PAGO, ABERTO).")
        String status,

        @Schema(description = "Forma de pagamento utilizada (se houver).")
        String formaPagamento,

        @Schema(description = "Data de vencimento.")
        String dataVencimento,

        @Schema(description = "Data em que foi realizada o pagamento (se houver).")
        String dataPagamento,

        @Schema(description = "Nome do Parceiro/Fornecedor de forma legível.")
        String nomeParceiro,

        @Schema(description = "Nome do Usuário responsável pelo lançamento.")
        String nomeUsuario
) {}