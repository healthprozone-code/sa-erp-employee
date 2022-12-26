package com.sa.erp.repositories;

import com.sa.erp.entities.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {


    @Query("{ 'firstName' : ?0, 'lastName': ?1}")
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);

}
