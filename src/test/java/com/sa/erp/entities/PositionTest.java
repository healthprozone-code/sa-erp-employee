package com.sa.erp.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PositionTest {

    @Test
    public void positionNoArgsConstructor(){
        Position aux = new Position();
        assertNull(aux.getId());
        assertNull(aux.getName());
        assertNull(aux.getUpdateDate());
        assertNull(aux.getCreateDate());
        assertFalse(aux.isEnable());
    }

    @Test
    public void positionAllArgsConstructor(){
        Position aux = new Position("id","name", true, LocalDate.now(), LocalDate.now());
        assertNotNull(aux.getId());
        assertNotNull(aux.getName());
        assertNotNull(aux.getUpdateDate());
        assertNotNull(aux.getCreateDate());
        assertNotNull(aux.isEnable());
        assertEquals(aux.getId(), "id");
        assertEquals(aux.getName(), "name");
        assertEquals(aux.getUpdateDate(), LocalDate.now());
        assertEquals(aux.getCreateDate(), LocalDate.now());
        assertEquals(aux.isEnable(), true);
    }

    @Test
    public void positionGetsAndSets(){
        Position aux = new Position();
        assertNull(aux.getId());
        assertNull(aux.getName());
        assertNull(aux.getUpdateDate());
        assertNull(aux.getCreateDate());
        assertFalse(aux.isEnable());
        aux.setId("id");
        aux.setName("name");
        aux.setUpdateDate(LocalDate.now());
        aux.setCreateDate(LocalDate.now());
        aux.setEnable(true);
        assertEquals(aux.getId(), "id");
        assertEquals(aux.getName(), "name");
        assertEquals(aux.getUpdateDate(), LocalDate.now());
        assertEquals(aux.getCreateDate(), LocalDate.now());
        assertEquals(aux.isEnable(), true);
    }

}
