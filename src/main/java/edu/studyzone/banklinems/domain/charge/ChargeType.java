package edu.studyzone.banklinems.domain.charge;

import edu.studyzone.banklinems.infra.exception.ChargeTypeNotFoundException;

import java.util.Arrays;

public enum ChargeType {
    REVENUE("revenue"),
    EXPENSE("expense");

    private final String chargeType;

    ChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getChargeType() {
        return chargeType;
    }

    public static ChargeType fromValue(String chargeType) {
        return Arrays.stream(ChargeType.values())
                .filter(type -> type.name().equalsIgnoreCase(chargeType))
                .findFirst()
                .orElseThrow(ChargeTypeNotFoundException::new);
    }
}
