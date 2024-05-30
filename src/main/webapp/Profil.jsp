 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="dao.DBDAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil</title>
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
 
<link rel="stylesheet" href="./Style/style.css" />
</head>
<body>
<%
	if (session == null) {
		session = request.getSession();
	}
	if (session.getAttribute("LogFlag") == null) {
		session.setAttribute("LogFlag", false);
	}
	%>
<%
	if (session != null && session.getAttribute("LogFlag") != null) {
		if ((boolean) session.getAttribute("LogFlag") != true) {
	%>
<jsp:include page="Menu.jsp" />
<%
	} else if (session.getAttribute("role") != null) {
	Object userRoleObj = session.getAttribute("role");
	String userRole = userRoleObj.toString();
	switch (userRole) {
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
	case "3":
	%>
<jsp:include page="Menu_conn.jsp" />
<%
	break;
	}
 
	}
	}
	%>
<%
	String userrole = "unkownen";
	String name = "unkownen";
	String email = "unkownen";
	String sports = "unkownen";
	if (session != null && session.getAttribute("username") != null && session.getAttribute("role") != null
&& session.getAttribute("email") != null) {
		name = (String) session.getAttribute("username");
		Object roleObj = session.getAttribute("role");
		String role = roleObj.toString();
		switch (role) {
		case "1":
			userrole = "ELu";
			break;
		case "2":
			userrole = "Acteur";
			break;
		case "3":
			userrole = "User";
			break;
		}
		email = (String) session.getAttribute("email");
		sports = (String) session.getAttribute("sports");
	}
	%>
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
<div class="container my-5">
<div class="row">
<div class="col-md-4">
<img src="./image/<%=image %>" name="image" alt="Profile Image" class="profile-image">
 
				<form action="UploadImage" method="Post" enctype="multipart/form-data">
<div class="input-group">
<input type="file" class="form-control" id="inputGroupFile04"
							aria-describedby="inputGroupFileAddon04" aria-label="Upload" name="file">
<button class="btn btn-outline-secondary" type="submit"
							id="inputGroupFileAddon04">Soumettre</button>
</div>
</form>
</div>
<div class="col-md-8">
<h3>Information generale</h3>
<table class="table">
<tr>
<th>Role</th>
<td><%=userrole%></td>
</tr>
<tr>
<th>Nom</th>
<td><%=name%></td>
</tr>
<tr>
<th>Adresse mail</th>
<td><%=email%></td>
</tr>
<tr>
<th>Abonnement</th>
<td>Vous n'etes pas encore abonne</td>
</tr>
<tr>
<th>Preference sportive</th>
<td><%=sports %></td>
<td>
<form action="UploadSportPrefer" method="post">
<div class="form-check">
<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault" name="basketball"/> <label class="form-check-label"
										for="flexCheckDefault"> Basketball </label>
</div>
<div class="form-check">
<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault" name="football"/> <label class="form-check-label"
										for="flexCheckDefault"> Football </label>
</div>
<div class="form-check">
<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault" name="tennis"/> <label class="form-check-label"
										for="flexCheckDefault"> Tennis </label>
</div>
<div class="form-check">
<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault" name="others"/> <label class="form-check-label"
										for="flexCheckDefault"> Autres </label>
</div>
<button type="submit" class="btn btn-primary">Soumettre</button>
</form>
</td>
</tr>
</table>
</div>
</div>
</div>
 
 
</body>
</html>