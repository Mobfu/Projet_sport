<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="UTF-8">
<title>Modifier le Card</title>
</head>
<body>

	<jsp:include page="Menu_conn.jsp" />
	<div class="container mt-5">

		<%
		String idStr = request.getParameter("id");
		int id = 0;
		id = Integer.parseInt(idStr);
		%>
		<h2>Change Information</h2>
		<form action="ModifNews" method="post">
			<input type="hidden" name="id" value=<%=id%>>
			<div class="mb-3">
				<label for="largeText" class="form-label">NEWS</label>
				<textarea class="form-control" id="news" rows="3" name="news"
					placeholder="Enter news ici..."></textarea>
			</div>

			<div class="mb-3">
				<label for="datePicker" class="form-label">Select Date</label> <input
					type="date" class="form-control" id="date" name="date">
			</div>

			<div class="mb-3">
				<label for="textInput" class="form-label">Les montants</label> <input
					type="text" class="form-control" id="montants" name="montants"
					placeholder="Enter montants ici...">
			</div>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>

</body>
</html>