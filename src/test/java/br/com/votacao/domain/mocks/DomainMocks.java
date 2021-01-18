package br.com.votacao.domain.mocks;

import br.com.votacao.domain.model.Pauta;
import br.com.votacao.domain.model.Usuario;
import br.com.votacao.domain.model.Voto;
import br.com.votacao.domain.model.enums.TipoVoto;

public class DomainMocks {

    public Usuario getUsuario() {
        return Usuario.builder()
                .id(1L)
                .nome("Lucas Barros Santos")
                .cpf("33475297078")
                .build();
    }

    public Pauta getPauta() {
        return Pauta.builder()
                .descricao("Pauta de teste")
                .id(1L)
                .build();
    }

    public Voto getVoto() {
        return Voto.builder()
                .id(1L)
                .usuario(getUsuario())
                .pauta(getPauta())
                .tipoVoto(TipoVoto.SIM)
                .build();
    }
}
