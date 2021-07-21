package io.sunuh.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sunuh.springboot.entity.DAOEmployee;

import io.sunuh.springboot.exception.ResourceNotFoundException;

import io.sunuh.springboot.repository.EmployeeRepository;


@CrossOrigin
@RestController
@RequestMapping("api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get All employee
	@GetMapping("/employees")
	public List<DAOEmployee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	//create employee rest api
	@PostMapping("/employees")
	public DAOEmployee createEmployee(@RequestBody DAOEmployee employee) {
		return employeeRepository.save(employee);
	}
	
	//get employee by Id rest api
	@GetMapping("/employees/{id}")
	public ResponseEntity<DAOEmployee> getEmployeeById(@PathVariable Long id) {
		DAOEmployee employee = employeeRepository
				.findById(id)
				.orElseThrow(
						() -> new ResourceNotFoundException("Employee not exist with id : " + id)
					);
		return ResponseEntity.ok(employee);
	}
	
	//update employee rest api
	@PutMapping("/employees/{id}")
	public ResponseEntity<DAOEmployee> updateEmployee(@PathVariable Long id, @RequestBody DAOEmployee employeeDetails) {
		DAOEmployee employee = employeeRepository
				.findById(id)
				.orElseThrow(
						() -> new ResourceNotFoundException("Employee not exist with id : " + id)
					);
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		DAOEmployee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	//delete employee rest api
	@DeleteMapping("employees/{id}")
	public ResponseEntity <Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		DAOEmployee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exis with id : " + id ));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
}
