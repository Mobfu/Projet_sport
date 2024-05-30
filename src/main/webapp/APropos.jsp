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
    <title> A propos</title>
    
      <style>
<<<<<<< HEAD
        /* Style pour l'arri�re-plan */
=======
        /* Style pour l'arriÃ¨re-plan */
>>>>>>> e7c9eb2c807ece00036eb90accf361a759f92d77
        body {
            /* définir l'image comme arrière paln ' */
            background-image: url('./image/card2.jpg');
            /* Centrer et attirer l'image pour remplir tout l'écran */
            background-size: cover;
           /* Pour que je puisse positionner l'image au centre de l'ÃÂ©cran */
            background-position: center;
<<<<<<< HEAD
            /* R�p�ter l'image si n�c�ssaire' */
=======
            /* RÃÂ©pÃÂ©ter l'image si nÃ©cessaire */
>>>>>>> e7c9eb2c807ece00036eb90accf361a759f92d77
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
   
     
    <section class="presen d-flex justify-content-center align-items-center pt-5">

    <nav class="cc-navbar navbar  position-center navbar-dark "> 
        <div class="container  ">
            <div class ="row ">
                    <h1 class="text-center redressed">Bienvenue</h1>
                     <h2 class="text-center redressed " >
                     
                        <u>Présentation</u>
                        
                     </h2>  
                     
                         <h4 class="text-center "> <u> Votre Compagnon numérique pour Trouver le Club de Sport Idéal </u></h4>
		                        <p class="text-center" >
			                        Dans un monde où l'activité
			                        physique est plus importante que jamais pour le bien-être 
			                        physique et mental, FITGROOVE se présente comme la solution
			                         incontournable pour tous les passionnéss de sport. 
			                         En tant que portail en ligne dédié exclusivement aux amateurs de sports,
			                          FITGROOVE offre une plateforme dynamique et intuitive pour explorer et 
			                          découvrir des clubs
			                         sportifs adaptés à  tous les niveaux et intérêts.
		                          </p>
                       
                        <h4 class="text-center "> <u> Recherche Personnalisée de Clubs de Sport </u></h4>
                        
		                        <p class="text-center"> FITGROOVE simplifie la 
		                        recherche de clubs de sport grâce à  une interface conviviale et 
		                        des fonctionnalités de recherche avancés. Que vous soyez débutant
		                         à  la recherche d'un club de jogging local ou un athlète expérimenté
		                         en quête d'un club d'aviron compétitif, notre service couvre une 
		                         vaste gamme de disciplines sportives. Grâce à  notre moteur de 
		                         recherche détaillé, vous pouvez filtrer les clubs selon des critères spécifiques tels 
		                         que la localisation, le niveau de compétence requis, les types d'activités
		                         proposées, et même les horaires d'entrainement</p>
                   
                        <h4 class="text-center"><u> Aide à  la Décision pour Votre Choix de Clubs </u> </h4>
                        
		                        <p class="text-center ">Choisir le bon club de sport peut être une décision complexe, 
		                        influencée par divers facteurs allant de la proximité géographique à l'ambiance du club. 
		                        FITGROOVE va au-dela de la simple mise en relation en offrant des guides détaillés et des conseils
		                         personnalisés pour vous aider dans votre choix. Notre système de recommandations est alimenté par
		                          des avis vérifiés et des évaluations des membres, vous permettant ainsi de bénéficier des expériences d'autres sportifs. 
		                          De plus, des articles réguliers et des blogs spécialisés vous fournissent
		                         des informations précieuses sur les tendances sportives et des conseils pour
		                          s'épanouir dans votre discipline.   </p>
                       
                        <h4 class="text-center "><u> Engagement envers l'Excellence</u>  </h4>
                        
		                        <p class="text-center"> Chez FITGROOVE, notre mission
		                         est de vous fournir un service personnalisé et fiable.
		                          Nous nous engageons à  maintenir la plus haute qualité de contenu, 
		                          des informations à jour et des fonctionnalités innovantes qui répondent 
		                          à vos besoins sportifs. Avec l'aide de technologies de pointe et d'une équipe dévouée à votre
		                         service, nous nous assurons que votre expérience 
		                         sur FITGROOVE soit non seulement utile, mais aussi inspirante. </p>
		                       
                       
                   
                        <p class="text-center">  Rejoignez FITGROOVE aujourd'hui et transformez votre parcours 
                        sportif avec le meilleur outil de recherche et de communauté pour les amateurs de sport.
                         Explorez, connectez-vous, et excellez avec FITGROOVE à votre porte d'entrée vers le monde du sport.
                        </p>
            </div>
        </div>
  </section>
  
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
                    <li class="nav-item">
                        <a href="contact.jsp" class="nav-link text-white">Contactez-nous</a>
                    </li>
                </ul>
            </div>    
        </div>
    </div>
  </section>
  
  
  
  <script src="assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
 
