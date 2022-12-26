package com.sa.erp.repositories;

import com.sa.erp.entities.Repo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepoRepository extends MongoRepository<Repo, String> {
}
