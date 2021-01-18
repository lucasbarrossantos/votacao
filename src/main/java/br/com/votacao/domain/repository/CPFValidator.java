package br.com.votacao.domain.repository;

import br.com.votacao.api.v1.model.ResponseCPFHeroku;
import br.com.votacao.domain.service.feign.CPFValidatorFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "validatorCPF", url = "${cpf.validator-url}", fallback = CPFValidatorFallback.class)
public interface CPFValidator {

    @GetMapping("/{cpf}")
    ResponseEntity<ResponseCPFHeroku> validarCPFValidoVotacao(@PathVariable("cpf") String cpf);

}
