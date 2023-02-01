package com.sa.erp.service;

import static org.junit.jupiter.api.Assertions.*;

import com.sa.erp.entities.Employee;
import com.sa.erp.entities.Position;
import com.sa.erp.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@EnabledIfSystemProperty(named = "spring.profiles.using", matches = "DEV")
@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;
    private Employee auxEmployeeTest;
    private String auxEmployeeId;
    private LocalDate currentDate;

    @BeforeEach
    public void initialize(){

        this.auxEmployeeTest = this.employeeService.saveEmployee(new Employee("id","ana", "lopez", "2288554499", "ana@gmail.com", LocalDate.of(1995, Month.AUGUST,2),
                "@lopezA", LocalDate.of(2022,Month.JULY,15), true, null,false,
                new Position("id","name", true, LocalDate.now(), LocalDate.now()), LocalDate.now(),LocalDate.now(),null));
        this.auxEmployeeId = this.auxEmployeeTest.getId();
        this.currentDate=LocalDate.now();
    }

    @Test
    public void findEmployeeById(){
        Optional<Employee> employeeFinded = this.employeeService.getEmployeeById(this.auxEmployeeId);
        assertTrue(employeeFinded.isPresent());
        assertNotNull(employeeFinded.get().getId());
        assertEquals(employeeFinded.get().getId(),this.auxEmployeeId);
    }

    @Test
    public void findEmployeeByFirstAndLastName(){
        Optional<Employee> employeeFinded = this.employeeService.getEmployeeByFirstNameAndLastName("ana","lopez");
        assertTrue(employeeFinded.isPresent());
        assertNotNull(employeeFinded.get().getId());
        assertEquals(employeeFinded.get().getId(),this.auxEmployeeId);
    }

    @Test
    public void findAllEmployee(){
        List<Employee> employeeFinded = this.employeeService.getAllEmployees();
        assertTrue(!employeeFinded.isEmpty());
        assertTrue(employeeFinded.size()>0);
    }

    @Test
    public void saveEmployee(){
        Position position =new Position("id","name", true, LocalDate.now(), LocalDate.now());
        Employee aux = new Employee("id","ana", "lopez", "2288554499", "ana@gmail.com", LocalDate.of(1995, Month.AUGUST,2),
                "@lopezA", LocalDate.of(2022,Month.JULY,15), true, null,false,
                position, LocalDate.now(),LocalDate.now(), null);
        Employee result=this.employeeService.saveEmployee(aux);
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
        assertEquals(aux.getUpdateDate(), this.currentDate);
        assertEquals(aux.getCreateDate(), this.currentDate);
    }

    @Test
    public void updateEmployee(){
        Employee aux =this.employeeService.getEmployeeById(this.auxEmployeeId).get();
        Position auxP = new Position("id","name", true, LocalDate.now(), LocalDate.now()) ;
        aux.setId("sks");
        aux.setFirstName("carla");
        aux.setLastName("roman");
        aux.setPhone("2288443579");
        aux.setEmail("carla@gmail.com");
        aux.setBirthday(LocalDate.of(1995, Month.AUGUST,2));
        aux.setGithubUser("@carlaR");
        aux.setStartingDate(LocalDate.of(2022,Month.JULY,15));
        aux.setEnable(false);
        aux.setVacationDays(null);
        aux.setEnableVacation(true);
        aux.setPosition(auxP);
        aux.setCreateDate(LocalDate.now());
        aux.setUpdateDate(LocalDate.now());
        aux = this.employeeService.updateEmployee(this.auxEmployeeId,aux);
        assertEquals(aux.getId(),"id");
        assertEquals(aux.getFirstName(),"carla");
        assertEquals(aux.getLastName(),"roman");
        assertEquals(aux.getPhone(),"2288443579");
        assertEquals(aux.getEmail(),"carla@gmail.com");
        assertEquals(aux.getBirthday(),LocalDate.of(1995, Month.AUGUST,2));
        assertEquals(aux.getGithubUser(), "@carlaR");
        assertEquals(aux.getStartingDate(),LocalDate.of(2022,Month.JULY,15));
        assertEquals(aux.isEnable(),true);
        assertEquals(aux.getVacationDays(),null);
        assertEquals(aux.isEnableVacation(),false);
        assertEquals(aux.getPosition(),auxP);
        assertEquals(aux.getUpdateDate(), this.currentDate);
        assertEquals(aux.getCreateDate(), this.currentDate);
    }

    @Test
    public void updateEmployeeError(){
        Employee aux =this.employeeService.getEmployeeById(this.auxEmployeeId).get();
        aux = this.employeeService.updateEmployee("shhshjs",aux);
        assertNull(aux);
    }

    @Test
    public void deleteEmployee(){
        Employee aux =this.employeeService.getEmployeeById(this.auxEmployeeId).get();
        aux = this.employeeService.deleteEmployee(this.auxEmployeeId);
        assertEquals(this.currentDate, aux.getUpdateDate());
        assertFalse(aux.isEnable());
    }

    @Test
    public void deleteEmployeeError(){
        Employee aux =this.employeeService.deleteEmployee("jsjksjk");
        assertNull(aux);
    }

    @Test
    public void getNextBirthdayPersonTest(){
        Employee birthdayEmployee = new Employee("63c810f773f2132e470a953b","roberto", "lopez", "22884466676", "roberto@gmail.com", LocalDate.of(1993, Month.MAY,5),
                "@robertol", LocalDate.of(2022,Month.JULY,15), true, null,false,null, LocalDate.now(),LocalDate.now(), null);
        birthdayEmployee = this.employeeService.saveEmployee(birthdayEmployee);
        Employee aux = this.employeeService.getNextBirthdayPersonWithMongo();
        assertEquals(aux.getId(), birthdayEmployee.getId());
        assertEquals(aux.getFirstName(), birthdayEmployee.getFirstName());
        assertEquals(aux.getLastName(), birthdayEmployee.getLastName());
        assertEquals(aux.getGithubUser(), birthdayEmployee.getGithubUser());

    }


}
