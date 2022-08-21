package edu.studyzone.banklinems.domain.account;

import edu.studyzone.banklinems.infra.exception.InsufficientBalanceException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;


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

    public BigDecimal getBalance() {
        return balance;
    }

    public void updateBalance(BigDecimal value) {
        BigDecimal balanceValue = this.balance.add(value);

        if (balanceValue.signum() == -1) throw new InsufficientBalanceException();

        this.balance = balanceValue;
    }

    public static BankAccountBuilder builder() {
        return new BankAccountBuilder();
    }

    public static final class BankAccountBuilder {
        private Long number;
        private BigDecimal balance;

        public BankAccountBuilder withNumber() {
            this.number = new Date().getTime();
            return this;
        }

        public BankAccountBuilder withBalance() {
            this.balance = new BigDecimal("0.0");
            return this;
        }

        public BankAccount build() {
            BankAccount bankAccount = new BankAccount();
            bankAccount.balance = this.balance;
            bankAccount.number = this.number;
            return bankAccount;
        }
    }
}
