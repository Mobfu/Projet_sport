package Module;

import java.sql.Timestamp;

public class Comment {
	private int id;
	private int newsId;
	private String username;
	private String text;
	private Timestamp timestamp;

	public Comment(int id, int newsId, String username, String text, Timestamp timestamp) {
	        this.id = id;
	        this.newsId = newsId;
	        this.username = username;
	        this.text = text;
	        this.timestamp = timestamp;
	    }

	public int getId() {
		return id;
	}

	public int getNewsId() {
		return newsId;
	}

	public String getUsername() {
		return username;
	}

	public String getText() {
		return text;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
