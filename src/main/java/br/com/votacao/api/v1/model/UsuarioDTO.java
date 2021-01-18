package br.com.votacao.api.v1.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

@Data
public class UsuarioDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @CPF
    private String cpf;

}
