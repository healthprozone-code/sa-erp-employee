package com.example.saerpemployee.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "employee")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private LocalDate birthday;
    private String githubUser;
    private LocalDate startingDate;
    private boolean enable;
    @DBRef
    private Position position;

}
