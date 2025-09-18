package br.com.sicredi.toolsChallenge.exception.external;

import lombok.Getter;

@Getter
public class ValorPagamentoNaoSuportadoException extends Owner4xxException {
    public ValorPagamentoNaoSuportadoException(String message) {
        super(message);
    }
}
