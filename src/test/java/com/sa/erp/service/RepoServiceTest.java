package com.sa.erp.service;

import com.sa.erp.entities.Repo;
import com.sa.erp.services.RepoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@EnabledIfSystemProperty(named = "spring.profiles.using", matches = "DEV")
@SpringBootTest
public class RepoServiceTest {

    @Autowired
    private RepoService repoService;

    private Repo auxTestRepo;

    private String auxTestID;

    @BeforeEach
    public void initialize() {
        this.auxTestRepo = this.repoService.saveRepo(new Repo(null, "hubio","https://github.com/hubio",true, null, LocalDate.now(), LocalDate.now()));
        this.auxTestID = this.auxTestRepo.getId();
    }

    @Test
    public void findRepoByID(){
        Optional<Repo> repoFinded = this.repoService.getRepoByID(this.auxTestID);
        assertTrue(repoFinded.isPresent());
        assertNotNull(repoFinded.get().getId());
        assertNotNull(repoFinded.get().getId(), this.auxTestRepo.getId());
    }

    @Test
    public void findAllRepo(){
        List<Repo> repoFinded = this.repoService.getAllRepos();
        assertTrue(!repoFinded.isEmpty());
        assertTrue(repoFinded.size()>0);
    }

    @Test
    public void addRepo(){
        Repo aux = new Repo();
        aux.setName("hubio");
        aux.setUrl("https://github.com/hubio");
        aux.setEnable(true);
        aux.setCreateDate(LocalDate.now());
        aux.setUpdateDate(LocalDate.now());
        aux.setEmployeeList(null);
        Repo result= this.repoService.saveRepo(this.auxTestRepo);
        assertEquals(result.getCreateDate(), aux.getCreateDate());
        assertEquals(result.getName(), aux.getName());
        assertEquals(result.getUrl(), aux.getUrl());
        assertEquals(result.getUpdateDate(), aux.getUpdateDate());
        assertEquals(result.isEnable(), aux.isEnable());
        assertNotNull(result.getId());
        assertNull(result.getEmployeeList());
    }

    @Test
    public void updateRepo(){
        Repo aux = this.repoService.getRepoByID(this.auxTestID).get();
        aux.setName("hubio2");
        aux.setUrl("https://github.com/hubio2");
        aux.setEnable(false);
        aux.setCreateDate(LocalDate.of(2022, Month.DECEMBER, 8));
        aux.setUpdateDate(LocalDate.of(2022, Month.DECEMBER, 8));
        aux.setEmployeeList(null);
        aux= this.repoService.updateRepo(aux.getId(),aux);
        assertFalse(aux.getUpdateDate().isEqual(LocalDate.of(2022, Month.DECEMBER, 8)));
        assertTrue(aux.getUpdateDate().isEqual(LocalDate.now()));
        assertFalse(aux.getCreateDate().isEqual(LocalDate.of(2022, Month.DECEMBER, 8)));
        assertTrue(aux.isEnable()==true);
        assertTrue(aux.getName().equals("hubio2"));
        assertTrue(aux.getUrl().equals("https://github.com/hubio2"));
        assertNull(aux.getEmployeeList());
    }

    @Test
    public void updateRepoError(){
        Repo aux = this.repoService.getRepoByID(this.auxTestID).get();
        aux= this.repoService.updateRepo("",aux);
        assertNull(aux);
    }

    @Test
    public void deleteRepo(){
        Repo aux = this.repoService.getRepoByID(this.auxTestID).get();
        aux= this.repoService.deleteRepo(aux.getId());
        assertFalse(aux.getUpdateDate().isEqual(LocalDate.of(2022, Month.DECEMBER, 8)));
        assertTrue(aux.getUpdateDate().isEqual(LocalDate.now()));
        assertFalse(aux.getCreateDate().isEqual(LocalDate.of(2022, Month.DECEMBER, 8)));
        assertTrue(aux.isEnable()==false);
        assertTrue(aux.getName().equals("hubio"));
        assertTrue(aux.getUrl().equals("https://github.com/hubio"));
        assertNull(aux.getEmployeeList());
    }

    @Test
    public void deleteRepoError(){
        Repo aux= this.repoService.deleteRepo("aux.getId()");
        assertNull(aux);
    }

}




