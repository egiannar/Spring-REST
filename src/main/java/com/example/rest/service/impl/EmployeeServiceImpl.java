package com.example.rest.service.impl;

import com.example.rest.entity.Employee;
import com.example.rest.exception.ResourceNotFoundException;
import com.example.rest.repository.EmployeeRepository;
import com.example.rest.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        super();
        this.repository = repository;
    }


    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(long theId) {
//        Optional<Employee> employeeById = repository.findById(theId);
//        if(employeeById.isPresent()) {
//            return Optional.of(employeeById.get());
//        } else {
//            throw new ResourceNotFoundException("Employee", "id", theId);
//        }

        return repository.findById(theId)
                .orElseThrow(() ->
                new ResourceNotFoundException("Employee", "id", theId));


    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        //check if employee with given id exists in database
        Employee existingEmployee = repository.findById(id)
                .orElseThrow(() ->
                new ResourceNotFoundException("Employee", "id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        //save existing employee to db
        repository.save(existingEmployee);
        return existingEmployee;

    }

    @Override
    public void deleteEmployee(long id) {
        //check if specific employee exists
        repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "id", id));

        repository.deleteById(id);

    }


}
