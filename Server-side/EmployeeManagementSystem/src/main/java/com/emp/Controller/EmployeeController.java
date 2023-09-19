package com.emp.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emp.Entity.Employee;
import com.emp.Service.IEmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private IEmployeeService employeeService;
	
	
	@PostMapping("/add")
	Integer CreateEmployee(@RequestBody Employee employee) {
		Integer id= employeeService.saveEmployee(employee);
		System.out.println(id);
		return id;
	}
	
	@PutMapping("/update/{id}")
	ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable Integer id) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee,id),HttpStatus.OK);
	}
	
	@GetMapping("/getall")
	public List<Employee> getEmployees(){
		return employeeService.getAllEMployees();
		
	}
	
	@GetMapping("/get/{id}")
	public Optional<Employee> getEmployees(@PathVariable Integer id){
		Optional<Employee> emp = employeeService.getEMployee(id);
		return emp;
		
	}
	
	@DeleteMapping("/del/{id}")
	public ResponseEntity<Employee> deleteEmployees(@PathVariable Integer id){
		
		ResponseEntity<Employee> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		try {
			employeeService.deleteEMployee(id);
		}catch(Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
			

}
