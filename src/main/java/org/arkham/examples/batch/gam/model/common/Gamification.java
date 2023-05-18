package org.arkham.examples.batch.gam.model.common;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@SuperBuilder(toBuilder = true)
public class Gamification implements Serializable {

    private final String contractKey;

    private final LocalDate hiringDate;

    private final BigDecimal lowerLimit;

    private final BigDecimal potentialLimit;

    private final String dueDate;

    private final boolean notifications;

    private final BigDecimal minimumAmount;

    private final BigDecimal amountPayment;

}
