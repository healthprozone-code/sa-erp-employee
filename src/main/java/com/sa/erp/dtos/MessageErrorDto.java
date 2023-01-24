package com.sa.erp.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
