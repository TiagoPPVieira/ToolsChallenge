package br.com.sicredi.toolsChallenge.model.commons.enums;

public enum PaymentType {
    AVISTA("AVISTA"),
    PARCELADO_LOJA("PARCELADO LOJA"),
    PARCELADO_EMISSOR("PARCELADO EMISSOR");
    private final String value;

    PaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
