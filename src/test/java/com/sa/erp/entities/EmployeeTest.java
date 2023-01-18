package com.sa.erp.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeTest {

    @Test
    public void employeeNoArgsConstructor(){
        Employee aux = new Employee();
        assertNull(aux.getId());
        assertNull(aux.getFirstName());
        assertNull(aux.getLastName());
        assertNull(aux.getPhone());
        assertNull(aux.getEmail());
        assertNull(aux.getBirthday());
        assertNull(aux.getGithubUser());
        assertNull(aux.getStartingDate());
        assertNull(aux.getUpdateDate());
        assertNull(aux.getCreateDate());
        assertFalse(aux.isEnable());
        assertNull(aux.getPosition());
        assertNull(aux.getVacationDays());
        assertFalse(aux.isEnableVacation());
    }

    @Test
    public void employeeAllArgsConstructor(){
        Employee aux = new Employee("id","ana", "lopez", "2288554499", "ana@gmail.com", LocalDate.of(1995, Month.AUGUST,2),
                "@lopezA", LocalDate.of(2022,Month.JULY,15), true, null,false,
                new Position("id","name", true, LocalDate.now(), LocalDate.now()), LocalDate.now(),LocalDate.now());
        assertNotNull(aux.getId());
        assertNotNull(aux.getFirstName());
        assertNotNull(aux.getLastName());
        assertNotNull(aux.getPhone());
        assertNotNull(aux.getEmail());
        assertNotNull(aux.getBirthday());
        assertNotNull(aux.getGithubUser());
        assertNotNull(aux.getStartingDate());
        assertTrue(aux.isEnable());
        assertNull(aux.getVacationDays());
        assertFalse(aux.isEnableVacation());
        assertNotNull(aux.getPosition());
        assertNotNull(aux.getUpdateDate());
        assertNotNull(aux.getCreateDate());
    }

    @Test
    public void employeeGetsAndSets(){
        Employee aux = new Employee();
        Position position =new Position("id","name", true, LocalDate.now(), LocalDate.now());
        aux.setId("id");
        aux.setFirstName("ana");
        aux.setLastName("lopez");
        aux.setPhone("2288554499");
        aux.setEmail("ana@gmail.com");
        aux.setBirthday(LocalDate.of(1995, Month.AUGUST,2));
        aux.setGithubUser("@lopezA");
        aux.setStartingDate(LocalDate.of(2022,Month.JULY,15));
        aux.setEnable(true);
        aux.setVacationDays(null);
        aux.setEnableVacation(false);
        aux.setPosition(position);
        aux.setCreateDate(LocalDate.now());
        aux.setUpdateDate(LocalDate.now());
        assertEquals(aux.getId(),"id");
        assertEquals(aux.getFirstName(),"ana");
        assertEquals(aux.getLastName(),"lopez");
        assertEquals(aux.getPhone(),"2288554499");
        assertEquals(aux.getEmail(),"ana@gmail.com");
        assertEquals(aux.getBirthday(),LocalDate.of(1995, Month.AUGUST,2));
        assertEquals(aux.getGithubUser(), "@lopezA");
        assertEquals(aux.getStartingDate(),LocalDate.of(2022,Month.JULY,15));
        assertEquals(aux.isEnable(),true);
        assertEquals(aux.getVacationDays(),null);
        assertEquals(aux.isEnableVacation(),false);
        assertEquals(aux.getPosition(),position);
        assertEquals(aux.getUpdateDate(), LocalDate.now());
        assertEquals(aux.getCreateDate(), LocalDate.now());
    }

    @Test
    public void employeeToString(){
        Employee aux = new Employee("id","ana", "lopez", "2288554499", "ana@gmail.com", LocalDate.of(1995, Month.AUGUST,2),
                "@lopezA", LocalDate.of(2022,Month.JULY,15), true, null,false,
                null, LocalDate.of(2023, Month.JANUARY, 18),LocalDate.of(2023, Month.JANUARY, 18));
        System.out.println(aux.toString());
        assertEquals(aux.toString(), "Employee(id=id, firstName=ana, lastName=lopez, phone=2288554499, email=ana@gmail.com, birthday=1995-08-02, githubUser=@lopezA, startingDate=2022-07-15, enable=true, vacationDays=null, enableVacation=false, position=null, createDate=2023-01-18, updateDate=2023-01-18)");
    }

}
