package br.com.sicredi.toolsChallenge.exception.external;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestExceptionHandlerTest {

    private RequestExceptionHandler handler;
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        handler = new RequestExceptionHandler();
        request = mock(HttpServletRequest.class);
    }

    @Test
    void shouldHandleOwner4xxException() {
        Owner4xxException exception = new Owner4xxException("Erro de teste");
        when(request.getRequestURI()).thenReturn("/api/payments");

        ResponseEntity<Map<String, Object>> response = handler.handleOwner4xxException(exception, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("message")).isEqualTo("Erro de teste");
        assertThat(response.getBody().get("path")).isEqualTo("/api/payments");
        assertThat(response.getBody().get("error")).isEqualTo("Bad Request");
        assertThat(response.getBody().get("timestamp")).isNotNull();
    }
    @Test
    void shouldHandleMethodArgumentNotValidException() {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("Erro de teste");
        when(request.getRequestURI()).thenReturn("/api/payments");

        ResponseEntity<Map<String, Object>> response = handler.handleValidationExceptions(exception, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("message")).isEqualTo("Erro de teste");
        assertThat(response.getBody().get("path")).isEqualTo("/api/payments");
        assertThat(response.getBody().get("error")).isEqualTo("Bad Request");
        assertThat(response.getBody().get("timestamp")).isNotNull();
    }
}
