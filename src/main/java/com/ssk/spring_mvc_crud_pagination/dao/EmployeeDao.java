package com.ssk.spring_mvc_crud_pagination.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.ssk.spring_mvc_crud_pagination.beans.Employee;

@Component
public class EmployeeDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Employee> getEmployees() {
		List<Employee> employees = jdbcTemplate.query("SELECT * FROM employee", new RowMapper<Employee>() {
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

	public int save(Employee employee) {
		String sql = "";
		if (employee.getId() == 0) {
			sql = "INSERT INTO employee (name, salary, designation) values('" + employee.getName() + "',"
					+ employee.getSalary() + ",'" + employee.getDesignation() + "')";

		} else {
			sql = "UPDATE employee SET name='" + employee.getName() + "', salary=" + employee.getSalary()
					+ ",designation='" + employee.getDesignation() + "' where id=" + employee.getId() + "";
		}
		return jdbcTemplate.update(sql);
	}

	public Employee getEmployee(Integer id) {
		String sql = "SELECT * FROM employee WHERE id=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id },
				new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	public int delete(int id) {
		String sql = "DELETE FROM employee WHERE id=" + id + "";
		return jdbcTemplate.update(sql);
	}

	public List<Employee> getEmployeesByPage(int skip, int recordPerPage) {
		List<Employee> employees = jdbcTemplate.query("SELECT * FROM employee LIMIT " + skip + ", " + recordPerPage,
				new RowMapper<Employee>() {
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
	
	public int getTotalEmployees() {
		String sql = "SELECT COUNT(*) FROM employee";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
