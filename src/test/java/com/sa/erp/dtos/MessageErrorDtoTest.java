package com.sa.erp.dtos;

import com.sa.erp.entities.Position;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MessageErrorDtoTest {

    @Test
    public void messageErrorDtoNoArgsConstructor(){
        MessageErrorDto aux = new MessageErrorDto();
        assertNull(aux.getErrorCode());
        assertNull(aux.getErrorDescription());
    }

    @Test
    public void messageErrorDtoAllArgsConstructor(){
        MessageErrorDto aux = new MessageErrorDto("204","Object no found");
        assertNotNull(aux.getErrorCode());
        assertNotNull(aux.getErrorDescription());
        assertEquals(aux.getErrorCode(), "204");
        assertEquals(aux.getErrorDescription(), "Object no found");
    }

    @Test
    public void messageErrorDtoGetsAndSets(){
        MessageErrorDto aux = new MessageErrorDto();
        assertNull(aux.getErrorCode());
        assertNull(aux.getErrorDescription());
        aux.setErrorCode("204");
        aux.setErrorDescription("Object no found");
        assertEquals(aux.getErrorCode(), "204");
        assertEquals(aux.getErrorDescription(), "Object no found");
    }

    @Test
    public void messageErrorDtoToString(){
        MessageErrorDto aux = new MessageErrorDto("204","Object no found");;
        assertEquals(aux.toString(), "MessageErrorDto(errorCode=204, errorDescription=Object no found)");
    }

}
