package edu.studyzone.banklinems.application.dto.charge;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.studyzone.banklinems.domain.charge.ChargeType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ChargeResponse {

    @JsonProperty("description")
    private String description;
    @JsonProperty("value")
    private BigDecimal value;
    @JsonProperty("charge_date_time")
    private LocalDateTime chargeDateTime;
    @JsonProperty("charge_type")
    private ChargeType chargeType;
    @JsonProperty("account_holder_id")
    private Integer accountHolderId;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setChargeDateTime(LocalDateTime chargeDateTime) {
        this.chargeDateTime = chargeDateTime;
    }

    public void setChargeType(ChargeType chargeType) {
        this.chargeType = chargeType;
    }

    public void setAccountHolderId(Integer accountHolderId) {
        this.accountHolderId = accountHolderId;
    }
}
