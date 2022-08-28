package edu.studyzone.banklinems.application.dto.person;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class BankAccountResponse {
    @JsonProperty("account_number")
    private Long accountNumber;

    @JsonProperty("account_balance")
    private BigDecimal balance;

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
