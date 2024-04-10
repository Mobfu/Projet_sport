<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<title>Login</title>
</head>
<body>
	<%
	if (session != null && session.getAttribute("LoginFailed") != null) {
		Boolean LoginFailed = (Boolean) session.getAttribute("LoginFailed");
		if (LoginFailed) {
			session.setAttribute("info", "Invalid login or password");
			session.removeAttribute("LoginFailed");
		}
	}
	session.setAttribute("isFirstVisit", true);
	%>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white">Please enter
				your admin login information</div>
			<div class="card-body">
				<form action="AdminLogin" method="post">
					<h4 class="card-title">UserName:</h4>
					<label for="user"></label> <input type="text" id="user" name="user"
						required><br>
					<h4 class="card-title">Password:</h4>
					<label for="password"></label> <input type="password" id="password"
						name="password" required>

					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
			<%
			if (session.getAttribute("info") != null) {
			%>
			<div class="alert alert-danger" role="alert">
				<%=session.getAttribute("info")%>
			</div>
			<%
			}
			%>

		</div>

	</div>
</body>
</html>