package org.arkham.examples.batch.gam.model.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@Document("gamification")
public class GamificationEntity {

    @Id
    @Field("_id")
    private String id;

    @Field("data")
    private GamificationCard card;

    @Field("contract_key")
    private String contractKey;

    @Field("hiring_date")
    private LocalDate hiringDate;

    @Field("notifications")
    private boolean notifications;

}
