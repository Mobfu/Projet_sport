 package Module;

public class News {

	private int id;
	private String username;
	private String news;
	private String horaire;
	private String montants;

	public News(int id, String username, String news, String horaire, String montants) {
		super();
		this.id = id;
		this.username = username;
		this.news = news;
		this.horaire = horaire;
		this.montants = montants;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public String getNews() {
		return news;
	}

	public String getHoraire() {
		return horaire;
	}

	public String getMontants() {
		return montants;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public void setHoraire(String horaire) {
		this.horaire = horaire;
	}

	public void setMontants(String montants) {
		this.montants = montants;
	}
	
	
}