package br.edu.ifpb.es.daw.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record ParceiroSalvarRequestDTO(
    @Schema(description = "Nome ou Razão Social do Fornecedor/Parceiro.")
    @NotBlank
    String nomeRazaoSocial,

    @Schema(description = "CPF ou CNPJ único do parceiro.")
    @NotBlank
    String cpfCnpj,

    @Schema(description = "E-mail de contato principal.")
    String emailContato,

    @Schema(description = "Dados bancários ou chave PIX para pagamentos.")
    String dadosBancariosPix
) {}
