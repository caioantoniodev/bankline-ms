package edu.studyzone.banklinems.application.usecase.person;

import edu.studyzone.banklinems.application.dto.person.AccountHolderResponse;
import edu.studyzone.banklinems.application.usecase.query.CreateQuery;
import edu.studyzone.banklinems.structure.repository.person.AccountHolderRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindAccountHolders {

    private final AccountHolderRepository accountHolderRepository;
    private final CreateQuery createQuery;

    public FindAccountHolders(AccountHolderRepository accountHolderRepository, CreateQuery createQuery) {
        this.accountHolderRepository = accountHolderRepository;
        this.createQuery = createQuery;
    }

    public List<AccountHolderResponse> findAll(Integer offset, Integer limit) {

        var offsetLimitPageable = createQuery.getOffsetLimitPageable(offset, limit);

        var accountHolders = accountHolderRepository.findAll(offsetLimitPageable);

        return accountHolders.stream()
                .map(accountHolder -> {
                    var accountHolderResponse = new AccountHolderResponse();

                    accountHolderResponse.setId(accountHolder.getId());
                    accountHolderResponse.setName(accountHolder.getName());
                    accountHolderResponse.setCpf(accountHolder.getCpf());
                    accountHolderResponse.setBankAccount(accountHolder.getBankAccount());

                    return accountHolderResponse;
                }).collect(Collectors.toList());
    }
}