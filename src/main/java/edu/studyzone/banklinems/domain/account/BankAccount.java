package edu.studyzone.banklinems.domain.account;

import edu.studyzone.banklinems.infra.exception.InsufficientBalanceException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Date;

@Embeddable
public class BankAccount {

    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_balance")
    private BigDecimal balance;

    public Long getAccountNumber() {
        return accountNumber;
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
        private Long accountNumber;
        private BigDecimal balance;

        public BankAccountBuilder withNumber() {
            this.accountNumber = new Date().getTime();
            return this;
        }

        public BankAccountBuilder withBalance() {
            this.balance = new BigDecimal("0.0");
            return this;
        }

        public BankAccount build() {
            BankAccount bankAccount = new BankAccount();
            bankAccount.balance = this.balance;
            bankAccount.accountNumber = this.accountNumber;
            return bankAccount;
        }
    }
}
