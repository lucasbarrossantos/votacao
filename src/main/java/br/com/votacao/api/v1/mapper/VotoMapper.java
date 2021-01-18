package br.com.votacao.api.v1.mapper;

import br.com.votacao.api.v1.model.VotoDTO;
import br.com.votacao.domain.model.Voto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VotoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public VotoDTO toVotoDTO(final Voto voto) {
        return modelMapper.map(voto, VotoDTO.class);
    }

    public Voto toVoto(final VotoDTO votoDTO) {
        return modelMapper.map(votoDTO, Voto.class);
    }

}
