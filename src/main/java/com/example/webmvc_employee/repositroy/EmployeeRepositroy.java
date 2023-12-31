package com.example.webmvc_employee.repositroy;

import com.example.webmvc_employee.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor // 생성자 + autowired
public class EmployeeRepositroy {
    @PersistenceContext
    private final EntityManager em;

    public List<Employee> findAll() {
        return em.createQuery("select e from Employee e", Employee.class)
                .getResultList();
    }

    public Employee findById(String empId) {
        return em.find(Employee.class, empId);
    }

    public void save(Employee employee) { // insert & update
        em.persist(employee);
    }

    public void delete(Employee employee) {
        em.remove(employee);
    }
}
