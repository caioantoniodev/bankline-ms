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

    @Column(name = "account_number")
    private Long accountNumber;

    public Integer getId() {
        return id;
    }

    public LocalDateTime getChargeDateTime() {
        return chargeDateTime;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public ChargeType getChargeType() {
        return chargeType;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private LocalDateTime chargeDateTime;
        private String description;
        private BigDecimal value;
        private ChargeType chargeType;
        private Long accountNumber;

        public Builder withChargeDateTime() {
            this.chargeDateTime = LocalDateTime.now();
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withValue(BigDecimal value) {
            this.value = value;
            return this;
        }

        public Builder withChargeType(ChargeType chargeType) {
            this.chargeType = chargeType;
            return this;
        }

        public Builder withAccountNumber(Long accountHolderId) {
            this.accountNumber = accountHolderId;
            return this;
        }

        public Charge build() {
            Charge charge = new Charge();
            charge.chargeType = this.chargeType;
            charge.value = this.value;
            charge.chargeDateTime = this.chargeDateTime;
            charge.description = this.description;
            charge.accountNumber = this.accountNumber;
            return charge;
        }
    }
}
