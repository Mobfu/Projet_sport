<nav class="cc-navbar navbar navbar-expand-lg navbar-dark w-100">
	<div class="container">
		<a class="navbar-brand test-uppercase fw-bolder mx-4 py-3"
			href="index.jsp">FitGroove</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
<%@ page import="dao.DBDAO"%>
<%
	DBDAO dao =new DBDAO();
	String image = "person-outline.svg";
	if(session != null && session.getAttribute("id")!=null){
	Object idObj = session.getAttribute("id");
	String id = idObj.toString();
	if(dao.getImageByID(id)!=null){
		image = dao.getImageByID(id);
	}
		
	}
	%>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">

				<li class="nav-item pe-2"><a class="nav-link active"
					aria-current="page" href="index.jsp">Accueil</a></li>

				<li class="nav-item pe-2"><a class="nav-link active"
					aria-current="page" href="listClub.jsp">Recherche</a></li>

				<li class="nav-item pe-2"><a class="nav-link"
					href="APropos.jsp">Presentation</a></li>

				<li class="nav-item pe-2"><a class="nav-link"
					href="NewsPub.jsp">News</a></li>
				<li class="nav-item dropdown"><img src="./image/<%=image %>"
					class="dropdown-toggle" data-bs-toggle="dropdown"
					aria-expanded="false">
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="Profil.jsp">Profile</a></li>
						<li><a class="dropdown-item" href="MembresElu.jsp">Section
								Elu</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="logout.jsp">Deconnection</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>