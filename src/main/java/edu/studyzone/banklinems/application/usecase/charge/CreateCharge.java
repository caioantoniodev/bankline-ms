package edu.studyzone.banklinems.application.usecase.charge;

import edu.studyzone.banklinems.application.dto.charge.ChargeRequest;
import edu.studyzone.banklinems.application.dto.charge.ChargeResponse;
import edu.studyzone.banklinems.domain.charge.Charge;
import edu.studyzone.banklinems.domain.charge.ChargeType;
import edu.studyzone.banklinems.domain.person.AccountHolder;
import edu.studyzone.banklinems.infra.repository.charge.ChargeRepository;
import edu.studyzone.banklinems.infra.repository.person.AccountHolderRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class CreateCharge {

    private final ChargeRepository chargeRepository;
    private final AccountHolderRepository accountHolderRepository;

    public CreateCharge(ChargeRepository chargeRepository, AccountHolderRepository accountHolderRepository) {
        this.chargeRepository = chargeRepository;
        this.accountHolderRepository = accountHolderRepository;
    }

    @Transactional
    public ChargeResponse charge(ChargeRequest request) {
        var accountHolder = getAccountHolderByAccountNumber(request.getAccountNumber());

        var chargeType = ChargeType.fromValue(request.getChargeType());

        var transactionValue = buildTransactionValue(chargeType, request.getValue());

        var charge = Charge.builder()
                .withValue(transactionValue)
                .withDescription(request.getDescription())
                .withChargeType(chargeType)
                .withChargeDateTime()
                .withAccountNumber(request.getAccountNumber())
                .build();

        accountHolder.getBankAccount().updateBalance(transactionValue);

        accountHolderRepository.save(accountHolder);

        chargeRepository.save(charge);

        return buildChargeResponse(charge);
    }

    private AccountHolder getAccountHolderByAccountNumber(Long accountNumber) {
        var accountHolderOptional = accountHolderRepository
                .findAccountHolderByBankAccountAccountNumber(accountNumber);

        if (accountHolderOptional.isEmpty())
            throw new RuntimeException("Account holder not exists");

        return accountHolderOptional.get();
    }

    private BigDecimal buildTransactionValue(ChargeType chargeType, BigDecimal value) {
        return chargeType.equals(ChargeType.REVENUE) ? value : value.multiply(BigDecimal.valueOf(-1));
    }

    private ChargeResponse buildChargeResponse(Charge charge) {
        var chargeResponse = new ChargeResponse();
        chargeResponse.setChargeType(charge.getChargeType());
        chargeResponse.setChargeDateTime(charge.getChargeDateTime());
        chargeResponse.setDescription(charge.getDescription());
        chargeResponse.setValue(charge.getValue());
        chargeResponse.setAccountNumber(charge.getAccountNumber());
        return chargeResponse;
    }
}
