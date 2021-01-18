package br.com.votacao.domain.service.feign;

import br.com.votacao.api.v1.model.ResponseCPFHeroku;
import br.com.votacao.domain.exception.CPFNaoEncontradoException;
import br.com.votacao.domain.exception.NegocioException;
import br.com.votacao.domain.repository.CPFValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CPFValidatorService {

    @Autowired
    private CPFValidator cpfValidator;

    public ResponseEntity<ResponseCPFHeroku> validarCPF(String cpf) {
        log.info("Validando associado no Heroku");
        ResponseEntity<ResponseCPFHeroku> response = cpfValidator.validarCPFValidoVotacao(cpf);

        ResponseCPFHeroku data = response.getBody();

        if (data != null && data.getStatus().equals("ABLE_TO_VOTE")) {
            log.info("Associado liberado para votar");
            return ResponseEntity.ok(response.getBody());
        }
        else {
            log.info("Associado não liberado para votar");
            throw new NegocioException(String.format("O Associado com CPF: %s não pode votar no momento!", cpf));
        }
    }

}
