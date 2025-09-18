package br.com.sicredi.toolsChallenge.exception.external;

import lombok.Getter;

@Getter
public class TipoPagamentoNaoSuportadoException extends Owner4xxException {
    public TipoPagamentoNaoSuportadoException(String message) {
        super(message);
    }
}
