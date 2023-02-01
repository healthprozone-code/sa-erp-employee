package com.sa.erp.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@EnabledIfSystemProperty(named = "spring.profiles.using", matches = "DEV")
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

    @Test
    public void positionToString(){
        Position aux = new Position("id","name", true, LocalDate.of(2023, Month.JANUARY, 18), LocalDate.of(2023, Month.JANUARY, 18));
        assertEquals(aux.toString(), "Position(id=id, name=name, enable=true, createDate=2023-01-18, updateDate=2023-01-18)");
    }

}
