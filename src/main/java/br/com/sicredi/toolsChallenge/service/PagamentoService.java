package br.com.sicredi.toolsChallenge.service;

import br.com.sicredi.toolsChallenge.dto.PagamentoRequest;
import br.com.sicredi.toolsChallenge.dto.PagamentoResponse;
import br.com.sicredi.toolsChallenge.exception.external.IdTransacaoDuplicadoException;
import br.com.sicredi.toolsChallenge.exception.external.TipoPagamentoNaoSuportadoException;
import br.com.sicredi.toolsChallenge.model.response.TransacaoEntity;
import br.com.sicredi.toolsChallenge.repository.TransactionRepository;
import br.com.sicredi.toolsChallenge.strategy.PagamentoStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PagamentoService {
    private final TransactionRepository repository;
    private final List<PagamentoStrategy> strategies;

    public PagamentoResponse pay(PagamentoRequest pagamentoRequest){
        strategies.stream()
                .filter(s -> s.supports(pagamentoRequest.transacao().getFormaPagamento().getTipo()))
                .findFirst()
                .orElseThrow(() -> new TipoPagamentoNaoSuportadoException("Tipo de pagamento não suportado"));

        TransacaoEntity transacaoEntity = new TransacaoEntity(pagamentoRequest.transacao());

        if (repository.existsById(transacaoEntity.getId())) {
            throw new IdTransacaoDuplicadoException("Id: " + transacaoEntity.getId() + " já existe na base de dados.");
        }

        repository.save(transacaoEntity);

        return new PagamentoResponse(transacaoEntity);
    }

    public Optional<TransacaoEntity> findById(String id) {
        return repository.findById(id);
    }
    public Optional<TransacaoEntity> findAndDeleteById(String id) {
        Optional<TransacaoEntity> transacaoEntity = repository.findById(id);
        repository.deleteById(id);
        return transacaoEntity;
    }

    public List<TransacaoEntity> findAll() {
        return repository.findAll();
    }
}
