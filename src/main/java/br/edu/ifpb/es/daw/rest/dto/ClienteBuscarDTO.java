package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ClienteBuscarDTO(
    @Schema(description = "Filtrar pelo nome ou razão social do cliente.")
    String nomeRazaoSocial,

    @Schema(description = "Filtrar por um CPF ou CNPJ específico.")
    String cpfCnpj,

    @Schema(description = "Número da página a ser retornada na paginação. Começa com zero.")
    Integer númeroPágina,

    @Schema(description = "Quantidade de registros a serem retornados por página.")
    Integer tamanhoPágina
) {
    public ClienteBuscarDTO {
        if (númeroPágina == null) {
            númeroPágina = 0;
        }
        if (tamanhoPágina == null) {
            tamanhoPágina = 10;
        }
    }
}
