<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee List</title>
</head>
<body>
	<div>
		<h1>Employee List</h1>
		<table border="2" width="70%" cellpadding="2">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Salary</th>
				<th>Designation</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="emp" items="${employeesByPage}">
				<tr>
					<td>${emp.id}</td>
					<td>${emp.name}</td>
					<td>${emp.salary}</td>
					<td>${emp.designation}</td>
					<td><a href="/editemp/${emp.id}">Edit</a></td>
					<td><a href="/deleteemp/${emp.id}">Delete</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6">
					<div style="display: flex; justify-content: space-between;">
						<span style="padding-left: 8px">Total Employees:
							${totalEmployees}</span> <span style="padding-right: 8px"> <c:forEach
								var="i" begin="1" end="${noOfPages}">
								<a href="/viewemp/${i}">${i}</a>
							</c:forEach>
						</span>
					</div>
				</td>
			</tr>
		</table>
		<br /> <a href="/empform">Add Employee</a>
	</div>
</body>
</html>