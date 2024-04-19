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
			    <title>Elu</title>
			    
			    <style>
			        /* Style pour l'arrière-plan */
			        body {
			            /* Définir l'image comme arriÃ¨re-plan */
			            background-image: url('./image/FondSiteWeb.jpg');
			            /* Centrer et Ã©tirer l'image pour remplir tout l'Ã©cran */
			            background-size: cover;
			           /* Pour que je puisse positionner l'image au centre de l'Ã©cran */
			            background-position: center;
			            /* RÃ©pÃ©ter l'image si nÃ©cessaire */
			            background-repeat: no-repeat;
			        }
			    </style>
			    
			</head>
			
			<body>
			
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
      
      
       <section class="banner d-flex justify-content-center align-items-center pt-5">
        <div class="container my-5 py-5">
            <p class="texte-capitalize py-3 redressed banner-desc" style="color: white;"> <u> DOSPORT</u></p>
            <div class ="row">
                
                <div class="col-md-6">
                    <p class="texte-capitalize py-3 redressed banner-desc "  style="color: white;"> Bienvenue à vous membre Elu </p>
                </div>
                
                <div class="col-md-8">
                
                    <h1  class="texte-capitalize py-3 redressed banner-desc" style="color: white;">
                    
                         Voici les indicateurs statistiques générales
                         
                    </h1>    
                   
                </div>
            </div>
        </div>
  </section>
      
    <section class="suite py-5" >
        <div class="container">
            <div class="row">
		                <div class="card mb-3" border-0 rounded-0">
		                    <div class="row g-0">
		                      <div class="col-md-6">
		                        <img src="./image/MembresFond.jpg"  class="img-fluid " >
		                      </div>
		                      <div class="col-md-6">
		                        <div class="card-body">
		                          <h5 class="card-title">Nombre d'habitants licenciés</h5>
		                          <p class="card-text"> Vous pouvez avoir accès aux nombres d'habitants licenciés par 
		                          commune,régions,départements ainsi que les fédérations auquels ces nombres apparatiennent.Vous pouvez également
		                          avoir le détail du genre intervenant dans ces statisitiques </p>
		                          <p class="card-text">
		                            <a href="./image/Licencies.pdf" class="btn btn-order rounded-0 merriweather">Voici la visualisation</a>
		                          </p>
		                          
		                        </div>
		                      </div>
		                    </div>
		                </div>
                
                		
                		 <div class="card my-5" border-0 rounded-0">
                    <div class="row g-0">
                      
                      <div class="col-md-6">
                        <div class="card-body">
                          <h5 class="card-title">Classements</h5>
                          <p class="card-text">
                              Voici le classement des federations contenant les licenciés ect..</p>
                               <p class="card-text">
                            <a href="./image/Nonrepert.pdf" class="btn btn-order rounded-0 merriweather">Classement</a>
                          </p>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <img src="./image/FondSiteWeb.jpg"  class="img-fluid " alt="...">
                      </div>
                    </div>
                  </div>
              
                
                
                
           	</div>
              
          </div>
              
    
             
              
        </section> 
      
      
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
      
      
      
			</body>


</html>
