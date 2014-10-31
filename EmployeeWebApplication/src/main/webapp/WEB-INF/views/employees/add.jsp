<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Distribution Manager|Add Employee</title>
</head>
<body>
	<h2>Add Employee</h2>
	<c:if test="${not empty flashMessage}">
		<div>${flashMessage}</div>
	</c:if>
	<form:form action="addEmployee" modelAttribute="employee">
		<label for="givenname">First Name: </label>
		<form:input id="givenname" path="givenname" type="text" required="required"/><br>
		<label for="surname"> Surname: </label> 
		<form:input id="surname" path="surname" type="text" required="required"/> <br>
		<label for="username"> Username: </label>
		<form:input id="username" path="username" type="text" required="required"/> <br>
		<label for="role"> Role: </label> 
		<form:select id="role" path="role" items="${employeeTypes}" /><br>
		<button type="submit" name="add" value="Add Employee">Add Employee</button>
	</form:form>
</body>
</html>