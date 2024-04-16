<!DOCTYPE html>
	<html>
	<head>
	 <link href="./image/IconWeb.jpg" rel="icon" type="image/x-icon">
	 <link rel="stylesheet" href="./Style/style.css"/>
	<meta charset="UTF-8">
	 <meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="...">
    
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="..." crossorigin="anonymous"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
   
    	<link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
 
	<title>Login</title>
	</head>
	
	 <style>
        /* Style pour l'arri�re-plan */
        body {
            /* Définir l'image comme arrière-plan */
            background-image: url('./image/FondSiteWeb.jpg');
            /* Centrer et étirer l'image pour remplir tout l'écran */
            background-size: cover;
           /* Pour que je puisse positionner l'image au centre de l'écran */
            background-position: center;
            /* Répéter l'image si nécessaire */
            background-repeat: no-repeat;
        }
    </style>
    
	
	
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
		
		
		 <nav class="cc-navbar navbar navbar-expand-lg position-fixed navbar-dark w-100">
        <div class="container">
          <a class="navbar-brand test-uppercase fw-bolder mx-4 py-3" href="index.jsp">DOSport</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
              <li class="nav-item pe-2">
                <a class="nav-link active" aria-current="page" href="index.jsp">Accueil</a>
              </li>
             
              <li class="nav-item pe-2">
                <a class="nav-link" href ="APropos.jsp">Presentation</a>
              </li>
              <li class="nav-item pe-2">
                <a class="nav-link" href ="addUser.jsp">Devenir membre</a>
              </li>
              <li class="nav-item pe-2">
                <a class="btn btn-order rounded-0" href ="Login.jsp">Connection</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
		
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
							<li class="list-group-item"><input
								class="form-check-input me-1" type="radio" name="role"
								value="User" id="role_3"> <label class="form-check-label"
								for="role_3">User</label></li>
						</ul>
	
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
					<br> <a href='./addUser.jsp'>
						<button type="text" class="btn btn-primary">Inscription</button>
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
		
		
		 <section class="footer py-5 d-flex justify-content-center">
    <div class="container">
        <div class="row">
            <div class="col-6 d-flex align-items-center">
                <p class="text-white">&copy; 2024 Rouen | All rights reserved.</p>
            </div>
            <div class="col-6">
                <ul class="nav text-center">
                       
                <li class="nav-item">
                        <a href="APropos.jsp" class="nav-link text-white">About</a>
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
  
  
	</body>
</html>