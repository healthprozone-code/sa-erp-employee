package com.sa.erp.repositories;

import com.sa.erp.entities.Employee;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {


    @Query("{ 'firstName' : ?0, 'lastName': ?1}")
    Optional<Employee> findByFirstNameAndLastName(String firstName, String lastName);
    @Aggregation(pipeline = {"{\n" +
            "    $project: {\n" +
            "      first_name: 1,\n" +
            "      last_name: 1,\n" +
            "      phone: 1,\n" +
            "      email: 1,\n" +
            "      birthday: 1,\n" +
            "      githubUser: 1,\n" +
            "      startingDate: 1,\n" +
            "      enable: 1,\n" +
            "      createDate: 1,\n" +
            "      updateDate: 1,\n" +
            "      todayDoY: { $dayOfYear: new Date() },\n" +
            "      bdDoy: { $dayOfYear: \"$birthday\" }\n" +
            "    }\n" +
            "  }",
            "{\n" +
                    "    $match: {\n" +
                    "      $expr: {\n" +
                    "        $gt: [\"%todayDoY\", \"$bdDoy\"],\n" +
                    "      }\n" +
                    "    }\n" +
                    "  }",
            "{\n" +
                    "    $sort: {\n" +
                    "      \"bdDoy\": 1,\n" +
                    "    }\n" +
                    "  }",
            "{\n" +
                    "    $limit: 1,\n" +
                    "  }"})
    Optional<Employee> GetNextBirthdayEmployee2();

}
