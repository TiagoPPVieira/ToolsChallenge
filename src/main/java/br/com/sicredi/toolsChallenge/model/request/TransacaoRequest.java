package br.com.sicredi.toolsChallenge.model.request;

import br.com.sicredi.toolsChallenge.model.commons.FormaPagamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransacaoRequest {
    @NotBlank(message = "O campo 'cartao' é obrigatório")
    private String cartao;
    @NotBlank(message = "O campo 'id' é obrigatório")
    private String id;
    @Valid
    private DescricaoRequest descricaoRequest;
    @Valid
    private FormaPagamento formaPagamento;
}
