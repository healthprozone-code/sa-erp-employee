package com.sa.erp.repositories;

import com.sa.erp.entities.EmployeeHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeHistoryRepository extends MongoRepository<EmployeeHistory, String> {
}
