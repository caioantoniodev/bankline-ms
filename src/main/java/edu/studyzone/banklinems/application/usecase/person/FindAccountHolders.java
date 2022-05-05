package edu.studyzone.banklinems.application.usecase.person;

import edu.studyzone.banklinems.application.dto.person.AccountHolderResponse;
import edu.studyzone.banklinems.domain.query.OffsetLimitPageable;
import edu.studyzone.banklinems.structure.repository.person.AccountHolderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindAccountHolders {

    private final AccountHolderRepository accountHolderRepository;

    public FindAccountHolders(AccountHolderRepository accountHolderRepository) {
        this.accountHolderRepository = accountHolderRepository;
    }

    public List<AccountHolderResponse> findAll(Integer offset, Integer limit) {

        var offsetLimitPageable = getOffsetLimitPageable(offset, limit);

        var accountHolders = accountHolderRepository.findAll(offsetLimitPageable);

        return accountHolders.stream()
                .map(accountHolder -> {
                    var accountHolderResponse = new AccountHolderResponse();

                    accountHolderResponse.setId(accountHolder.getId());
                    accountHolderResponse.setName(accountHolder.getName());
                    accountHolderResponse.setBankNumber(accountHolder.getBankAccount().getNumber());

                    return accountHolderResponse;
                }).collect(Collectors.toList());
    }

    private OffsetLimitPageable getOffsetLimitPageable(Integer offset, Integer limit) {

        if (offset != null && limit == null)
            return new OffsetLimitPageable(offset, 50);

        if (limit != null && offset == null)
            return new OffsetLimitPageable(0, limit);

        if (limit != null && offset != null)
            return new OffsetLimitPageable(offset, limit);

        return new OffsetLimitPageable(0, 50);
    }
}