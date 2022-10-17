package com.lex.springboot.restapi.service;

import com.lex.springboot.restapi.dao.EmployeeRepository;
import com.lex.springboot.restapi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        System.out.println("Service Layer - findAll method");
//        throw new RuntimeException("Test Error");
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer theId) {
        System.out.println("Service Layer - getEmployeeById method");
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }else {
            throw new RuntimeException("Didn't find employee id - " + theId);
        }
        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(Integer theId) {
        employeeRepository.deleteById(theId);
    }
}
