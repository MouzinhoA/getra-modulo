package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.util.UUID;

@Builder
public record ParceiroResponseDTO(
    @Schema(description = "ID interno do parceiro.")
    Long id,

    @Schema(description = "Lookup ID do parceiro.")
    UUID lookupId,

    @Schema(description = "Nome ou Razão Social.")
    String nomeRazaoSocial,

    @Schema(description = "CPF ou CNPJ.")
    String cpfCnpj,

    @Schema(description = "E-mail de contato.")
    String emailContato,

    @Schema(description = "Chave Pix / Dados Bancários.")
    String dadosBancariosPix
) {}
