package me.rolandawemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees/")
public class EmployeesController {

	@RequestMapping({"/add"})
	public String index() {
		return "/employees/add";
	}
}
