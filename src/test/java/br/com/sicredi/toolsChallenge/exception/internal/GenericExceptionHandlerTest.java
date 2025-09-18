package br.com.sicredi.toolsChallenge.exception.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class GenericExceptionHandlerTest {

    private GenericExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GenericExceptionHandler();
    }

    @Test
    void shouldHandleGenericException() {
        Exception exception = new Exception("Erro inesperado");

        ResponseEntity<String> response = handler.handleGeneric(exception);

        assertThat(response.getStatusCodeValue()).isEqualTo(500);
        assertThat(response.getBody()).isEqualTo("Unexpected error: Erro inesperado");
    }
}
