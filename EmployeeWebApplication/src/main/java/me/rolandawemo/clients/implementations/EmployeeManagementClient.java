package me.rolandawemo.clients.implementations;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import me.rolandawemo.clients.interfaces.IEmployeeManagement;
import me.rolandawemo.models.Employee;
import ws.services.EmployeeManagementService;

public class EmployeeManagementClient implements IEmployeeManagement {

	public static final String SERVICE_LOCATION = "http://localhost:9001/dm/employees?wsdl";
	public static final String NAMESPACE_URI = "http://ws/";
	public static final String NAMESPACE_PORT = "DMEmployeeManagementPort";

	public static final QName SERVICE_NAME = new QName(NAMESPACE_URI,
			"DMEmployeeManagementService");
	private Service service;
	public EmployeeManagementClient() {
		super();
	}

	public EmployeeManagementService connect() throws MalformedURLException {
		service = Service.create(new URL(SERVICE_LOCATION), SERVICE_NAME);
		return service.getPort(EmployeeManagementService.class);
	}

	@Override
	public boolean add(String givenname, String surname, String username,
			String role) {

		try {
			return this.connect().addEmployee(givenname, surname, username, role);
		} catch (MalformedURLException e) {
			// TODO  LOG
			return false;
		}

	}

	public boolean edit(int id, String givenname, String surname,
			String username, String role) {
		try {
			return this.connect().editEmployee(id, givenname, surname, username,
					role);
		} catch (MalformedURLException e) {
			// TODO  LOG
			return false;
		}
	}

	@Override
	public ArrayList<Employee> list() {
		try {
			return this.connect().getAllEmployees();
		} catch (MalformedURLException e) {
			// TODO  LOG
			return new ArrayList<Employee>();
		}
	}

	@Override
	public Employee getEmployeeById(int id) {
		try {
			return this.connect().searchEmployeesById(id);
		} catch (MalformedURLException e) {
			// TODO  LOG
			return new Employee();
		}
	}

	@Override
	public boolean deleteEmployee(int id) {
		try {
			return this.connect().deleteEmployee(id);
		} catch (MalformedURLException e) {
			// TODO  LOG
			return false;
		}
	}

}
