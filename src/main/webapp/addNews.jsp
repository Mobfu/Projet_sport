 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
<title>AddNews</title>
</head>
<body>
	<jsp:include page="Menu_conn.jsp" />
	<div class="container mt-5">
		<%
		if (session != null && session.getAttribute("addnewsFailed") != null) {
		%>
		<div class="alert alert-danger" role="alert">Add News Failed!</div>
		<%
		}
		session.removeAttribute("addnewsFailed");
		%>
		<h2>Insert Information</h2>
		<form action="AddNews" method="post">
			<div class="mb-3">
				<label for="largeText" class="form-label">NEWS</label>
				<textarea class="form-control" id="news" rows="3" name="news"
					placeholder="Enter news ici..."></textarea>
			</div>

			<div class="mb-3">
				<label for="datePicker" class="form-label">Select Date</label> <input
					type="date" class="form-control" id="date" name="date">
			</div>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>


</body>
</html>