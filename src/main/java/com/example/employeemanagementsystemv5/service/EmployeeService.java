package com.example.employeemanagementsystemv5.service;

import com.example.employeemanagementsystemv5.repository.EmployeeDAO;
import com.example.employeemanagementsystemv5.repository.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    public List<Employees> findAllEmployees() {
        return employeeDAO.findAll();
    }

    public Optional<Employees> findEmployeeById(int id) {
        return employeeDAO.findById(id);
    }

    public Employees saveEmployee(Employees employee) {
        return employeeDAO.save(employee);
    }

    public Employees updateEmployee(int id, Employees updatedEmployee) {
        updatedEmployee.setId(id);
        return employeeDAO.update(updatedEmployee);
    }

    public void deleteEmployee(int id) {
        employeeDAO.deleteById(id);
    }
}
