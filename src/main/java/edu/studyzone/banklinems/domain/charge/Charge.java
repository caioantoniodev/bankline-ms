package edu.studyzone.banklinems.domain.charge;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tab_charge")
public class Charge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "charge_date_time")
    private LocalDateTime chargeDateTime;

    private String description;
    private BigDecimal value;

    @Column(name = "charge_type")
    @Enumerated(EnumType.STRING)
    private ChargeType chargeType;

    @Column(name = "bank_account_id")
    private Integer bankAccountId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getChargeDateTime() {
        return chargeDateTime;
    }

    public void setChargeDateTime(LocalDateTime chargeDateTime) {
        this.chargeDateTime = chargeDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public ChargeType getChargeType() {
        return chargeType;
    }

    public void setChargeType(ChargeType chargeType) {
        this.chargeType = chargeType;
    }

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Integer bankAccountId) {
        this.bankAccountId = bankAccountId;
    }
}
