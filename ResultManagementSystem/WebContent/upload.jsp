<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="bootstrap.min.css">
<script src="bootstrap.min.js"></script>
<link rel="stylesheet" href="style.css">
<title>Results Site| Admin</title>
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
	<div>
		<h4>Hi ${user.firstName} ${user.lastName}</h4>
		<p style="color: red">${message}</p>
		<form action="fileUpload" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="sel1">Select:</label> <select class="form-control"
					id="sel1" name="classname">
					<option value="BCA_1st_Sem">BCA 1st Sem</option>
					<option value="BCA_2nd_Sem">BCA 2nd Sem</option>
					<option value="BCA_3rd_Sem">BCA 3rd Sem</option>
					<option value="BCA_4th_Sem">BCA 4th Sem</option>
					<option value="BCA_5th_Sem">BCA 5th Sem</option>
				</select>
			</div>
			<input type="file" name="results" size="50" /> <br /> <input
				type="submit" value="Upload" />
		</form>
	</div>

</body>
</html>