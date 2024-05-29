package dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import Module.Club;
import Module.News;
import Module.Utilisateur;
import Module.Action;

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
	
		public Boolean addUser(String name, String email, String password)  {
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
				} catch (SQLIntegrityConstraintViolationException e) {
					System.err.println("Erreur duplicité du nom d'utilisateur");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
	        String query = "SELECT * FROM `ps8_bdd`.`user` WHERE (`username` = ? OR `email` = ?) AND `password` = SHA2(?,256) AND `userrole` = 0";
	        try (PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setString(1, username);
	            ps.setString(2, username);
	            ps.setString(3, password);
 
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
			 // Méthode pour mettre à jour le mot de passe de l'utilisateur
	    public boolean updatePassword(String username, String newPassword) {
	        // Vérifier si le nouveau mot de passe est vide
	        if (newPassword == null || newPassword.isEmpty()) {
	            System.out.println("Erreur: Le nouveau mot de passe ne peut pas être vide.");
	            return false;
	        }

	        // Vérifier si la connexion à la base de données est établie
	        if (dbConnect()) {
	            String query = "UPDATE `ps8_bdd`.`user` SET `password` = SHA2(?,256) WHERE `username` = ?";
	            try (PreparedStatement ps = conn.prepareStatement(query)) {
	                ps.setString(1, newPassword);
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

	    public boolean checkPassword(String user, String password) {
	        String query = "SELECT password FROM user WHERE username=? AND userrole= 0";
	        try {
	            if (dbConnect()) {
	                try (PreparedStatement ps = conn.prepareStatement(query)) {
	                    ps.setString(1, user);
	                    //ps.setInt(2, role);

	                    try (ResultSet rs = ps.executeQuery()) {
	                        if (rs.next()) {
	                            String currentPassword = rs.getString("password");
	                            // Si le mot de passe stocké est null ou vide, renvoyer true
	                            if (currentPassword == null || currentPassword.isEmpty()) {
	                                return true;
	                            }
	                           
	                        } else {
	                            // Aucun utilisateur trouvé avec le nom d'utilisateur et le rôle donnés
	                            return false;
	                        }
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
					String code_commune=resultSet.getString("code_commune");
					String nom_commune=resultSet.getString("nom_commune");
					String code_qpv=resultSet.getString("code_qpv");
					String nom_qpv=resultSet.getString("nom_qpv");
					String deprtement=resultSet.getString("deprtement");
					String region=resultSet.getString("region");
					String statut_geo=resultSet.getString("statut_geo");
					String code_fede=resultSet.getString("code_fede");
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
		case "Administrateur": userrole = 0;
			break;
        case "Sportif": userrole = 1;
        	break;
        case "Membre Ministère Sport": userrole = 2;
        	break;
        case "Elu": userrole = 3;
        	break;
        case "": userrole=4;
        	break;
		}
		if(dbConnect()) {
			try {
				String query;
				if (username.isEmpty()) {
	                query = "SELECT * FROM USER WHERE userrole = ?";
	                PreparedStatement statement = conn.prepareStatement(query);
	                statement.setInt(1, userrole);
	                ResultSet resultSet = statement.executeQuery();
	                while(resultSet.next()) {
	                	int Iduser = resultSet.getInt("Iduser");
	                	String email = resultSet.getString("email");
	                	username=resultSet.getString("username");
	                	Utilisateur utilisateur = new Utilisateur (Iduser, username, email, userrole);
						maListe.add(utilisateur);
	                }
				}else if(userrole==4) {
					query = "SELECT * FROM USER WHERE username = ?";
					PreparedStatement statement = conn.prepareStatement(query);
	                statement.setString(1, username);
	                ResultSet resultSet = statement.executeQuery();
	                while(resultSet.next()) {
	                	int Iduser = resultSet.getInt("Iduser");
	                	String email = resultSet.getString("email");
	                	String username2 = resultSet.getString("username");
	                	userrole=resultSet.getInt("userrole");
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
	
	public boolean verifNomUnique(String name) throws SQLIntegrityConstraintViolationException {
	    List<Utilisateur> maListe = listeUtilisateurs();
	    for (Utilisateur utilisateur : maListe) {
	        if (name.equals(utilisateur.getUsername())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean verifMailUnique(String mail) {
	    List<Utilisateur> maListe = listeUtilisateurs();
	    for (Utilisateur utilisateur : maListe) {
	        if (mail.equals(utilisateur.getEmail())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public List <Club> ResultatRechercheClub(String lieu, String nom_federation){
		List<Club> maListe = new ArrayList<>();
		if(dbConnect()) {
			try {
				String query;
				if (nom_federation.isEmpty()) {
					query = "SELECT * FROM club WHERE (deprtement = ? OR region = ? OR nom_commune = ?)";
	                PreparedStatement statement = conn.prepareStatement(query);
	                statement.setString(1, lieu);
	                statement.setString(2, lieu);
	                statement.setString(3, lieu);
	                ResultSet resultSet = statement.executeQuery();
	                while(resultSet.next()) {
	                	int Idclub=resultSet.getInt("Idclub");
						String code_commune=resultSet.getString("code_commune");
						String nom_commune=resultSet.getString("nom_commune");
						String code_qpv=resultSet.getString("code_qpv");
						String nom_qpv=resultSet.getString("nom_qpv");
						String deprtement=resultSet.getString("deprtement");
						String region=resultSet.getString("region");
						String statut_geo=resultSet.getString("statut_geo");
						String code_fede=resultSet.getString("code_fede");
						nom_federation=resultSet.getString("nom_federation");
						int nbr_clubs=resultSet.getInt("nbr_clubs");
						int nbr_epa=resultSet.getInt("nbr_epa");
						int total_epa_clubs=resultSet.getInt("total_epa_clubs");
						Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
						maListe.add(club);
	                }
				}else if(lieu.isEmpty()) {
					query = "SELECT * FROM CLUB WHERE nom_federation = ?";
					PreparedStatement statement = conn.prepareStatement(query);
	                statement.setString(1, nom_federation);
	                ResultSet resultSet = statement.executeQuery();
	                while(resultSet.next()) {
	                	int Idclub=resultSet.getInt("Idclub");
						String code_commune=resultSet.getString("code_commune");
						String nom_commune=resultSet.getString("nom_commune");
						String code_qpv=resultSet.getString("code_qpv");
						String nom_qpv=resultSet.getString("nom_qpv");
						String deprtement=resultSet.getString("deprtement");
						String region=resultSet.getString("region");
						String statut_geo=resultSet.getString("statut_geo");
						String code_fede=resultSet.getString("code_fede");
						nom_federation=resultSet.getString("nom_federation");
						int nbr_clubs=resultSet.getInt("nbr_clubs");
						int nbr_epa=resultSet.getInt("nbr_epa");
						int total_epa_clubs=resultSet.getInt("total_epa_clubs");
						Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
						maListe.add(club);
	                }
	            } else {
	            	query = "SELECT * FROM club WHERE nom_federation = ? AND (deprtement = ? OR region = ? OR nom_commune = ?)";
	                PreparedStatement statement = conn.prepareStatement(query);
	                statement.setString(1, nom_federation);
	                statement.setString(2, lieu);
	                statement.setString(3, lieu);
	                statement.setString(4, lieu);
	                ResultSet resultSet = statement.executeQuery();
	                while(resultSet.next()) {
	                	int Idclub=resultSet.getInt("Idclub");
						String code_commune=resultSet.getString("code_commune");
						String nom_commune=resultSet.getString("nom_commune");
						String code_qpv=resultSet.getString("code_qpv");
						String nom_qpv=resultSet.getString("nom_qpv");
						String deprtement=resultSet.getString("deprtement");
						String region=resultSet.getString("region");
						String statut_geo=resultSet.getString("statut_geo");
						String code_fede=resultSet.getString("code_fede");
						nom_federation=resultSet.getString("nom_federation");
						int nbr_clubs=resultSet.getInt("nbr_clubs");
						int nbr_epa=resultSet.getInt("nbr_epa");
						int total_epa_clubs=resultSet.getInt("total_epa_clubs");
						Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
						maListe.add(club);
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
	
	    public int generateRandomNumber() {
	        Random random = new Random();
	        return 1000 + random.nextInt(9000);
	    }
		    
	    public boolean verifyEmailExists(String email) throws SQLException {
	        String query = "SELECT COUNT(*) FROM users WHERE email = ?";
	        Connection connection = null;
			try (PreparedStatement stmt = connection.prepareStatement(query)) {
	            stmt.setString(1, email);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt(1) > 0;
	                }
	            }
	        }
	        return false;
	    }

	    public void sendEmailNotification(String to, String subject, String body) {
	        String from = "fitgroove@outlook.fr"; // Modifier avec votre adresse e-mail
	        String host = "smtp-mail.outlook.com"; // Modifier avec le serveur SMTP de votre fournisseur de messagerie

	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", "587");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.auth", "true");

	        Session session = Session.getInstance(properties, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("fitgroove@outlook.fr", "ProjetS81&23");
	            }
	        });

	        try {
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(from));
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	            message.setSubject(subject);
	            message.setText(body);

	            Transport.send(message);
	            System.out.println("E-mail envoyé avec succès.");
	        } catch (MessagingException mex) {
	            mex.printStackTrace();
	        }
	    }
	    public static String getAdresseIp() {
			try {
				InetAddress ip = InetAddress.getLocalHost();
				return ip.getHostAddress();
			}catch(UnknownHostException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		
		public void AjoutAction(String type_action, String nom_utilisateur) {
			if(dbConnect()) {
				String query="INSERT INTO `ps8_bdd`.`action` (`type_action`,`nom_utilisateur`,`heure`, `adresse_ip`) VALUES (?,?,?,?)";
				try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
					ps.setString(1, type_action);
					ps.setString(2, nom_utilisateur);
					Timestamp heure = new Timestamp(System.currentTimeMillis());
					ps.setTimestamp(3, heure);
					String ip = getAdresseIp();
					ps.setString(4, ip);
					int rowsAffected = ps.executeUpdate();
					if (rowsAffected > 0) {
						ResultSet generatedKeys = ps.getGeneratedKeys();
						if (generatedKeys.next()) {
							int generatedId = generatedKeys.getInt(1);
							System.out.println("ID de l'action insérée : " + generatedId);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					dbClose();
				}
			}
		}
		
		public List <Action> listeActions(){
			List<Action> maListe = new ArrayList<>();
			if(dbConnect()) {
				try {
					String query="SELECT * FROM ACTION";
					Statement statement = conn.createStatement();
					ResultSet resultSet = statement.executeQuery(query);
					while(resultSet.next()) {
						int IdAction = resultSet.getInt("Id");
						String type_action = resultSet.getString("type_action");
						String nom_utilisateur = resultSet.getString("nom_utilisateur");
						Timestamp heure = resultSet.getTimestamp("heure");
						String adresse_ip=resultSet.getString("adresse_ip");
						Action action = new Action (IdAction, type_action, nom_utilisateur, heure, adresse_ip);
						maListe.add(action);
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					dbClose();
				}
			}
			return maListe;
		}
		
		public List <Action> ResultatRechercheAction(String type_action, String nom_utilisateur){
			List<Action> maListe = new ArrayList<>();
			if(dbConnect()) {
				try {
					String query;
					if (type_action.isEmpty()) {
						query = "SELECT * FROM action WHERE nom_utilisateur = ?";
		                PreparedStatement statement = conn.prepareStatement(query);
		                statement.setString(1, nom_utilisateur);
		                ResultSet resultSet = statement.executeQuery();
		                while(resultSet.next()) {
		                	int IdAction = resultSet.getInt("Id");
							type_action = resultSet.getString("type_action");
							Timestamp heure = resultSet.getTimestamp("heure");
							String adresse_ip=resultSet.getString("adresse_ip");
							Action action = new Action (IdAction, type_action, nom_utilisateur, heure, adresse_ip);
							maListe.add(action);
		                }
					}else if(nom_utilisateur.isEmpty()) {
						query = "SELECT * FROM action WHERE type_action = ?";
						PreparedStatement statement = conn.prepareStatement(query);
		                statement.setString(1, type_action);
		                ResultSet resultSet = statement.executeQuery();
		                while(resultSet.next()) {
		                	int IdAction = resultSet.getInt("Id");
							type_action = resultSet.getString("type_action");
							nom_utilisateur = resultSet.getString("nom_utilisateur");
							Timestamp heure = resultSet.getTimestamp("heure");
							String adresse_ip=resultSet.getString("adresse_ip");
							Action action = new Action (IdAction, type_action, nom_utilisateur, heure, adresse_ip);
							maListe.add(action);
		                }
		            } else {
		            	query = "SELECT * FROM action WHERE type_action = ? AND nom_utilisateur = ?";
		                PreparedStatement statement = conn.prepareStatement(query);
		                statement.setString(1, type_action);
		                statement.setString(2, nom_utilisateur);
		                ResultSet resultSet = statement.executeQuery();
		                while(resultSet.next()) {
		                	int IdAction = resultSet.getInt("Id");
							type_action = resultSet.getString("type_action");
							Timestamp heure = resultSet.getTimestamp("heure");
							String adresse_ip=resultSet.getString("adresse_ip");
							Action action = new Action (IdAction, type_action, nom_utilisateur, heure, adresse_ip);
							maListe.add(action);
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
