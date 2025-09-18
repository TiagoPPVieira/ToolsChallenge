package br.com.sicredi.toolsChallenge.exception.external;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

public class RequestExceptionHandler {
    @ExceptionHandler(Owner4xxException.class)
    public <E extends Owner4xxException> ResponseEntity<Map<String, Object>> handleOwner4xxException(
            E ex,
            HttpServletRequest request) {

        Map<String, Object> body = Map.of(
                "timestamp", LocalDateTime.now(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "error", "Bad Request",
                "message", ex.getMessage(),
                "path", request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(body);
    }
}
