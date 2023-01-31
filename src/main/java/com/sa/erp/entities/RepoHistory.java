package com.sa.erp.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "repo_history")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RepoHistory {

    @Id
    private String id;
    private Repo repo;
    private LocalDate createDate;

}
