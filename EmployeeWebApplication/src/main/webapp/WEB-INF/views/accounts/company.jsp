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
<title>Distribution Manager | Company Accounts</title>

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
		<a class="navbar-brand" href="/">Distribution Manager</a>
	</div>
	<!-- /.navbar-header -->

	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#">
				<i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li>
					<a href="#">
						<i class="fa fa-user fa-fw"></i>
						User Profile
					</a>
				</li>
				<li class="divider"></li>
				<li>
					<a href="login.html">
						<i class="fa fa-sign-out fa-fw"></i>
						Logout
					</a>
				</li>
			</ul>
			<!-- /.dropdown-user -->
		</li>
		<!-- /.dropdown -->
	</ul>
	<!-- /.navbar-top-links -->

	<div class="navbar-default sidebar" role="navigation">
		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
				<li>
					<a href="#">
						<i class="fa fa-user fa-fw"></i>
						Clients
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="/clients/add">Add Client</a>
						</li>
						<li>
							<a href="/clients/list">Clients List</a>
						</li>
					</ul>
					<!-- /.nav-second-level -->
				</li>
				<li>
					<a href="#">
						<i class="fa fa-money fa-fw"></i>
						Accounts
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="/accounts/company">Company</a>
						</li>
						<li>
							<a href="/accounts/consumers">Consumers</a>
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
				<h1 class="page-header">Company</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="col-lg-12">
			<c:if test="${not empty flashMessage}">
				<div class="alert ${flashClass} alert-dismissable">${flashMessage}</div>
			</c:if>
			<div class="row">
				<div class="table-responsive col-lg-12">
					<table class="table table-striped table-bordered table-hover"
						id="datatables">
						<thead>
							<tr>
								<th>Name</th>
								<th>Company</th>
								<th>Ammount</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${empty clients}">
								<tr>
									<td>No Clients found.</td>
								</tr>
							</c:if>
							<c:if test="${not empty clients}">
								<c:forEach items="${clients}" var="client">
									<tr>
										<td>${client.prefix} ${client.firstName}
											${client.lastName}</td>
										<td>${client.company}</td>
										<td>
											<c:choose>
												<c:when test="${0 eq client.account.id}"> Please add an account.</c:when>
												<c:otherwise>${client.account.ammount}</c:otherwise>
											</c:choose>
										</td>
										<td>
											<!-- add account if it does not exist -->
											<c:if test="${0 eq client.account.id}">
												<a href="/accounts/add?clientId=${client.id}"
													title="Add Account">
													<i class="fa fa-plus fa-fw fa-lg"></i>
												</a>
											</c:if>
											<c:if test="${0 < client.account.id}">
												<a href="/accounts/credit?clientId=${client.id}"
													title="Credit Account">
													<i class="fa fa-plus-square fa-fw fa-lg"></i>
												</a>
												<a href="/accounts/debit?clientId=${client.id}"
													title="Debit Account">
													<i class="fa fa-minus-square fa-fw fa-lg"></i>
												</a>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
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

	<!-- DataTables JavaScript -->
	<script
		src="../resources/js/sbadmin/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script
		src="../resources/js/sbadmin/js/plugins/dataTables/dataTables.bootstrap.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../resources/js/sbadmin/js/sb-admin-2.js"></script>

	<script>
		$(document).ready(function() {
			$('#datatables').dataTable();
		});
	</script>
</body>
</html>