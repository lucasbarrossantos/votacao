package br.com.votacao.domain.exception;

public class CPFNaoEncontradoException extends EntidadeNaoEncontradaException {

    public CPFNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
