package com.example.employeemanagementsystemv5.service;

import com.example.employeemanagementsystemv5.repository.EmployeeDAO;
import com.example.employeemanagementsystemv5.repository.Employees;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void deleteEmployee(int id) {
        log.info("deleteEmployee : Service");
        employeeDAO.deleteById(id);
    }
}
