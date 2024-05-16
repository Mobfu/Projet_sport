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
        /* Style pour l'arrière-plan */
        body {
            /* Définir l'image comme arrière-plan */
            background-image: url('./image/FondSiteWeb.jpg');
            /* Centrer et attirer l'image pour remplir tout l'Ã©cran */
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
 
<%
	if (session != null && session.getAttribute("LogFlag") != null) {
		if ((boolean) session.getAttribute("LogFlag") != true) {
	%>
	<jsp:include page="Menu.jsp" />
	<%
	} else if(session.getAttribute("id")!= null){
		Object userIdObj = session.getAttribute("id");
		String userId = userIdObj.toString();
			switch(userId){
			case "1":
				%>
				<jsp:include page="Menu_elu.jsp" />
				<%
				break;
			case "2":
				%>
				<jsp:include page="Menu_conn.jsp" />
				<%
				break;
			}
		
	}
	}
	%>
      
      
    <section class="presen d-flex justify-content-center align-items-center pt-5">
        <div class="container  ">
            <div class ="row ">
                    <h1 class="text-center redressed">Bienvenue</h1>
                     <h2 class="text-center redressed " >
                     
                        <u>Pr�sentation</u>
                        
                     </h2>  
                     
                         <h4 class="text-center "> <u> Votre Compagnon num�rique pour Trouver le Club de Sport Id�al </u></h4>
		                        <p class="text-center">
			                        Dans un monde o� l'activit�
			                        physique est plus importante que jamais pour le bien-�tre 
			                        physique et mental, DOSPORT se pr�sente comme la solution
			                         incontournable pour tous les passionn�ss de sport. 
			                         En tant que portail en ligne d�di�s exclusivement aux amateurs de sports,
			                          DOSPORT offre une plateforme dynamique et intuitive pour explorer et 
			                          d�couvrir des clubs
			                         sportifs adapt�s � tous les niveaux et int�r�ts.
		                          </p>
                       
                        <h4 class="text-center "> <u> Recherche Personnalis�e de Clubs de Sport </u></h4>
                        
		                        <p class="text-center"> DOSPORT simplifie la 
		                        recherche de clubs de sport gr�ce � une interface conviviale et 
		                        des fonctionnalit�s de recherche avanc�s. Que vous soyez d�butant
		                         à la recherche d'un club de jogging local ou un athl�te exp�riment�
		                         en qu�te d'un club d'aviron comp�titif, notre service couvre une 
		                         vaste gamme de disciplines sportives. Gr�ce � notre moteur de 
		                         recherche d�taill�s, vous pouvez filtrer les clubs selon des crit�res sp�cifiques tels 
		                         que la localisation, le niveau de comp�tence requis, les types d'activit�s
		                         propos�es, et m�me les horaires d'entrainement</p>
                   
                        <h4 class="text-center"><u> Aide � la D�cision pour Votre Choix de Clubs </u> </h4>
                        
		                        <p class="text-center ">Choisir le bon club de sport peut �tre une d�cision complexe, 
		                        influence par divers facteurs allant de la proximit� g�ographique � l'ambiance du club. 
		                        DOSPORT va au-dela de la simple mise en relation en offrant des guides d�taill�s et des conseils
		                         personnalis�s pour vous aider dans votre choix. Notre syst�me de recommandations est aliment� par
		                          des avis v�rifi�s et des�valuations des membres, vous permettant ainsi de b�n�ficier des exp�riences d'autres sportifs. 
		                          De plus, des articles r�guliers et des blogs sp�cialis�s vous fournissent
		                         des informations pr�cieuses sur les tendances sportives et des conseils pour
		                          s'�panouir dans votre discipline choisie.   </p>
                       
                        <h4 class="text-center "><u>Engagement envers l'Excellence</u>  </h4>
                        
		                        <p class="text-center">Chez DOSPORT, notre mission
		                         est de vous fournir un service exceptionnel et fiable.
		                          Nous nous engageons � maintenir la plus haute qualit� de contenu, 
		                          des informations � jour et des fonctionnalit�s innovantes qui r�pondent 
		                          à vos besoins sportifs. Avec l'aide de technologies de pointe et une�quipe d�vou�e � votre
		                         service, nous nous assurons que votre exp�rience 
		                         sur DOSPORT soit non seulement utile, mais aussi inspirante. </p>
		                       
                       
                   
                        <p class="text-center">  Rejoignez DOSPORT aujourd'hui et transformez votre parcours 
                        sportif avec le meilleur outil de recherche et de communaut� pour les amateurs de sports.
                         Explorez, connectez-vous, et excellez avec DOSPORT � votre porte d'entr�e vers le monde du sport.
                        </p>
                     
                 													  
               
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
  
  <script src="assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
 