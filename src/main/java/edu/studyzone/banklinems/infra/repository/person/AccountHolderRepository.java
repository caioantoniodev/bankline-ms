package edu.studyzone.banklinems.infra.repository.person;

import edu.studyzone.banklinems.domain.person.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {

    Optional<AccountHolder> findAccountHolderByBankAccountAccountNumber(Long bankAccount);
}
