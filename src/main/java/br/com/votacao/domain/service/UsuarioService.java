package br.com.votacao.domain.service;

import br.com.votacao.domain.exception.UsuarioNaoEncontradoException;
import br.com.votacao.domain.model.Usuario;
import br.com.votacao.domain.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
        log.info("Salvando usuário");
        usuario = usuarioRepository.save(usuario);
        log.info("Usuário salvo");
        return usuario;
    }

    public Usuario buscarOuFalhar(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }
}
