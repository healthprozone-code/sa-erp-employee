package com.example.saerpemployee.repositories;

import com.example.saerpemployee.entities.Repo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepoRepository extends MongoRepository<Repo, String> {
}
