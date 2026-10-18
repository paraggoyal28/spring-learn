package com.learn.spring.basic_hiberante_app.controller;

import com.learn.spring.basic_hiberante_app.model.Employee;
import com.learn.spring.basic_hiberante_app.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    // Build create employee rest endpoint
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // build fetch all employees rest endpoint
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // build get employee by id rest endpoint
    // http://localhost:8080/api/employees/1
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    // Build update employee rest endpoint
    // http://localhost:8080/api/employees/1
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,
                                                   @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    // Build delete employee rest endpoint
    // http://localhost:8080/api/employees/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id) {
        // delete employee by id
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
    }
}
