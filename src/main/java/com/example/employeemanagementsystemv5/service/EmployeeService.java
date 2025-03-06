package com.example.employeemanagementsystemv5.service;

import com.example.employeemanagementsystemv5.common.EmployeeNotFoundException;
import com.example.employeemanagementsystemv5.common.JsonSchemaValidator;
import com.example.employeemanagementsystemv5.repository.EmployeeDAO;
import com.example.employeemanagementsystemv5.repository.Employees;
import com.networknt.schema.ValidationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private JsonSchemaValidator jsonSchemaValidator;


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
        Set<ValidationMessage> errors = jsonSchemaValidator.validate(employee);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Invalid Employee Data: " + errors);
        }

        return employeeDAO.save(employee);
    }


    public Employees updateEmployee(int id, Employees updatedEmployee) {
        log.info("updateEmployee : Service");
        Set<ValidationMessage> errors = jsonSchemaValidator.validate(updatedEmployee);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Invalid Employee Data: " + errors);
        }

        Optional<Employees> existingEmployee = employeeDAO.findById(id);
        if (existingEmployee.isEmpty()) {
            log.error("updateEmployee : Employee with ID {} not found", id);
            try {
                throw new EmployeeNotFoundException();
            } catch (EmployeeNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        updatedEmployee.setId(id);
        return employeeDAO.update(updatedEmployee);
    }


    public ResponseEntity<Void> deleteEmployee(int id) {
        log.info("Deleting employee with ID: {}", id);

        try {
            Optional<Employees> employee = employeeDAO.findById(id);
            if (employee.isEmpty()) {
                log.error("deleteEmployee : Employee with ID {} not found", id);
                return ResponseEntity.notFound().build();
            }

            employeeDAO.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("deleteEmployee : Error while deleting employee", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
