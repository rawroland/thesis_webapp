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
<title>Distribution Manager | Report</title>

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
				<li>
					<a href="#">
						<i class="fa fa-cogs fa-fw"></i>
						Products
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="/products/add">Add Product</a>
						</li>
						<li>
							<a href="/products/list">Products</a>
						</li>
					</ul>
					<!-- /.nav-second-level -->
				</li>
				<li>
					<a href="#">
						<i class="fa fa-exchange fa-fw"></i>
						Transactions
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="/transactions/purchase">Save Purchase</a>
						</li>
						<li>
							<a href="/transactions/sale">Save Sale</a>
						</li>
					</ul>
					<!-- /.nav-second-level -->
				</li>
				<li>
					<a href="#">
						<i class="fa fa-bar-chart-o fa-fw"></i>
						Reports
						<span class="fa arrow"></span>
					</a>
					<ul class="nav nav-second-level">
						<li>
							<a href="/reports/create">Create Reporting Group</a>
						</li>
						<li>
							<a href="/reports/saleReport">Generate Sales Report</a>
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
				<h1 class="page-header">Transactions</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<div class="row">
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
									<th>Date</th>
									<th>Client</th>
									<th>Product</th>
									<th>Amount</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${empty transactions}">
									<tr>
										<td>No transactions found.</td>
									</tr>
								</c:if>
								<c:if test="${not empty transactions}">
									<c:forEach items="${transactions}" var="transaction">
										<tr>
											<td>${transaction.date}</td>
											<td>${transaction.account.client.company}</td>
											<td>${transaction.product.name}</td>
											<td>${transaction.cost}</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">Complete Percentages</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="flot-chart">
							<div class="flot-chart-content" id="chart"></div>
						</div>
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
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


	<!-- Flot Charts JavaScript -->
	<script src="../resources/js/sbadmin/js/plugins/flot/excanvas.min.js"></script>
	<script src="../resources/js/sbadmin/js/plugins/flot/jquery.flot.js"></script>
	<script
		src="../resources/js/sbadmin/js/plugins/flot/jquery.flot.pie.js"></script>
	<script
		src="../resources/js/sbadmin/js/plugins/flot/jquery.flot.resize.js"></script>
	<script
		src="../resources/js/sbadmin/js/plugins/flot/jquery.flot.tooltip.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../resources/js/sbadmin/js/sb-admin-2.js"></script>

	<script type="text/javascript">
		jQuery.browser = {};
		(function() {
			jQuery.browser.msie = false;
			jQuery.browser.version = 0;
			if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
				jQuery.browser.msie = true;
				jQuery.browser.version = RegExp.$1;
			}
		})();
		$(document).ready(
				function() {
					table = $('#datatables').dataTable({
						bFilter : false
					});
					data = table.fnGetData();
					chartData = {};
					$.each(data, function(index, value) {
						if (chartData.hasOwnProperty(value[1])) {
							chartData[value[1]] = chartData[value[1]]
									+ parseInt(value[3]);
						} else {
							chartData[value[1]] = parseInt(value[3]);
						}
					});
					chart = [];
					$.each(chartData, function(index, value) {
						temp = {
							label : index,
							data : value
						};
						chart.push(temp);
					});
					var plotObj = $.plot($("#chart"), chart, {
						series : {
							pie : {
								show : true
							}
						},
						grid : {
							hoverable : true
						},
						tooltip : true,
						tooltipOpts : {
							content : "%p.0%, %s", // show percentages, rounding to 2 decimal
							// places
							shifts : {
								x : 20,
								y : 0
							},
							defaultTheme : false
						}
					});
				});
	</script>
</body>
</html>