package com.ibm.pricesimulator.jsf.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.pricesimulator.data.Employee;
import com.ibm.pricesimulator.service.PersonServiceImpl;

@Component
@ManagedBean
@SessionScoped
public class ViewEmployeesManagedBean {
	
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	private List<Employee> employees = new ArrayList<Employee>();
	
	public ViewEmployeesManagedBean(){
		
	}
	
	@PostConstruct
	public void populateEmployeeList(){
		this.employees.addAll(personServiceImpl.getEmployees());
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	
}
