<%@ page import="java.util.Properties" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="dao.DBDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mot de passe oublié</title>
    <link href="./image/IconWeb.jpg" rel="icon" type="image/x-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="./Style/style.css"/>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
        <div class="card-header bg-secondary text-white">Mot de passe oublié</div>
        <div class="card-body">
    <div class="form-container">
        <form action="ConfirmPasswordRecoveryServlet" method="post">
            <div class="form-group">
                <label for="email">Adresse email</label>
                <input type="email" id="email" name="email" required>
            </div>
            <button type="submit" class="btn btn-secondary mt-2">Envoyer le code de récupération</button>
        </form>
    </div>
    </div>
    </div>
</div>
<footer class="py-5 d-flex justify-content-center">
    <div class="container">
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
    </div>
</footer>
</body>
</html>
