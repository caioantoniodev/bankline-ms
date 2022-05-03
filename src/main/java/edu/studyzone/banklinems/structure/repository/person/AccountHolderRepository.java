package edu.studyzone.banklinems.structure.repository.person;

import edu.studyzone.banklinems.domain.person.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer> {
}
