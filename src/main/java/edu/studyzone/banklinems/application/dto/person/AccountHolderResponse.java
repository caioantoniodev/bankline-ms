package edu.studyzone.banklinems.application.dto.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

public class AccountHolderResponse {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("bank_account")
    private BankAccountResponse bankAccount;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBankAccount(BankAccountResponse bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (StringUtils.isNotBlank(cpf)) {

            cpf = StringUtils.leftPad(cpf, 11, '0');

            this.cpf = String.format("%s.%s.%s/%s", cpf.substring(0, 3), cpf.substring(3, 6),
                    cpf.substring(6, 9), cpf.substring(9, 11));
        }
    }
}

