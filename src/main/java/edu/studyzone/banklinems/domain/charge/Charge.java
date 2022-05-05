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

    public Integer getBankAccountId() {
        return bankAccountId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private LocalDateTime chargeDateTime;
        private String description;
        private BigDecimal value;
        private ChargeType chargeType;
        private Integer bankAccountId;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withChargeDateTime(LocalDateTime chargeDateTime) {
            this.chargeDateTime = chargeDateTime;
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

        public Builder withBankAccountId(Integer bankAccountId) {
            this.bankAccountId = bankAccountId;
            return this;
        }

        public Charge build() {
            Charge charge = new Charge();
            charge.id = this.id;
            charge.chargeType = this.chargeType;
            charge.value = this.value;
            charge.chargeDateTime = this.chargeDateTime;
            charge.description = this.description;
            charge.bankAccountId = this.bankAccountId;
            return charge;
        }
    }
}
