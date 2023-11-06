package com.example.webmvc_employee.service;

import com.example.webmvc_employee.dto.EmployeeUpdateDto;
import com.example.webmvc_employee.entity.Employee;
import com.example.webmvc_employee.repositroy.EmployeeRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepositroy employeeRepositroy;

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepositroy.findAll();
    }
    @Transactional(readOnly = true)
    public Employee getEmployee(String empId) {
        return employeeRepositroy.findById(empId);
    }

    public void addEmployee(Employee employee) {
        employeeRepositroy.save(employee);
    }

    public void updateEmployee(EmployeeUpdateDto employeeUpdateDto) {
        Employee employee = employeeRepositroy.findById(employeeUpdateDto.getEmpId());
        employee.setEmpType(employeeUpdateDto.getEmpType());
        employee.setSalary(employeeUpdateDto.getSalary());
        employeeRepositroy.save(employee);
    }

    public void deleteEmployee(String empId) {
        Employee emp = employeeRepositroy.findById(empId);
        if(emp != null) {
            employeeRepositroy.delete(emp);
        }
    }
}

