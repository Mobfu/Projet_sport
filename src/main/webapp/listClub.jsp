<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ProjetSport.FederationDao" %>
<%@ page import="ProjetSport.RegionDao" %>
<%@ page import="ProjetSport.ClubDAO" %>
<%@ page import="Module.Club" %>
 
 
 <jsp:include page="Menu.jsp" />
<form accept-charset="UTF-8">
<div id="listClub" class="d-flex" style="margin-top: 30px;">
	
    <select class="form-select w-25 ms-3" aria-label="Liste des fédérations" name="fede">
        <option value="">Liste des fédérations</option>
        <%
            FederationDao federationDao = new FederationDao();
            List<String> federations = federationDao.getList();
            for (String federation : federations) {
            	
            	String fedr = new String(federation.getBytes(), "UTF-8");
            	
        %>
        <option value="<%= fedr %>"
        ><%= fedr %></option>
        <%
            }
        %>
    </select>
    
    
    <select class="form-select w-25 ms-3 custom-select" aria-label="Critère de recherche" id="searchType">
        <option value="Région">Région</option>
        <option value="Code postal">Code postal</option>
    </select>
    
    <div id="additionalInput">
        <select class="form-select w-75 ms-3 custom-select" aria-label="Liste des fédérations" id="additionalInputS" name="regi">
        <option value="">Liste des régions</option>
        <%
            RegionDao regionDao = new RegionDao();
            List<String> regions = regionDao.getList();
            for (String region : regions) {
            	String regio = new String(region.getBytes(), "UTF-8");
        %>
        <option value="<%= regio %>"><%= regio %></option>
        <%
            }
        %>
    </select>
    </div>
    <select class="form-select w-25 ms-3 custom-select" aria-label="Nombre de Club" id="clubNumber" name="clubNumber">
            <option value="">Nombre maximum</option>
            <option value="20">20</option>
            <option value="50">50</option>
            <option value="100">100</option>
            <option value="200">200</option>
            <option value="500">500</option>
            
        </select>
    <button type="submit" class="btn btn-secondary ms-4" id="searchButton" onclick="generatePDF()">Rechercher</button>
</div>
</form>  
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <table class="table table-bordered" id="tbl">
	
    <tr>
        <td colspan='6'>
            <center><h2>Liste de club</h2></center>
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
    String req = request.getParameter("fede");
    String req1 = request.getParameter("regi");
    String req2 = null;
    if(req1 == null){
    	req2 = request.getParameter("codep");
    }
    String reqDecoded = null;
    String req1Decoded = null;
    String req2Decoded = null;
    String num = request.getParameter("clubNumber");
    
    int numDecoded = 10; // Par défaut, si le paramètre est null
    if (num != null && !num.isEmpty()) {
        numDecoded = Integer.parseInt(num);
    }
    
    if (req != null && req1 != null) {
    	reqDecoded = new String(req.getBytes("ISO-8859-1"), "UTF-8");
    	req1Decoded = new String(req1.getBytes(), "UTF-8");
    }if (req != null && req2 != null) {
    	reqDecoded = new String(req.getBytes("ISO-8859-1"), "UTF-8");
    	req2Decoded = new String(req2.getBytes(), "UTF-8");
    }
    if (reqDecoded != null && req1Decoded != null) {
        // Les paramètres ont été récupérés avec succès, vous pouvez les utiliser
        ClubDAO clubDao = new ClubDAO();
        ArrayList<Club> listClub = clubDao.getList(reqDecoded, req1Decoded);
        
        
        for (int i = 0; i < Math.min(numDecoded, listClub.size()); i++) {
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
    }
    if (reqDecoded != null && req2Decoded != null) {
        // Les paramètres ont été récupérés avec succès, vous pouvez les utiliser
        ClubDAO clubDao = new ClubDAO();
        ArrayList<Club> listClub = clubDao.getListByCode(reqDecoded, req2Decoded);
        
        
        for (int i = 0; i < Math.min(numDecoded, listClub.size()); i++) {
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
    }
%>
 
 
	</table>
 
		</div>
		<div class="col-md-3">
<button onclick="generatePDF()" class="btn btn-primary" id="pdfButton" hidden>Télécharger le PDF</button>
</div>
    </div>
</div>
 
 
 
 
 
<script>
	function() pdfappears{
    pdfButton.hidden = false;
};
function generatePDF(){
    
    
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
document.addEventListener("DOMContentLoaded", function() {
    const searchTypeSelect = document.getElementById('searchType');
    const searchButton = document.getElementById('searchButton');
    const tabb = document.getElementById('tabb');
    const pdfButton = document.getElementById('pdfButton');
    const additionalInputContainer = document.getElementById('additionalInput');
    
    searchTypeSelect.addEventListener("change", function() {
        const selectedType = searchTypeSelect.value;
        additionalInputContainer.innerHTML = ""; // Clear current content
 
        if (selectedType === "Code postal") {
            const input = document.createElement("input");
            input.type = "text";
            input.name="codep";
            input.className = "form-control w-75 ms-4";
            input.id = "codepostal";
            input.placeholder = "Entrez le code postal";
            additionalInputContainer.appendChild(input);
        } else {
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
                select.appendChild(option);
            });
            additionalInputContainer.appendChild(select);
        }
        
    });
    
    
});
</script>