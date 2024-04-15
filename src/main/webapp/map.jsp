<div id="map"></div>
<script>
	var map = L.map('map').setView([ 51.505, -0.09 ], 13);

	L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
		maxZoom : 19,
		attribution : '© OpenStreetMap contributors'
	}).addTo(map);

	var marker = L.marker([ 51.5, -0.09 ]).addTo(map);

	var circle = L.circle([ 51.508, -0.11 ], {
		color : 'red',
		fillColor : '#f03',
		fillOpacity : 0.5,
		radius : 500
	}).addTo(map);

	marker.bindPopup("<b>Hello world!</b><br>I am a popup.").openPopup();
	circle.bindPopup("I am a circle.");
</script>