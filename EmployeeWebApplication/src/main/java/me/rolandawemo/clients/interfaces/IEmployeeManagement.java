package me.rolandawemo.clients.interfaces;

import java.util.ArrayList;

import me.rolandawemo.models.Employee;

public interface IEmployeeManagement {
	
	public boolean add(String givenname, String surname, String username, String role);
	
	public ArrayList<Employee> list();
	
	public Employee getEmployeeById(int id);

	boolean deleteEmployee(int id);
}
