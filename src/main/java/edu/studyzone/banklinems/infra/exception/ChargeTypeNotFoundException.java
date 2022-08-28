package edu.studyzone.banklinems.infra.exception;

public class ChargeTypeNotFoundException extends RuntimeException {

    public ChargeTypeNotFoundException() {
        super("Charge type not found.");
    }
}
