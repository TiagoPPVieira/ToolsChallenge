package br.com.sicredi.toolsChallenge.service;

import br.com.sicredi.toolsChallenge.dto.PagamentoRequest;
import br.com.sicredi.toolsChallenge.dto.PagamentoResponse;
import br.com.sicredi.toolsChallenge.model.commons.FormaPagamento;
import br.com.sicredi.toolsChallenge.model.request.DescricaoRequest;
import br.com.sicredi.toolsChallenge.model.request.TransacaoRequest;
import br.com.sicredi.toolsChallenge.repository.TransactionRepository;
import br.com.sicredi.toolsChallenge.strategy.AVistaPagamentoImpl;
import br.com.sicredi.toolsChallenge.strategy.ParcEmissorPagamentoImpl;
import br.com.sicredi.toolsChallenge.strategy.ParcLojaPagamentoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static br.com.sicredi.toolsChallenge.model.commons.enums.PaymentType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PagamentoServiceTest {
    @InjectMocks
    private PagamentoService pagamentoService;
    @Mock
    private TransactionRepository repository;
    @Mock
    private ParcLojaPagamentoImpl parcLojaPagamento;
    @Mock
    private ParcEmissorPagamentoImpl parcEmissorPagamento;
    @Mock
    private AVistaPagamentoImpl avistaPagamento;

    @BeforeEach
    void setUp() {
        pagamentoService = new PagamentoService(repository, List.of(parcLojaPagamento, parcEmissorPagamento, avistaPagamento));
    }
    private DescricaoRequest descricaoRequestOk;
    private FormaPagamento formaPagamentoAVista;
    private FormaPagamento formaPagamentoParcLj;
    private FormaPagamento formaPagamentoParcEmissor;

    @BeforeEach
    public void buildTransaction(){
        descricaoRequestOk = DescricaoRequest.builder()
                .valor("100.00")
                .dataHora(LocalDateTime.parse("17/09/2025 18:30:00", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")))
                .estabelecimento("Petshop Mundo Cão")
                .build();
        formaPagamentoAVista = FormaPagamento.builder()
                .tipo(AVISTA)
                .parcelas("1")
                .build();
        formaPagamentoParcLj = FormaPagamento.builder()
                .tipo(PARCELADO_LOJA)
                .parcelas("1")
                .build();
         formaPagamentoParcEmissor = FormaPagamento.builder()
                .tipo(PARCELADO_EMISSOR)
                .parcelas("1")
                .build();
    }

    @Test
    void shouldProcessAVistaPaymentSuccessfully() {
        TransacaoRequest transacaoRequest = new TransacaoRequest("123", "456", descricaoRequestOk, formaPagamentoAVista);
        PagamentoRequest request = new PagamentoRequest(transacaoRequest);

        when(avistaPagamento.supports(AVISTA)).thenReturn(true);
        when(repository.existsById("456")).thenReturn(false);

        PagamentoResponse response = pagamentoService.pay(request);

        assertNotNull(response);
        assertEquals("AVISTA", response.transacaoEntity().getFormaPagamento().getTipo().getValue());
        assertEquals(100.0, Double.valueOf(response.transacaoEntity().getDescricao().getValor()));
    }

    @Test
    void shouldProcessParcLjPaymentSuccessfully() {
        TransacaoRequest transacaoRequest = new TransacaoRequest("123", "456", descricaoRequestOk, formaPagamentoParcLj);

        when(parcLojaPagamento.supports(PARCELADO_LOJA)).thenReturn(true);
        when(repository.existsById("456")).thenReturn(false);

        PagamentoRequest request = new PagamentoRequest(transacaoRequest);

        PagamentoResponse response = pagamentoService.pay(request);

        assertNotNull(response);
        assertEquals("PARCELADO LOJA", response.transacaoEntity().getFormaPagamento().getTipo().getValue());
        assertEquals(100.0, Double.valueOf(response.transacaoEntity().getDescricao().getValor()));
    }

    @Test
    void shouldProcessParcEmissorPaymentSuccessfully() {
        TransacaoRequest transacaoRequest = new TransacaoRequest("123", "456", descricaoRequestOk, formaPagamentoParcEmissor);

        when(parcEmissorPagamento.supports(PARCELADO_EMISSOR)).thenReturn(true);
        when(repository.existsById("456")).thenReturn(false);

        PagamentoRequest request = new PagamentoRequest(transacaoRequest);

        PagamentoResponse response = pagamentoService.pay(request);

        assertNotNull(response);
        assertEquals("PARCELADO EMISSOR", response.transacaoEntity().getFormaPagamento().getTipo().getValue());
        assertEquals(100.0, Double.valueOf(response.transacaoEntity().getDescricao().getValor()));
    }
}
