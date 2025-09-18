package br.com.sicredi.toolsChallenge.model.commons.enums;

public enum Status {
    AUTORIZADO, NEGADO;

    public static String getStatus(String valor) {
        double valorDouble = Double.parseDouble(valor.replaceAll("\\.", ""));
        return valorDouble > 0
                ? AUTORIZADO.name()
                : NEGADO.name();
    }
}
