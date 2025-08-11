package net.personalProject.journalApp.Repository;

import net.personalProject.journalApp.entity.ConfigAppCache;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigAppCacheRepository extends MongoRepository<ConfigAppCache, String> {
}
