package com.lex.springboot.restapi.controller;

import com.lex.springboot.restapi.dao.EmployeeRepository;
import com.lex.springboot.restapi.exception.ResourceNotFoundException;
import com.lex.springboot.restapi.model.Employee;
import com.lex.springboot.restapi.service.EmployeeService;
import com.lex.springboot.restapi.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        System.out.println("Controller Layer - findAll method");
        return employeeService.findAll();
    }

    // build get employee by id REST API
    @GetMapping("{theId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer theId){
        System.out.println("Controller Layer - getEmployeeById method");
        Employee employee = employeeService.findById(theId);
        if (employee == null){
            throw new RuntimeException ("Employee not exist with id: " + theId);
        }
        return ResponseEntity.ok(employee);
    }

    // build create employee REST API
    @PostMapping
    public Employee createEmployee(@RequestBody Employee theEmployee) {
        employeeService.save(theEmployee);
        return theEmployee;
    }

    // build update employee by id REST API
    @PutMapping("{theId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer theId,@RequestBody Employee theEmployee) {
        Employee updateEmployee = employeeService.findById(theId);
        if (updateEmployee == null){
            throw new RuntimeException ("Employee not exist with id: " + theId);
        }else{
            updateEmployee.setFirstName(theEmployee.getFirstName());
            updateEmployee.setLastName(theEmployee.getLastName());
            updateEmployee.setEmail(theEmployee.getEmail());
            employeeService.save(updateEmployee);
        }
        return ResponseEntity.ok(updateEmployee);
    }

    // delete employee by id
    @DeleteMapping("{theId}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer theId) {
        Employee employee = employeeService.findById(theId);
        if (employee == null){
            throw new RuntimeException ("Employee not exist with id: " + theId);
        }else{
            employeeService.deleteById(theId);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
