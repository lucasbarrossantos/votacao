package br.com.votacao.domain.service;

import br.com.votacao.api.v1.model.ResultadoDTO;
import br.com.votacao.domain.exception.PautaNaoEncontradaException;
import br.com.votacao.domain.model.Pauta;
import br.com.votacao.domain.repository.PautaRepository;
import br.com.votacao.domain.repository.VotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class PautaService {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotoRepository votoRepository;

    public Pauta salvar(Pauta pauta) {
        log.info("Salvando pauta");
        pauta = pautaRepository.save(pauta);
        log.info("Pauta salva!");
        return pauta;
    }

    public ResultadoDTO resultadoPauta(Long id) {
        log.info("Montando resultado da votação");
        ResultadoDTO resultadoDTO = votoRepository.resultadoVotacao(id)
                .orElse(ResultadoDTO.builder()
                        .descricao("Ainda não há votos computados para esta Pauta!")
                        .qtdVotos(0L)
                        .build());
        log.info("Resultado montado com sucesso!");
        return resultadoDTO;
    }

    public Pauta buscarOuFalhar(Long id) {
        return pautaRepository.findById(id)
                .orElseThrow(() -> new PautaNaoEncontradaException(id));
    }

    public void abrirSessao(Long pautaId) {
        log.info("Abrindo uma nova sessão de votação para Pauta: {}", pautaId);
        Pauta pauta = buscarOuFalhar(pautaId);
        pauta.setInicioSessao(LocalDateTime.now());
        pautaRepository.save(pauta);
        log.info("Sessão aberta para nova votação");
    }
}
