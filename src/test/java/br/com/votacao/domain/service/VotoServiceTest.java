package br.com.votacao.domain.service;

import br.com.votacao.domain.mocks.DomainMocks;
import br.com.votacao.domain.model.Pauta;
import br.com.votacao.domain.model.Voto;
import br.com.votacao.domain.repository.VotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VotoServiceTest {

    private DomainMocks domainMocks;

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private RegrasService regrasService;

    @BeforeEach
    void init() {
        domainMocks = new DomainMocks();
    }

    @Test
    @DisplayName("Deve salvar um voto")
    void deveSalvarUmVoto() {
        Voto voto = domainMocks.getVoto();

        Mockito.when(votoRepository.save(Mockito.any(Voto.class))).thenReturn(voto);
        Mockito.when(usuarioService.buscarOuFalhar(Mockito.anyLong())).thenReturn(domainMocks.getUsuario());
        Mockito.doNothing().when(regrasService).validarSessao(Mockito.any(Pauta.class));
        Mockito.doNothing().when(regrasService).validarAssociado(Mockito.anyString());

        Voto votoSalvo = votoService.salvar(voto);

        assertEquals(votoSalvo.getId(), 1L);
        Mockito.verify(regrasService, Mockito.atLeastOnce()).validarAssociado(Mockito.anyString());
    }

}
