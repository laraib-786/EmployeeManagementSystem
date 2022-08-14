package com.example.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.exception.ResourceNotFoundException;
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
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping(value="/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
		Employee employee=employeeRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("Employee does not exist with id: "+id));
		return ResponseEntity.ok(employee);
		
	}

}
