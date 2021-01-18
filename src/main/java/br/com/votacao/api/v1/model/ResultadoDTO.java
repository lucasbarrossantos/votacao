package br.com.votacao.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ResultadoDTO {

    private Long qtdVotos;
    private String descricao;

}
