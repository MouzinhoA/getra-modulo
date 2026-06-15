package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ContaPagarBuscarDTO(
    @Schema(description = "Filtrar contas a pagar pela descrição ou título.")
    String descricao,

    @Schema(description = "Filtrar pelo status da conta (PAGO, PENDENTE, VENCIDO).")
    String status,

    @Schema(description = "Número da página a ser retornada na paginação. Começa com zero.")
    Integer númeroPágina,

    @Schema(description = "Quantidade de registros a serem retornados por página.")
    Integer tamanhoPágina
) {
    public ContaPagarBuscarDTO {
        if (númeroPágina == null) {
            númeroPágina = 0;
        }
        if (tamanhoPágina == null) {
            tamanhoPágina = 10;
        }
    }
}
