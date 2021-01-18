package br.com.votacao.domain.service;

import br.com.votacao.api.v1.model.ResultadoDTO;
import br.com.votacao.core.rabbitmq.MachineAMQPConfig;
import br.com.votacao.domain.exception.NegocioException;
import br.com.votacao.domain.model.Pauta;
import br.com.votacao.domain.model.Voto;
import br.com.votacao.domain.repository.VotoRepository;
import br.com.votacao.domain.service.feign.CPFValidatorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class RegrasService {

    private final VotoRepository votoRepository;
    private final CPFValidatorService cpfValidatorService;
    private final PautaService pautaService;
    private final RabbitTemplate rabbitTemplate;

    public void validarAssociado(String cpf) {
        cpfValidatorService.validarCPF(cpf);
    }

    public void validarVotoDuplicado(Voto voto) {
        log.info("Validando voto duplicado");
        Optional<Voto> votoOptional = votoRepository.findByUsuarioIdAndPautaId(voto.getUsuario().getId(), voto.getPauta().getId());

        if (votoOptional.isPresent()) {
            log.info("Voto já computado!");
            throw new NegocioException("Já existe um voto computado para este usuário nesta pauta!");
        }
    }

    @SneakyThrows
    public void validarSessao(Pauta pauta) {
        log.info("Validando cessão");
        pauta = pautaService.buscarOuFalhar(pauta.getId());

        if (pauta.getInicioSessao() == null) {
            log.info("Sessão ainda não iniciada");
            throw new NegocioException(String.format("É preciso iniciar a sessão antes do início da votação: Pauta: %s", pauta.getId()));
        }

        if (pauta.getInicioSessao().until(LocalDateTime.now(), ChronoUnit.MINUTES) > 1) {
            log.warn("Sessão encerrada!");
            enviarResultado(pauta);
            throw new NegocioException(String.format("Sessão de votação encerrada para a Pauta:{%s}", pauta.getId()));
        }
        log.info("Sessão válida");
    }

    private void enviarResultado(Pauta pauta) throws JsonProcessingException {
        log.info("Notificando aplicação do resultado da votação");
        ResultadoDTO resultadoDTO = pautaService.resultadoPauta(pauta.getId());
        String json = new ObjectMapper().writeValueAsString(resultadoDTO);
        rabbitTemplate.convertAndSend(MachineAMQPConfig.EXCHANGE_NAME, "", json);
        log.info("Aplicação notificada! Payload:{}", json);
    }

}
