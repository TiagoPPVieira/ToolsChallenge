package br.com.sicredi.toolsChallenge.model.commons;

import br.com.sicredi.toolsChallenge.model.commons.enums.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusTest {

    @Test
    void shouldReturnAutorizadoForPositiveValue() {
        assertEquals("AUTORIZADO", Status.getStatus("1000"));
        assertEquals("AUTORIZADO", Status.getStatus("1.000"));
        assertEquals("AUTORIZADO", Status.getStatus("0.01"));
    }

    @Test
    void shouldReturnNegadoForZeroOrNegativeValue() {
        assertEquals("NEGADO", Status.getStatus("0"));
        assertEquals("NEGADO", Status.getStatus("0.00"));
        assertEquals("NEGADO", Status.getStatus("-100"));
        assertEquals("NEGADO", Status.getStatus("-0.01"));
    }
}
