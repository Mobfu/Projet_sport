 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.DBDAO"%>
<%@ page import="Module.News"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.css" />
<link rel="stylesheet" href="./Style/map.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="./Style/style.css" />
<style>
/* Style pour l'arrière-plan */
body {
	background-image: url('./image/news.jpeg');
	background-repeat: repeat;
}
.dropdown img {
    cursor: pointer;
    width: 35px;
    height: 35px;
    border-radius: 50%;
}
</style>
<title>News Public</title>
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
	<div class="container">
		<div id="cardsContainer" class="d-flex flex-wrap mt-4">
			<%
			DBDAO dao = new DBDAO();
			List<News> newsList = dao.getNews();
			for (News news : newsList) {
			%>
			<div class="card" style="width: 18rem;">
				<div class="card-body">
					<h5 class="card-title">
						UserName:<%=news.getUsername()%></h5>
					<p class="card-text">
						News:<%=news.getNews()%></p>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Temps de publication:<%=news.getHoraire()%></li>
				</ul>

			</div>
			<%
			}
			%>
		</div>
	</div>

</body>
</html>