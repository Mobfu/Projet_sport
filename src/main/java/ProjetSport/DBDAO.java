package ProjetSport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Module.News;
import Module.User;

public class DBDAO {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			// TODO Auto-generated catch block
			throw new IllegalStateException("Can't find driver in the classpath");
		}
	}
	private String url = "jdbc:mysql://localhost:3306/ps8_bdd";
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

	public Boolean insertUser(String name, String email, String password, int role) {
	    if (dbConnect()) {
	        try {
	            String checkQuery = "SELECT count(*) FROM user WHERE email = ?";
	            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
	                checkStmt.setString(1, email);
	                ResultSet rs = checkStmt.executeQuery();
	                if (rs.next() && rs.getInt(1) > 0) {
	                    return false;
	                }
	            }

	            String query = "INSERT INTO user (username, email, password, create_time, userrole) VALUES (?, ?, SHA2(?,256), ?,?)";
	            try (PreparedStatement ps = conn.prepareStatement(query)) {
	                ps.setString(1, name);
	                ps.setString(2, email);
	                ps.setString(3, password);
	                ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
	                ps.setInt(5, role);

	                int result = ps.executeUpdate();
	                return result > 0;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            dbClose();
	        }
	    }
	    return false;
	}


	public Boolean addUser(String name, String email, String password) {
		if (dbConnect()) {
			String query = "INSERT INTO `ps8_bdd`.`user` (`username`, `email`, `password`, `create_time`, `userrole`) VALUES (?, ?, SHA2(?,256), ?, ?)";
			try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, password);
				ps.setTimestamp(4, new Timestamp(System.currentTimeMillis())); // Utilise le temps actuel comme heure de
																				// création
				ps.setInt(5, 4000); // Définit le rôle par défaut à 4000

				int rowsAffected = ps.executeUpdate();
				if (rowsAffected > 0) {
					ResultSet generatedKeys = ps.getGeneratedKeys();
					if (generatedKeys.next()) {
						int generatedId = generatedKeys.getInt(1);
						System.out.println("ID de l'utilisateur inséré : " + generatedId);
						return true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
		}
		return false;
	}

	public boolean checkUser(String email, String password, int role) {
		String query = String.format("SELECT * FROM ps8_bdd.user WHERE email=? AND password=SHA2(?,256) AND userrole=?");
		try {
			if (dbConnect()) {
				try (PreparedStatement ps = conn.prepareStatement(query)) {
					ps.setString(1, email);
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
	
	public int getIdByEmail(String email) {
		int id = -1;
		if(dbConnect()) {
			String query = "SELECT iduser FROM ps8_bdd.user WHERE email = ?";
			try(PreparedStatement ps = conn.prepareStatement(query)){
				ps.setString(1, email);
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					id = rs.getInt("iduser");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				dbClose();
			}
		}
		return id;
	}
	
	public String getNameByEmail(String email) {
		String username = null;
		if(dbConnect()) {
			String query = "SELECT username FROM ps8_bdd.user WHERE email = ?";
			try(PreparedStatement ps = conn.prepareStatement(query)){
				ps.setString(1, email);
				
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					username = rs.getString("username");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				dbClose();
			}
		}
		return username;
	}
	

	public boolean saveTempLogin(LocalDateTime now, int role) {
		String query = String.format("INSERT INTO timeoflog(idtimeofLog, RoleUser) VALUES (?,?)");

		try {
			if (dbConnect()) {
				try (PreparedStatement ps = conn.prepareStatement(query)) {
					ps.setObject(1, now);
					ps.setInt(2, role);

					int rs = ps.executeUpdate();
					return rs > 0;
				} finally {
					dbClose();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	// avoir les donnee de table templogin
	public DefaultTableModel getTempLoginTableModel() {
		if (!dbConnect()) {
			return null;
		}

		DefaultTableModel tableModel = null;
		String query = "SELECT * FROM timeoflog";

		try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			// avoir tout les noms de colum
			Vector<String> columnNames = new Vector<>();
			for (int column = 1; column <= columnCount; column++) {
				columnNames.add(metaData.getColumnName(column));
			}

			// aovir les donnes
			Vector<Vector<Object>> data = new Vector<>();
			while (rs.next()) {
				Vector<Object> vector = new Vector<>();
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					vector.add(rs.getObject(columnIndex));
				}
				data.add(vector);
			}

			// cree new table
			tableModel = new DefaultTableModel(data, columnNames);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return tableModel;
	}

	// filtre sur role dans historique login
	public DefaultTableModel getTempLoginByRole(int filterRole) {
		if (!dbConnect()) {
			return null;
		}

		DefaultTableModel tableModel = null;
		String query = "SELECT * FROM timeoflog WHERE RoleUser = ?"; // 使用参数化查询

		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, filterRole); // 设置查询参数

			try (ResultSet rs = ps.executeQuery()) {
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();

				Vector<String> columnNames = new Vector<>();
				for (int column = 1; column <= columnCount; column++) {
					columnNames.add(metaData.getColumnName(column));
				}

				Vector<Vector<Object>> data = new Vector<>();
				while (rs.next()) {
					Vector<Object> vector = new Vector<>();
					for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
						vector.add(rs.getObject(columnIndex));
					}
					data.add(vector);
				}

				tableModel = new DefaultTableModel(data, columnNames);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return tableModel;
	}

	public void deletePasswords() {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = DriverManager.getConnection(url, username, password);

			String query = "UPDATE projet_sport.user SET password = NULL";
			statement = connection.prepareStatement(query);

			int rowsAffected = statement.executeUpdate();
			System.out.println("Nombre de mots de passe supprimés : " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void deleteUserByUsername(String usernameToDelete) {
		try (Connection connection = DriverManager.getConnection(url, username, password);
				PreparedStatement statement = connection
						.prepareStatement("DELETE FROM `ps8_bdd`.`user` WHERE username = ?")) {

			statement.setString(1, usernameToDelete);

			int rowsAffected = statement.executeUpdate();
			System.out.println("Nombre d'utilisateurs supprimés : " + rowsAffected);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteUserPassword(String usernameToDelete) {
		if (dbConnect()) {
			String query = "UPDATE `ps8_bdd`.`user` SET `password` = NULL WHERE `username` = ?";
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setString(1, usernameToDelete);

				int rowsAffected = ps.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println("Nombre de mots de passe supprimés : " + rowsAffected);
				} else {
					System.out.println("Aucun mot de passe trouvé pour l'utilisateur : " + usernameToDelete);
				}
			} catch (SQLException e) {
				System.err.println("Erreur lors de la suppression du mot de passe : " + e.getMessage());
			} finally {
				dbClose();
			}
		}
	}

	public boolean updateRoleUser(String username, int role) {
		if (dbConnect()) {
			String query = "UPDATE `ps8_bdd`.`user` SET `userrole` = ? WHERE `username` = ?";
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setInt(1, role);
				ps.setString(2, username);

				int rowsAffected = ps.executeUpdate();
				return rowsAffected > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
		}
		return false;
	}

	public Boolean insertNews(int userid, String username, String news, String horaire) {
		if (dbConnect()) {
			String query = String.format("INSERT INTO news (userid, username, news, horaire) VALUES (?,?,?,?)");
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setInt(1, userid);
				ps.setString(2, username);
				ps.setString(3, news);
				ps.setString(4, horaire);

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

	public List<News> getNews() {
		List<News> newsList = new ArrayList<>();
		if (dbConnect()) {
			String query = "SELECT id, userid, username, news, horaire FROM news";
			try (PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					int userid = rs.getInt("userid");
					String username = rs.getString("username");
					String news = rs.getString("news");
					String horaire = rs.getString("horaire");
					newsList.add(new News(id, userid, username, news, horaire));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
		}
		return newsList;
	}

	public Boolean modifNews(int id, String news, String horaire) {
		if (dbConnect()) {
			String query = "UPDATE news SET news = ?, horaire = ? WHERE id = ?";
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setString(1, news);
				ps.setString(2, horaire);
				ps.setInt(3, id);

				int rs = ps.executeUpdate();
				System.out.println("Modification reussi!");
				return rs > 0;
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("failed to modification!");
			} finally {
				dbClose();
			}
		}
		return false;
	}

	public boolean SuprimerNews(int idnews) {
		if (dbConnect()) {
			String query = "DELETE FROM ps8_bdd.news WHERE id = ? ";
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setInt(1, idnews);

				int rs = ps.executeUpdate();
				System.out.println("suprime reussi!");
				return rs > 0;
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("failed to supression!");
			} finally {
				dbClose();
			}
		}
		return false;
	}
	
}