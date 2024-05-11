package com.srang.springcommandlinerunner;

import com.srang.springcommandlinerunner.employee.Employee;
import com.srang.springcommandlinerunner.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringCommandLineRunnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCommandLineRunnerApplication.class, args);
		System.out.println("This is post run");
	}

	@Autowired
	private EmployeeRepository employeeRepository;


	//Using commandlinerunner as Bean
	@Bean
	public CommandLineRunner startUp(){
		return args -> {
			for(int i = 1; i<=3 ; i++){
				Employee emp1 = new Employee();
				emp1.setFirstName("FirstName" +i);
				emp1.setLastName("LastName" + i);
				emp1.setSalary(125000.00 + i);
				emp1.setJoiningDate(LocalDate.of(2021,02,12).plusYears(i));
				employeeRepository.save(emp1);
			}
		};
	}

/*
	//override commandlinerunner via implementing the CommandLiner in the Main Class
	@Override
	public void run(String... args) throws Exception {
		System.out.println("This is from run method");

		for(int i = 1; i<=3 ; i++){
			Employee emp1 = new Employee();
			emp1.setFirstName("FirstName" +i);
			emp1.setLastName("LastName" + i);
			emp1.setSalary(125000.00 + i);
			emp1.setJoiningDate(LocalDate.of(2021,02,12).plusYears(i));
			employeeRepository.save(emp1);
		}
	}*/
}
