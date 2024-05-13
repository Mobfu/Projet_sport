package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import Module.Club;
import Module.News;
import Module.Utilisateur;

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
				String query = String.format(
						"INSERT INTO user (username, email, password, create_time, userrole) VALUES (?, ?, SHA2(?,256), ?,?)");
				try (PreparedStatement ps = conn.prepareStatement(query)) {
					ps.setString(1, name);
					ps.setString(2, email);
					ps.setString(3, password);
					ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
					ps.setInt(5, role);
	
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
	
			public boolean checkUserA(String username, String password) {
	    boolean userExists = false;
	    if (dbConnect()) {
	        String query = "SELECT * FROM `ps8_bdd`.`user` WHERE `username` = ? AND `password` = SHA2(?,256) AND `userrole` = 0";
	        try (PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setString(1, username);
	            ps.setString(2, password);
 
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                userExists = true;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            dbClose();
	        }
	    }
	    return userExists;
	}
	public boolean checkUser(String user, String password, int role) {
			String query = String.format("SELECT * FROM user WHERE username=? AND password=SHA2(?,256) AND userrole=?");
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
		public boolean saveTempLogin(LocalDateTime now, int role) {
			String query = String.format("INSERT INTO timeoflog(idtimeofLog, RoleUser) VALUES (?,?)");
	
			try {
				if (dbConnect()) {
					try (PreparedStatement ps = conn.prepareStatement(query)) {
						ps.setObject(1, now);
						ps.setInt(2, role);
	
						int rs = ps.executeUpdate(); // 执行更新
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
		 
		
		public Boolean insertNews(String username, String news, String horaire, String montants) {
			if (dbConnect()) {
				String query = String.format("INSERT INTO news (username, news, horaire, montants) VALUES (?,?,?,?)");
				try (PreparedStatement ps = conn.prepareStatement(query)) {
					ps.setString(1, username);
					ps.setString(2, news);
					ps.setString(3, horaire);
					ps.setString(4, montants);

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
		        String query = "SELECT id, username, news, horaire, montants FROM news";
		        try (PreparedStatement ps = conn.prepareStatement(query);
		             ResultSet rs = ps.executeQuery()) { 
		            while (rs.next()) {
		            	int id = rs.getInt("id");
		                String username = rs.getString("username");
		                String news = rs.getString("news");
		                String horaire = rs.getString("horaire");
		                String montants = rs.getString("montants");
		                newsList.add(new News(id, username, news, horaire, montants));
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } finally {
		            dbClose();
		        }
		    }
		    return newsList;
		}

		public Boolean modifNews(String username, String news, String horaire, String montants) {
		    if (dbConnect()) {
		        String query = "UPDATE news SET news = ?, horaire = ?, montants = ? WHERE username = ?";
		        try (PreparedStatement ps = conn.prepareStatement(query)) {
		            ps.setString(1, news);
		            ps.setString(2, horaire);
		            ps.setString(3, montants);
		            ps.setString(4, username);

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
	
	public List<String> searchClubsByFederationAndLocation(String federationName, String location) {
        List<String> results = new ArrayList<>();
        String query = "SELECT * FROM club WHERE nom_federation = ? AND (deprtement = ? OR region = ? OR nom_commune = ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, federationName);
            statement.setString(2, location);
            statement.setString(3, location);
            statement.setString(4, location);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Construire le résultat à afficher pour chaque ligne de résultat
                String result = "Club trouvé: " + resultSet.getString("nom_commune") + ", " +
                                resultSet.getString("deprtement") + ", " +
                                resultSet.getString("region") + ", " +
                                resultSet.getString("nom_federation");
                results.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

	
	public List <Utilisateur> listeUtilisateurs(){
		List<Utilisateur> maListe = new ArrayList<>();
		if(dbConnect()) {
			try {
				String query="SELECT * FROM USER";
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next()) {
					int Iduser = resultSet.getInt("Iduser");
					String username = resultSet.getString("username");
					String email = resultSet.getString("email");
					int userrole = resultSet.getInt("userrole");
					Utilisateur utilisateur = new Utilisateur (Iduser, username, email, userrole);
					maListe.add(utilisateur);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
		}
		return maListe;
	}
	
	public List <Club> listeClubs(){
		List<Club> maListe = new ArrayList<>();
		if(dbConnect()) {
			try {
				String query="SELECT * FROM CLUB";
				Statement statement = conn.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next()) {					
					int Idclub=resultSet.getInt("Idclub");
					int code_commune=resultSet.getInt("code_commune");
					String nom_commune=resultSet.getString("nom_commune");
					String code_qpv=resultSet.getString("code_qpv");
					String nom_qpv=resultSet.getString("nom_qpv");
					int deprtement=resultSet.getInt("deprtement");
					String region=resultSet.getString("region");
					String statut_geo=resultSet.getString("statut_geo");
					int code_fede=resultSet.getInt("code_fede");
					String nom_federation=resultSet.getString("nom_federation");
					int nbr_clubs=resultSet.getInt("nbr_clubs");
					int nbr_epa=resultSet.getInt("nbr_epa");
					int total_epa_clubs=resultSet.getInt("total_epa_clubs");
					Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
					maListe.add(club);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
		}
		return maListe;
	}
	
	public List <Utilisateur> ResultatRecherche(String role, String username){
		List<Utilisateur> maListe = new ArrayList<>();
		int userrole=0;
		switch(role) {
		case "Administrateur":
			userrole = 0;
        case "Sportif":
        	userrole = 1;
        case "Membre Ministère Sport":
        	userrole = 2;
        case "Elu":
        	userrole = 3;
        default:
        	userrole = 0; 
	}
		if(dbConnect()) {
			try {
				String query;
				if (username == null) {
	                query = "SELECT * FROM USER WHERE userrole = ?";
	                PreparedStatement statement = conn.prepareStatement(query);
	                statement.setInt(1, userrole);
	                ResultSet resultSet = statement.executeQuery();
	                while(resultSet.next()) {
	                	int Iduser = resultSet.getInt("Iduser");
	                	String email = resultSet.getString("email");
	                	String username2 = resultSet.getString("username");
	                	Utilisateur utilisateur = new Utilisateur (Iduser, username2, email, userrole);
						maListe.add(utilisateur);
	                }
	            } else {
	                query = "SELECT * FROM USER WHERE userrole = ? AND username = ? ";
	                PreparedStatement statement = conn.prepareStatement(query);
	                statement.setInt(1, userrole);
	                statement.setString(2, username);
	                ResultSet resultSet = statement.executeQuery();
	                while(resultSet.next()) {
	                	int Iduser = resultSet.getInt("Iduser");
	                	String email = resultSet.getString("email");
	                	Utilisateur utilisateur = new Utilisateur (Iduser, username, email, userrole);
						maListe.add(utilisateur);
	                }
	            }
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}
		}
		return maListe;
	}

}
