package com.sa.erp.repositories;

import com.sa.erp.entities.RepoHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepoHistoryRepository extends MongoRepository<RepoHistory, String> {
}
