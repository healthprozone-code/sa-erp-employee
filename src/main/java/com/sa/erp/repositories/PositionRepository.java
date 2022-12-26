package com.sa.erp.repositories;

import com.sa.erp.entities.Position;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PositionRepository extends MongoRepository<Position, String> {
}
