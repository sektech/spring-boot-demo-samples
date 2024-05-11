package com.srang.springcommandlinerunner.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee save(String fname, String lname, Double salary, LocalDate doj){
        Employee employee = new Employee();
        employee.setFirstName(fname);
        employee.setLastName(lname);
        employee.setSalary(salary);
        employee.setJoiningDate(doj);
        return employeeRepository.save(employee);
    }
    public void delete(Employee employee){
        employeeRepository.delete(employee);
    }
    public Employee findById(long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.isPresent() ? employee.get() : null;
    }
    public Employee update(Employee employee){
        return employeeRepository.save(employee);
    }
}
