package com.learn.spring.basic_hiberante_app.service.impl;

import com.learn.spring.basic_hiberante_app.exception.ResourceNotFoundException;
import com.learn.spring.basic_hiberante_app.model.Employee;
import com.learn.spring.basic_hiberante_app.repository.EmployeeRepository;
import com.learn.spring.basic_hiberante_app.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
       /*
        Optional<Employee> employeeOptional =  employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        } else {
            throw new ResourceNotFoundException("Employee", "Id", id);
        }

        */
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {

        // we need to check whether employee with given id exists in the database or not
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "ID", id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
        // save existing employee to the database
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        // check whether the employee exist in the database or not
        employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "Id", id));
        employeeRepository.deleteById(id);
    }
}
