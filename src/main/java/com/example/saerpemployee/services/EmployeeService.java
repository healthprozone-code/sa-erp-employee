package com.example.saerpemployee.services;

import com.example.saerpemployee.documents.Employee;
import com.example.saerpemployee.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

}
