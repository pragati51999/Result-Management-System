<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="bootstrap.min.css">
<script src="bootstrap.min.js"></script>
<link rel="stylesheet" href="style.css">
<title>Results Site| Student</title>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Logo</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="logout.jsp">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<br>
	<div class="container">
		<h2>Results for ${user.className} - ${user.firstName} ${user.lastName}</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Subject</th>
					<th>Marks</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${user.resultMap}" var="result">
					<tr>
						<td>${result.key}</td>
						<td>${result.value}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
			        <tr>
						<th>Total</th>
						<th>${user.totalMarks}</th>
					</tr>
					<tr>
						<th>Percentage</th>
						<th>${user.percentage}</th>
					</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>