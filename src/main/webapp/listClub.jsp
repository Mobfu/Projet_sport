<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.FederationDao" %>
<%@ page import="dao.RegionDao" %>
<%@ page import="dao.ClubDAO" %>
<%@ page import="Module.Club" %>
<%@ page import="dao.CommuneDao" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Recherche de clubs</title>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js" integrity="sha512-GsLlZN/3F2ErC5ifS5QtgpiJtWd43JWSuIgh7mbzZ8zBps+dvLusV+eNQATqgA/HdeKFVgA5v3S/cIrLF7QnIg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</head>
<% 
if (session == null) {
    session = request.getSession(); //
}
if (session.getAttribute("LogFlag") == null) {
    session.setAttribute("LogFlag", false);
}
%>


<body >
	<%
	if (session != null && session.getAttribute("LogFlag") != null) {
		if ((boolean) session.getAttribute("LogFlag") != true) {
	%>
	<jsp:include page="Menu.jsp" />
	<%
	} else {
	%>
	<jsp:include page="Menu_conn.jsp" />
	<%
	}
	}
	%>

<div class="card text-center" >
  <div class="card-header" style="background-color:gray;">
<form accept-charset="UTF-8" method="post" action="SaveData11"> 

	<%
	session = request.getSession(false);
    String fedeVal = (String) (session != null ? session.getAttribute("fede") : "");
    String regiVal = (String) (session != null ? session.getAttribute("regi") : "");
    String codeVal = (String) (session != null ? session.getAttribute("codep") : "");
    String commuVal = (String) (session != null ? session.getAttribute("commu") : "");
    String searchVal = (String) (session != null ? session.getAttribute("searchType") : "");
    String switchDisplay = (String) (session != null ? session.getAttribute("switchDisplay") : "");
	
    %>
<div id="listClub" class="d-flex" style="margin-top: 15px; margin-bottom: 15px;">
	
    <select class="form-select w-25 ms-3" aria-label="Liste des fédérations" name="fede">
    <% String fedr = ""; %>
        <option value="<%= fedr %>">Toutes les fédérations</option>
        
        
        <%
            FederationDao federationDao = new FederationDao();
            List<String> federations = federationDao.getList();
            
            for (String federation : federations) {
            	 fedr = new String(federation.getBytes(), "UTF-8");
        %>
        <option value="<%= fedr %>" <%= (fedr.equals(fedeVal) ? "selected" : "") %>>
            <%= fedr %>
          </option>
        <%
            }
        %>
    </select>
    
        <%
            CommuneDao communeDao = new CommuneDao();
            List<String> communes = communeDao.getList();
            String selectedCommune = request.getParameter("commu");
            for (String commune : communes) {
            	commune = new String(commune.getBytes(), "UTF-8");
            }
        %> 
    <select class="form-select w-25 ms-3 custom-select" aria-label="Critère de recherche" id="searchType" name="searchType">
    
        <option value="">Choisir un mode de recherche</option>
        <option value="Region" <%= "Region".equals(searchVal) ? "selected" : "" %>>Région</option>
        
          <option value="Codepostal" <%= "Codepostal".equals(searchVal) ? "selected" : "" %>>Code postal</option>
          <option value="Commune" <%= "Commune".equals(searchVal) ? "selected" : "" %>>Commune</option>
        </select>
    
    <div id="additionalInput">
       
        
        <%
            RegionDao regionDao = new RegionDao();
            List<String> regions = regionDao.getList();
            for (String region : regions) {
            	String regio = new String(region.getBytes(), "UTF-8");
            }
        %>
    </div>
    
    <button type="submit" class="btn btn-secondary ms-4" id="searchButton" >Rechercher</button>
    <div class="form-check form-switch" style="margin-top:7px;">
    <input class="form-check-input ms-4" type="checkbox" role="switch" id="flexSwitchCheckDefault" name="switchDisplay">
    <label  for="flexSwitchCheckDefault" class="switch-label">Carte</label>
</div>

<input type="hidden" id="pageInput" name="page" value="<%= request.getParameter("page") != null ? request.getParameter("page") : "1" %>">
    	<input type="hidden" name="limit" value="10">
</form>
</div>
  <div class="card-body" style="background-color:gray;">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <table class="table table-bordered" id="tbl">
	
    <tr>
        <td colspan='6'>
            <center><h2>Liste des clubs</h2></center>
        </td>
    </tr>
    <tr>
    	<th>Fédération</th>
    	<th>Région</th>
        <th>Code Commune</th>
        <th>Commune</th>
        <th>Code Fédération</th>
        <th>Total</th>
        
    </tr>
    <%
    int limit = Integer.parseInt(request.getParameter("limit") != null ? request.getParameter("limit") : "10");
    int currentPage = Integer.parseInt(request.getParameter("page") != null ? request.getParameter("page") : "1");
    int offset = (currentPage - 1) * limit;
    int count = 0;
    String req = fedeVal;
    String req1 = regiVal;
    String req2 = codeVal;
    String req3 = commuVal;

    String reqDecoded = req != null ? URLEncoder.encode(req, StandardCharsets.UTF_8.name()) : "";
    String req1Decoded = req1 != null ? URLEncoder.encode(req1, StandardCharsets.UTF_8.name()) : "";
    String req2Decoded = req2 != null ? URLEncoder.encode(req2, StandardCharsets.UTF_8.name()) : "";
    String req3Decoded = req3 != null ? URLEncoder.encode(req3, StandardCharsets.UTF_8.name()) : "";

    ArrayList<Club> listClub = null;
    ClubDAO clubDao = new ClubDAO();
    
    String searchType = request.getParameter("searchType");

    if (req != null && !req.isEmpty()) {
        if (req1 != null && !req1.isEmpty()) {
            // Recherche par fédération et région
            listClub = clubDao.getListByRegion(req, req1, limit, offset);
            count = clubDao.getListByRegion(req, req1).size();
        } else if (req2 != null && !req2.isEmpty()) {
            // Recherche par fédération et code
            listClub = clubDao.getListByCode(req, req2, limit, offset);
            count = clubDao.getListByCode(req, req2).size();
        } else if (req3 != null && !req3.isEmpty()) {
            // Recherche par fédération et commune
            listClub = clubDao.getListByCommune(req, req3, limit, offset);
            count = clubDao.getListByCommune(req, req3).size();
        } else {
            // Recherche par fédération seulement
            listClub = clubDao.getAllClubByFederation(req,limit, offset);
            count = clubDao.getAllClubByFederation(req).size();
        }
    } else if (req1 != null && !req1.isEmpty()) {
        // Recherche par région seulement
        listClub = clubDao.getAllClubByRegion(req1, limit, offset);
        count = clubDao.getAllCountByRegion(req1);
    } else if (req2 != null && !req2.isEmpty()) {
        // Recherche par code seulement
        listClub = clubDao.getAllClubByCode(req2, limit, offset);
        count = clubDao.getAllClubByCode(req2).size();
    } else if (req3 != null && !req3.isEmpty()) {
        // Recherche par commune seulement
        listClub = clubDao.getAllClubByCommune(req3, limit, offset);
        count = clubDao.getAllClubByCommune(req3).size();
    } else {
        // Recherche de tous les clubs si aucun filtre n'est appliqué
        listClub = clubDao.getAllClub(limit, offset);
        count = clubDao.getCountAllClub();
    }

    // Affichage des résultats
    for (int i = 0; i < listClub.size(); i++) {
        Club club = listClub.get(i);
        %>
        <tr id="tabb">
            <td><%= club.getNom_federation() %></td>
            <td><%= club.getRegion() %></td>
            <td><%= club.getCode_commune() %></td>
            <td><%= club.getNom_commune() %></td>
            <td><%= club.getCode_fede() %></td>
            <td><%= club.getTotal_epa_clubs() %></td>
        </tr>
        <%
    }
    %>

	</table>
	<div class="pagination">
    <%
        int totalRecords = count;
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / limit);
        
    %>
         <nav aria-label="...">
  <ul class="pagination">
  <li class="page-item ">
      <a href="?page=<%= 1 %>&limit=<%= limit %>&fede=<%= reqDecoded %>&regi=<%= req1Decoded %>&codep=<%= req2Decoded %>&searchType=<%= searchType %>&commu=<%= req3Decoded %>" class="page-link" ><<</a>
    </li>
    <li id="prevli"class="page-item ">
      <a href="?page=<%= currentPage - 1 %>&limit=<%= limit %>&fede=<%= reqDecoded %>&regi=<%= req1Decoded %>&codep=<%= req2Decoded %>&searchType=<%= searchType %>&commu=<%= req3Decoded %>" class="page-link" <%= (currentPage == totalPages) ? "disabled" : "" %>>Previous</a>
    </li>
        
    <li class="page-item active">
      <a href="?page=<%= currentPage %>&limit=<%= limit %>&fede=<%= reqDecoded %>&regi=<%= req1Decoded %>&codep=<%= req2Decoded %>&searchType=<%= searchType %>&commu=<%= req3Decoded %>" class="page-link" ><%= currentPage %></a>
    </li>
    <%
    int a = currentPage;
    for (int i = a + 1; i <= a + 5; i++) {
    	if(i>=totalPages){
			break;
		}
      
    %>
    <li class="page-item">
    	<a class="page-link" href="?page=<%= i %>&limit=<%= limit %>&fede=<%= reqDecoded %>&regi=<%= req1Decoded %>&codep=<%= req2Decoded %>&searchType=<%= searchType %>&commu=<%= req3Decoded %>" class="page-link" <%= (i == currentPage) ? "hidden" : "" %>><%= i %></a>
    </li>
    <%

    }
    	%>
    
    <li id="nextli" class="page-item ">
      <a href="?page=<%= currentPage + 1 %>&limit=<%= limit %>&fede=<%= reqDecoded %>&regi=<%= req1Decoded %>&codep=<%= req2Decoded %>&searchType=<%= searchType %>&commu=<%= req3Decoded %>" class="page-link" <%= (currentPage == totalPages) ? "disabled" : "" %>>Next</a>
    </li>
    <li class="page-item ">
      <a href="?page=<%= totalPages %>&limit=<%= limit %>&fede=<%= reqDecoded %>&regi=<%= req1Decoded %>&codep=<%= req2Decoded %>&searchType=<%= searchType %>&commu=<%= req3Decoded %>" class="page-link" >>></a>
    </li>
  </ul>
</nav>
         
         
          </div>
		</div>
		<div class="col-md-3">
<button onclick="generatePDF()" class="btn btn-primary" id="pdfButton" style="margin-left:2rem;">Télécharger le PDF</button>
</div>
    </div>
</div> </div>

  <div class="card-footer text-body-secondary">
   <jsp:include page="Footer.jsp" />
  </div>
  
</div>
  
<script>

    // Récupérer les données de la session passées en variable JavaScript
   
    

let prevli = document.getElementById('prevli');
let currentPage = <%= currentPage %>
let totalPages = <%= totalPages %>

if (currentPage === 1){
	prevli.className = "page-item disabled"
}

let nextli = document.getElementById('nextli');

if (currentPage === totalPages){
	nextli.className = "page-item disabled"
}
const BASE_URL = "http://localhost:8080/test";
async function getCommune() {
    try {
        const response = await fetch(BASE_URL + "/ServletCommune");
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        const commuList = await response.json();
        return commuList;
    } catch (error) {
        console.error("Fetch error:", error);
        return [];
    }
}
document.addEventListener("DOMContentLoaded", async function() {
    try {
        const searchTypeSelect = document.getElementById('searchType');
        const additionalInputContainer = document.getElementById('additionalInput');
        
        const commuList = await getCommune();
        const fedeVal = "<%= fedeVal != null ? fedeVal : "" %>";
        const regiVal = "<%= regiVal != null ? regiVal : "" %>";
        const codeVal = "<%= codeVal != null ? codeVal : "" %>";
        const commuVal = "<%= commuVal != null ? commuVal : "" %>";
        

        searchTypeSelect.addEventListener("change", function() {
            const selectedType = searchTypeSelect.value;
            additionalInputContainer.innerHTML = ""; // Clear current content

            if (selectedType === "Codepostal") {
                const input = document.createElement("input");
                input.type = "number";
                input.name="codep";
                input.className = "form-control w-75 ms-4";
                input.id = "codepostal";
                input.placeholder = "Entrez le code postal";
                input.min = "0";
                input.value = codeVal; // Set previous value if available
                additionalInputContainer.appendChild(input);
            }

            if (selectedType === "Region") {
                const select = document.createElement("select");
                select.className = "form-select w-75 ms-4";
                const regionOption = document.createElement("option");
                regionOption.textContent = "Liste des régions";
                select.name="regi";
                select.appendChild(regionOption);
                const list = [
                    <% 
                        for (int i = 0; i < regions.size(); i++) {
                            out.print("\"" + regions.get(i) + "\"");
                            if (i < regions.size() - 1) {
                                out.print(", "); // Ajouter une virgule si ce n'est pas le dernier élément
                            }
                        }
                    %>
                ];
                list.forEach(region => {
                    const option = document.createElement("option");
                    option.textContent = region;
                    option.value = region; // Add value attribute
                    if (region === regiVal) {
                        option.selected = true; // Select previous value if available
                    }
                    select.appendChild(option);
                });
                additionalInputContainer.appendChild(select);
            }
            
            if (selectedType === "Commune") {
                const select = document.createElement("select");
                select.className = "form-select w-75 ms-4";
                const communeOption = document.createElement("option");
                communeOption.textContent = "Liste des communes";
                select.name="commu";
                select.appendChild(communeOption);
                
                commuList.forEach(commune => {
                    const option = document.createElement("option");
                    option.textContent = commune;
                    option.value = commune; // Add value attribute
                    if (commune === commuVal) {
                        option.selected = true; // Select previous value if available
                    }
                    select.appendChild(option);
                });
                additionalInputContainer.appendChild(select);
            }
        });

        const searchButton = document.getElementById('searchButton');
        searchButton.addEventListener('click', function(event) {
            document.getElementById('pageInput').value = "1";
        });

        // Trigger the change event to populate the additional input on page load
        const selectedType = searchTypeSelect.value;
        if (selectedType) {
            searchTypeSelect.dispatchEvent(new Event('change'));
        }

        function generatePDF() {
            let element = document.getElementById('tbl'); // Sélection de l'élément à convertir en PDF
            let opt = {
                margin: 1,
                filename: 'Liste.pdf',
                image: { type: 'jpeg', quality: 0.98 },
                html2canvas: { scale: 2 },
                jsPDF: { unit: 'cm', format: 'a4', orientation: 'portrait' }
            };
            html2pdf().set(opt).from(element).save(); // Conversion de l'élément en PDF et téléchargement
        }
        
        document.getElementById('pdfButton').addEventListener('click', generatePDF);
        
    
        console.log(document.querySelector('#flexSwitchCheckDefault').value)
        document.querySelector('#flexSwitchCheckDefault').addEventListener('change', function() {
        	if(this.checked){
        		window.location.href = 'map.jsp'; // Rediriger vers la page "map.jsp"
        	}
            
        });

    } catch (error) {
        console.error('An error occurred:', error);
    }
});

</script>
</body>
</html>
