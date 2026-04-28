package financeflow.exception;

public class TransacaoNaoEncontradaException extends RuntimeException {
    public TransacaoNaoEncontradaException(String message) {
        super(message);
    }
}
