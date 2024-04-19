package Module;

public class Utilisateur {

	private String username, email;
	int Iduser, userrole;
	
	public Utilisateur(int Iduser, String username, String email, int userrole) {
		this.Iduser=Iduser;
		this.username=username;
		this.email=email;
		this.userrole=userrole;
	}

	public int getIduser() {
		return Iduser;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getUserrole() {
		return userrole;
	}
	
	public void setIduser(int Iduser) {
		this.Iduser=Iduser;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public void setUserrole(int userrole) {
		this.userrole=userrole;
	}
}

