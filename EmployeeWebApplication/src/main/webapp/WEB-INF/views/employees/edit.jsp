<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Distribution Manager | Edit Employee</title>

<!-- Bootstrap Core CSS -->
<link href="../resources/css/sbadmin/css/bootstrap.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="../resources/css/sbadmin/css/plugins/metisMenu/metisMenu.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="../resources/css/sbadmin/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="../resources/css/sbadmin/font-awesome-4.1.0/css/font-awesome.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-static-top" role="navigation"
		style="margin-bottom: 0">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="index.html">Distribution Manager</a>
	</div>
	<!-- /.navbar-header -->


	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li>
					<a href="#">
						<i class="fa fa-users fa-fw"></i>
						Employees
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="/employees/add">Add Employee</a>
						</li>
						<li>
							<a href="/employees/list">Employees List</a>
						</li>
					</ul>
					<!-- /.nav-second-level -->
				</li>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side --> </nav>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Edit Employee</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Employee Information</div>
				<div class="panel-body">
					<c:if test="${not empty flashMessage}">
						<div class="alert ${flashClass} alert-dismissable">${flashMessage}</div>
					</c:if>
					<div class="row">
						<div class="col-lg-6">
							<form:form action="editEmployee" modelAttribute="employee"
								role="form">
								<div class="form-group">
									<label for="givenname"> Name: </label>
									<form:input class="form-control" id="givenname"
										path="givenname" type="text" required="required" />
								</div>
								<div class="form-group">
									<label for="surname"> Surname: </label>
									<form:input class="form-control" id="surname" path="surname"
										type="text" required="required" />
								</div>
								<div class="form-group">
									<label for="type">Role: </label>
									<form:select class="form-control" id="role" path="role"
										items="${employeeTypes}" />
								</div>
								<form:hidden path="id"/>
								<button type="submit" name="add" value="Edit Employee"
									class="btn btn-default">Edit Employee</button>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- jQuery Version 1.11.0 -->
	<script src="../resources/js/sbadmin/js/jquery-1.11.0.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="../resources/js/sbadmin/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="../resources/js/sbadmin/js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../resources/js/sbadmin/js/sb-admin-2.js"></script>
</body>
</html>