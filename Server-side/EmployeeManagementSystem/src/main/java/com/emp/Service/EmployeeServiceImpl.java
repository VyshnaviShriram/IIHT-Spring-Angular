package com.emp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.Entity.Employee;
import com.emp.Repo.IEmployeeRepo;
import com.emp.exception.ResourceNotFoundExceptionHandler;

@Service
public class EmployeeServiceImpl implements IEmployeeService{
	
	@Autowired
	IEmployeeRepo employeeRepo;

	@Override
	public Integer saveEmployee(Employee employee) {
		Employee savedEmployee = employeeRepo.save(employee);
		return savedEmployee.getId();
	}

	@Override
	public List<Employee> getAllEMployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee updateEmployee(Employee employee, Integer id) {
		
		Employee existingEmployee = employeeRepo.findById(id).orElseThrow(
				()->new ResourceNotFoundExceptionHandler("Employee", "id", id));
		existingEmployee.setEmail(employee.getEmail());
		existingEmployee.setEname(employee.getEname());
		existingEmployee.setEposition(employee.getEposition());
		existingEmployee.setSalary(employee.getSalary());
		employeeRepo.save(existingEmployee);
		return existingEmployee;		
	}

	@Override
	public Optional<Employee> getEMployee(Integer id) {
		return employeeRepo.findById(id);
	}

	@Override
	public void deleteEMployee(Integer id) {
		employeeRepo.deleteById(id);
		
	}
	
	

}
