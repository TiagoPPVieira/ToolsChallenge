package br.com.sicredi.toolsChallenge.model.response;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Embeddable
public class DescricaoResponse {
    private String valor;
    private String dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private String status;

    public DescricaoResponse() {
    }

    public DescricaoResponse(String valor, String dataHora, String estabelecimento, String nsu, String codigoAutorizacao, String status) {
        this.valor = valor;
        this.dataHora = dataHora;
        this.estabelecimento = estabelecimento;
        this.nsu = nsu;
        this.codigoAutorizacao = codigoAutorizacao;
        this.status = status;
    }
}
