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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.css" />
<link rel="stylesheet" href="./Style/map.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
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
<title>AddUser</title>
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


	<div class="container">
		<div class="card">
			<div class="card-header bg-primary text-white">Veuillez entrer les informations suivantes afin de créer un compte</div>
			<div class="card-body">
				<form action="AddUser" method="post">
					<h4 class="class-title">Nom d'utilisateur :</h4>
					<label for="name"></label> <input type="text" id="name" name="name"
						required><br>

					<h4 class="class-title">Mot de passe :</h4>
					<label for="password"></label> <input type="password" id="password"
						name="password" required><br>
						
					<h4 class="class-title">E-mail:</h4>
					<label for="password"></label> <input type="email" id="email"
						name="email" required><br>
						
					<h4></h4>
					<ul class="list-group">
						<li class="list-group-item"><input
							class="form-check-input me-1" type="radio" name="role"
							value="Elu" id="role_1" checked> <label
							class="form-check-label" for="role_1">Élu</label></li>
						<li class="list-group-item"><input
							class="form-check-input me-1" type="radio" name="role"
							value="Acteur" id="role_2"> <label
							class="form-check-label" for="role_2">Acteur du monde sportif</label></li>
							<li class="list-group-item"><input
							class="form-check-input me-1" type="radio" name="role"
							value="User" id="role_3"> <label
							class="form-check-label" for="role_3">Membre simple</label></li>
					</ul>
					<button type="submit" class="btn btn-primary">Valider</button>
				</form>
				<%
				if (session != null && session.getAttribute("addFailed") != null && (Boolean) session.getAttribute("addFailed")) {
				%>
				<div class="alert alert-danger" role="alert">
					Cet email est déjà utilisé !
				</div>
				<%
				}
				session.removeAttribute("addFailed");
				%>
			</div>
		</div>
		<a href="Login.jsp">revenir à l'onglet de connexion</a>
	</div>
</body>
</html>
