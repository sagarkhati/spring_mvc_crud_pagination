package com.ssk.spring_mvc_crud_pagination.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssk.spring_mvc_crud_pagination.beans.Employee;
import com.ssk.spring_mvc_crud_pagination.dao.EmployeeDao;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeDao empDao;
	
	@GetMapping("/viewemp")
	public String viewEmployees(Model model) {
		List<Employee> employees = empDao.getEmployees();
		model.addAttribute("employees", employees);
		return "view_emp";
	}
	
	@GetMapping("/empform")
	public String addEmployee(Model model) {
		model.addAttribute("command", new Employee());
		return "emp_form";
	}
}
