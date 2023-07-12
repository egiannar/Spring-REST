package com.example.rest.controller;

import com.example.rest.entity.Employee;
import com.example.rest.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * This is a method to create a new employee with our API (POST).
     * @param employee
     * @return
     */
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
            return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    /**
     * This is a method to retrieve all the employees with our API(GET)
     * @return
     */
    @GetMapping()
    public List<Employee> getAllEmployees() {
            return employeeService.getAllEmployees();
    }

    /**
     * Get employee by Id (GET).
     * @param theId
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long theId) {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(theId), HttpStatus.OK);
    }

    /**
     * Update employee by id with API(UPDATE).
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
                                                   @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    /**
     * Delete employee with API(DELETE).
     *
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        //delete the employee from DB
        employeeService.deleteEmployee(id);

        return new ResponseEntity<String>("Employee deleted syccesfully", HttpStatus.OK);
    }

}