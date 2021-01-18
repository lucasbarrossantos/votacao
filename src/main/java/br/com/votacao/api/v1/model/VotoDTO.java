package br.com.votacao.api.v1.model;

import lombok.Data;

@Data
public class VotoDTO {

    private Long id;
    private Long usuarioId;
    private Long pautaId;
    private String tipoVoto;

}
