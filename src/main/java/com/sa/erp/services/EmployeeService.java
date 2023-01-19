package com.sa.erp.services;

import com.sa.erp.entities.Employee;
import com.sa.erp.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    public Optional<Employee> getEmployeeById(String id){
        return employeeRepo.findById(id);
    }

    public Optional<Employee> getEmployeeByFirstNameAndLastName(String firstName, String lastName){
        return employeeRepo.findByFirstNameAndLastName(firstName, lastName);
    }

    public Employee saveEmployee(Employee employee){
        employee.setUpdateDate(LocalDate.now());
        employee.setCreateDate(LocalDate.now());
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(String id, Employee employee){
        Optional<Employee> aux = this.getEmployeeById(id);
        if(aux.isPresent()){
            Employee pivot = aux.get();
            pivot.setFirstName(employee.getFirstName());
            pivot.setLastName(employee.getLastName());
            pivot.setPhone(employee.getPhone());
            pivot.setEmail(employee.getEmail());
            pivot.setBirthday(employee.getBirthday());
            pivot.setGithubUser(employee.getGithubUser());
            pivot.setStartingDate(employee.getStartingDate());
            pivot.setPosition(employee.getPosition());
            pivot.setUpdateDate(LocalDate.now());
            pivot.setCreateDate(LocalDate.now());
            return this.saveEmployee(pivot);
        }else{
            return null;
        }
    }

    public Employee deleteEmployee(String id){
        Optional<Employee> aux = this.getEmployeeById(id);
        if(aux.isPresent()){
            Employee pivot = aux.get();
            pivot.setEnable(false);
            return this.saveEmployee(pivot);
        }else{
            return null;
        }
    }
    public Employee getNextBirthdayPersonWithMongo(){
        Optional<Employee> employeeAux= employeeRepo.GetNextBirthdayEmployee2();
        Employee nextBirthdayPerson=null;
        if(employeeAux.isPresent()){
            nextBirthdayPerson=employeeAux.get();
        }
        return nextBirthdayPerson;
    }

}
