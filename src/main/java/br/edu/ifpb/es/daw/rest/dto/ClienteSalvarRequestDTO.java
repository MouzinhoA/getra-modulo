package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record ClienteSalvarRequestDTO(
    @Schema(description = "Nome ou Razão Social do cliente.")
    @NotBlank
    String nomeRazaoSocial,

    @Schema(description = "CPF ou CNPJ único do cliente.")
    @NotBlank
    String cpfCnpj,

    @Schema(description = "E-mail de contato do cliente.")
    String email,

    @Schema(description = "Telefone de contato.")
    String telefone,

    @Schema(description = "Endereço completo do cliente.")
    String endereco
) {}
