package edu.studyzone.banklinems.domain.person;

import edu.studyzone.banklinems.domain.account.BankAccount;

import javax.persistence.*;

@Entity
@Table(name = "tab_account_holder")
public class AccountHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 11)
    private String cpf;

    @Column(length = 60)
    private String name;

    @Embedded
    private BankAccount bankAccount;

    public Integer getId() {
        return id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String cpf;
        private String name;
        private BankAccount bankAccount;

        public Builder withCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withBankAccount(BankAccount bankAccount) {
            this.bankAccount = bankAccount;
            return this;
        }

        public AccountHolder build() {
            AccountHolder accountHolder = new AccountHolder();
            accountHolder.name = this.name;
            accountHolder.cpf = this.cpf;
            accountHolder.bankAccount = this.bankAccount;
            return accountHolder;
        }
    }
}
