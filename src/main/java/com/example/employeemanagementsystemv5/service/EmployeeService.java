package com.example.employeemanagementsystemv5.service;

import com.example.employeemanagementsystemv5.common.exception.EmployeeNotFoundException;
import com.example.employeemanagementsystemv5.repository.EmployeeDAO;
import com.example.employeemanagementsystemv5.repository.Employees;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDAO employeeDAO;

    public List<Employees> findAllEmployees() {
        log.info("findAllEmployees : Service");
        return employeeDAO.findAll();
    }

    public Optional<Employees> findEmployeeById(int id) {
        log.info("findEmployeeById : Service");
        return employeeDAO.findById(id);
    }

    public Employees saveEmployee(Employees employee) {
        log.info("saveEmployee : Service");
        return employeeDAO.save(employee);
    }

    public Employees updateEmployee(int id, Employees updatedEmployee) {
        log.info("updateEmployee : Service");
        updatedEmployee.setId(id);
        return employeeDAO.update(updatedEmployee);
    }

    public ResponseEntity<Void> deleteEmployee(int id) throws EmployeeNotFoundException {
        log.info("deleteEmployee : Service");

        try {
            employeeDAO.deleteById(id);
        } catch (EmployeeNotFoundException e) {
            log.error("deleteEmployee : employee of ID " + id + " not found");
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("deleteEmployee : General Exception");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
        return ResponseEntity.noContent().build();
    }
}
