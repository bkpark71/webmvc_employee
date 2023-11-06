package com.example.webmvc_employee.service;

import com.example.webmvc_employee.dto.EmployeeCreateDto;
import com.example.webmvc_employee.dto.EmployeeUpdateDto;
import com.example.webmvc_employee.entity.Department;
import com.example.webmvc_employee.entity.Employee;
import com.example.webmvc_employee.entity.EmployeeFamily;
import com.example.webmvc_employee.repositroy.DepartmentRepository;
import com.example.webmvc_employee.repositroy.EmployeeRepositroy;
import com.example.webmvc_employee.repositroy.FamilyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepositroy employeeRepositroy;
    private final FamilyRepository familyRepository;

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepositroy.findAll();
    }
    @Transactional(readOnly = true)
    public Employee getEmployee(String empId) {
        return employeeRepositroy.findById(empId);
    }

    public void addEmployee(EmployeeCreateDto employeeCreateDto){
            //int deptId, Employee employee, EmployeeFamily family) {
        Department department = departmentRepository.findById(employeeCreateDto.getDeptId());
        Employee employee = Employee.createEmployee(department, employeeCreateDto);
        employeeRepositroy.save(employee);
        EmployeeFamily family = employee.getEmployeeFamily();
        familyRepository.save(family);
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

