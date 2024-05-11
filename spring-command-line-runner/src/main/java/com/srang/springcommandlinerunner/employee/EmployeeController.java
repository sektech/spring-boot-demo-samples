package com.srang.springcommandlinerunner.employee;


import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> findAll(){
        List<Employee> employees = employeeService.findAll();
        if(employees.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        Employee newEmployee = employeeService.save(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getJoiningDate());
        if (newEmployee != null)
            return new ResponseEntity<>(newEmployee,HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id){
        Employee employ = employeeService.findById(id);
        if( employ!=null) {
            employeeService.delete(employ);
            return new ResponseEntity<>("Employee record deleted successfully",HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Employee with id "+ id + " not found",HttpStatus.NOT_FOUND);

    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> update(@PathVariable("id") long id, @RequestBody Employee tobeUpdatedEmployee){
        Employee employ = employeeService.findById(id);
        if( employ!=null) {
            employ.setFirstName(tobeUpdatedEmployee.getFirstName());
            employ.setLastName(tobeUpdatedEmployee.getLastName());
            employ.setSalary(tobeUpdatedEmployee.getSalary());
            employ.setJoiningDate(tobeUpdatedEmployee.getJoiningDate());
            employeeService.update(employ);
            return new ResponseEntity<>(employ,HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
