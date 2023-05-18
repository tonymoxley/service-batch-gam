package org.arkham.examples.batch.gam.proccessor;

import org.arkham.examples.batch.gam.model.common.Gamification;
import org.arkham.examples.batch.gam.model.common.GamificationTwo;
import org.arkham.examples.batch.gam.model.document.GamificationEntity;
import org.springframework.batch.item.ItemProcessor;

public class GamificationProcessor implements ItemProcessor<GamificationEntity, Gamification> {

    @Override
    public Gamification process(GamificationEntity gamification) throws Exception {
        //TO-DO map fields of query to model csv
        return GamificationTwo.builder()
                .contractKey(gamification.getContractKey())
                .amountPayment(gamification.getCard().getAmountPayment())
                .cardNumber(gamification.getCard().getCardNumber())
                .customerKey(gamification.getCard().getCustomerKey())
                .displayNumber(gamification.getCard().getDisplayNumber())
                .dueDate(gamification.getCard().getDueDate())
                .lowerLimit(gamification.getCard().getLowerLimit())
                .minimumAmount(gamification.getCard().getMinimumAmount())
                .status(gamification.getCard().getStatus())
                .typeCard(gamification.getCard().getTypeCard())
                .hiringDate(gamification.getHiringDate())
                .notifications(gamification.isNotifications())
                .build();
    }

}
