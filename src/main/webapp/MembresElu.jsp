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
			    
			    <script> src="https://cdn.jsdelivr.net/npm/chart.js"></script>
			    
			    <title>Elu</title>
			    
			    <style>
			        /* Style pour l'arriére-plan */
			        body {
			            /* Définir l'image comme arriére-plan */
			            background-image: url('./image/FondSiteWeb.jpg');
			            /* Centrer et étirer l'image pour remplir tout l'écran */
			            background-size: cover;
			           /* Pour que je puisse positionner l'image au centre de l'écran */
			            background-position: center;
			            /* Répéter l'image si nécessaire */
			            background-repeat: no-repeat;
			        }
			    </style>
			    
			</head>
			
			<body>
			
				   <jsp:include page="Menu_elu.jsp" />
      
       <section class="banner d-flex justify-content-center align-items-center pt-5">
        <div class="container my-5 py-5">
            <p class="texte-capitalize py-3 redressed banner-desc" style="color: white;"> <u>FITGROOVE</u></p>
            <div class ="row">  
                
                <div class="col-md-6">
                    <p class="texte-capitalize py-3 redressed banner-desc "  style="color: white;"> Bienvenue à vous membre Elu </p>
                </div>
                
                <div class="col-md-8">
                
                    <h1  class="texte-capitalize py-3 redressed banner-desc" style="color: white;">
                    
                      Obtenez l'ensemble des informations sur vos différentes localités
                        
                         
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
		                          <h5 class="card-title">Habitants licenciés</h5>
		                          <p class="card-text"> Vous pouvez avoir accès aux nombres d'habitants licenciés par 
		                          commune,régions,départements ainsi que les fédérations auquels ces nombres apparatiennent.Vous pouvez également
		                          avoir le détail du genre intervenant dans ces statisitiques </p>
		                          <p class="card-text">
		                            <a href="Visual.jsp" class="btn btn-order rounded-0 merriweather">Visualisations des licenciements</a>
		                          </p>
		                          
		                        </div>
		                      </div>
		                    </div>
		                </div>
                
                		
                		 <div class="card my-5" border-0 rounded-0">
                    <div class="row g-0">
                      
                      <div class="col-md-6">
                        <div class="card-body">
                          <h5 class="card-title">Habitants non répertoriés</h5>
                          <p class="card-text">
	                              Un document contenant les chiffres des habitants non répertoriés de certaines fédérations sportives
	                               offre une vision précise et exhaustive de la démographie des pratiquants 
	                               dans le domaine du sport.En mettant en lumiére ces chiffres non
	                                répertoriés, le document fournit des informations cruciales pour orienter les politiques de développement du sport,
	                                promouvoir l'inclusion et la diversité, et renforcer l'accés équitable é la pratique sportive pour tous.</p>
                               <p class="card-text">
                            <a href= "Visual2.jsp" class="btn btn-order rounded-0 merriweather">Habitants non répertoriés</a>
                          </p>
	                        </div>
	                      </div>
	                      <div class="col-md-6">
	                        <img src="./image/card2.jpg"  class="img-fluid " alt="...">
	                      </div>
	                    </div>
	                  </div>
              
                
                
                
                <section class="suite py-5" >
        <div class="container">
            <div class="row">
		                <div class="card mb-3" border-0 rounded-0">
		                    <div class="row g-0">
		                      <div class="col-md-6">
		                        <img src="./image/card3.jpg"  class="img-fluid " >
		                      </div>
		                      <div class="col-md-6">
		                        <div class="card-body">
		                          <h5 class="card-title">Classement</h5>
			                          <p class="card-text">En compilant ces données, nous pouvons dresser un tableau clair de l'implication des habitants dans les 
			                          activités sportives, en mettant en évidence les endroits où 
			                          le sport joue un rôle significatif dans la vie quotidienne.Cette analyse 
			                          comparative offre des insights utiles pour les décideurs politiques,comme vous en les 
			                          aidant à  identifier les régions où des initiatives de promotion du sport pourraient étre 
			                          les plus bénéfiques, ainsi que comprendre les facteurs socio-économiques qui influent
			                           sur la participation sportive.  </p>
		                          <p class="card-text">
		                            <a href="Visual3.jsp" class="btn btn-order rounded-0 merriweather"> Voici la visualisation
                                    </a>
		                          </p>
		                          
		                        </div>
		                      </div>
		                    </div>
		                </div>
                
                
           	</div>
              
          </div>
      
        </section> 
      
      
       <jsp:include page="Footer.jsp" />
      
			</body>


</html>
 
 