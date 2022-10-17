package com.lex.springboot.restapi.service;

import com.lex.springboot.restapi.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(Integer theId);

    void save(Employee employee);

    void deleteById(Integer theId);
}
