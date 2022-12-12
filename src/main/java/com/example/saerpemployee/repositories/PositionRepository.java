package com.example.saerpemployee.repositories;

import com.example.saerpemployee.documents.Position;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PositionRepository extends MongoRepository<Position, String> {
}
