package org.arkham.examples.batch.gam.model.common;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder(toBuilder = true)
public class GamificationTwo extends Gamification implements Serializable {

    private String cardNumber;

    private final String typeCard;

    private final String displayNumber;

    private final String status;

    private final String customerKey;

}
