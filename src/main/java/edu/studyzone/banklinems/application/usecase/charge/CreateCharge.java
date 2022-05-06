package edu.studyzone.banklinems.application.usecase.charge;

import edu.studyzone.banklinems.application.dto.charge.ChargeRequest;
import edu.studyzone.banklinems.application.dto.charge.ChargeResponse;
import edu.studyzone.banklinems.domain.charge.Charge;
import edu.studyzone.banklinems.domain.charge.ChargeType;
import edu.studyzone.banklinems.structure.repository.charge.ChargeRepository;
import edu.studyzone.banklinems.structure.repository.person.AccountHolderRepository;
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

        var transactionValue = buildTransactionValue(request.getChargeType(), request.getValue());

        var charge = Charge.builder()
                .withValue(transactionValue)
                .withDescription(request.getDescription())
                .withChargeType(request.getChargeType())
                .withChargeDateTime()
                .withAccountHolderId(request.getAccountHolderId())
                .build();


        var accountHolderOptional = accountHolderRepository
                .findById(request.getAccountHolderId());

        if (accountHolderOptional.isEmpty())
            throw new RuntimeException("Account holder not exists");

        var accountHolder = accountHolderOptional.get();

        accountHolder.getBankAccount().updateBalance(transactionValue);

        accountHolderRepository.save(accountHolder);

        chargeRepository.save(charge);

        var chargeResponse = new ChargeResponse();
        chargeResponse.setChargeType(charge.getChargeType());
        chargeResponse.setChargeDateTime(charge.getChargeDateTime());
        chargeResponse.setDescription(charge.getDescription());
        chargeResponse.setValue(charge.getValue());
        chargeResponse.setAccountHolderId(charge.getAccountHolderId());

        return chargeResponse;
    }
    
    private BigDecimal buildTransactionValue(ChargeType chargeType, BigDecimal value) {
        return chargeType.equals(ChargeType.REVENUE) ? value : value.multiply(BigDecimal.valueOf(-1));
    }
}
