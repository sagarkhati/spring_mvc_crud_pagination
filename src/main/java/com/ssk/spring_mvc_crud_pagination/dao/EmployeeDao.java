package com.ssk.spring_mvc_crud_pagination.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ssk.spring_mvc_crud_pagination.beans.Employee;

@Component
public class EmployeeDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Employee> getEmployees() { 
		List<Employee> employees = jdbcTemplate.query("SELECT * FROM employee", new RowMapper<Employee>(){    
	        public Employee mapRow(ResultSet rs, int row) throws SQLException {    
	        	Employee e = new Employee();    
	            e.setId(rs.getInt(1));    
	            e.setName(rs.getString(2));    
	            e.setSalary(rs.getInt(3));    
	            e.setDesignation(rs.getString(4));    
	            return e;    
	        }    
	    });
		
		return employees;
	}    
}
