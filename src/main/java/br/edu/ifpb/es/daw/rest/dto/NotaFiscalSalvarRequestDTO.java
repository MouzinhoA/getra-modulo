package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record NotaFiscalSalvarRequestDTO(
    @Schema(description = "Número da nota fiscal.")
    @NotBlank
    String numero,

    @Schema(description = "Data de emissão da nota.")
    @NotNull
    LocalDate dataEmissao,

    @Schema(description = "Valor total da nota fiscal.")
    @NotNull
    BigDecimal valorTotal,

    @Schema(description = "Status de retorno da API do governo.")
    String statusApi,

    @Schema(description = "ID externo retornado pela API do governo.")
    String idExtGovApi,

    @Schema(description = "Link para o XML da nota.")
    String linkXml,

    @Schema(description = "Link para o PDF da nota.")
    String linkPdf,

    @Schema(description = "ID da Fatura associada.")
    @NotNull
    Long idFatura
) {}
