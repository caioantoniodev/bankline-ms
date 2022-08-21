package edu.studyzone.banklinems.infra.exception;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException() {
        super("Insufficient balance.");
    }
}
