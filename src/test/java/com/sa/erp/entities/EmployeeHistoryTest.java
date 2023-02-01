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
public class EmployeeHistoryTest {

    @Test
    public void employeeHistoryNoArgsConstructor(){
        EmployeeHistory aux = new EmployeeHistory();
        assertNull(aux.getId());
        assertNull(aux.getCreateDate());
        assertNull(aux.getEmployee());
    }

    @Test
    public void employeeHistoryAllArgsConstructor(){
        Employee employee = new Employee("id","ana", "lopez", "2288554499", "ana@gmail.com", LocalDate.of(1995, Month.AUGUST,2),
                "@lopezA", LocalDate.of(2022,Month.JULY,15), true, null,false,
                null, LocalDate.of(2023, Month.JANUARY, 18),LocalDate.of(2023, Month.JANUARY, 18), null);
        EmployeeHistory aux = new EmployeeHistory("id", employee,  LocalDate.now());
        assertNotNull(aux.getId());
        assertNotNull(aux.getCreateDate());
        assertNotNull(aux.getEmployee());
        assertEquals(aux.getId(), "id");
        assertEquals(aux.getCreateDate(), LocalDate.now());
        assertEquals(aux.getEmployee(), employee);
    }

    @Test
    public void employeeHistoryGetsAndSets(){
        EmployeeHistory aux = new EmployeeHistory();
        assertNull(aux.getId());
        assertNull(aux.getEmployee());
        assertNull(aux.getCreateDate());
        Employee employee = new Employee("id","ana", "lopez", "2288554499", "ana@gmail.com", LocalDate.of(1995, Month.AUGUST,2),
                "@lopezA", LocalDate.of(2022,Month.JULY,15), true, null,false,
                null, LocalDate.of(2023, Month.JANUARY, 18),LocalDate.of(2023, Month.JANUARY, 18), null);
        aux.setId("id");
        aux.setEmployee(employee);
        aux.setCreateDate(LocalDate.now());
        assertEquals(aux.getId(), "id");
        assertEquals(aux.getEmployee(), employee);
        assertEquals(aux.getCreateDate(), LocalDate.now());
    }

    @Test
    public void employeeHistoryToString(){
        EmployeeHistory aux = new EmployeeHistory("id", new Employee("id","ana", "lopez", "2288554499", "ana@gmail.com", LocalDate.of(1995, Month.AUGUST,2),
                "@lopezA", LocalDate.of(2022,Month.JULY,15), true, null,false,
                null, LocalDate.of(2023, Month.JANUARY, 18),LocalDate.of(2023, Month.JANUARY, 18), null),  LocalDate.now());
        assertEquals(aux.toString(), "EmployeeHistory(id=id, employee=Employee(id=id, firstName=ana, lastName=lopez, phone=2288554499, email=ana@gmail.com, birthday=1995-08-02, githubUser=@lopezA, startingDate=2022-07-15, enable=true, vacationDays=null, enableVacation=false, position=null, createDate=2023-01-18, updateDate=2023-01-18, repoList=null), createDate=2023-01-31)");
    }

}
