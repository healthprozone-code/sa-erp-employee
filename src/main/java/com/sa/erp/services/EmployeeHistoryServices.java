package com.sa.erp.services;

import com.sa.erp.entities.Employee;
import com.sa.erp.entities.EmployeeHistory;
import com.sa.erp.repositories.EmployeeHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmployeeHistoryServices {

    @Autowired
    private EmployeeHistoryRepository employeeHistoryRepository;


    /**
     * method to save record of employee
     * @param employee employee data to save
     */
    public void saveEmployeeHistory(Employee employee){
        EmployeeHistory aux = new EmployeeHistory(null, employee, LocalDate.now());
        this.employeeHistoryRepository.save(aux);
    }

}
