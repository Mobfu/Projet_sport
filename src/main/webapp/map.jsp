<div id="map" class="map"></div>
	<script>
		window.onload = function() {
			var map = L.map('map').setView([ 49.8654, 0.0851 ], 7);
			L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
				maxZoom : 19,
				attribution : '© OpenStreetMap contributors'
			}).addTo(map);

		};
	</script>