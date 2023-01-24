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

    /**
     *
     * @return a list of employee saved
     */
    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    /**
     *
     * @param id of the employee to find
     * @return Optional with teh employee finded
     */
    public Optional<Employee> getEmployeeById(String id){
        return employeeRepo.findById(id);
    }

    /**
     *
     * @param firstName first name of the employe to find
     * @param lastName last name of the employee to fined
     * @return Optional with teh employee finded
     */
    public Optional<Employee> getEmployeeByFirstNameAndLastName(String firstName, String lastName){
        return employeeRepo.findByFirstNameAndLastName(firstName, lastName);
    }

    /**
     *
     * @param employee to save
     * @return employee saved
     */
    public Employee saveEmployee(Employee employee){
        employee.setUpdateDate(LocalDate.now());
        employee.setCreateDate(LocalDate.now());
        return employeeRepo.save(employee);
    }

    /**
     *
     * @param id of the employee to update
     * @param employee data of the employee to update
     * @return employee updated
     */
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

    /**
     *
     * @param id of the employee to delete
     * @return employee deleted
     */
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

    /**
     *
     * @return the employee with the next birthday
     */
    public Employee getNextBirthdayPersonWithMongo(){
        Optional<Employee> employeeAux= employeeRepo.GetNextBirthdayEmployee2();
        Employee nextBirthdayPerson=null;
        if(employeeAux.isPresent()){
            nextBirthdayPerson=employeeAux.get();
        }
        return nextBirthdayPerson;
    }

}
