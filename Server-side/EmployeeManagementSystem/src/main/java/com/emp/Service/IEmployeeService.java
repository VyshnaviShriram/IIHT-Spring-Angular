package com.emp.Service;

import java.util.List;
import java.util.Optional;

import com.emp.Entity.Employee;

public interface IEmployeeService {

	Integer saveEmployee(Employee employee);
	
	Employee updateEmployee(Employee employee, Integer id);

	List<Employee> getAllEMployees();

	Optional<Employee> getEMployee(Integer id);

	void deleteEMployee(Integer id);
	
}
