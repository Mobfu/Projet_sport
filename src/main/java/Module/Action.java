package Module;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
public class Action {
	private int idAction;
	private String type_action, nom_utilisateur, adresse_ip;
	private Timestamp temps;
	
	public Action (int IdAction, String type_action, String nom_utilisateur, Timestamp temps, String adresse_ip) {
		this.idAction=IdAction;
		this.type_action=type_action;
		this.nom_utilisateur=nom_utilisateur;
		this.temps=temps;
		this.adresse_ip=adresse_ip;
	}
	public int getIdAction() {
		return idAction;
	}
	
	public String getType_action() {
		return type_action;
	}
	
	public String getNom_utilisateur() {
		return nom_utilisateur;
	}
	
	public Timestamp getTemps() {
		return temps;
	}
	
	public String getAdresse_ip() {
		return adresse_ip;
	}
	
	public void setIdAction(int idAction) {
		this.idAction=idAction;
	}
	
	public void setType_action(String type_action) {
		this.type_action=type_action;
	}
	
	public void setNom_utilisateur(String nom_utilisateur) {
		this.nom_utilisateur=nom_utilisateur;
	}
	
	public void setTemps(Timestamp temps) {
		this.temps=temps;
	}
	
	public void setAdresse_ip (String adresse_ip) {
		this.adresse_ip=adresse_ip;
	}
}
