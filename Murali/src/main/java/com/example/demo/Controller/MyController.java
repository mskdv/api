package com.example.demo.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Employee;
import com.example.demo.repo.EmployeeRepo;

@RestController
@RequestMapping("/api")

public class MyController {


	@Autowired
	EmployeeRepo employeeRepository;
	@GetMapping("/employeeread")
	public List<Employee> get(){
		return employeeRepository.findAll();
	}
	
	@PostMapping("/employeeadd")
	public Employee save(@RequestBody Employee employeeObj) {
		return employeeRepository.save(employeeObj);
	}
	
	@GetMapping("/employee/{id}")
	public Employee get(@PathVariable int id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}else {
			throw new RuntimeException("Employee not found for the id "+id);
		}
	}
	
	@DeleteMapping("/employee/{id}")
	public String delete(@PathVariable int id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
			employeeRepository.delete(employee.get());
			return "Employee is deleted with id "+id;
		}else {
			throw new RuntimeException("Employee not found for the id "+id);
		}
	}
	
	@PutMapping("/employee")
	public Employee update(@RequestBody Employee employeeObj) {
		return employeeRepository.save(employeeObj);
	}
	
}
