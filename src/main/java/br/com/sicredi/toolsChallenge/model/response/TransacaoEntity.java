package br.com.sicredi.toolsChallenge.model.response;

import br.com.sicredi.toolsChallenge.model.commons.FormaPagamento;
import br.com.sicredi.toolsChallenge.model.request.TransacaoRequest;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import static br.com.sicredi.toolsChallenge.util.AuthorizationCodeGenerator.getCodigoAutorizacao;
import static br.com.sicredi.toolsChallenge.util.NsuGenerator.getNsu;
import static br.com.sicredi.toolsChallenge.model.commons.enums.Status.getStatus;

@Entity
@Getter
@Setter
public class TransacaoEntity {
    private String cartao;
    @Id
    private String id;
    @Embedded
    private DescricaoResponse descricao;
    @Embedded
    private FormaPagamento formaPagamento;

    public TransacaoEntity(TransacaoRequest transacaoRequest) {
        this.cartao = transacaoRequest.getCartao();
        this.id = transacaoRequest.getId();
        this.formaPagamento = transacaoRequest.getFormaPagamento();

        descricao = DescricaoResponse.builder()
                .estabelecimento(transacaoRequest.getDescricaoRequest().getEstabelecimento())
                .valor(transacaoRequest.getDescricaoRequest().getValor())
                .dataHora(transacaoRequest.getDescricaoRequest().getDataHora())
                .nsu(getNsu())
                .codigoAutorizacao(getCodigoAutorizacao())
                .status(getStatus(transacaoRequest.getDescricaoRequest().getValor()))
                .build();
    }
}
