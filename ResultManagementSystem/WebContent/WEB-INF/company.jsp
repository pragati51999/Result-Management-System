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
		<h2>Hi ${user.firstName} ${user.lastName}</h2>
		<h2>Eligible students for ${user.className}</h2>
		<c:forEach items="${eligibleUserMap}" var="result">
			<table class="table">
				<thead>
					<tr>
						<th>${result.key}</th>
					</tr>
					<tr>
						<th>Student</th>
						<th>Agg Marks</th>
						<th>Percentage</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${result.value}" var="userList">
						<tr>

							<td>${userList.userId}</td>
							<td>${userList.totalMarks}</td>
							<td>${userList.percentage}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:forEach>
	</div>
</body>
</html>