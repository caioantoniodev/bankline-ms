package edu.studyzone.banklinems.application.dto.charge;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.studyzone.banklinems.domain.charge.ChargeType;

import java.math.BigDecimal;

public class ChargeRequest {

    private String description;
    private BigDecimal value;

    @JsonProperty("charge_type")
    private ChargeType chargeType;

    @JsonProperty("bank_account_id")
    private Integer bankAccountId;

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public ChargeType getChargeType() {
        return chargeType;
    }

    public Integer getBankAccountId() {
        return bankAccountId;
    }
}
