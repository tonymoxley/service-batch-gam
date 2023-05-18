package org.arkham.examples.batch.gam.repository;

import org.arkham.examples.batch.gam.model.document.GamificationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface IGamificationRepository extends MongoRepository<GamificationEntity, String> {
}
