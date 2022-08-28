package edu.studyzone.banklinems.application.dto.charge;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ChargeRequest {

    @JsonProperty("description")
    @NotBlank(message = "Should be not blank.")
    private String description;
    @JsonProperty("value")
    @DecimalMin(value = "1.0", message = "Should be greater than 0.0.")
    private BigDecimal value;
    @JsonProperty("charge_type")
    @NotBlank(message = "Should be 'revenue' or 'expense'.")
    private String chargeType;

    @JsonProperty("account_number")
    @Positive(message = "Should be valid number.")
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
