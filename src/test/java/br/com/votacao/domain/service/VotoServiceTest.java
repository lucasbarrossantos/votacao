package br.com.votacao.domain.service;

import br.com.votacao.domain.mocks.DomainMocks;
import br.com.votacao.domain.model.Usuario;
import br.com.votacao.domain.model.Voto;
import br.com.votacao.domain.repository.VotoRepository;
import br.com.votacao.domain.service.feign.CPFValidatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VotoServiceTest {

    private DomainMocks domainMocks;

    @InjectMocks
    private VotoService votoService;

    @Mock
    private VotoRepository votoRepository;

    @Mock
    private CPFValidatorService cpfValidatorService;

    @BeforeEach
    void init() {
        domainMocks = new DomainMocks();
    }

    @Test
    @DisplayName("Deve salvar um voto")
    void deveSalvarUmVoto() {
        Voto voto = domainMocks.getVoto();

        Mockito.when(cpfValidatorService.validarCPF(Mockito.anyString())).thenReturn(ResponseEntity.ok().build());
        Mockito.when(votoRepository.save(Mockito.any(Voto.class))).thenReturn(voto);

        Voto votoSalvo = votoService.salvar(voto);

        assertEquals(votoSalvo.getId(), 1L);
        Mockito.verify(cpfValidatorService, Mockito.atLeastOnce()).validarCPF(Mockito.anyString());
    }

}
