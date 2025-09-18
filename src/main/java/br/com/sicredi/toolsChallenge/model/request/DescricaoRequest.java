package br.com.sicredi.toolsChallenge.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class DescricaoRequest {
    @NotBlank(message = "O campo 'valor' é obrigatório")
    @Pattern(regexp = "^\\d{1,9}(\\.\\d{2})?$", message = "O campo valor deve conter apenas números")
    private String valor;
    @NotNull(message = "O campo 'dataHora' é obrigatório")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private String dataHora;
    @NotBlank(message = "O campo 'estabelecimento' é obrigatório")
    private String estabelecimento;
}
