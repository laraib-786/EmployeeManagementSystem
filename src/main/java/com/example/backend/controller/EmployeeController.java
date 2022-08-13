package com.example.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Employee;
import com.example.backend.repository.EmployeeRepository;



@RestController
@CrossOrigin
@RequestMapping("/api/v1/")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@GetMapping(value="/employees")
	public List<Employee> getAllEmployees(){
		List<Employee> employees=employeeRepository.findAll();
		return employees;
	}
	
	
	
	@PostMapping(value="/employees")
	public Employee allEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}

}
