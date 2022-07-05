package com.example.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.backend.model.Employee;
import com.example.backend.repository.EmployeeRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
//@EnableAutoConfiguration(exclude=SecurityAutoConfiguration.class)
//@AutoConfigureTestDatabase
public class EmployeeControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@BeforeEach
	public void beforeEach() {
		employeeRepository.deleteAll();
		employeeRepository.save(new Employee("priya@gmail.com","Priya","Mehra"));
		System.out.println(employeeRepository.count());
	employeeRepository.save(new Employee("sneha@gmail.com","Sneha","Mehra"));
		
	}
	
	@AfterEach
	public void AfterEach() {
		employeeRepository.deleteAll();
	}	
	
	
	@Test
	public void should_give_all_employee_lists() throws Exception {

		 mockMvc.perform( MockMvcRequestBuilders
			      .get("/api/v1/employees")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.[1].firstName").value("Sneha"));
	}
	

}
