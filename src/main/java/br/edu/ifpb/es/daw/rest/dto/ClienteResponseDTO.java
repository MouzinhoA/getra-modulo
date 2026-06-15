package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import java.util.UUID;

@Builder
public record ClienteResponseDTO(
        @Schema(description = "Lookup ID da tarefa.")
        UUID lookupId,

        @Schema(description = "Nome ou Razão Social do cliente.")
        String nomeRazaoSocial,

        @Schema(description = "CPF ou CNPJ do cliente.")
        String cpfCnpj,

        @Schema(description = "E-mail de contato.")
        String email,

        @Schema(description = "Telefone de contato.")
        String telefone,

        @Schema(description = "Endereço completo.")
        String endereco
) {}
