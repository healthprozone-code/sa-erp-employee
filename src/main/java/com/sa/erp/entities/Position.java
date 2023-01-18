package com.sa.erp.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "position")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Position {

    @Id
    private String id;
    private String name;
    private boolean enable;
    private LocalDate createDate;
    private LocalDate updateDate;

}
