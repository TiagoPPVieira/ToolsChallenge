package br.com.sicredi.toolsChallenge.strategy;

import br.com.sicredi.toolsChallenge.model.commons.enums.PaymentType;

public interface PagamentoStrategy {
    boolean supports(PaymentType type);
}
