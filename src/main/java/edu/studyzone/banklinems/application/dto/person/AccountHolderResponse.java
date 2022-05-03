package edu.studyzone.banklinems.application.dto.person;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountHolderResponse {
    private Integer id;
    private String name;
    @JsonProperty("bank_number")
    private Long bankNumber;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getBankNumber() {
        return bankNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private String name;
        private Long bankNumber;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withBankNumber(Long bankNumber) {
            this.bankNumber = bankNumber;
            return this;
        }

        public AccountHolderResponse build() {
            AccountHolderResponse accountHolderResponse = new AccountHolderResponse();
            accountHolderResponse.id = this.id;
            accountHolderResponse.name = this.name;
            accountHolderResponse.bankNumber = this.bankNumber;
            return accountHolderResponse;
        }
    }
}
