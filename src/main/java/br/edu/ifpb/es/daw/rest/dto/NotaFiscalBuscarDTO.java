package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record NotaFiscalBuscarDTO(
    @Schema(description = "Filtrar pelo número exato da nota fiscal.")
    String numero,

    @Schema(description = "Filtrar pelo status da nota na API do governo (ex: AUTORIZADA, CANCELADA).")
    String statusApi,

    @Schema(description = "Número da página a ser retornada na paginação. Começa com zero.")
    Integer númeroPágina,

    @Schema(description = "Quantidade de registros a serem retornados por página.")
    Integer tamanhoPágina
) {
    public NotaFiscalBuscarDTO {
        if (númeroPágina == null) {
            númeroPágina = 0;
        }
        if (tamanhoPágina == null) {
            tamanhoPágina = 10;
        }
    }
}
