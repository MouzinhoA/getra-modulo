package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record RecorrenciaBuscarDTO(
    @Schema(description = "Filtrar pela periodicidade (ex: MENSAL, ANUAL).")
    String periodicidade,

    @Schema(description = "Filtrar pelo status da recorrência (true para ativas, false para inativas).")
    Boolean status,

    @Schema(description = "Número da página a ser retornada na paginação. Começa com zero.")
    Integer númeroPágina,

    @Schema(description = "Quantidade de registros a serem retornados por página.")
    Integer tamanhoPágina
) {
    public RecorrenciaBuscarDTO {
        if (númeroPágina == null) {
            númeroPágina = 0;
        }
        if (tamanhoPágina == null) {
            tamanhoPágina = 10;
        }
    }
}
