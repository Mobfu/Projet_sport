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
    <title>Habitants licenciés</title>
    
      <style>
        /* Style pour l'arrière-plan */
        body {
            /* Définir l'image comme arrière-plan */
            background-image: url('./image/FondSiteWeb.jpg');
            /* Centrer et attirer l'image pour remplir tout l'écran */
            background-size: cover;
           /* Pour que je puisse positionner l'image au centre de l'écran */
            background-position: center;
            /* Répéter l'image si nécessaire' */
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
                    <h1 class="text-center redressed">Voici les visualisations</h1>
                     <h2 class="text-center redressed " >
                     
   <iframe title="Nombre dâ€™habitants licenciés par fédération" width="1140" height="810" src="https://app.powerbi.com/reportEmbed?reportId=33321683-f01e-470b-9fc8-fe1531985f5e&autoAuth=true&ctid=371cb156-9558-4286-a3cd-3059699b890c" frameborder="0" allowFullScreen="true"></iframe>
  
  <script src="assets/js/bootstrap.bundle.min.js"></script>
</body>
</html>
 