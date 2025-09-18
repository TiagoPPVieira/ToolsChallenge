package br.com.sicredi.toolsChallenge.strategy;

import br.com.sicredi.toolsChallenge.model.commons.enums.PaymentType;
import org.springframework.stereotype.Service;

@Service
public class ParcEmissorPagamentoImpl implements PagamentoStrategy {
    @Override
    public boolean supports(PaymentType type) {
        return type == PaymentType.PARCELADO_EMISSOR;
    }
}
