package edu.studyzone.banklinems.domain;

import edu.studyzone.banklinems.domain.account.BankAccount;
import edu.studyzone.banklinems.infra.exception.InsufficientBalanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BankAccountTest {

    BigDecimal negativeValue;
    BigDecimal zeroValue;
    BigDecimal positiveValue;

    @BeforeEach
    void setUp() {
        negativeValue = new BigDecimal("-20.0");
        zeroValue = new BigDecimal("0.0");
        positiveValue = new BigDecimal("20.0");
    }

    @Test
    void shouldReturnZeroAfterCreateBankAccount() {
        BankAccount bankAccount = BankAccount.builder().withBalance().build();

        Assertions.assertEquals(zeroValue, bankAccount.getBalance());
    }

    @Test
    void shouldReturnNumberAccountAfterCreateBankAccount() {
        BankAccount bankAccount = BankAccount.builder().withNumber().build();

        Assertions.assertNotNull(bankAccount.getNumber());
    }

    @Test
    void shouldThrowsInsufficientBalanceExceptionWhenExpenseIsGreaterThanBalance() {
        BankAccount bankAccount = BankAccount.builder().withBalance().build();

        Assertions.assertThrows(InsufficientBalanceException.class, () ->
                bankAccount.updateBalance(negativeValue), "Insufficient balance.");
        Assertions.assertEquals(zeroValue, bankAccount.getBalance());
    }

    @Test
    void shouldReturnTenAfterExpenseSuccessful() {
        BankAccount bankAccount = BankAccount.builder().withBalance().build();

        bankAccount.updateBalance(positiveValue);
        bankAccount.updateBalance(new BigDecimal("-10.0"));

        Assertions.assertEquals(new BigDecimal("10.0"), bankAccount.getBalance());
    }

    @Test
    void shouldReturnZeroAfterExpenseSuccessful() {
        BankAccount bankAccount = BankAccount.builder().withBalance().build();

        bankAccount.updateBalance(positiveValue);
        bankAccount.updateBalance(negativeValue);

        Assertions.assertEquals(zeroValue, bankAccount.getBalance());
    }
}
