package br.com.sicredi.toolsChallenge.controller;

import br.com.sicredi.toolsChallenge.model.response.TransacaoEntity;
import br.com.sicredi.toolsChallenge.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estorno")
@AllArgsConstructor
public class EstornoController {
    private final PagamentoService pagamentoService;

    @GetMapping("/{id}")
    @Operation(summary = "Estorna um pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento estornado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<TransacaoEntity> findById(@PathVariable String id){
        return pagamentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
