package com.ssk.spring_mvc_crud_pagination.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

	@GetMapping("/viewemp")
	public String viewEmployees() {
		return "view_emp";
	}
	
	@GetMapping("/empform")
	public String addEmployee() {
		return "emp_form";
	}
}
