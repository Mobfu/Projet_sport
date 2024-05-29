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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<link href="assets/css/bootstrap.min.css" rel="stylesheet" />

<link rel="stylesheet" href="./Style/style.css" />
<style>
/* Style pour l'arrière-plan */
body {
	/* Définir l'image comme arriÃ¨re-plan */
	background-image: url('./image/login.jpg');
	background-repeat: no-repeat;
}
</style>
<title>Login</title>
</head>
<body>

	<jsp:include page="Menu.jsp" />
	<%
	if (session != null && session.getAttribute("LoginFailed") != null) {
		Boolean LoginFailed = (Boolean) session.getAttribute("LoginFailed");
		if (LoginFailed) {
			session.setAttribute("info", "Mot de passe ou email incorrect ");
			session.removeAttribute("LoginFailed");
		}
	}
	%>
	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white">Veuillez entrer vos informations de connexion</div>
			<div class="card-body">
				<form action="UserLogin" method="post">
					<h4 class="card-title">E-mail:</h4>
					<label for="user"></label> <input type="text" id="email" name="email"
						required><br>
					<h4 class="card-title">Mot De passe:</h4>
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

					<button type="submit" class="btn btn-primary">Envoyez</button>
				</form>
				<br> <a href='./addUser.jsp'>
					<button type="text" class="btn btn-primary">Inscription</button>
				</a>
			<br>
            <a href='./MotDepasseOublie.jsp'>
                <button type="button" class="btn btn-secondary mt-2">Mot de passe oublié</button>
            </a>
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

        <%
        if (session.getAttribute("isFirstVisit") != null && (boolean) session.getAttribute("isFirstVisit")) {
        %>
        <div class="alert alert-primary" role="alert">
            <p>Ce site utilise des cookies. En continuant à naviguer sur ce site, vous acceptez notre utilisation des cookies.</p>
        </div>
        <%
        session.removeAttribute("isFirstVisit");
        %>
        <%
        }
        %>

<<<<<<< HEAD
			<%
			if (session != null && session.getAttribute("info") != null) {
			%>
			<div class="alert alert-danger" role="alert">
				<%=session.getAttribute("info")%>
			</div>
			<%
			}
			session.removeAttribute("info");
			%>
		</div>
		
		<jsp:include page="Footer.jsp" />
=======
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

<section class="footer py-5 d-flex justify-content-center">
    <div class="container">
        <div class="row">
            <div class="col-6 d-flex align-items-center">
                <p class="text-white">&copy; 2024 Rouen | Tous droits réservés.</p>
            </div>
            <div class="col-6">
                <ul class="nav text-center">
                    <li class="nav-item">
                        <a href="APropos.jsp" class="nav-link text-white">À propos</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link text-white">Recherche</a>
                    </li>
                    <li class="nav-item">
                        <a href="#" class="nav-link text-white">Contact</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</section>

<script src="assets/js/bootstrap.bundle.min.js"></script>
>>>>>>> 12881dfbba4f8c86ac4ebfd50eb73121401d942e

</body>
</html>
