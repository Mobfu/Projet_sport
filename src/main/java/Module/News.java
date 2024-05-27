 package Module;

public class News {
    private int id;
    private int userid;
    private String username;
    private String news;
    private String horaire;

    public News(int id, int userid, String username, String news, String horaire) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.news = news;
        this.horaire = horaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {  
        return userid;
    }

    public void setUserId(int userId) {  
        this.userid = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }
}