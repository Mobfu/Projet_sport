package Module;

public class User {
	private int Iduser;
	private String username;
	private String email;
	private String passwrod;
	private int userrole;
	private String create_time;
	
	public User(int iduser, String username, String email, String passwrod,String create_time, int userrole) {
		super();
		Iduser = iduser;
		this.username = username;
		this.email = email;
		this.passwrod = passwrod;
		this.userrole = userrole;
		this.create_time = create_time;
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

	public String getPasswrod() {
		return passwrod;
	}

	public int getUserrole() {
		return userrole;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setIduser(int iduser) {
		Iduser = iduser;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}

	public void setUserrole(int userrole) {
		this.userrole = userrole;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	

}
