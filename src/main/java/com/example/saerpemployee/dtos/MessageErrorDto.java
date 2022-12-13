package com.example.saerpemployee.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageErrorDto implements Serializable {

    @Schema(
            description = "http code of the error",
            example = "204")
    private String errorCode;
    @Schema(
            description = "description of the error",
            example = "Person no found")
    private String errorDescription;

}
