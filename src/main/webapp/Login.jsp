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
        /* Style pour l'arriÃ¨re-plan */
        body {
            /* définir l'image comme arrière paln ' */
            background-image: url('./image/card2.jpg');
            /* Centrer et attirer l'image pour remplir tout l'écran */
            background-size: cover;
           /* Pour que je puisse positionner l'image au centre de l'ÃÂ©cran */
            background-position: center;
            /* RÃÂ©pÃÂ©ter l'image si nÃ©cessaire */
            background-repeat: no-repeat;
        }
    </style>
    
<title>Login</title>
</head>
<body>
<%
	if (session != null && session.getAttribute("LogFlag") != null) {
		if ((boolean) session.getAttribute("LogFlag") != true) {
	%>
	<jsp:include page="Menu.jsp" />
	<%
	} else {
	%>
	<jsp:include page="Menu_conn.jsp" />
	<%
	}
	}
	%>
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
							class="form-check-label" for="role_1">Élu</label></li>
						<li class="list-group-item"><input
							class="form-check-input me-1" type="radio" name="role"
							value="Acteur" id="role_2"> <label
							class="form-check-label" for="role_2">Acteur du monde sportif</label></li>
					</ul>

					<button type="submit" class="btn btn-primary">Valider</button>
				</form>
				<br> <a href='./addUser.jsp'>
					<button type="text" class="btn btn-primary">Inscription</button>
				</a>
			<br>
<<<<<<< HEAD
            <a href='./Motdepasseoubli�.jsp'>
                <button type="button" class="btn btn-secondary mt-2">Mot de passe oubli�?</button>
=======
            <a href='./MotDepasseOublie.jsp'>
 
                <button type="button" class="btn btn-secondary mt-2">Mot de passe oublié?</button>
 
>>>>>>> ddb91e8f63e823c141166f8c5a8199967734ef62
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
            <p>Ce site utilise des cookies. En continuant à  naviguer sur ce site, vous acceptez notre utilisation des cookies.</p>
        </div>
        <%
        session.removeAttribute("isFirstVisit");
        %>
        <%
        }
        %>


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
