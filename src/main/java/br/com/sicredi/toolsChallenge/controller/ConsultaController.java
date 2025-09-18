package br.com.sicredi.toolsChallenge.controller;

import br.com.sicredi.toolsChallenge.model.response.TransacaoEntity;
import br.com.sicredi.toolsChallenge.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/consulta")
@AllArgsConstructor
public class ConsultaController {
    private final PagamentoService pagamentoService;

    @GetMapping("/{id}")
    @Operation(summary = "Consulta um pagamento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<TransacaoEntity> findById(@PathVariable String id){
        return pagamentoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Consulta todos os pagamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<List<TransacaoEntity>> findAll(){
        List<TransacaoEntity> transacaos = pagamentoService.findAll();

        return new ResponseEntity<>(transacaos, HttpStatus.OK);
    }
}
