package br.com.sicredi.toolsChallenge.controller;

import br.com.sicredi.toolsChallenge.dto.PagamentoRequest;
import br.com.sicredi.toolsChallenge.dto.PagamentoResponse;
import br.com.sicredi.toolsChallenge.model.request.DescricaoRequest;
import br.com.sicredi.toolsChallenge.model.commons.FormaPagamento;
import br.com.sicredi.toolsChallenge.model.request.TransacaoRequest;
import br.com.sicredi.toolsChallenge.model.response.TransacaoEntity;
import br.com.sicredi.toolsChallenge.service.PagamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static br.com.sicredi.toolsChallenge.model.commons.enums.PaymentType.AVISTA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PagamentoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PagamentoService pagamentoService;

    @InjectMocks
    private PagamentoController pagamentoController;

    private ObjectMapper objectMapper;

    private PagamentoRequest pagamentoRequest;
    private PagamentoResponse pagamentoResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pagamentoController).build();
        objectMapper = new ObjectMapper();

        DescricaoRequest descricaoRequest = DescricaoRequest.builder()
                .valor("100.00")
                .dataHora("18/09/2025 12:00:00")
                .estabelecimento("Petshop Mundo Cão")
                .build();

        FormaPagamento formaPagamento = FormaPagamento.builder()
                .tipo(AVISTA)
                .parcelas("1")
                .build();

        TransacaoRequest transacaoRequest = new TransacaoRequest("123", "456", descricaoRequest, formaPagamento);
        TransacaoEntity transacaoEntity = new TransacaoEntity(transacaoRequest);

        pagamentoRequest = new PagamentoRequest(transacaoRequest);
        pagamentoResponse = new PagamentoResponse(transacaoEntity);
    }

    @Test
    void shouldCreatePagamentoSuccessfully() throws Exception {
        when(pagamentoService.pay(any(PagamentoRequest.class))).thenReturn(pagamentoResponse);

        mockMvc.perform(post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pagamentoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transacaoEntity.id").value("456"))
                .andExpect(jsonPath("$.transacaoEntity.formaPagamento.tipo").value("AVISTA"))
                .andExpect(jsonPath("$.transacaoEntity.descricao.valor").value("100.00"));
    }
}
