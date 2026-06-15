package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PerfilBuscarDTO(
    @Schema(description = "Filtrar pelo nome do perfil (ex: ADMIN, OPERADOR).")
    String nome,

    @Schema(description = "Número da página a ser retornada na paginação. Começa com zero.")
    Integer númeroPágina,

    @Schema(description = "Quantidade de registros a serem retornados por página.")
    Integer tamanhoPágina
) {
    public PerfilBuscarDTO {
        if (númeroPágina == null) {
            númeroPágina = 0;
        }
        if (tamanhoPágina == null) {
            tamanhoPágina = 10;
        }
    }
}
