package com.sa.erp.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RepoTest {

    @Test
    public void repoNoArgsConstructor(){
        Repo aux = new Repo();
        assertNull(aux.getId());
        assertNull(aux.getName());
        assertNull(aux.getUrl());
        assertNull(aux.getUpdateDate());
        assertNull(aux.getCreateDate());
        assertFalse(aux.isEnable());
        assertNull(aux.getEmployeeList());
    }

    @Test
    public void repoAllArgsConstructor(){
        Repo aux = new Repo("id", "name", "https://www.google.com", true, null, LocalDate.now(), LocalDate.now());
        assertNotNull(aux.getId());
        assertNotNull(aux.getName());
        assertNotNull(aux.getUrl());
        assertNotNull(aux.getUpdateDate());
        assertNotNull(aux.getCreateDate());
        assertNotNull(aux.isEnable());
        assertNull(aux.getEmployeeList());
        assertEquals(aux.getId(), "id");
        assertEquals(aux.getName(), "name");
        assertEquals(aux.getUrl(), "https://www.google.com");
        assertEquals(aux.getUpdateDate(),LocalDate.now());
        assertEquals(aux.getCreateDate(),LocalDate.now());
        assertEquals(aux.isEnable(),true);
        assertEquals(aux.getEmployeeList(),null);
    }

    @Test
    public void repoGetsAndSets(){
        Repo aux = new Repo();
        assertNull(aux.getId());
        assertNull(aux.getName());
        assertNull(aux.getUrl());
        assertNull(aux.getUpdateDate());
        assertNull(aux.getCreateDate());
        assertFalse(aux.isEnable());
        assertNull(aux.getEmployeeList());
        aux.setId("id");
        aux.setName("name");
        aux.setUrl("https://www.google.com");
        aux.setUpdateDate(LocalDate.now());
        aux.setCreateDate(LocalDate.now());
        aux.setEnable(true);
        aux.setEmployeeList(null);
        assertEquals(aux.getId(), "id");
        assertEquals(aux.getName(), "name");
        assertEquals(aux.getUrl(), "https://www.google.com");
        assertEquals(aux.getUpdateDate(),LocalDate.now());
        assertEquals(aux.getCreateDate(),LocalDate.now());
        assertEquals(aux.isEnable(),true);
        assertEquals(aux.getEmployeeList(),null);
    }

    @Test
    public void repoToString(){
        Repo aux = new Repo("id", "name", "https://www.google.com", true, null, LocalDate.of(2023, Month.JANUARY, 18), LocalDate.of(2023, Month.JANUARY, 18));
        System.out.println(aux);
        assertEquals(aux.toString(),"Repo(id=id, name=name, url=https://www.google.com, enable=true, employeeList=null, createDate=2023-01-18, updateDate=2023-01-18)");
    }

}
