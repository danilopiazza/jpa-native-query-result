package io.github.danilopiazza.jpa.nativequeryresult.repository;

import java.util.List;

import javax.persistence.EntityManager;

import io.github.danilopiazza.jpa.nativequeryresult.entity.Employee;

public class EmployeeRepository {
    private final EntityManager em;

    public EmployeeRepository(EntityManager em) {
        this.em = em;
    }

    @SuppressWarnings("unchecked")
    public List<Employee> findAll() {
        return em.createNativeQuery("SELECT id, username, first_name, last_name, email, birth_date, salary FROM employees ORDER BY id", Employee.class)
                .getResultList();
    }
}
