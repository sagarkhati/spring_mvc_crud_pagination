package com.ssk.spring_mvc_crud_pagination.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@GetMapping({ "/empform", "editemp/{id}" })
	public String addEmployee(@PathVariable(required = false) Integer id, Model model) {
		Employee employee = null;
		if (id == null) {
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

	@GetMapping("/deleteemp/{id}")
	public String delete(@PathVariable int id) {
		empDao.delete(id);
		return "redirect:/viewemp";
	}

	@RequestMapping(value = "/viewemp/{pageid}")
	public String edit(@PathVariable int pageid, Model model) {
		int recordPerPage = 5;
		int skip = (pageid - 1) * recordPerPage;
		System.out.println(pageid + " -- " + skip + " -- " + recordPerPage);
//		List<Employee> list = empDao.getEmployeesByPage(pageid, total);
		List<Employee> employeesByPage = empDao.getEmployeesByPage(skip, recordPerPage);
		employeesByPage.forEach(emp -> System.out.println(emp.getId()));
		int totalEmployees = empDao.getTotalEmployees();
		System.out.println(totalEmployees);
		model.addAttribute("employeesByPage", employeesByPage);
		model.addAttribute("noOfPages", (int)Math.ceil(totalEmployees / 5.0));
		return "view_emp_by_page";
	}
}
