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
            <button type="button" class="btn btn-dark"><a href="Login.jsp">Vous connectez</a></button>
            <button type="button" class="btn btn-dark"><a href="listClub.jsp">Rechercher des clubs</a></button></button>
            
          </div>
        </div>
      </div>
      <script>
        var typed = new Typed('#anitext', {
          strings: ['Rechercher Les clubs de sport à proximité','Visualiser les statistiques correspondant à chaque club et fédération','Tener vous informer grâce au forum'],
          typeSpeed: 50, loop: true , showCursor: false
        });
      </script>
</body>
</html>

