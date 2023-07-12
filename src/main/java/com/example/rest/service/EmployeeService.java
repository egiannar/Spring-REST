package com.example.rest.service;

import com.example.rest.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(long theId);
    Employee updateEmployee(Employee employee, long id);
    void deleteEmployee(long id);

}
