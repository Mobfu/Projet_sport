<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Réinitialiser le mot de passe</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
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
        <div class="card-header bg-secondary text-white">Réinitialiser le mot de passe</div>
        <div class="card-body">
        <div class="form-container">
            <form action="ResetPasswordServlet1" method="post">
                <div class="form-group">
                    <label for="newPassword">Nouveau mot de passe</label>
                    <input type="password" id="newPassword" name="newPassword" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary mt-3">Réinitialiser le mot de passe</button>
            </form>
        </div>
        </div>
        </div>
    </div>
</body>
</html>
