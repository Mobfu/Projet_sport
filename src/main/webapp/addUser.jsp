<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<title>AddUser</title>
</head>
<body>

	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white">Please enter
				your information for Inscription</div>
			<div class="card-body">
				<form action="AddUser" method="post">
					<h4 class="class-title">UserName:</h4>
					<label for="name"></label> <input type="text" id="name" name="name"
						required><br>

					<h4 class="class-title">Password:</h4>
					<label for="password"></label> <input type="password" id="password"
						name="password" required><br>
					<h4></h4>
					<ul class="list-group">
						<li class="list-group-item"><input
							class="form-check-input me-1" type="radio" name="role"
							value="Elu" id="role_1" checked> <label
							class="form-check-label" for="role_1">Elu</label></li>
						<li class="list-group-item"><input
							class="form-check-input me-1" type="radio" name="role"
							value="Acteur" id="role_2"> <label
							class="form-check-label" for="role_2">Acteur</label></li>
						<li class="list-group-item"><input
							class="form-check-input me-1" type="radio" name="role"
							value="User" id="role_3"> <label class="form-check-label"
							for="role_3">User</label></li>
					</ul>


					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
				<%
				if (session != null && session.getAttribute("addFailed") != null && (Boolean) session.getAttribute("addFailed")) {
				%>
				<div class="alert alert-danger" role="alert">
					<%=session.getAttribute("messageF")%>
				</div>
				<%
				}
				session.removeAttribute("messageF");
				%>


			</div>
		</div>
		<a href="Login.jsp">retourne Login</a>
	</div>
</body>
</html>