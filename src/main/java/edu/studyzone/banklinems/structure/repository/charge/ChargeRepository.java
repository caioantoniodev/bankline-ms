package edu.studyzone.banklinems.structure.repository.charge;

import edu.studyzone.banklinems.domain.charge.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Integer> {
}
