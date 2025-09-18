package br.com.sicredi.toolsChallenge.util;

import java.security.SecureRandom;

public class AuthorizationCodeGenerator {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int MIN_CODE = 100_000_000;
    private static final int RANGE = 900_000_000;

    public static String getCodigoAutorizacao() {
        int code = MIN_CODE + RANDOM.nextInt(RANGE);
        return String.valueOf(code);
    }
}
