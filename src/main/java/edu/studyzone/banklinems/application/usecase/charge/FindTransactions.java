package edu.studyzone.banklinems.application.usecase.charge;

import edu.studyzone.banklinems.application.dto.charge.ChargeResponse;
import edu.studyzone.banklinems.application.usecase.query.CreateQuery;
import edu.studyzone.banklinems.infra.repository.charge.ChargeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindTransactions {

    private final ChargeRepository chargeRepository;
    private final CreateQuery createQuery;

    public FindTransactions(ChargeRepository chargeRepository, CreateQuery createQuery) {
        this.chargeRepository = chargeRepository;
        this.createQuery = createQuery;
    }

    public List<ChargeResponse> findAll(Integer offset, Integer limit) {

        var offsetLimitPageable = createQuery.getOffsetLimitPageable(offset > 0 ? offset - 1 : 0, limit);

        var accountHolders = chargeRepository.findAll(offsetLimitPageable);

        return accountHolders.stream()
                .map(charge -> {
                    var chargeResponse = new ChargeResponse();

                    chargeResponse.setValue(charge.getValue());
                    chargeResponse.setAccountNumber(charge.getAccountNumber());
                    chargeResponse.setChargeType(charge.getChargeType());
                    chargeResponse.setDescription(charge.getDescription());
                    chargeResponse.setChargeDateTime(charge.getChargeDateTime());

                    return chargeResponse;
                }).collect(Collectors.toList());
    }
}
