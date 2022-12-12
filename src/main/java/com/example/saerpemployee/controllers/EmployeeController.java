package com.example.saerpemployee.controllers;


import com.example.saerpemployee.documents.Employee;
import com.example.saerpemployee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<?> getAllEmployees(){
        List<Employee> aux = employeeService.getAllEmployees();
        if(!aux.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id")String id){
        Optional<Employee> aux = employeeService.getEmployeeById(id);
        if(aux.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(aux.get());
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @GetMapping("/{firstname}/{lastname}")
    public ResponseEntity<?> getEmployeeByFirtsNameAndLastName(@PathVariable("firstname")String firstName, @PathVariable("lastname")String lastName){
        Optional<Employee> aux = employeeService.getEmployeeByFirstNameAndLastName(firstName, lastName);
        if(aux.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(aux.get());
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee){
        Employee aux = employeeService.saveEmployee(employee);
        if(aux!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id")String id, @RequestBody Employee employee){
        Employee aux = employeeService.updateEmployee(id, employee);
        if(aux!=null){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id")String id){
        Employee aux = employeeService.deleteEmployee(id);
        if (aux!=null){
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
