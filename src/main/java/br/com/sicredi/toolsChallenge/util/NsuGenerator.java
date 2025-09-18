package br.com.sicredi.toolsChallenge.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class NsuGenerator {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final AtomicInteger SEQUENCE = new AtomicInteger(0);

    public static String getNsu() {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(FORMATTER);

        int seq = SEQUENCE.updateAndGet(current -> (current >= 9999) ? 1 : current + 1);

        return timestamp + String.format("%04d", seq);
    }
}
