package br.com.votacao.domain.service.feign;

import br.com.votacao.api.v1.model.ResponseCPFHeroku;
import br.com.votacao.domain.repository.CPFValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
public class CPFValidatorFallback implements CPFValidator {

    @Override
    public ResponseEntity<ResponseCPFHeroku> validarCPFValidoVotacao(String cpf) {
        log.error("CPF não é válido para votar: {}", cpf);
        return ResponseEntity.notFound().build();
    }

}
