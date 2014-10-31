package me.rolandawemo.clients.implementations;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import me.rolandawemo.clients.interfaces.IEmployeeManagement;
import me.rolandawemo.models.Employee;
import ws.services.EmployeeManagementService;

public class EmployeeManagementClient implements IEmployeeManagement{

	public static final String SERVICE_LOCATION = "http://localhost:9001/dm/employees?wsdl";
	public static final String NAMESPACE_URI = "http://ws/";
	public static final String NAMESPACE_PORT = "DMEmployeeManagementPort";
	
	public static final QName SERVICE_NAME = new QName(NAMESPACE_URI,"DMEmployeeManagementService");
	private Service service;
	private EmployeeManagementService employee;
	
	public EmployeeManagementClient() {
		try {
			service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		employee = service.getPort(EmployeeManagementService.class);
	}
	
	@Override
	public boolean add(String givenname, String surname, String username,
			String role) {
		
		return this.employee.addEmployee(givenname, surname, username, role);
		
	}

	public boolean edit(int id, String givenname, String surname,
			String username, String role) {
		return this.employee.editEmployee(id, givenname, surname, username, role);
	}

	@Override
	public ArrayList<Employee> list() {
		return this.employee.getAllEmployees();
	}

	@Override
	public Employee getEmployeeById(int id) {
		return this.employee.searchEmployeesById(id);
	}

	@Override
	public boolean deleteEmployee(int id) {
		return this.employee.deleteEmployee(id);
	}

}
