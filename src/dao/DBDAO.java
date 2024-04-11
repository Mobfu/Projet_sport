//package ProjetSport;
package dao;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class DBDAO {
 
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			throw new IllegalStateException("Can't find driver in the classpath");
		}
	}
	private String url = "jdbc:mysql://localhost:3306/projet_sport";
	private String username = "root";
	private String password = "root";
	Connection conn = null;
	public boolean dbConnect() {
		try {
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("reussi de dbConnect!");
		} catch (SQLException e) {
			System.out.println("fail to connect base de donnee!");
			return false;
		}
		return true;
	}
 
	public void dbClose() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
 
	public Boolean addUser(String name, String password, int role) {
	    String tableName;
	    switch (role) {
	        case 0:
	            tableName = "projet_sport.administrateur";
	            break;
	        case 1:
	            tableName = "projet_sport.elu";
	            break;
	        case 2:
	            tableName = "projet_sport.acteur";
	            break;
	        case 3:
	            tableName = "projet_sport.user";
	            break;
	        default:
	        	System.out.println("Cant add cette role!");
	            return false; 
	    }
	    return insertUser(tableName, name, password, role);
	}
 
	private Boolean insertUser(String tableName, String name, String password, int role) {
	    if (dbConnect()) {
	        String query = String.format("INSERT INTO %s (name, password, role) VALUES (?, SHA2(?,256), ?)", tableName);
	        try (PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setString(1, name);
	            ps.setString(2, password);
	            ps.setInt(3, role);
	            int rs = ps.executeUpdate();
	            return rs > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            dbClose();
	        }
	    }
	    return false;
	}
 
	
	public Boolean verifierUser(String name, String password, int role) {
	    String tableName;
	    switch (role) {
	        case 0:
	            tableName = "projet_sport.administrateur";
	            break;
	        case 1:
	            tableName = "projet_sport.elu";
	            break;
	        case 2:
	            tableName = "projet_sport.acteur";
	            break;
	        case 3:
	            tableName = "projet_sport.user";
	            break;
	        default:
	            System.out.println("Invalid role!");
	            return false;
	    }
	    return validateUser(tableName, name, password, role);
	}
 
	public boolean validateUser(String tableName, String user, String password, int role) {
	    String query = String.format("SELECT * FROM %s WHERE name=? AND password=SHA2(?,256) AND role=?", tableName);
	    try {
	        if (dbConnect()) {
	            try (PreparedStatement ps = conn.prepareStatement(query)) {
	                ps.setString(1, user);
	                ps.setString(2, password);
	                ps.setInt(3, role);
	                try (ResultSet rs = ps.executeQuery()) {
	                    return rs.next();
	                }
	            } finally {
	                dbClose();
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
 
	
}