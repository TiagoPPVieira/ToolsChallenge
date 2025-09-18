package br.com.sicredi.toolsChallenge.model.response;

import br.com.sicredi.toolsChallenge.model.commons.FormaPagamento;
import br.com.sicredi.toolsChallenge.model.request.TransacaoRequest;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public TransacaoEntity() {
    }

    public TransacaoEntity(TransacaoRequest transacaoRequest) {
        this.cartao = transacaoRequest.getCartao();
        this.id = transacaoRequest.getId();
        this.formaPagamento = transacaoRequest.getFormaPagamento();

        descricao = DescricaoResponse.builder()
                .estabelecimento(transacaoRequest.getDescricao().getEstabelecimento())
                .valor(transacaoRequest.getDescricao().getValor())
                .dataHora(String.valueOf(transacaoRequest.getDescricao().getDataHora()))
                .nsu(getNsu())
                .codigoAutorizacao(getCodigoAutorizacao())
                .status(getStatus(transacaoRequest.getDescricao().getValor()))
                .build();
    }
}
