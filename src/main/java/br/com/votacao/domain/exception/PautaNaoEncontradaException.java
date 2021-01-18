package br.com.votacao.domain.exception;

public class PautaNaoEncontradaException extends EntidadeNaoEncontradaException {

    public PautaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public PautaNaoEncontradaException(Long id) {
        this(String.format("Não existe uma pauta com o id: %s", id));
    }

}
