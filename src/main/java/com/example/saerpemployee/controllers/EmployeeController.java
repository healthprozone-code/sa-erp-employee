package com.example.saerpemployee.controllers;


import com.example.saerpemployee.documents.Employee;
import com.example.saerpemployee.documents.Position;
import com.example.saerpemployee.dtos.MessageErrorDto;
import com.example.saerpemployee.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all employees in system", method = "get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all employees registered",
                    content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "204", description = "Employees not found or registered",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @GetMapping("/")
    public ResponseEntity<?> getAllEmployees(){
        List<Employee> aux = employeeService.getAllEmployees();
        if(!aux.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageErrorDto("204", "Employees no found or registered"));
        }
    }

    @Operation(summary = "Get a employee by its id", method = "get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found a employee registered by its id",
                    content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "204", description = "Employee not found or registered",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @Parameter(required = true,
            description = "id of the employee to find",
            in = ParameterIn.PATH, name = "id",
            content = @Content(schema = @Schema(type = "String")))
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id")String id){
        Optional<Employee> aux = employeeService.getEmployeeById(id);
        if(aux.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(aux.get());
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageErrorDto("204", "Employee no found or registered"));
        }
    }

    @Operation(summary = "Get a employee by its first name and last name", method = "get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found a employee registered by its first name and last name",
                    content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "204", description = "Employee not found or registered",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @Parameters(value = {@Parameter(required = true,
            description = "first name of the employee to find",
            in = ParameterIn.PATH, name = "firstname",
            content = @Content(schema = @Schema(type = "String"))),
            @Parameter(required = true,
                    description = "last name of the employee to find",
                    in = ParameterIn.PATH, name = "lastname",
                    content = @Content(schema = @Schema(type = "String")))})

    @GetMapping("/{firstname}/{lastname}")
    public ResponseEntity<?> getEmployeeByFirtsNameAndLastName(@PathVariable("firstname")String firstName, @PathVariable("lastname")String lastName){
        Optional<Employee> aux = employeeService.getEmployeeByFirstNameAndLastName(firstName, lastName);
        if(aux.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(aux.get());
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

    @Operation(summary = "Save a employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee saved",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "Employee not saved",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @PostMapping("/")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee){
        Employee aux = employeeService.saveEmployee(employee);
        if(aux!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Update a employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "Employee not updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @Parameter(required = true, description = "id of the employee to update", in = ParameterIn.PATH, name = "id",
            content = @Content(schema = @Schema(type = "String")))
    @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "json of the employee to update, don't change the status of the employee",
            content = @Content(schema = @Schema(implementation = Position.class), mediaType = "application/json"))
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id")String id, @RequestBody Employee employee){
        Employee aux = employeeService.updateEmployee(id, employee);
        if(aux!=null){
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Delete a employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class))}),
            @ApiResponse(responseCode = "400", description = "Employee not deleted",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @Parameter(required = true, description = "id of the employee to delete", in = ParameterIn.PATH, name = "id",
            content = @Content(schema = @Schema(type = "String")))
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
