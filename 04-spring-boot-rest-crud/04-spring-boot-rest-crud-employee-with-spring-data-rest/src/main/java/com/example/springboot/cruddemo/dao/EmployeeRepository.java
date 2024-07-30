package com.example.springboot.cruddemo.dao;

import com.example.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // no need to write any code
}
