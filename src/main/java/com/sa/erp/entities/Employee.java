package com.sa.erp.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

    @Id
    private String id;
    @Field("first_name")
    private String firstName;
    @Field("last_name")
    private String lastName;
    private String phone;
    private String email;
    private LocalDate birthday;
    @Field("github_user")
    private String githubUser;
    @Field("starting_date")
    private LocalDate startingDate;
    private boolean enable;
    private List<LocalDate> vacationDays;
    private boolean enableVacation;
    private Position position;
    private LocalDate createDate;
    private LocalDate updateDate;

}
