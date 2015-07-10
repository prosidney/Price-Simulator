package com.ibm.pricesimulator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.pricesimulator.data.Employee;
 
@Service
public class PersonServiceImpl {
	
	public List<Employee> getEmployees(){
		List<Employee> ret = new ArrayList<Employee>();
		
		for(int i = 1 ; i <= 10 ; i++){
			Employee emp = new Employee();
			emp.setEmployeeId(String.valueOf(i));
			emp.setEmployeeName("Employee#"+i);
			
			ret.add(emp);
		}
		
		return ret;
	}
}
