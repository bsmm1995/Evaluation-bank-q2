package com.bp.cbe.domain.enums;

public enum VerificationCode {

    VERIFIED(604, "Verificado"), WAITING(605, "En espera"), APPROVED(606, "Aprobado");
    final int code;
    final String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    VerificationCode(int c, String des) {
        code = c;
        description = des;
    }
}
