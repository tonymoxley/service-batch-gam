package org.arkham.examples.batch.gam.model.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Data
public class GamificationCard {

    @Field("card_number")
    private String cardNumber;

    @Field("customer_key")
    private String customerKey;

    @Field("due_date")
    private String dueDate;

    @Field("lower_limit")
    private BigDecimal lowerLimit;

    @Field("potential_limit")
    private BigDecimal potentialLimit;

    @Field("minimum_amount")
    private BigDecimal minimumAmount;

    @Field("amount_payment")
    private BigDecimal amountPayment;

    @Field("display_number")
    private final String displayNumber;

    @Field("type_card")
    private final String typeCard;

    @Field("status")
    private String status;

}
