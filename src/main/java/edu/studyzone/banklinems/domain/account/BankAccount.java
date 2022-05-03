package edu.studyzone.banklinems.domain.account;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.math.BigDecimal;


@Table(name = "tab_bank_account")
@Embeddable
public class BankAccount {

    @Column(name = "bank_number")
    private Long number;

    @Column(name = "bank_balance")
    private BigDecimal balance;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
