package edu.studyzone.banklinems.application.usecase.charge;

import edu.studyzone.banklinems.application.dto.charge.ChargeRequest;
import edu.studyzone.banklinems.application.dto.charge.ChargeResponse;
import edu.studyzone.banklinems.domain.charge.Charge;
import edu.studyzone.banklinems.domain.charge.ChargeType;
import edu.studyzone.banklinems.structure.repository.charge.ChargeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class CreateCharge {

    private final ChargeRepository chargeRepository;

    public CreateCharge(ChargeRepository chargeRepository) {
        this.chargeRepository = chargeRepository;
    }

    @Transactional
    public ChargeResponse charge(ChargeRequest request) {

        var transactionValue = buildTransactionValue(request.getChargeType(), request.getValue());

        var charge = Charge.builder()
                .withValue(transactionValue)
                .withDescription(request.getDescription())
                .withChargeType(request.getChargeType())
                .withChargeDateTime()
                .withBankAccountId(request.getBankAccountId())
                .build();


        chargeRepository.save(charge);

        var chargeResponse = new ChargeResponse();
        chargeResponse.setChargeType(charge.getChargeType());
        chargeResponse.setChargeDateTime(charge.getChargeDateTime());
        chargeResponse.setDescription(charge.getDescription());
        chargeResponse.setValue(charge.getValue());
        chargeResponse.setBankAccountId(charge.getBankAccountId());

        return chargeResponse;
    }
    
    private BigDecimal buildTransactionValue(ChargeType chargeType, BigDecimal value) {
        return chargeType.equals(ChargeType.REVENUE) ? value : value.multiply(BigDecimal.valueOf(-1));
    }
}
