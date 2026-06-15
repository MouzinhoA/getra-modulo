package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record FaturaBuscarDTO(
    @Schema(description = "Filtrar pelo status atual da fatura (ex: PAGO, PENDENTE).")
    String status,

    @Schema(description = "Filtrar faturas de um cliente específico informando o ID dele.")
    Long idCliente,

    @Schema(description = "Número da página a ser retornada na paginação. Começa com zero.")
    Integer númeroPágina,

    @Schema(description = "Quantidade de registros a serem retornados por página.")
    Integer tamanhoPágina
) {
    public FaturaBuscarDTO {
        if (númeroPágina == null) {
            númeroPágina = 0;
        }
        if (tamanhoPágina == null) {
            tamanhoPágina = 10;
        }
    }
}
