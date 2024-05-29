package Module;

public class Club {
	private int Idclub,  nbr_clubs, nbr_epa, total_epa_clubs;
	private String nom_commune,code_fede, code_commune, region, statut_geo, nom_federation, code_qpv, nom_qpv, deprtement;
	
	//Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation
	//nbr_clubs, nbr_epa, total_epa_clubs
	
	public Club (int Idclub, String code_commune, String nom_commune, String code_qpv, String nom_qpv, String deprtement, String region, String statut_geo, String code_fede, String nom_federation, int nbr_clubs, int nbr_epa, int total_epa_clubs) {
		this.Idclub=Idclub;
		this.code_commune=code_commune;
		this.nom_commune=nom_commune;
		this.code_qpv=code_qpv;
		this.nom_qpv=nom_qpv;
		this.deprtement=deprtement;
		this.region=region;
		this.statut_geo=statut_geo;
		this.code_fede=code_fede;
		this.nom_federation=nom_federation;
		this.nbr_clubs=nbr_clubs;
		this.nbr_epa=nbr_epa;
		this.total_epa_clubs=total_epa_clubs;
	}
	
	public int getIdclub() {
		return Idclub;
	}
	
	public String getCode_commune() {
		return code_commune;
	}
	
	public String getDeprtement() {
		return deprtement;
	}
	
	public String getCode_fede() {
		return code_fede;
	}
	
	public int getNbr_clubs() {
		return nbr_clubs;
	}
	
	public int getNbr_epa() {
		return nbr_epa;
	}
	
	public int getTotal_epa_clubs() {
		return total_epa_clubs;
	}
	
	public String getNom_commune() {
		return nom_commune;
	}
	
	public String getRegion() {
		return region;
	}
	
	public String getStatut_geo() {
		return statut_geo;
	}
	
	public String getNom_federation() {
		return nom_federation;
	}
	
	public String getCode_qpv() {
		return code_qpv;
	}
	
	public String getNom_qpv() {
		return nom_qpv;
	}
	
	public void setIdclub(int Idclub) {
		this.Idclub=Idclub;
	}
	
	public void setCode_commune(String code_commune) {
		this.code_commune=code_commune;
	}
	
	public void setDeprtement(String deprtement) {
		this.deprtement=deprtement;
	}
	
	public void setCode_fede(String code_fede) {
		this.code_fede=code_fede;
	}
	
	public void setNbr_clubs(int nbr_clubs) {
		this.nbr_clubs=nbr_clubs;
	}
	
	public void setNbr_epa(int nbr_epa) {
		this.nbr_epa=nbr_epa;
	}
	
	public void setTotal_epa_clubs(int total_epa_clubs) {
		this.total_epa_clubs=total_epa_clubs;
	}
	
	public void setNom_commune(String nom_commune) {
		this.nom_commune=nom_commune;
	}
	
	public void setRegion(String region) {
		this.region=region;
	}
	
	public void setStatut_geo(String statut_geo) {
		this.statut_geo=statut_geo;
	}
	
	public void setNom_federation(String nom_federation) {
		this.nom_federation=nom_federation;
	}
	
	public void setCode_qpv(String code_qpv) {
		this.code_qpv=code_qpv;
	}
	
	public void setNom_qpv(String nom_qpv) {
		this.nom_qpv=nom_qpv;
	}
}
