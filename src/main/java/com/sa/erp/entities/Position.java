package com.sa.erp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "position")
@Data
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
