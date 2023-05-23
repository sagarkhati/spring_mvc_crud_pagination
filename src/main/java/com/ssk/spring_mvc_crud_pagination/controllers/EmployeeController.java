package com.ssk.spring_mvc_crud_pagination.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

	@GetMapping({"/empform", "editemp/{id}"})
	public String addEmployee(@PathVariable(required = false) Integer id, Model model) {
		Employee employee = null;
		if(id == null) {
			employee = new Employee();
		} else {
			employee = empDao.getEmployee(id);    
		}
		model.addAttribute("command", employee);
		return "emp_form";
	}

	@PostMapping("/saveEmp")
	public String save(@ModelAttribute("employee") Employee employee) {
		empDao.save(employee);
		return "redirect:/viewemp";
	}
}
