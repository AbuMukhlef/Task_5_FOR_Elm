package com.example.employeemanagementsystemv5.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class EmployeeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Employees> findAll() {
        return entityManager.createQuery("SELECT e FROM Employees e", Employees.class).getResultList();
    }

    public Optional<Employees> findById(int id) {
        Employees employee = entityManager.find(Employees.class, id);
        return Optional.ofNullable(employee);
    }

    public Employees save(Employees employee) {
        entityManager.persist(employee);
        return employee;
    }

    public Employees update(Employees employee) {
        return entityManager.merge(employee);
    }

    public void deleteById(int id) {
        Employees employee = entityManager.find(Employees.class, id);
        if (employee != null) {
            entityManager.remove(employee);
        }
    }
}
