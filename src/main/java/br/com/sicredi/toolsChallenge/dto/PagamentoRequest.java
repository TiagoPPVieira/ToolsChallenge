package br.com.sicredi.toolsChallenge.dto;

import br.com.sicredi.toolsChallenge.model.request.TransacaoRequest;
import jakarta.validation.Valid;

public record PagamentoRequest(@Valid TransacaoRequest transacao) {
}
