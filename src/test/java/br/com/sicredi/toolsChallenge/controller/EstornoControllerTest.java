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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static br.com.sicredi.toolsChallenge.model.commons.enums.PaymentType.AVISTA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EstornoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PagamentoService pagamentoService;

    @InjectMocks
    private EstornoController estornoController;

    private TransacaoEntity transacaoEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(estornoController).build();

        DescricaoRequest descricaoRequest = DescricaoRequest.builder()
                .valor("100.00")
                .dataHora(LocalDateTime.parse("17/09/2025 18:30:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                .estabelecimento("Petshop Mundo Cão")
                .build();
        FormaPagamento formaPagamento = FormaPagamento.builder()
                .tipo(AVISTA)
                .parcelas("1")
                .build();
        TransacaoRequest transacaoRequest = new TransacaoRequest("123", "456", descricaoRequest, formaPagamento);
        transacaoEntity = new TransacaoEntity(transacaoRequest);
    }

    @Test
    void shouldReturnTransacaoWhenIdExists() throws Exception {
        when(pagamentoService.findAndDeleteById("456")).thenReturn(Optional.of(transacaoEntity));

        mockMvc.perform(get("/api/estorno/456")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("456"));
    }

    @Test
    void shouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        when(pagamentoService.findAndDeleteById("999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/estorno/999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
