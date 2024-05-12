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
<title>Elu</title>

<style>
/* Style pour l'arri�re-plan */
body {
	/* D�finir l'image comme arrière-plan */
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


	<section
		class="banner d-flex justify-content-center align-items-center pt-5">
		<div class="container my-5 py-5">
			<p class="texte-capitalize py-3 redressed banner-desc"
				style="color: white;">
				<u> DOSPORT</u>
			</p>
			<div class="row">

				<div class="col-md-6">
					<p class="texte-capitalize py-3 redressed banner-desc "
						style="color: white;">Bienvenue � vous membre Elu</p>
				</div>

				<div class="col-md-8">

					<h1 class="texte-capitalize py-3 redressed banner-desc"
						style="color: white;">Voici les indicateurs statistiques
						g�n�rales</h1>

				</div>
			</div>
		</div>
	</section>

	<section class="suite py-5">
		<div class="container">
			<div class="row">
				<div class="card mb-3" border-0rounded-0">
					<div class="row g-0">
						<div class="col-md-6">
							<img src="./image/MembresFond.jpg" class="img-fluid ">
						</div>
						<div class="col-md-6">
							<div class="card-body">
								<h5 class="card-title">Habitants licenci�s</h5>
								<p class="card-text">Vous pouvez avoir acc�s aux nombres
									d'habitants licenci�s par commune,r�gions,d�partements ainsi
									que les f�d�rations auquels ces nombres apparatiennent.Vous
									pouvez �galement avoir le d�tail du genre intervenant dans ces
									statisitiques</p>
								<p class="card-text">
									<a href="./image/Licencies.pdf"
										class="btn btn-order rounded-0 merriweather">Voici la
										visualisation</a>
								</p>

							</div>
						</div>
					</div>
				</div>


				<div class="card my-5" border-0rounded-0">
					<div class="row g-0">

						<div class="col-md-6">
							<div class="card-body">
								<h5 class="card-title">Habitants non r�pertori�s</h5>
								<p class="card-text">Un document contenant les chiffres des
									habitants non r�pertori�s de certaines f�d�rations sportives
									offre une vision pr�cise et exhaustive de la d�mographie des
									pratiquants dans le domaine du sport.En mettant en lumi�re ces
									chiffres non r�pertori�s, le document fournit des informations
									cruciales pour orienter les politiques de d�veloppement du
									sport, promouvoir l'inclusion et la diversit�, et renforcer
									l'acc�s �quitable � la pratique sportive pour tous.</p>
								<p class="card-text">
									<a href="./image/Nonrepert.pdf"
										class="btn btn-order rounded-0 merriweather">Classement</a>
								</p>
							</div>
						</div>
						<div class="col-md-6">
							<img src="./image/card2.jpg" class="img-fluid " alt="...">
						</div>
					</div>
				</div>




				<section class="suite py-5">
					<div class="container">
						<div class="row">
							<div class="card mb-3" border-0rounded-0">
								<div class="row g-0">
									<div class="col-md-6">
										<img src="./image/card3.jpg" class="img-fluid ">
									</div>
									<div class="col-md-6">
										<div class="card-body">
											<h5 class="card-title">Classement</h5>
											<p class="card-text">En compilant ces donn�es, nous
												pouvons dresser un tableau clair de l'implication des
												habitants dans les activit�s sportives, en mettant en
												�vidence les endroits o� le sport joue un r�le significatif
												dans la vie quotidienne.Cette analyse comparative offre des
												insights utiles pour les d�cideurs politiques,comme vous en
												les aidant � identifier les r�gions o� des initiatives de
												promotion du sport pourraient �tre les plus b�n�fiques,
												ainsi qu'� comprendre les facteurs socio-�conomiques qui
												influent sur la participation sportive.</p>
											<p class="card-text">
												<a href="./image/Classement.pdf"
													class="btn btn-order rounded-0 merriweather">Voici la
													visualisation</a>
											</p>

										</div>
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
								<p class="text-white">&copy; 2024 Rouen | All rights
									reserved.</p>
							</div>
							<div class="col-6">
								<ul class="nav text-center">

									<li class="nav-item"><a href="APropos.jsp"
										class="nav-link text-white">About</a></li>
									<li class="nav-item"><a href="#"
										class="nav-link text-white">Recherche</a></li>
									<li class="nav-item"><a href="#"
										class="nav-link text-white">Contact</a></li>
								</ul>
							</div>
						</div>
					</div>
				</section>
</body>


</html>
