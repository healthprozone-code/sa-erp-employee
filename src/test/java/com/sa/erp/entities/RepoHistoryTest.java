package com.sa.erp.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@EnabledIfSystemProperty(named = "spring.profiles.using", matches = "DEV")
@SpringBootTest
public class RepoHistoryTest {

    @Test
    public void repoHistoryNoArgsConstructor(){
        RepoHistory aux = new RepoHistory();
        assertNull(aux.getId());
        assertNull(aux.getCreateDate());
        assertNull(aux.getRepo());
    }

    @Test
    public void repoHistoryAllArgsConstructor(){
        Repo repo = new Repo("id", "name", "https://www.google.com", true, null, LocalDate.now(), LocalDate.now());
        RepoHistory aux = new RepoHistory("id", repo,  LocalDate.now());
        assertNotNull(aux.getId());
        assertNotNull(aux.getCreateDate());
        assertNotNull(aux.getRepo());
        assertEquals(aux.getId(), "id");
        assertEquals(aux.getCreateDate(), LocalDate.now());
        assertEquals(aux.getRepo(), repo);
    }

    @Test
    public void repoHistoryGetsAndSets(){
        Repo repo = new Repo("id", "name", "https://www.google.com", true, null, LocalDate.now(), LocalDate.now());
        RepoHistory aux = new RepoHistory();
        assertNull(aux.getId());
        assertNull(aux.getRepo());
        assertNull(aux.getCreateDate());
        aux.setId("id");
        aux.setRepo(repo);
        aux.setCreateDate(LocalDate.now());
        assertEquals(aux.getId(), "id");
        assertEquals(aux.getRepo(), repo);
        assertEquals(aux.getCreateDate(), LocalDate.now());
    }

    @Test
    public void repoHistoryToString(){
        Repo repo = new Repo("id", "name", "https://www.google.com", true, null, LocalDate.now(), LocalDate.now());
        RepoHistory repoHistory = new RepoHistory("id", repo,  LocalDate.now());
        assertEquals(repoHistory.toString(), "RepoHistory(id=id, repo=Repo(id=id, name=name, url=https://www.google.com, enable=true, employeeList=null, createDate=2023-01-31, updateDate=2023-01-31), createDate=2023-01-31)");
    }

}
