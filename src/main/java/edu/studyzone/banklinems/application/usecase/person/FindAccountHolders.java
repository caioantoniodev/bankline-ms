package edu.studyzone.banklinems.application.usecase.person;

import edu.studyzone.banklinems.application.dto.person.AccountHolderResponse;
import edu.studyzone.banklinems.application.dto.person.BankAccountResponse;
import edu.studyzone.banklinems.application.usecase.query.CreateQuery;
import edu.studyzone.banklinems.infra.repository.person.AccountHolderRepository;
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

        var offsetLimitPageable = createQuery.getOffsetLimitPageable(offset > 0 ? offset - 1 : 0, limit);

        var accountHolders = accountHolderRepository.findAll(offsetLimitPageable);

        return accountHolders.stream()
                .map(accountHolder -> {

                    var bankAccountResponse = new BankAccountResponse();

                    bankAccountResponse.setAccountNumber(accountHolder.getBankAccount().getAccountNumber());
                    bankAccountResponse.setBalance(accountHolder.getBankAccount().getBalance());

                    var accountHolderResponse = new AccountHolderResponse();

                    accountHolderResponse.setId(accountHolder.getId());
                    accountHolderResponse.setName(accountHolder.getName());
                    accountHolderResponse.setCpf(accountHolder.getCpf());
                    accountHolderResponse.setBankAccount(bankAccountResponse);

                    return accountHolderResponse;
                }).collect(Collectors.toList());
    }
}