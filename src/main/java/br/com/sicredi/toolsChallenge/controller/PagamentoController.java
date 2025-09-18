package br.com.sicredi.toolsChallenge.controller;

import br.com.sicredi.toolsChallenge.dto.PagamentoRequest;
import br.com.sicredi.toolsChallenge.dto.PagamentoResponse;
import br.com.sicredi.toolsChallenge.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@AllArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;

    @PostMapping
    @Operation(summary = "Cria um pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<PagamentoResponse> create(@RequestBody @Valid PagamentoRequest pagamentoRequest){
        return ResponseEntity.ok(pagamentoService.pay(pagamentoRequest));
    }
}
