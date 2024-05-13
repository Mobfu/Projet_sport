 <%@ page import="java.util.List"%>
<%@ page import="dao.DBDAO"%>
<%@ page import="Module.User"%>
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.css" />
<link rel="stylesheet" href="./Style/map.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
   
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
   
    <link rel="stylesheet" href="./Style/style.css"/>

<title>Login</title>
</head>
<body>

	<jsp:include page="Menu.jsp" />
	
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
				your login information</div>
			<div class="card-body">
				<form action="UserLogin" method="post">
					<h4 class="card-title">UserName:</h4>
					<label for="user"></label> <input type="text" id="user" name="user"
						required><br>
					<h4 class="card-title">Password:</h4>
					<label for="password"></label> <input type="password" id="password"
						name="password" required>
					<ul class="list-group">
						<li class="list-group-item"><input
							class="form-check-input me-1" type="radio" name="role"
							value="Elu" id="role_1" checked> <label
							class="form-check-label" for="role_1">Elu</label></li>
						<li class="list-group-item"><input
							class="form-check-input me-1" type="radio" name="role"
							value="Acteur" id="role_2"> <label
							class="form-check-label" for="role_2">Acteur</label></li>
					</ul>

					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
				<br> <a href='./addUser.jsp'>
					<button type="text" class="btn btn-primary">Inscription</button>
				</a>
			</div>

			<%
			if (session.getAttribute("isFirstVisit") != null && (boolean) session.getAttribute("isFirstVisit")) {
			%>
			<div class="alert alert-primary" role="alert">
				<p>This site uses cookies. By continuing to browse this site,
					you are agreeing to our use of cookies.</p>
			</div>
			<%
			session.removeAttribute("isFirstVisit");
			%>
			<%
			}
			%>

			<%
			if (session != null && session.getAttribute("addSucce") != null && (Boolean) session.getAttribute("addSucce")) {
			%>
			<div class="alert alert-success" role="alert">
				<%=session.getAttribute("messageS")%>
			</div>
			<%
			}
			session.removeAttribute("messageS");
			%>

		</div>

	</div>
</body>
</html>