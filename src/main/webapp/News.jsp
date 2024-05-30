 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import=" dao.DBDAO"%>
<%@ page import="Module.News"%>
<%@ page import="Module.User"%>
<%@ page import="Module.Comment"%>
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
body {
	background-image: url('./image/news.jpeg');
	background-repeat: repeat;
}
</style>
<title>News</title>
</head>
<body>
	<jsp:include page="Menu_conn.jsp" />
	<div class="container">
	<%
	if(session.getAttribute("role")!= null){
		Object userRoleObj = session.getAttribute("role");
		String userRole = userRoleObj.toString();
			switch(userRole){
			case "2":
				%>
				<a id="addCardBtn" class="btn btn-primary" href="addNews.jsp">AjouteNews</a>
				<%
				break;
			case "3":
				%>
				<%
				break;
			}
		
	}
	%>
		

		<%
		if (session != null && session.getAttribute("supsucce") != null) {
		%>
		<div class="alert alert-primary" role="alert">Supression Succe !
		</div>
		<%
		session.removeAttribute("supsucce");
		%>
		<%
		}
		%>

		<div id="cardsContainer" class="row row-cols-1 row-cols-md-2 g-4">
			<%
			DBDAO dao = new DBDAO();
			List<News> newsList = dao.getNews();
			for (News news : newsList) {
			%>
			<div class="col">
				<div class="card">
					<!-- style="width: 18rem; height: auto;" -->
					<div class="card-body">
						<h5 class="card-title">
							NomUtilisateur:<%=news.getUsername()%></h5>
						<p class="card-text">
							News:<%=news.getNews()%></p>
					</div>
					<ul class="list-group list-group-flush">
						<li class="list-group-item">Temps de publication:<%=news.getHoraire()%></li>
					</ul>

					<%
					if (session != null && session.getAttribute("id") != null) {
						Object iduser = session.getAttribute("id");
						int id = Integer.parseInt(iduser.toString());
						if (id == news.getUserId()) {
					%>
					<form action="SuprimNews" method="post">
						<div class="card-body">
							<input type="hidden" name="id" value=<%=news.getId()%>> <a
								href="modifNews.jsp?id=<%=news.getId()%>"
								class="btn btn-primary">Modification</a>
							<button type="submit" class="btn btn-danger">Supression</button>
						</div>
					</form>
					<%
					}
					%>
					<div class="card-footer">
						<form action="PostComment" method="post">
							<div class="input-group mb-3">
								<input type="hidden" name="newsId" value="<%=news.getId()%>">
								<input type="text" class="form-control"
									placeholder="Ajouter un commentaire" name="comment">
								<button class="btn btn-outline-secondary" type="submit"
									id="button-addon2">Commenter</button>
							</div>
						</form>

						<ul class="list-group mt-3">
							<%
							List<Comment> comments = dao.getCommentsForNews(news.getId());
							for (Comment comment : comments) {
							%>
							<li class="list-group-item"><strong><%=comment.getUsername()%>:</strong>
								<%=comment.getText()%> <span class="timestamp"> <%=comment.getTimestamp()%>
							</span></li>
							<%
							}
							%>
						</ul>
					</div>
				</div>
				<%
				}
				%>
				<%
				}
				%>
			</div>
		</div>
		</div>
</body>

</html>
 