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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
