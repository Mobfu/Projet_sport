 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
     <link href="./image/IconWeb.jpg" rel="icon" type="image/x-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="...">
    
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="..." crossorigin="anonymous"></script>
 
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
   
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
   
    <link rel="stylesheet" href="./Style/style.css"/>
    <title>Contactez-nous</title>
    
      <style>
        /* Style pour l'arrière-plan */
        body {
            /* d�finir l'image comme arri�re paln ' */
            background-image: url('./image/FondSiteWeb.jpg');
            /* Centrer et attirer l'image pour remplir tout l'�cran */
            background-size: cover;
           /* Pour que je puisse positionner l'image au centre de l'Ã©cran */
            background-position: center;
            /* RÃ©pÃ©ter l'image si nécessaire */
            background-repeat: no-repeat;
        }
    </style>
    
    
</head>
<% 
if (session == null) {
    session = request.getSession(); 
}
if (session.getAttribute("LogFlag") == null) {
    session.setAttribute("LogFlag", false);
}
%>

<body> 
    <section class="presen d-flex justify-content-center align-items-center pt-5">
    <nav class="cc-navbar navbar  position-center navbar-dark "> 
        <div class="container  ">
            <div class ="row ">
                    <h1 class="text-center redressed">Page de contact</h1>                  
            </div>  
        </div>
         
  </section>
  
     <jsp:include page="saute.jsp" />    
     
   <h2 class="text-center redressed">Adresse E-mail : FitGroove@outlook.com</h2>
   <h2 class="text-center redressed">Num�ro de t�l�phone : 0604199561</h2>
 </nav>
  
  	   <jsp:include page="saute.jsp" />     
  	
 <section class="footer py-4 d-flex justify-content-center">
    <div class="container">
        <div class="row">
            <div class="col-6 d-flex align-items-center">
                <p class="text-white">&copy; 2024 Rouen | All rights reserved.</p>
            </div>
            
            <div class="col-6">
            
                <ul class="nav text-center">
                       
                <li>
                        <a href="APropos.jsp" class="nav-link text-white">� propos</a>
              	</li>
              	
                </ul>
            </div>    
        </div>
    </div>
  </section>
  
  
  
  <script src="assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
 