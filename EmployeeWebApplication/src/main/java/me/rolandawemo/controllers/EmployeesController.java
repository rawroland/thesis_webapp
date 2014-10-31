package me.rolandawemo.controllers;

import java.util.ArrayList;
import java.util.Map;

import me.rolandawemo.clients.implementations.EmployeeManagementClient;
import me.rolandawemo.models.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/employees/")
public class EmployeesController {

	private ArrayList<String> employeeTypes;
	@Autowired
	private EmployeeManagementClient employeeClient;

	public EmployeesController() {
		this.employeeTypes = new ArrayList<String>();
		this.employeeTypes.add("cashier");
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, @ModelAttribute("flashMessage") String flashMessage) {
		model.addAttribute("employees", this.employeeClient.list());
		model.addAttribute("flashMessage", flashMessage);
		return "employees/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("employeeTypes", this.employeeTypes);
		return "employees/add";
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public String addEmployee(@ModelAttribute Employee employee, Model model, RedirectAttributes attributes) {
		if (this.employeeClient.add(employee.getGivenname(),
				employee.getSurname(), employee.getUsername(),
				employee.getRole())) {
			attributes.addFlashAttribute("flashMessage", "Employee was successfully added.");
			return "redirect:/employees/list";
		} else {
			attributes.addFlashAttribute("flashMessage", "Employee was successfully added.");
			return "redirect:/employees/add";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam Map<String, String> parameters) {
		int id = Integer.parseInt(parameters.get("id"));
		Employee employee = this.employeeClient.getEmployeeById(id);
		model.addAttribute("employee", employee);
		model.addAttribute("employeeTypes", this.employeeTypes);
		return "employees/edit";
	}
	
	@RequestMapping(value = "/editEmployee", method = RequestMethod.POST)
	public String editEmployee(@ModelAttribute Employee employee, Model model, RedirectAttributes attributes) {
		if (this.employeeClient.edit(employee.getId(), employee.getGivenname(),
				employee.getSurname(), employee.getUsername(),
				employee.getRole())) {
			attributes.addFlashAttribute("flashMessage", "Employee was successfully edited.");
			return "redirect:/employees/list";
		} else {
			attributes.addFlashAttribute("flashMessage", "An error occurred while editing the employee.");
			return "redirect:/employees/edit?id=" + employee.getId();
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Model model, @RequestParam Map<String, String> parameters, RedirectAttributes attributes) {
		int id = Integer.parseInt(parameters.get("id"));
		boolean deleted = this.employeeClient.deleteEmployee(id);
		if (deleted) {
			attributes.addFlashAttribute("flashMessage", "Employee was successfully deleted.");
		} else {
			attributes.addFlashAttribute("flashMessage", "An error occurred while deleting the employee.");
		}
		return "redirect:/employees/list";
	}

	public EmployeeManagementClient getEmployeeClient() {
		return employeeClient;
	}

	public void setEmployeeClient(EmployeeManagementClient client) {
		this.employeeClient = client;
	}
}
