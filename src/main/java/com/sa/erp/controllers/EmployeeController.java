package com.sa.erp.controllers;


import com.sa.erp.entities.Employee;
import com.sa.erp.entities.Position;
import com.sa.erp.dtos.MessageErrorDto;
import com.sa.erp.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
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
    public ResponseEntity<?> getAllEmployees() {
        StopWatch sw = new StopWatch();
        sw.start();
        log.info("request from getAllEmployees");
        List<Employee> aux = employeeService.getAllEmployees();
        log.debug("responseBody: {}  ms", aux.toString());
        sw.stop();
        log.debug("time of performance {}", sw.getTotalTimeMillis());
        return ResponseEntity.status(HttpStatus.OK).body(aux);
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
        StopWatch sw = new StopWatch();
        sw.start();
        log.info("request from getEmployeeById");
        log.debug("requestParam ID: {}", id);
        Optional<Employee> aux = employeeService.getEmployeeById(id);
        if(aux.isPresent()){
            log.debug("responseBody: {} ",aux.get().toString());
            sw.stop();
            log.debug("time of performance {}",sw.getTotalTimeMillis());
            return ResponseEntity.status(HttpStatus.OK).body(aux.get());
        }else{
            log.info("error in getEmployeeById");
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
        StopWatch sw = new StopWatch();
        sw.start();
        log.info("request from getEmployeeByFirtsNameAndLastName");
        log.debug("requestParam firstName: {} and requestParam lastName: {}",firstName, lastName);
        Optional<Employee> aux = employeeService.getEmployeeByFirstNameAndLastName(firstName, lastName);
        if(aux.isPresent()){
            log.debug("responseBody: {} ",aux.get().toString());
            sw.stop();
            log.debug("time of performance {}",sw.getTotalTimeMillis());
            return ResponseEntity.status(HttpStatus.OK).body(aux.get());
        }else{
            log.info("error in getEmployeeByFirtsNameAndLastName");
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
    public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
        StopWatch sw = new StopWatch();
        sw.start();
        log.info("request from saveEmployee");
        log.debug("requestBody: {}", employee);
        Employee aux = employeeService.saveEmployee(employee);
        log.debug("responseBody: {} ", aux.toString());
        sw.stop();
        log.debug("time of performance {}", sw.getTotalTimeMillis());
        return ResponseEntity.status(HttpStatus.CREATED).body(aux);
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
        StopWatch sw = new StopWatch();
        sw.start();
        log.info("request from updateEmployee");
        log.debug("requestParam id: {} ,requestBody: {}", id, employee);
        Employee aux = employeeService.updateEmployee(id, employee);
        if(aux!=null){
            log.debug("responseBody: {} ",aux.toString());
            sw.stop();
            log.debug("time of performance {}",sw.getTotalTimeMillis());
            return ResponseEntity.status(HttpStatus.OK).body(aux);
        }else{
            log.info("error in updateEmployee");
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
        StopWatch sw = new StopWatch();
        sw.start();
        log.info("request from deleteEmployee");
        log.debug("requestParam id: {}", id);
        Employee aux = employeeService.deleteEmployee(id);
        if (aux!=null){
            log.debug("responseBody: {} ",aux.toString());
            sw.stop();
            log.debug("time of performance {}",sw.getTotalTimeMillis());
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }else{
            log.info("error in deleteEmployee");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Get the nearest Birthday Employee", method = "get")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the nearest Birthday Employee",
                    content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Employee.class)))}),
            @ApiResponse(responseCode = "204", description = "Employee not found or registered",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageErrorDto.class)))})
    @GetMapping("/nextbirthday")
    public ResponseEntity<?> getNearestBirthdayEmployee() {
        StopWatch sw = new StopWatch();
        sw.start();
        log.info("request from getNearestBirthdayEmployee");
        Employee aux = employeeService.getNextBirthdayPersonWithMongo();
        log.debug("responseBody: {} ", aux.toString());
        sw.stop();
        log.debug("time of performance {}", sw.getTotalTimeMillis());
        return ResponseEntity.status(HttpStatus.OK).body(aux);
    }

}
