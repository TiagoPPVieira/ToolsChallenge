package br.com.sicredi.toolsChallenge.exception.external;

import lombok.Getter;

@Getter
public class IdTransacaoDuplicadoException extends Owner4xxException {
    public IdTransacaoDuplicadoException(String message) {
        super(message);
    }
}
