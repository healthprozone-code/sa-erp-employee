package com.sa.erp.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Repo {

    @Id
    private String id;
    private String name;
    private String url;
    private boolean enable;
    private List<Employee> employeeList;
    private LocalDate createDate;
    private LocalDate updateDate;

}
