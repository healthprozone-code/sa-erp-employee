package com.sa.erp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "position")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repo {

    @Id
    private String id;
    private String name;
    private String url;
    private boolean enable;
    @DBRef
    private List<Employee> employeeList;

}
