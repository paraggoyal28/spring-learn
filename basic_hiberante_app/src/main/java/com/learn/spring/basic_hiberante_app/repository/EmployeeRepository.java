package com.learn.spring.basic_hiberante_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.spring.basic_hiberante_app.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
