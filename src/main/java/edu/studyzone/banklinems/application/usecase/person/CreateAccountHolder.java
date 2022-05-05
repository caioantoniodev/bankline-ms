package edu.studyzone.banklinems.application.usecase.person;

import edu.studyzone.banklinems.application.dto.person.AccountHolderRequest;
import edu.studyzone.banklinems.application.dto.person.AccountHolderResponse;
import edu.studyzone.banklinems.domain.account.BankAccount;
import edu.studyzone.banklinems.domain.person.AccountHolder;
import edu.studyzone.banklinems.structure.repository.person.AccountHolderRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountHolder {

    private final AccountHolderRepository accountHolderRepository;

    public CreateAccountHolder(AccountHolderRepository accountHolderRepository) {
        this.accountHolderRepository = accountHolderRepository;
    }

    public AccountHolderResponse create(AccountHolderRequest request) {

        var bankAccount = BankAccount.builder()
                .withBalance()
                .withNumber()
                .build();

        var accountHolder = AccountHolder.builder()
                .withName(request.getName())
                .withCpf(request.getCpf())
                .withBankAccount(bankAccount)
                .build();

        accountHolderRepository.save(accountHolder);

        var accountHolderResponse = new AccountHolderResponse();

        accountHolderResponse.setId(accountHolder.getId());
        accountHolderResponse.setName(accountHolder.getName());
        accountHolderResponse.setBankNumber(bankAccount.getNumber());

        return accountHolderResponse;
    }
}
