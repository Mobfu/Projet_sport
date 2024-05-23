<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="./image/IconWeb.jpg" rel="icon" type="image/x-icon">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" integrity="...">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
	integrity="..." crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

<link href="assets/css/bootstrap.min.css" rel="stylesheet" />

<link rel="stylesheet" href="./Style/style.css" />
<title>A propos</title>

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

<body>

	<%
	if (session != null && session.getAttribute("LogFlag") != null) {
		if ((boolean) session.getAttribute("LogFlag") != true) {
	%>
	<jsp:include page="Menu.jsp" />
	<%
	} else if(session.getAttribute("role")!= null){
		Object userRoleObj = session.getAttribute("role");
		String userRole = userRoleObj.toString();
			switch(userRole){
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


	<section
		class="presen d-flex justify-content-center align-items-center pt-5">
		<div class="container  ">
			<div class="row ">
				<h1 class="text-center redressed">Bienvenue</h1>
				<h2 class="text-center redressed ">

					<u>Présentation</u>

				</h2>

				<h4 class="text-center ">
					<u> Votre Compagnon numérique pour Trouver le Club de Sport
						Idéal </u>
				</h4>
				<p class="text-center">Dans un monde oé l'activité physique est
					plus importante que jamais pour le bien-étre physique et mental,
					DOSPORT se présente comme la solution incontournable pour tous les
					passionnéss de sport. En tant que portail en ligne ddiés
					exclusivement aux amateurs de sports, DOSPORT offre une plateforme
					dynamique et intuitive pour explorer et découvrir des clubs
					sportifs adaptés é tous les niveaux et intéréts.</p>

				<h4 class="text-center ">
					<u> Recherche Personnalisée de Clubs de Sport </u>
				</h4>

				<p class="text-center">DOSPORT simplifie la recherche de clubs
					de sport gréce é une interface conviviale et des fonctionnalités de
					recherche avancés. Que vous soyez débutant é la recherche d'un club
					de jogging local ou un athléte expérimenté en quéte d'un club
					d'aviron compétitif, notre service couvre une vaste gamme de
					disciplines sportives. Gréce é notre moteur de recherche détaillés,
					vous pouvez filtrer les clubs selon des critéres spécifiques tels
					que la localisation, le niveau de compétence requis, les types
					d'activités proposées, et méme les horaires d'entrainement</p>

				<h4 class="text-center">
					<u> Aide a la Décision pour Votre Choix de Clubs </u>
				</h4>

				<p class="text-center ">Choisir le bon club de sport peut étre
					une décision complexe, influence par divers facteurs allant de la
					proximit géographique a l'ambiance du club. DOSPORT va au-dela de
					la simple mise en relation en offrant des guéides détaillés et des
					conseils personnalisés pour vous aider dans votre choix. Notre
					systéme de recommandations est alimenté par des avis vérifiés et
					desévaluations des membres, vous permettant ainsi de bénéficier des
					expériences d'autres sportifs. De plus, des articles réguliers et
					des blogs spécialisés vous fournissent des informations précieuses
					sur les tendances sportives et des conseils pour s'épanouir dans
					votre discipline choisie.</p>

				<h4 class="text-center ">
					<u>Engagement envers l'Excellence</u>
				</h4>

				<p class="text-center">Chez DOSPORT, notre mission est de vous
					fournir un service exceptionnel et fiable. Nous nous engageons é
					maintenir la plus haute qualité de contenu, des informations é jour
					et des fonctionnalités innovantes qui répondent é vos besoins
					sportifs. Avec l'aide de technologies de pointe et uneéquipe
					dévouée é votre service, nous nous assurons que votre expérience
					sur DOSPORT soit non seulement utile, mais aussi inspirante.</p>



				<p class="text-center">Rejoignez DOSPORT aujourd'hui et
					transformez votre parcours sportif avec le meilleur outil de
					recherche et de communauté pour les amateurs de sports. Explorez,
					connectez-vous, et excellez avec DOSPORT é votre porte d'entrée
					vers le monde du sport.</p>



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

						<li class="nav-item"><a href="APropos.jsp"
							class="nav-link text-white">About</a></li>
						<li class="nav-item"><a href="#" class="nav-link text-white">Recherche</a>
						</li>
						<li class="nav-item"><a href="#" class="nav-link text-white">Contact</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</section>

	<script src="assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
