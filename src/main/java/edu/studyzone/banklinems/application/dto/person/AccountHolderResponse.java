package edu.studyzone.banklinems.application.dto.person;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

public class AccountHolderResponse {

    private Integer id;
    private String name;
    private String cpf;
    @JsonProperty("bank_number")
    private Long bankNumber;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBankNumber(Long bankNumber) {
        this.bankNumber = bankNumber;
    }

    public void setCpf(String cpf) {
        if (StringUtils.isNotBlank(cpf)) {

            cpf = StringUtils.leftPad(cpf, 11, '0');

            this.cpf = cpf.substring(0, 3)
                        .concat(".")
                        .concat(cpf.substring(3, 6))
                        .concat(".")
                        .concat(cpf.substring(6, 9))
                        .concat("/")
                        .concat(cpf.substring(9, 11));
        }
    }
}
