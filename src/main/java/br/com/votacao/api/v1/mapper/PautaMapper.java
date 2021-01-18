package br.com.votacao.api.v1.mapper;

import br.com.votacao.api.v1.model.PautaDTO;
import br.com.votacao.domain.model.Pauta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PautaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PautaDTO toPautaDTO(final Pauta pauta) {
        return modelMapper.map(pauta, PautaDTO.class);
    }

    public Pauta toPauta(final PautaDTO pautaDTO) {
        return modelMapper.map(pautaDTO, Pauta.class);
    }

}
