<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.FederationDao" %>
<%@ page import="dao.RegionDao" %>
<%@ page import="dao.ClubDAO" %>
<%@ page import="Module.Club" %>
<%@ page import="dao.CommuneDao" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Index du Sport</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/leaflet.css" />
<link rel="stylesheet" href="./Style/map.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="./Style/style.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js" integrity="sha512-GsLlZN/3F2ErC5ifS5QtgpiJtWd43JWSuIgh7mbzZ8zBps+dvLusV+eNQATqgA/HdeKFVgA5v3S/cIrLF7QnIg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css" integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY=" crossorigin=""/>
<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js" integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo=" crossorigin=""></script>
<link rel="stylesheet" href="https://unpkg.com/leaflet.markercluster/dist/MarkerCluster.css" />
<link rel="stylesheet" href="https://unpkg.com/leaflet.markercluster/dist/MarkerCluster.Default.css" />
<script src="https://unpkg.com/leaflet.markercluster/dist/leaflet.markercluster.js"></script>

</head>
<% 
if (session == null) {
    session = request.getSession(); //
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
	} else {
	%>
	<jsp:include page="Menu_conn.jsp" />
	<%
	}
	}
	%>
	
	<%
	session = request.getSession(false);
	String fedeVal;
	String regiVal;
	String codeVal;
	String commuVal;
	String ourLng;
	String ourLat;
	String rayon;
	String searchVal ;
	String switchDisplay;
	if (session != null) {
	    // a session exists
	    ourLng = (String) session.getAttribute("lonInput");
    	ourLat = (String) session.getAttribute("latInput");
		fedeVal = (String) session.getAttribute("fede");
		regiVal = (String) session.getAttribute("regi");
		rayon = (String) session.getAttribute("rayon");
		 codeVal = (String) session.getAttribute("codep");
		 commuVal = (String) session.getAttribute("commu");
		 searchVal = (String) session.getAttribute("searchType");
		 switchDisplay = (String) session.getAttribute("switchDisplay");
	} else {
	    // no session
	    ourLng = (String) session.getAttribute("lonInput");
    	ourLat = (String) session.getAttribute("latInput");
		 fedeVal = (String) session.getAttribute("fede");
		 rayon = (String) session.getAttribute("rayon");
		regiVal = (String) session.getAttribute("regi");
		 codeVal = (String) session.getAttribute("codep");
		 commuVal = (String) session.getAttribute("commu");
		 searchVal = (String) session.getAttribute("searchType");
		 switchDisplay = (String) session.getAttribute("switchDisplay");
	}
	
    %>
<div class="card text-center">
  <div class="card-header" style="background-color:gray;">
    <form accept-charset="UTF-8" id="formFilter" method="POST" action="SaveData13">
      <div id="listClub" class="d-flex" style="margin-top: 15px; margin-bottom: 15px;">
        
        <select class="form-select w-25 ms-3" aria-label="Liste des fédérations" name="fede" id="fede">
    <% String fedr = ""; %>
        <option value="<%= fedr %>" <%= (fedr.equals(fedeVal) ? "selected" : "") %>>Toutes les fédérations</option>
        
        
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
        
        <select class="form-select w-25 ms-3 custom-select" aria-label="Critère de recherche" id="searchType" name="searchType">
    
        <option value="">Choisir un mode de recherche</option>
        <option value="Region" <%= "Region".equals(searchVal) ? "selected" : "" %>>Région</option>
        
          <option value="Codepostal" <%= "Codepostal".equals(searchVal) ? "selected" : "" %>>Code postal</option>
          <option value="Commune" <%= "Commune".equals(searchVal) ? "selected" : "" %>>Commune</option>
          <option value="Rayon">Rayon</option>
        </select>
        
        <div id="additionalInput">
          
            <% 
            	RegionDao regionDao = new RegionDao(); 
            
           		 List<String> regions = regionDao.getList(); 
				for (String region : regions) {
                	
                region = new String(region.getBytes(), "UTF-8");
                
                }
           		 
           		CommuneDao CommuneDao = new CommuneDao();
                List<String> communes = CommuneDao.getList();
                for (String commune : communes) {
                	
                	 commune = new String(commune.getBytes(), "UTF-8");
                
                }
           			 %>
        </div>
        <button type="submit" class="btn btn-secondary ms-4" id="searchButton" >Rechercher</button>
         <div class="form-check form-switch" style="margin-top:7px;">
    <input class="form-check-input ms-4" type="checkbox" role="switch" id="flexSwitchCheckDefault" name="switchDisplay">
    <label for="flexSwitchCheckDefault" class="switch-label">Liste</label>
      </div>
      <input type="hidden" id="lonInput" name="lonInput" value="<%= ourLng != null ? ourLng : "" %>">
<input type="hidden" id="latInput" name="latInput" value="<%= ourLat != null ? ourLat : "" %>">
    </form>
  </div>
  <div class="card-body">
    <div id="map" class="map rounded-4"></div>
  </div>
  <div class="card-footer text-body-secondary">Footer</div>
</div>
<script>
const fedeVal = "<%= fedeVal != null ? fedeVal : "" %>";
const regiVal = "<%= regiVal != null ? regiVal : "" %>";
const codeVal = "<%= codeVal != null ? codeVal : "" %>";
const commuVal = "<%= commuVal != null ? commuVal : "" %>";
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
let circle;  
let posCircle;
let myPosition;
let map; 

let lonInput = document.getElementById("lonInput");
let latInput = document.getElementById("latInput");


// Fonction pour ajuster la hauteur de la carte en fonction de la hauteur de la fenêtre
function setMapHeight() {
    let windowHeight = window.innerHeight; // Récupère la hauteur de la fenètre en pixel
    let mapElement = document.getElementById('map'); //Récupère le composant à partir de son id qui est map
    mapElement.style.height = windowHeight + 'px';
}

document.addEventListener('DOMContentLoaded', () => {
    setMapHeight();
    window.addEventListener('resize', setMapHeight);

    map = L.map('map').setView([0, 0], 13);
    // Ajouter une couche de tuiles  OpenStreetMap
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map);

    function updateMapLocation(position) {
        var latitude = position.coords.latitude;
        var longitude = position.coords.longitude;
        lonInput.value = longitude;
        latInput.value = latitude;
        console.log(lonInput.value);
        console.log(latInput.value);
        // Mettre à jour la carte avec la nouvelle position
        map.setView([latitude, longitude], 13);

        if (!circle) {
            circle = L.circle([latitude, longitude], {
                color: 'blue',
                fillColor: '#f03',
                fillOpacity: 0.5
            }).addTo(map);
        } else {
            // Déplacer le cercle à la nouvelle position
            circle.setLatLng([latitude, longitude]);
        }
        
        if (!posCircle) {
            posCircle = L.circle([latitude, longitude], {
                color: 'green',
                fillColor: '#f03',
                fillOpacity: 0.5,
                radius : 500
            }).addTo(map);
        }else{
        	 posCircle.setLatLng([latitude, longitude]);
        }

        // Ajouter un marqueur à la position
        myPosition = posCircle.addTo(map)
            .bindPopup("Vous etes ici").openPopup();
    }
    const fedeElement = document.querySelector('select[name="fede"]');
    const searchTypeElement = document.getElementById('searchType');
    const selectedType = searchTypeElement.value;
    const additionalInputElement = document.getElementById('additionalInput').firstChild;
    const fede = fedeElement.options[fedeElement.selectedIndex].value;
    const rayonInput = document.getElementById('rayon');

    
    
    const regi = additionalInputElement.value;
    const codep = additionalInputElement.value;
    const commu = additionalInputElement.value;
   async function getClubsData() {
        try {
            const response = await fetch(BASE_URL + "/ServlettClub");
            if (!response.ok) {
                throw new Error("HTTP error, status = " + response.status);
            }
            const dataClub = await response.json();
            //console.log(dataClub);

        } catch (error) {
            console.error("Fetch error:", error);
        }
    }

    (async () => {
        await getClubsData();
    })();

    async function getCoorsData() {
        try {
            const response = await fetch(BASE_URL + "/ServletCoor");
            if (!response.ok) {
                throw new Error("HTTP error, status = " + response.status);
            }
            const dataCoor = await response.json();
            //console.log(dataCoor);

        } catch (error) {
            console.error("Fetch error:", error);
        }
    }

    (async () => {
        await getCoorsData();
    })();

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(updateMapLocation, (error) => {
            console.error("Geolocation error:", error);
        });
    } else {
        console.error("Geolocation non supporter par ce serveur.");
    }
});

var markers = L.markerClusterGroup();


async function getClubWithFilter() {
    try {
        
        
        const fedeElement = document.querySelector('select[name="fede"]');
        const searchTypeElement = document.getElementById('searchType');
        const selectedType = searchTypeElement.value;
        const additionalInputElement = document.getElementById('additionalInput').firstChild;
        const fede = fedeElement.options[fedeElement.selectedIndex].value;
        const rayonInput = document.getElementById('rayon');
        
        
        const regi = additionalInputElement.value;
        const codep = additionalInputElement.value;
        const commu = additionalInputElement.value;
        let rayon;
        const st = searchTypeElement.value;
        
        let params = new URLSearchParams();
        params.append("fede",fede);
        params.append("regi",regi);
        
       	if (additionalInputElement) {
            if (selectedType === "Rayon") {
                params.append("rayon", parseFloat(additionalInputElement.value));
            } else if (selectedType === "Region") {
                params.append("regi", additionalInputElement.value);
            } else if (selectedType === "Code postal") {
                params.append("codep", additionalInputElement.value);
            } else if (selectedType === "Commune") {
                params.append("commu", additionalInputElement.value);
            }
        }

        const responseCoor = await fetch(BASE_URL + "/ServletCoorRegion?" + params.toString());
        const responseClub = await fetch(BASE_URL + "/ServletClubRegion?" + params.toString());
        
        if (!responseCoor.ok) {
            throw new Error("HTTP error, status = " + responseCoor.status);
        }if (!responseClub.ok) {
            throw new Error("HTTP error, status = " + responseClub.status);
        }
        const dataCoor = await responseCoor.json();
        const dataClub = await responseClub.json();
        
        const filteredClubs = dataClub.filter(function (club) {
            return (club.nom_federation.includes(fede) && club.region.includes(regi))||(club.nom_federation.includes(fede) && club.nom_commune.includes(commu))||(club.nom_federation.includes(fede) && dataCoor.some(coor => coor.zip_code == codep && coor.insee_code == club.code_commune));
        });
        
        

        const filteredCoors = dataCoor.filter(function (coor) {
            return filteredClubs.some(club => club.code_commune == coor.insee_code);
        });

        console.log(rayon);
        console.log(filteredCoors);
        var point = L.latLng(0, 0);
        
        if (fede !== "" && selectedType === "Region") {
        // Supprimer tous les marqueurs existants sur la carte
        map.eachLayer(function (layer) {
            if (layer instanceof L.Marker && layer !== myPosition) {
                map.removeLayer(layer);
            }
        });
        markers.clearLayers();
        filteredCoors.forEach(function (coor) {
            if (coor.latitude !== "" && coor.longitude !== "") {
                var latitude = parseFloat(coor.latitude);
                var longitude = parseFloat(coor.longitude);
                var point = L.latLng(latitude, longitude);

                // Find the corresponding club for the current coordinate
                let correspondingClubs = filteredClubs.filter(club => club.code_commune == coor.insee_code);

                correspondingClubs.forEach(correspondingClub => {
                    let mark = L.marker([latitude, longitude]);

                    mark.bindPopup("<div class='card'><div class='card-header'>Federation: " + correspondingClub.nom_federation + "</div><ul class='list-group list-group-flush'><li class='list-group-item list-group-item-action'>Code postale : " + coor.zip_code + "</li><li class='list-group-item list-group-item-action'>Region : " + correspondingClub.region + "</li><li class='list-group-item list-group-item-action '>Commune: " + correspondingClub.nom_commune + "</li></ul></div>");
                    
                    // Add each marker to the marker cluster group
                    markers.addLayer(mark);
                });
            }
        });

        // Add the marker cluster group to the map
        map.addLayer(markers);
    }
    } catch (error) {
        console.error("Fetch error:", error);
    }
}

async function getAllClubWithRegion() {
    try {
        
        
        const fedeElement = document.querySelector('select[name="fede"]');
        const searchTypeElement = document.getElementById('searchType');
        const selectedType = searchTypeElement.value;
        const additionalInputElement = document.getElementById('additionalInput').firstChild;
        const fede = fedeElement.options[fedeElement.selectedIndex].value;

        
        
        const regi = additionalInputElement.value;
        const codep = additionalInputElement.value;
        const commu = additionalInputElement.value;
        let rayon;
        const st = searchTypeElement.value;
        
        let params = new URLSearchParams();
        
        params.append("regi",regi);
        
       	if (additionalInputElement) {
            if (selectedType === "Rayon") {
                params.append("rayon", parseFloat(additionalInputElement.value));
            } else if (selectedType === "Region") {
                params.append("regi", additionalInputElement.value);
            } else if (selectedType === "Code postal") {
                params.append("codep", additionalInputElement.value);
            } else if (selectedType === "Commune") {
                params.append("commu", additionalInputElement.value);
            }
        }

        const responseCoor = await fetch(BASE_URL + "/ServletAllCoorsRegion?" + params.toString());
        const responseClub = await fetch(BASE_URL + "/ServletAllClubRegion?" + params.toString());
        
        if (!responseCoor.ok) {
            throw new Error("HTTP error, status = " + responseCoor.status);
        }if (!responseClub.ok) {
            throw new Error("HTTP error, status = " + responseClub.status);
        }
        const dataCoor = await responseCoor.json();
        const dataClub = await responseClub.json();
        
        const filteredClubs = dataClub.filter(function (club) {
            return (club.nom_federation.includes(fede) && club.region.includes(regi))||(club.nom_federation.includes(fede) && club.nom_commune.includes(commu))||(club.nom_federation.includes(fede) && dataCoor.some(coor => coor.zip_code == codep && coor.insee_code == club.code_commune));
        });
        
        

        const filteredCoors = dataCoor.filter(function (coor) {
            return filteredClubs.some(club => club.code_commune == coor.insee_code);
        });

        console.log(rayon);
        console.log(filteredCoors);
        var point = L.latLng(0, 0);
        
        if (fede === "" && selectedType === "Region") {
        // Supprimer tous les marqueurs existants sur la carte
        map.eachLayer(function (layer) {
            if (layer instanceof L.Marker && layer !== myPosition) {
                map.removeLayer(layer);
            }
        });
        markers.clearLayers();
        filteredCoors.forEach(function (coor) {
            if (coor.latitude !== "" && coor.longitude !== "") {
                var latitude = parseFloat(coor.latitude);
                var longitude = parseFloat(coor.longitude);
                var point = L.latLng(latitude, longitude);

                // Find the corresponding club for the current coordinate
                let correspondingClubs = filteredClubs.filter(club => club.code_commune == coor.insee_code);

                correspondingClubs.forEach(correspondingClub => {
                    let mark = L.marker([latitude, longitude]);

                    mark.bindPopup("<div class='card'><div class='card-header'>Federation: " + correspondingClub.nom_federation + "</div><ul class='list-group list-group-flush'><li class='list-group-item list-group-item-action'>Code postale : " + coor.zip_code + "</li><li class='list-group-item list-group-item-action'>Region : " + correspondingClub.region + "</li><li class='list-group-item list-group-item-action '>Commune: " + correspondingClub.nom_commune + "</li></ul></div>");
                    
                    // Add each marker to the marker cluster group
                    markers.addLayer(mark);
                });
            }
        });

        // Add the marker cluster group to the map
        map.addLayer(markers);
    }
    } catch (error) {
        console.error("Fetch error:", error);
    }
}

async function getAllClubWithCommune() {
    try {
        
        
        const fedeElement = document.querySelector('select[name="fede"]');
        const searchTypeElement = document.getElementById('searchType');
        const selectedType = searchTypeElement.value;
        const additionalInputElement = document.getElementById('additionalInput').firstChild;
        const fede = fedeElement.options[fedeElement.selectedIndex].value;

        
        
        const regi = additionalInputElement.value;
        const codep = additionalInputElement.value;
        const commu = additionalInputElement.value;
        let rayon;
        const st = searchTypeElement.value;
        
        let params = new URLSearchParams();
        
        params.append("commu",commu);
        
       	if (additionalInputElement) {
            if (selectedType === "Rayon") {
                params.append("rayon", parseFloat(additionalInputElement.value));
            } else if (selectedType === "Region") {
                params.append("regi", additionalInputElement.value);
            } else if (selectedType === "Code postal") {
                params.append("codep", additionalInputElement.value);
            } else if (selectedType === "Commune") {
                params.append("commu", additionalInputElement.value);
            }
        }

        const responseCoor = await fetch(BASE_URL + "/ServletAllCoorsCommune?" + params.toString());
        const responseClub = await fetch(BASE_URL + "/ServletAllClubCommune?" + params.toString());
        
        if (!responseCoor.ok) {
            throw new Error("HTTP error, status = " + responseCoor.status);
        }if (!responseClub.ok) {
            throw new Error("HTTP error, status = " + responseClub.status);
        }
        const dataCoor = await responseCoor.json();
        const dataClub = await responseClub.json();
        
        const filteredClubs = dataClub.filter(function (club) {
            return (club.nom_federation.includes(fede) && club.region.includes(regi))||(club.nom_federation.includes(fede) && club.nom_commune.includes(commu))||(club.nom_federation.includes(fede) && dataCoor.some(coor => coor.zip_code == codep && coor.insee_code == club.code_commune));
        });
        
        

        const filteredCoors = dataCoor.filter(function (coor) {
            return filteredClubs.some(club => club.code_commune == coor.insee_code);
        });

        console.log(rayon);
        console.log(filteredCoors);
        var point = L.latLng(0, 0);
        
        if (fede === "" && selectedType === "Commune") {
        // Supprimer tous les marqueurs existants sur la carte
        map.eachLayer(function (layer) {
            if (layer instanceof L.Marker && layer !== myPosition) {
                map.removeLayer(layer);
            }
        });
        markers.clearLayers();
        filteredCoors.forEach(function (coor) {
            if (coor.latitude !== "" && coor.longitude !== "") {
                var latitude = parseFloat(coor.latitude);
                var longitude = parseFloat(coor.longitude);
                var point = L.latLng(latitude, longitude);

                // Find the corresponding club for the current coordinate
                let correspondingClubs = filteredClubs.filter(club => club.code_commune == coor.insee_code);

                correspondingClubs.forEach(correspondingClub => {
                    let mark = L.marker([latitude, longitude]);

                    mark.bindPopup("<div class='card'><div class='card-header'>Federation: " + correspondingClub.nom_federation + "</div><ul class='list-group list-group-flush'><li class='list-group-item list-group-item-action'>Code postale : " + coor.zip_code + "</li><li class='list-group-item list-group-item-action'>Region : " + correspondingClub.region + "</li><li class='list-group-item list-group-item-action '>Commune: " + correspondingClub.nom_commune + "</li></ul></div>");
                    
                    // Add each marker to the marker cluster group
                    markers.addLayer(mark);
                });
            }
        });

        // Add the marker cluster group to the map
        map.addLayer(markers);
        }
    } catch (error) {
        console.error("Fetch error:", error);
    }
}

async function getAllClubWithCode() {
    try {
        
        
        const fedeElement = document.querySelector('select[name="fede"]');
        const searchTypeElement = document.getElementById('searchType');
        const selectedType = searchTypeElement.value;
        const additionalInputElement = document.getElementById('additionalInput').firstChild;
        const fede = fedeElement.options[fedeElement.selectedIndex].value;

        
        
        const regi = additionalInputElement.value;
        const codep = additionalInputElement.value;
        const commu = additionalInputElement.value;
        let rayon;
        const st = searchTypeElement.value;
        
        let params = new URLSearchParams();
        
        params.append("codep",codep);
        
       	if (additionalInputElement) {
            if (selectedType === "Rayon") {
                params.append("rayon", parseFloat(additionalInputElement.value));
            } else if (selectedType === "Region") {
                params.append("regi", additionalInputElement.value);
            } else if (selectedType === "Code postal") {
                params.append("codep", additionalInputElement.value);
            } else if (selectedType === "Commune") {
                params.append("commu", additionalInputElement.value);
            }
        }

        const responseCoor = await fetch(BASE_URL + "/ServletAllCoorsCode?" + params.toString());
        const responseClub = await fetch(BASE_URL + "/ServletAllClubCode?" + params.toString());
        
        if (!responseCoor.ok) {
            throw new Error("HTTP error, status = " + responseCoor.status);
        }if (!responseClub.ok) {
            throw new Error("HTTP error, status = " + responseClub.status);
        }
        const dataCoor = await responseCoor.json();
        const dataClub = await responseClub.json();
        
        const filteredClubs = dataClub.filter(function (club) {
            return (club.nom_federation.includes(fede) && club.region.includes(regi))||(club.nom_federation.includes(fede) && club.nom_commune.includes(commu))||(club.nom_federation.includes(fede) && dataCoor.some(coor => coor.zip_code == codep && coor.insee_code == club.code_commune));
        });
        
        

        const filteredCoors = dataCoor.filter(function (coor) {
            return filteredClubs.some(club => club.code_commune == coor.insee_code);
        });

        console.log(rayon);
        console.log(filteredCoors);
        var point = L.latLng(0, 0);
        
        if (fede === "" && selectedType === "Codepostal") {
        // Supprimer tous les marqueurs existants sur la carte
        map.eachLayer(function (layer) {
            if (layer instanceof L.Marker && layer !== myPosition) {
                map.removeLayer(layer);
            }
        });
        markers.clearLayers();
        filteredCoors.forEach(function (coor) {
            if (coor.latitude !== "" && coor.longitude !== "") {
                var latitude = parseFloat(coor.latitude);
                var longitude = parseFloat(coor.longitude);
                var point = L.latLng(latitude, longitude);

                // Find the corresponding club for the current coordinate
                let correspondingClubs = filteredClubs.filter(club => club.code_commune == coor.insee_code);

                correspondingClubs.forEach(correspondingClub => {
                    let mark = L.marker([latitude, longitude]);

                    mark.bindPopup("<div class='card'><div class='card-header'>Federation: " + correspondingClub.nom_federation + "</div><ul class='list-group list-group-flush'><li class='list-group-item list-group-item-action'>Code postale : " + coor.zip_code + "</li><li class='list-group-item list-group-item-action'>Region : " + correspondingClub.region + "</li><li class='list-group-item list-group-item-action '>Commune: " + correspondingClub.nom_commune + "</li></ul></div>");
                    
                    // Add each marker to the marker cluster group
                    markers.addLayer(mark);
                });
            }
        });

        // Add the marker cluster group to the map
        map.addLayer(markers);
        }
    } catch (error) {
        console.error("Fetch error:", error);
    }
}

async function getClubWithCode() {
    try {
        
        
        const fedeElement = document.querySelector('select[name="fede"]');
        const searchTypeElement = document.getElementById('searchType');
        const selectedType = searchTypeElement.value;
        const additionalInputElement = document.getElementById('additionalInput').firstChild;
        const fede = fedeElement.options[fedeElement.selectedIndex].value;

        
        
       
        const codep = additionalInputElement.value;
       
        let rayon;
        const st = searchTypeElement.value;
        
        let params = new URLSearchParams();
        params.append("fede",fede);
        params.append("codep",codep);
        
       	if (additionalInputElement) {
            if (selectedType === "Rayon") {
                params.append("rayon", parseFloat(additionalInputElement.value));
            } else if (selectedType === "Region") {
                params.append("regi", additionalInputElement.value);
            } else if (selectedType === "Code postal") {
                params.append("codep", additionalInputElement.value);
            } else if (selectedType === "Commune") {
                params.append("commu", additionalInputElement.value);
            }
        }

        const responseCoor = await fetch(BASE_URL + "/ServletCoorCode?" + params.toString());
        const responseClub = await fetch(BASE_URL + "/ServletClubCode?" + params.toString());
        
        if (!responseCoor.ok) {
            throw new Error("HTTP error, status = " + responseCoor.status);
        }if (!responseClub.ok) {
            throw new Error("HTTP error, status = " + responseClub.status);
        }
        const dataCoor = await responseCoor.json();
        const dataClub = await responseClub.json();
        
        const filteredClubs = dataClub.filter(function (club) {
            return (club.nom_federation.includes(fede) && club.region.includes(regi))||(club.nom_federation.includes(fede) && club.nom_commune.includes(commu))||(club.nom_federation.includes(fede) && dataCoor.some(coor => coor.zip_code == codep && coor.insee_code == club.code_commune));
        });
        
        

        const filteredCoors = dataCoor.filter(function (coor) {
            return filteredClubs.some(club => club.code_commune == coor.insee_code);
        });

       console.log('selectedType',selectedType);
       // console.log('regi',regi);
        console.log(filteredCoors);
        var point = L.latLng(0, 0);
        
        if (fede !== "" && selectedType === "Codepostal" ) {
        // Supprimer tous les marqueurs existants sur la carte
        map.eachLayer(function (layer) {
            if (layer instanceof L.Marker && layer !== myPosition) {
                map.removeLayer(layer);
            }
        });
        markers.clearLayers();
        filteredCoors.forEach(function (coor) {
            if (coor.latitude !== "" && coor.longitude !== "") {
                var latitude = parseFloat(coor.latitude);
                var longitude = parseFloat(coor.longitude);
                var point = L.latLng(latitude, longitude);

                // Find the corresponding club for the current coordinate
                let correspondingClubs = filteredClubs.filter(club => club.code_commune == coor.insee_code);

                correspondingClubs.forEach(correspondingClub => {
                    let mark = L.marker([latitude, longitude]);

                    mark.bindPopup("<div class='card'><div class='card-header'>Federation: " + correspondingClub.nom_federation + "</div><ul class='list-group list-group-flush'><li class='list-group-item list-group-item-action'>Code postale : " + coor.zip_code + "</li><li class='list-group-item list-group-item-action'>Region : " + correspondingClub.region + "</li><li class='list-group-item list-group-item-action '>Commune: " + correspondingClub.nom_commune + "</li></ul></div>");
                    
                    // Add each marker to the marker cluster group
                    markers.addLayer(mark);
                });
            }
        });

        // Add the marker cluster group to the map
        map.addLayer(markers);
        }
    } catch (error) {
        console.error("Fetch error:", error);
    }
}

async function getClubWithCommune() {
    try {
        
        
        const fedeElement = document.querySelector('select[name="fede"]');
        const searchTypeElement = document.getElementById('searchType');
        const selectedType = searchTypeElement.value;
        const additionalInputElement = document.getElementById('additionalInput').firstChild;
        const fede = fedeElement.options[fedeElement.selectedIndex].value;

        
        
        const regi = additionalInputElement.value;
        const codep = additionalInputElement.value;
        const commu = additionalInputElement.value;
        let rayon;
        const st = searchTypeElement.value;
        
        let params = new URLSearchParams();
        params.append("fede",fede);
        params.append("commu",commu);
        
       	if (additionalInputElement) {
            if (selectedType === "Rayon") {
                params.append("rayon", parseFloat(additionalInputElement.value));
            } else if (selectedType === "Region") {
                params.append("regi", additionalInputElement.value);
            } else if (selectedType === "Code postal") {
                params.append("codep", additionalInputElement.value);
            } else if (selectedType === "Commune") {
                params.append("commu", additionalInputElement.value);
            }
        }

        const responseCoor = await fetch(BASE_URL + "/ServletCoorCommune?" + params.toString());
        const responseClub = await fetch(BASE_URL + "/ServletClubCommune?" + params.toString());
        
        if (!responseCoor.ok) {
            throw new Error("HTTP error, status = " + responseCoor.status);
        }if (!responseClub.ok) {
            throw new Error("HTTP error, status = " + responseClub.status);
        }
        const dataCoor = await responseCoor.json();
        const dataClub = await responseClub.json();
        
        const filteredClubs = dataClub.filter(function (club) {
            return (club.nom_federation.includes(fede) && club.region.includes(regi))||(club.nom_federation.includes(fede) && club.nom_commune.includes(commu))||(club.nom_federation.includes(fede) && dataCoor.some(coor => coor.zip_code == codep && coor.insee_code == club.code_commune));
        });
        
        

        const filteredCoors = dataCoor.filter(function (coor) {
            return filteredClubs.some(club => club.code_commune == coor.insee_code);
        });

        console.log(rayon);
        console.log(filteredCoors);
        var point = L.latLng(0, 0);
        
        if (fede !== "" && selectedType === "Commune") {
        // Supprimer tous les marqueurs existants sur la carte
        map.eachLayer(function (layer) {
            if (layer instanceof L.Marker && layer !== myPosition) {
                map.removeLayer(layer);
            }
        });
        markers.clearLayers();
        filteredCoors.forEach(function (coor) {
            if (coor.latitude !== "" && coor.longitude !== "") {
                var latitude = parseFloat(coor.latitude);
                var longitude = parseFloat(coor.longitude);
                var point = L.latLng(latitude, longitude);

                // Find the corresponding club for the current coordinate
                let correspondingClubs = filteredClubs.filter(club => club.code_commune == coor.insee_code);

                correspondingClubs.forEach(correspondingClub => {
                    let mark = L.marker([latitude, longitude]);

                    mark.bindPopup("<div class='card'><div class='card-header'>Federation: " + correspondingClub.nom_federation + "</div><ul class='list-group list-group-flush'><li class='list-group-item list-group-item-action'>Code postale : " + coor.zip_code + "</li><li class='list-group-item list-group-item-action'>Region : " + correspondingClub.region + "</li><li class='list-group-item list-group-item-action '>Commune: " + correspondingClub.nom_commune + "</li></ul></div>");
                    
                    // Add each marker to the marker cluster group
                    markers.addLayer(mark);
                });
            }
        });

        // Add the marker cluster group to the map
        map.addLayer(markers);
        }
    } catch (error) {
        console.error("Fetch error:", error);
    }
}


async function getClubWithRadius() {
    try {
        const lonInput = document.getElementById('lonInput').value;
        const latInput = document.getElementById('latInput').value;
        const fede = document.getElementById('fede').value;
        const searchTypeElement = document.getElementById('searchType');
        const additionalInputElement = document.getElementById('additionalInput').querySelector('select, input');
        const selectedType = searchTypeElement.value;
        let filteredClubs = [];
        let params = new URLSearchParams();
        params.append("lonInput", lonInput);
        params.append("latInput", latInput);
        params.append("fede",fede);
        
       	if (additionalInputElement) {
            if (selectedType === "Rayon") {
                params.append("rayon", parseFloat(additionalInputElement.value));
            } else if (selectedType === "Region") {
                params.append("regi", additionalInputElement.value);
            } else if (selectedType === "Code postal") {
                params.append("codep", additionalInputElement.value);
            } else if (selectedType === "Commune") {
                params.append("commu", additionalInputElement.value);
            }
        }

        const responseCoor = await fetch(BASE_URL + "/ServletCoorRadius?" + params.toString());
        const responseClub = await fetch(BASE_URL + "/ServletClubRadius?" + params.toString());
        
        
        if (!responseCoor.ok) {
            throw new Error("HTTP error, status = " + responseCoor.status);
        }if (!responseClub.ok) {
            throw new Error("HTTP error, status = " + responseClub.status);
        }
        const dataCoor = await responseCoor.json();
        const dataClub = await responseClub.json();
        
        filteredClubs = dataClub.filter(function (club) {
            return (club.nom_federation === fede);
        });
        
        const filteredCoors = dataCoor.filter(function (coor) {
            return filteredClubs.some(club => club.code_commune == coor.insee_code);
        });
        console.log(filteredClubs);
     // Supprimer tous les marqueurs existants sur la carte
        map.eachLayer(function (layer) {
            if (layer instanceof L.Marker && layer !== myPosition) {
                map.removeLayer(layer);
            }
        });

        if (fede !== "" &&selectedType === "Rayon") {
        	// Supprimer tous les marqueurs existants sur la carte
            
            markers.clearLayers();
        	filteredCoors.forEach(function (coor) {
        	    if (coor.latitude !== "" && coor.longitude !== "") {
        	        var latitude = parseFloat(coor.latitude);
        	        var longitude = parseFloat(coor.longitude);
        	        var point = L.latLng(latitude, longitude);

        	        // Find the corresponding club for the current coordinate
        	        let correspondingClubs = filteredClubs.filter(club => club.code_commune == coor.insee_code);

        	        correspondingClubs.forEach(correspondingClub => {
        	            let mark = L.marker([latitude, longitude]);

        	            mark.bindPopup("<div class='card'><div class='card-header'>Federation: " + correspondingClub.nom_federation + "</div><ul class='list-group list-group-flush'><li class='list-group-item list-group-item-action'>Code postale : " + coor.zip_code + "</li><li class='list-group-item list-group-item-action'>Region : " + correspondingClub.region + "</li><li class='list-group-item list-group-item-action '>Commune: " + correspondingClub.nom_commune + "</li></ul></div>");
        	            
        	            // Add each marker to the marker cluster group
        	            markers.addLayer(mark);
        	        });
        	    }
        	});

        	// Add the marker cluster group to the map
        	map.addLayer(markers);
        }
        
    } catch (error) {
        console.error("Fetch error:", error);
    }
}


async function getAllClubWithRadius() {
    try {
        const lonInput = document.getElementById('lonInput').value;
        const latInput = document.getElementById('latInput').value;
        const fede = document.getElementById('fede').value;
        const searchTypeElement = document.getElementById('searchType');
        const additionalInputElement = document.getElementById('additionalInput').querySelector('select, input');
        const selectedType = searchTypeElement.value;
        let filteredClubs = [];
        let params = new URLSearchParams();
        params.append("lonInput", lonInput);
        params.append("latInput", latInput);
        params.append("fede",fede);
        
       	if (additionalInputElement) {
            if (selectedType === "Rayon") {
                params.append("rayon", parseFloat(additionalInputElement.value));
            } else if (selectedType === "Region") {
                params.append("regi", additionalInputElement.value);
            } else if (selectedType === "Code postal") {
                params.append("codep", additionalInputElement.value);
            } else if (selectedType === "Commune") {
                params.append("commu", additionalInputElement.value);
            }
        }

        const responseCoor = await fetch(BASE_URL + "/ServletCoorRadius?" + params.toString());
        const responseAllClub = await fetch(BASE_URL + "/ServletAllClubRadius?" + params.toString());
        
        if (!responseCoor.ok) {
            throw new Error("HTTP error, status = " + responseCoor.status);
        }if (!responseAllClub.ok) {
            throw new Error("HTTP error, status = " + responseAllClub.status);
        }
        const dataCoor = await responseCoor.json();
        const dataClub = await responseAllClub.json();
        
        filteredClubs = dataClub.filter(function (club) {
            return (club.nom_federation === fede);
        });
        
        const filteredCoors = dataCoor.filter(function (coor) {
            return filteredClubs.some(club => club.code_commune == coor.insee_code);
        });
        console.log(filteredClubs);
     // Supprimer tous les marqueurs existants sur la carte
        

		if (fede === "" && selectedType === "Rayon") {
        	// Supprimer tous les marqueurs existants sur la carte
        	
            map.eachLayer(function (layer) {
            if (layer instanceof L.Marker && layer !== myPosition) {
                map.removeLayer(layer);
            }
        });
            
            filteredClubs = dataClub;
            
            const filteredCoors = dataCoor.filter(function (coor) {
                return filteredClubs.some(club => club.code_commune == coor.insee_code);
            });
            console.log(filteredClubs);
        
            markers.clearLayers();
           
            filteredCoors.forEach(function (coor) {
                if (coor.latitude !== "" && coor.longitude !== "") {
                    var latitude = parseFloat(coor.latitude);
                    var longitude = parseFloat(coor.longitude);
                    var point = L.latLng(latitude, longitude);

                    // Find the corresponding club for the current coordinate
                    let correspondingClubs = filteredClubs.filter(club => club.code_commune == coor.insee_code);

                    correspondingClubs.forEach(correspondingClub => {
                        let mark = L.marker([latitude, longitude]);

                        mark.bindPopup("<div class='card'><div class='card-header'>Federation: " + correspondingClub.nom_federation + "</div><ul class='list-group list-group-flush'><li class='list-group-item list-group-item-action'>Code postale : " + coor.zip_code + "</li><li class='list-group-item list-group-item-action'>Region : " + correspondingClub.region + "</li><li class='list-group-item list-group-item-action '>Commune: " + correspondingClub.nom_commune + "</li></ul></div>");
                        
                        // Add each marker to the marker cluster group
                        markers.addLayer(mark);
                    });
                }
            });

            // Add the marker cluster group to the map
            map.addLayer(markers);
        }
        
    } catch (error) {
        console.error("Fetch error:", error);
    }
}


document.getElementById('formFilter').addEventListener('submit', async function (event) {
    event.preventDefault(); // Prevent the form from submitting normally
    const fede = document.getElementById('fede').value;
    const regi = document.getElementById('regi');
    const codep = document.getElementById('codep');
    const commu = document.getElementById('commu');
    const rayonInput = document.getElementById('rayon');
    
    



    
    if (rayonInput) {
        const rayon = rayonInput.value;
        circle.setRadius(rayon * 1010); // Conversion de kilomètres en mètres
    }else{
    	circle.setRadius(0); // Conversion de kilomètres en mètres
    }
    const lonInput = document.getElementById('lonInput');
    const latInput = document.getElementById('latInput');

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            lonInput.value = position.coords.longitude;
            latInput.value = position.coords.latitude;
            
            if(fede !== "" && regi !== ""){
				 getClubWithFilter(); // Call the function to fetch and filter clubs
			}if(fede === "" && regi !== "" && (codep === "" || codep === null)){
				 getAllClubWithRegion(); // Call the function to fetch and filter clubs
			}if(fede === "" && regi === null && codep === null){
				 getAllClubWithCommune(); // Call the function to fetch and filter clubs
			} if(fede === "" && codep !== "" && (rayonInput !== null || rayonInput !== "")  && (regi === "" || regi === null) && (commu === "" || commu === null)){
				 getAllClubWithCode(); // Call the function to fetch and filter clubs
			} 
            if(fede !== "" && codep !== ""){
				 getClubWithCode(); // Call the function to fetch and filter clubs
			}if(fede !== "" && commu !== "" && (rayonInput !== null || rayonInput !== "") && (codep === "" || codep === null) && (regi === "" || regi === null)){
				 getClubWithCommune(); // Call the function to fetch and filter clubs
			}if(fede === "" && (rayonInput !== null || rayonInput !== "") && (codep === "" || codep === null) && (regi === "" || regi === null) && (commu === "" || commu === null)){
				 getAllClubWithRadius(); // Call the function to fetch and filter clubs
			}if(fede !== "" && (rayonInput !== null || rayonInput !== "") && (codep === "" || codep === null) && (regi === "" || regi === null) && (commu === "" || commu === null)){
				getClubWithRadius(); // Call the function to fetch and filter club
			}
           
        }, function(error) {
            console.error("Geolocation error:", error);
            if(fede !== "" && regi !== ""){
				 getClubWithFilter(); // Call the function to fetch and filter clubs
			}if(fede === "" && regi !== "" && (codep === "" || codep === null)){
				 getAllClubWithRegion(); // Call the function to fetch and filter clubs
			}if(fede === "" && regi === null && codep === null){
				 getAllClubWithCommune(); // Call the function to fetch and filter clubs
			} if(fede === "" && codep !== "" && (rayonInput !== null || rayonInput !== "")  && (regi === "" || regi === null) && (commu === "" || commu === null)){
				 getAllClubWithCode(); // Call the function to fetch and filter clubs
			} 
           if(fede !== "" && codep !== ""){
				 getClubWithCode(); // Call the function to fetch and filter clubs
			}if(fede !== "" && commu !== "" && (rayonInput !== null || rayonInput !== "") && (codep === "" || codep === null) && (regi === "" || regi === null)){
				 getClubWithCommune(); // Call the function to fetch and filter clubs
			}if(fede === "" && (rayonInput !== null || rayonInput !== "") && (codep === "" || codep === null) && (regi === "" || regi === null) && (commu === "" || commu === null)){
				 getAllClubWithRadius(); // Call the function to fetch and filter clubs
			}if(fede !== "" && (rayonInput !== null || rayonInput !== "") && (codep === "" || codep === null) && (regi === "" || regi === null) && (commu === "" || commu === null)){
				getClubWithRadius(); // Call the function to fetch and filter club
			}
        });
    } else {
        console.error("Geolocation not supported by this browser.");
        if(fede !== "" && regi !== ""){
			 getClubWithFilter(); // Call the function to fetch and filter clubs
		}if(fede === "" && regi !== "" && (codep === "" || codep === null)){
			 getAllClubWithRegion(); // Call the function to fetch and filter clubs
		}if(fede === "" && regi === null && codep === null){
			 getAllClubWithCommune(); // Call the function to fetch and filter clubs
		} if(fede === "" && codep !== "" && (rayonInput !== null || rayonInput !== "")  && (regi === "" || regi === null) && (commu === "" || commu === null)){
			 getAllClubWithCode(); // Call the function to fetch and filter clubs
		} 
       if(fede !== "" && codep !== ""){
			 getClubWithCode(); // Call the function to fetch and filter clubs
		}if(fede !== "" && commu !== "" && (rayonInput !== null || rayonInput !== "") && (codep === "" || codep === null) && (regi === "" || regi === null)){
			 getClubWithCommune(); // Call the function to fetch and filter clubs
		}if(fede === "" && (rayonInput !== null || rayonInput !== "") && (codep === "" || codep === null) && (regi === "" || regi === null) && (commu === "" || commu === null)){
			 getAllClubWithRadius(); // Call the function to fetch and filter clubs
		}if(fede !== "" && (rayonInput !== null || rayonInput !== "") && (codep === "" || codep === null) && (regi === "" || regi === null) && (commu === "" || commu === null)){
			getClubWithRadius(); // Call the function to fetch and filter club
		}
    }
});




document.addEventListener("DOMContentLoaded", async function() {
    try {
        const searchTypeSelect = document.getElementById('searchType');
        const additionalInputContainer = document.getElementById('additionalInput');
        const searchButton = document.getElementById('searchButton');
        const pdfButton = document.getElementById('pdfButton');
        const flexSwitch = document.querySelector('#flexSwitchCheckDefault');

        const fedeVal = "<%= fedeVal != null ? fedeVal : "" %>";
        const regiVal = "<%= regiVal != null ? regiVal : "" %>";
        const codeVal = "<%= codeVal != null ? codeVal : "" %>";
        const commuVal = "<%= commuVal != null ? commuVal : "" %>";
        const commuList = await getCommune();
        if (searchTypeSelect && additionalInputContainer) {
            searchTypeSelect.addEventListener("change", function() {
                const selectedType = searchTypeSelect.value;
                additionalInputContainer.innerHTML = ""; // Clear current content

                if (selectedType === "Codepostal") {
                    const input = document.createElement("input");
                    input.type = "number";
                    input.name = "codep";
                    input.className = "form-control w-75 ms-4";
                    input.id = "codep";
                    input.placeholder = "Entrez le code postal";
                    input.min = "0";
                    commu = "";
                    regi = "";
                    rayonInput = "";
                    input.value = codeVal; // Set previous value if available
                    additionalInputContainer.appendChild(input);
                }
                if (selectedType === "Rayon") {
                    const input = document.createElement("input");
                    input.type = "number";
                    input.name = "rayon";
                    input.className = "form-control w-75 ms-4";
                    input.id = "rayon";
                    input.min = "0";
                    commu = "";
                    regi = "";
                    input.placeholder = "Rayon en km";
                    additionalInputContainer.appendChild(input);
                }
                if (selectedType === "Region") {
                    const select = document.createElement("select");
                    select.className = "form-select w-75 ms-4";
                    const regionOption = document.createElement("option");
                    regionOption.textContent = "Liste des régions";
                    commu = "";
                    codep = "";
                    rayonInput = "";
                    select.name = "regi";
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
                    select.name = "commu";
                    codep = "";
                    regi = "";
                    rayonInput = "";
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

            // Trigger the change event to populate the additional input on page load
            const selectedType = searchTypeSelect.value;
            if (selectedType) {
                searchTypeSelect.dispatchEvent(new Event('change'));
            }
        }


        if (flexSwitch) {
            console.log(flexSwitch.value);
            flexSwitch.addEventListener('change', function() {
            	 	    
            window.location.href = 'listClub.jsp';
            	
            });
        }

    } catch (error) {
        console.error('An error occurred:', error);
    }
});

</script>
</body>
</html>
