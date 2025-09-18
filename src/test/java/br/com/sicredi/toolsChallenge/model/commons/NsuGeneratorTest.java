package br.com.sicredi.toolsChallenge.model.commons;

import br.com.sicredi.toolsChallenge.util.NsuGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NsuGeneratorTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Test
    void shouldGenerateNsuWithCorrectFormat() {
        String nsu = NsuGenerator.getNsu();

        String timestampPart = nsu.substring(0, 14);
        String seqPart = nsu.substring(14);

        assertDoesNotThrow(() -> LocalDateTime.parse(timestampPart, FORMATTER));

        assertEquals(4, seqPart.length());
    }

    @Test
    void shouldIncrementSequence() {
        String nsu1 = NsuGenerator.getNsu();
        String nsu2 = NsuGenerator.getNsu();

        String seq1 = nsu1.substring(14);
        String seq2 = nsu2.substring(14);

        assertNotEquals(seq1, seq2);
        assertEquals(Integer.parseInt(seq2), (Integer.parseInt(seq1) % 9999) + 1);
    }

    @Test
    void shouldGenerateUniqueNsus() {
        List<String> nsus = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            nsus.add(NsuGenerator.getNsu());
        }

        assertEquals(100, nsus.size(), "NSUs devem ser únicos");
    }
}
