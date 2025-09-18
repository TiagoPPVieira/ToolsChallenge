package br.com.sicredi.toolsChallenge.controller;

import br.com.sicredi.toolsChallenge.model.request.DescricaoRequest;
import br.com.sicredi.toolsChallenge.model.commons.FormaPagamento;
import br.com.sicredi.toolsChallenge.model.request.TransacaoRequest;
import br.com.sicredi.toolsChallenge.model.response.TransacaoEntity;
import br.com.sicredi.toolsChallenge.service.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static br.com.sicredi.toolsChallenge.model.commons.enums.PaymentType.AVISTA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ConsultaControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PagamentoService pagamentoService;

    @InjectMocks
    private ConsultaController consultaController;
    private TransacaoRequest transacaoRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(consultaController).build();

        DescricaoRequest descricaoRequest = DescricaoRequest.builder()
                .valor("100.00")
                .dataHora("17/09/2025 18:30:00")
                .estabelecimento("Petshop Mundo Cão")
                .build();
        FormaPagamento formaPagamento = FormaPagamento.builder()
                .tipo(AVISTA)
                .parcelas("1")
                .build();
        transacaoRequest = new TransacaoRequest("123", "", descricaoRequest, formaPagamento);
    }

    @Test
    void shouldReturnTransacaoById() throws Exception {
        TransacaoEntity transacaoEntity = new TransacaoEntity(transacaoRequest);
        transacaoEntity.setId("123");

        when(pagamentoService.findById("123")).thenReturn(Optional.of(transacaoEntity));

        mockMvc.perform(get("/api/consulta/123")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"));

        verify(pagamentoService, times(1)).findById("123");
    }

    @Test
    void shouldReturnNotFoundIfTransacaoDoesNotExist() throws Exception {
        when(pagamentoService.findById("999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/consulta/999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(pagamentoService, times(1)).findById("999");
    }

    @Test
    void shouldReturnAllTransacoes() throws Exception {
        TransacaoEntity t1 = new TransacaoEntity(transacaoRequest);
        t1.setId("1");
        TransacaoEntity t2 = new TransacaoEntity(transacaoRequest);
        t2.setId("2");

        when(pagamentoService.findAll()).thenReturn(List.of(t1, t2));

        mockMvc.perform(get("/api/consulta")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(pagamentoService, times(1)).findAll();
    }
}
