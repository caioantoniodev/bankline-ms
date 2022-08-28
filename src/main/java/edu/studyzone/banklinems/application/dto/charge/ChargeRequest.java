package edu.studyzone.banklinems.application.dto.charge;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ChargeRequest {

    @JsonProperty("description")
    private String description;
    @JsonProperty("value")
    private BigDecimal value;
    @JsonProperty("charge_type")
    private String chargeType;
    @JsonProperty("account_number")
    private Long accountNumber;

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getChargeType() {
        return chargeType;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }
}
