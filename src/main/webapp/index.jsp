<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://unpkg.com/typed.js@2.1.0/dist/typed.umd.js"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="./Style/styles.css">
    
</head>

	  <style>
        /* Style pour l'arriÃ¨re-plan */
        body {
            /* définir l'image comme arrière paln ' */
            background-image: url('./image/card2.jpg');
            /* Centrer et attirer l'image pour remplir tout l'écran */
            background-size: cover;
           /* Pour que je puisse positionner l'image au centre de l'ÃƒÂ©cran */
            background-position: center;
            /* RÃƒÂ©pÃƒÂ©ter l'image si nÃ©cessaire */
            background-repeat: no-repeat;
        }
    </style>
<body>
    <nav class="navbar">
        <div class="container-fluid">
          <a class="navbar-brand" href="index.jsp" id="title">
            FitGroove
          </a>
        </div>
      </nav>
      <div class="container-lg">
        
        <div class="row">
          <div class="col-md-6 left-div" id="anitext">    
          </div>
          <div class="col-md-4 right-div" id="sidetext">
             Vous pouvez
              <a href="Login.jsp" class="btn btn-light d-block mb-2">Vous connecter</a>
              <a href="listClub.jsp" class="btn btn-light d-block mb-2">Rechercher des clubs</a>
              <a href="News.jsp" class="btn btn-light d-block mb-2">Visualiser les News du monde sportif</a>
          
          </div>
        </div>
      </div>
      <script>
        var typed = new Typed('#anitext', {
          strings: ['Rechercher Les clubs de sport à proximité','Visualiser les statistiques correspondant à chaque club et fédération','Tenez-vous informer grâce au forum'],
          typeSpeed: 50, loop: true , showCursor: false
        });
      </script>
</body>
</html>

