package br.com.sicredi.toolsChallenge.model.commons;

import br.com.sicredi.toolsChallenge.model.commons.enums.PaymentType;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Embeddable
public class FormaPagamento {
    @NotNull(message = "O campo 'tipo' é obrigatório")
    @Valid
    private PaymentType tipo;
    @NotBlank(message = "O campo 'parcelas' é obrigatório")
    @Pattern(
            regexp = "^(0[1-9]|[1-9][0-9])$",
            message = "O campo parcelas deve ser um número inteiro maior ou igual a 1"
    )
    private String parcelas;
}
