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
<c:forEach var="emp" items="${employees}">   
<tr>
<td>${emp.id}</td>
<td>${emp.name}</td>
<td>${emp.salary}</td>
<td>${emp.designation}</td>
<td><a href="editemp/${emp.id}">Edit</a></td>
<td><a href="deleteemp/${emp.id}">Delete</a></td></tr>  
</c:forEach>  
</table>
</div>
</body>
</html>