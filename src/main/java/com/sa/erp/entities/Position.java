package com.sa.erp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "position")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    @Id
    private String id;
    private String name;
    private boolean enable;

}
