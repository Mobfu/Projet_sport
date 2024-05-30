package Module;

 

public class Coordonnee {
	
	private String insee_code;
	private String zip_code;
	private Float latitude;
	private Float longitude;
	
	public Coordonnee(String insee_code, String zip_code, Float latitude, Float longitude) {
		
		this.insee_code = insee_code;
		this.zip_code = zip_code;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getInsee_code() {
		return insee_code;
	}

	public String getZip_code() {
		return zip_code;
	}

	public Float getLatitude() {
		return latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setInsee_code(String insee_code) {
		this.insee_code = insee_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	
	
	 

}
