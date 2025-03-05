package com.example.employeemanagementsystemv5.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class EmployeeDAO {
    private static final Logger log = LoggerFactory.getLogger(EmployeeDAO.class);
    @PersistenceContext
    private EntityManager entityManager;

    public List<Employees> findAll() {
        log.info("findAllEmployees : EmployeeDAO");
        log.debug("findAllEmployees : EmployeeDAO");
        return entityManager.createQuery("SELECT e FROM Employees e", Employees.class).getResultList();
    }

    public Optional<Employees> findById(int id) {
        log.info("findById : EmployeeDAO");
        log.debug("findById : EmployeeDAO");
        Employees employee = entityManager.find(Employees.class, id);
        return Optional.ofNullable(employee);
    }

    public Employees save(Employees employee) {
        log.info("saveEmployee : EmployeeDAO");
        log.debug("saveEmployee : EmployeeDAO");
        entityManager.persist(employee);
        return employee;
    }

    public Employees update(Employees employee) {
        log.info("updateEmployee : EmployeeDAO");
        log.debug("updateEmployee : EmployeeDAO");
        return entityManager.merge(employee);
    }

    public void deleteById(int id) {
        log.info("deleteById : EmployeeDAO");
        Employees employee = entityManager.find(Employees.class, id);
        if (employee != null) {
            entityManager.remove(employee);
        }  else {
            log.info("Employee with id {} not found", id);
            log.debug("Employee with id {} not found", id);
        }
    }
}
