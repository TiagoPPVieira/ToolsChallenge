package br.com.sicredi.toolsChallenge.model.commons;

import br.com.sicredi.toolsChallenge.util.AuthorizationCodeGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorizationCodeGeneratorTest {
    @Test
    void shouldGenerateNineDigitCode() {
        String code = AuthorizationCodeGenerator.getCodigoAutorizacao();
        assertEquals(9, code.length(), "O código deve ter 9 dígitos");
        assertTrue(code.matches("\\d{9}"), "O código deve conter apenas números");
    }

    @Test
    void shouldGenerateCodesWithinExpectedRange() {
        for (int i = 0; i < 1000; i++) {
            String codeStr = AuthorizationCodeGenerator.getCodigoAutorizacao();
            int code = Integer.parseInt(codeStr);
            assertTrue(code >= 100_000_000 && code <= 999_999_999,
                    "O código deve estar entre 100.000.000 e 999.999.999");
        }
    }

    @Test
    void shouldGenerateUniqueCodes() {
        List<String> codes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            codes.add(AuthorizationCodeGenerator.getCodigoAutorizacao());
        }
        assertEquals(100, codes.size(), "Todos os códigos gerados devem ser únicos");
    }
}
