package br.com.votacao.api.v1.mapper;

import br.com.votacao.api.v1.model.UsuarioDTO;
import br.com.votacao.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDTO toUsuarioDTO(final Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public Usuario toUsuario(final UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, Usuario.class);
    }

}
