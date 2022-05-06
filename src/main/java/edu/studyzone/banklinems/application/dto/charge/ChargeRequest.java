package edu.studyzone.banklinems.application.dto.charge;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.studyzone.banklinems.domain.charge.ChargeType;

import java.math.BigDecimal;

public class ChargeRequest {

    @JsonProperty("description")
    private String description;
    @JsonProperty("value")
    private BigDecimal value;
    @JsonProperty("charge_type")
    private ChargeType chargeType;
    @JsonProperty("account_holder_id")
    private Integer accountHolderId;

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public ChargeType getChargeType() {
        return chargeType;
    }

    public Integer getAccountHolderId() {
        return accountHolderId;
    }
}
