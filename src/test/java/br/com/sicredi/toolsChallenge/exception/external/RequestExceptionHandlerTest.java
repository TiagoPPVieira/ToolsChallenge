package br.com.sicredi.toolsChallenge.exception.external;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        when(request.getRequestURI()).thenReturn("/api/test");

        ResponseEntity<Map<String, Object>> response = handler.handleOwner4xxException(exception, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().get("message")).isEqualTo("Erro de teste");
        assertThat(response.getBody().get("path")).isEqualTo("/api/test");
        assertThat(response.getBody().get("error")).isEqualTo("Bad Request");
        assertThat(response.getBody().get("timestamp")).isNotNull();
    }
}
