<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Distribution Manager| Employees</title>
</head>
<body>
	<h2>List of Employees</h2>
	<c:if test="${not empty flashMessage}">
		<div>${flashMessage}</div>
	</c:if>
	<table>
		<thead>
			<tr>
				<th>Name</th>
				<th>Username</th>
				<th>Role</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty employees}">
				<tr><td>No Employees found.</td></tr>
			</c:if>
			<c:if test="${not empty employees}">
				<c:forEach items="${employees}" var="employee">
					<tr>
						<td>${employee.givenname} ${employee.surname}</td>
						<td>${employee.username}</td>
						<td>${employee.role}</td>
						<td>
							<a href="edit?id=${employee.id}">Edit </a>
							<a href="delete?id=${employee.id}">Delete </a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</body>
</html>