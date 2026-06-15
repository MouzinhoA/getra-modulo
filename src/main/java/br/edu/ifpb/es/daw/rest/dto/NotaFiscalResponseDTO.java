package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record NotaFiscalResponseDTO(
    @Schema(description = "ID interno da nota fiscal.")
    Long id,

    @Schema(description = "Lookup ID da nota fiscal.")
    UUID lookupId,

    @Schema(description = "Número da nota.")
    String numero,

    @Schema(description = "Data de emissão.")
    LocalDate dataEmissao,

    @Schema(description = "Valor total.")
    BigDecimal valorTotal,

    @Schema(description = "Status da API do governo.")
    String statusApi,

    @Schema(description = "Link do PDF.")
    String linkPdf,

    @Schema(description = "ID da Fatura correspondente.")
    Long idFatura
) {}
