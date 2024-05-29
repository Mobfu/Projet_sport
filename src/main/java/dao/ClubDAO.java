package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Module.Club;
import Module.Coordonnee;



import java.util.logging.Level;
import java.util.logging.Logger;



public class ClubDAO extends ConnectionDAO{
	
	public ArrayList<Club> getAllClub(int limit, int offset) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Club> returnValue = new ArrayList<Club>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.club LIMIT ? OFFSET ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, limit);
	        ps.setInt(2, offset);
            	
            rs = ps.executeQuery();

            while (rs.next()) {
               
                
                int Idclub=rs.getInt("Idclub");
                String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String region=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
            }
        } catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public ArrayList<Club> getAllClub() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Club> returnValue = new ArrayList<Club>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.club";
            ps = con.prepareStatement(query);
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	 int Idclub=rs.getInt("Idclub");
            	 String code_commune=rs.getString("code_commune");
 				String nom_commune=rs.getString("nom_commune");
 				String code_qpv=rs.getString("code_qpv");
 				String nom_qpv=rs.getString("nom_qpv");
 				String deprtement=rs.getString("deprtement");
 				String region=rs.getString("region");
 				String statut_geo=rs.getString("statut_geo");
 				String code_fede=rs.getString("code_fede");
 				String nom_federation=rs.getString("nom_federation");
 				int nbr_clubs=rs.getInt("nbr_clubs");
 				int nbr_epa=rs.getInt("nbr_epa");
 				int total_epa_clubs=rs.getInt("total_epa_clubs");
 				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                 returnValue.add(club);
                
            }
        } catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public ArrayList<Club> getListByCommune(String federation, String commune, int limit, int offset) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Club> returnValue = new ArrayList<Club>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.club WHERE nom_federation = ? AND nom_commune = ? LIMIT ? OFFSET ?";
            ps = con.prepareStatement(query);
          
            	ps.setString(1, federation);
                ps.setString(2, commune);
                ps.setInt(3, limit);
    	        ps.setInt(4, offset);
          
            rs = ps.executeQuery();

            while (rs.next()) {
            	 int Idclub=rs.getInt("Idclub");
            	 String code_commune=rs.getString("code_commune");
 				String nom_commune=rs.getString("nom_commune");
 				String code_qpv=rs.getString("code_qpv");
 				String nom_qpv=rs.getString("nom_qpv");
 				String deprtement=rs.getString("deprtement");
 				String region=rs.getString("region");
 				String statut_geo=rs.getString("statut_geo");
 				String code_fede=rs.getString("code_fede");
 				String nom_federation=rs.getString("nom_federation");
 				int nbr_clubs=rs.getInt("nbr_clubs");
 				int nbr_epa=rs.getInt("nbr_epa");
 				int total_epa_clubs=rs.getInt("total_epa_clubs");
 				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                 returnValue.add(club);
                
            }
        } catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	public ArrayList<Club> getListByCommune(String federation, String commune) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Club> returnValue = new ArrayList<Club>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.club WHERE nom_federation = ? AND nom_commune = ? ";
            ps = con.prepareStatement(query);
          
            	ps.setString(1, federation);
                ps.setString(2, commune);
                
            rs = ps.executeQuery();

            while (rs.next()) {
            	 int Idclub=rs.getInt("Idclub");
            	 String code_commune=rs.getString("code_commune");
 				String nom_commune=rs.getString("nom_commune");
 				String code_qpv=rs.getString("code_qpv");
 				String nom_qpv=rs.getString("nom_qpv");
 				String deprtement=rs.getString("deprtement");
 				String region=rs.getString("region");
 				String statut_geo=rs.getString("statut_geo");
 				String code_fede=rs.getString("code_fede");
 				String nom_federation=rs.getString("nom_federation");
 				int nbr_clubs=rs.getInt("nbr_clubs");
 				int nbr_epa=rs.getInt("nbr_epa");
 				int total_epa_clubs=rs.getInt("total_epa_clubs");
 				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                 returnValue.add(club);
                
            }
        } catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
		
	public ArrayList<Club> getListByCode(String federation, String codepostal, int limit, int offset) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Club> returnValue = new ArrayList<Club>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.club WHERE code_commune IN (SELECT Insee_code FROM ps8_bdd.coordonnee WHERE Zip_code = ?) AND nom_federation = ? LIMIT ? OFFSET ?;";
            ps = con.prepareStatement(query);
            ps.setString(1, codepostal);
            ps.setString(2, federation);
            ps.setInt(3, limit);
	        ps.setInt(4, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
            	int Idclub=rs.getInt("Idclub");
           	 String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String region=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
               }
        } catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public ArrayList<Club> getAllClubByCode(String codepostal, int limit, int offset) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Club> returnValue = new ArrayList<Club>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.club WHERE code_commune IN (SELECT Insee_code FROM ps8_bdd.coordonnee WHERE Zip_code = ?) LIMIT ? OFFSET ?;";
            ps = con.prepareStatement(query);
            ps.setString(1, codepostal);
            ps.setInt(2, limit);
	        ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
            	int Idclub=rs.getInt("Idclub");
           	 String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String region=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
                  
            }
        } catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public ArrayList<Club> getClubByFedeRadius(double lon, double lat, int radius, String federation) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Club> returnValue = new ArrayList<Club>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.club WHERE code_commune IN (SELECT Insee_code FROM ps8_bdd.coordonnee WHERE 6371.0 * 2 * ASIN(SQRT(POWER(SIN(RADIANS(? - latitude) / 2), 2) + COS(RADIANS(latitude)) * COS(RADIANS(?)) * POWER(SIN(RADIANS(? - longitude) / 2), 2))) < ?) AND nom_federation = ?;";
            
            ps = con.prepareStatement(query);
            ps.setDouble(1, lat);
            ps.setDouble(2, lat);
            ps.setDouble(3,lon);
            ps.setInt(4,radius);
            ps.setString(5, federation);
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	int Idclub=rs.getInt("Idclub");
           	 String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String region=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
                 
            }
        } catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public ArrayList<Club> getAllClubByRadius(double lon, double lat, int radius) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Club> returnValue = new ArrayList<Club>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.club WHERE code_commune IN (SELECT Insee_code FROM ps8_bdd.coordonnee WHERE 6371.0 * 2 * ASIN(SQRT(POWER(SIN(RADIANS(? - latitude) / 2), 2) + COS(RADIANS(latitude)) * COS(RADIANS(?)) * POWER(SIN(RADIANS(? - longitude) / 2), 2))) < ?);";
            
            ps = con.prepareStatement(query);
            ps.setDouble(1, lat);
            ps.setDouble(2, lat);
            ps.setDouble(3,lon);
            ps.setInt(4,radius);
            
            
            rs = ps.executeQuery();

            while (rs.next()) {
            	int Idclub=rs.getInt("Idclub");
           	 String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String region=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
                 
            }
        } catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	
	public ArrayList<Club> getAllClubByCode(String codepostal) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Club> returnValue = new ArrayList<Club>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.club WHERE code_commune IN (SELECT Insee_code FROM ps8_bdd.coordonnee WHERE Zip_code = ?);";
            ps = con.prepareStatement(query);
            ps.setString(1, codepostal);
            

            rs = ps.executeQuery();

            while (rs.next()) {
            	int Idclub=rs.getInt("Idclub");
           	 String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String region=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
               }
        } catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public ArrayList<Club> getAllClubByFederation(String federation, int limit, int offset) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Club> returnValue = new ArrayList<Club>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.club WHERE nom_federation = ? LIMIT ? OFFSET ?";
            ps = con.prepareStatement(query);
            ps.setString(1, federation);
            ps.setInt(2, limit);
            ps.setInt(3, offset);

            rs = ps.executeQuery();

            while (rs.next()) {
            	int Idclub=rs.getInt("Idclub");
           	 String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String region=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
               }
        } catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public ArrayList<Club> getAllClubByFederation(String federation) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Club> returnValue = new ArrayList<Club>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.club WHERE nom_federation = ? ";
            ps = con.prepareStatement(query);
            ps.setString(1, federation);
            

            rs = ps.executeQuery();

            while (rs.next()) {
            	int Idclub=rs.getInt("Idclub");
           	 String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String region=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
               }
        } catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}


	
	public ArrayList<Club> getListByCode(String federation, String codepostal) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Club> returnValue = new ArrayList<Club>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.club WHERE code_commune IN (SELECT Insee_code FROM ps8_bdd.coordonnee WHERE Zip_code = ?) AND nom_federation = ? ;";
            ps = con.prepareStatement(query);
            ps.setString(1, codepostal);
            ps.setString(2, federation);
           

            rs = ps.executeQuery();

            while (rs.next()) {
            	int Idclub=rs.getInt("Idclub");
           	 String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String region=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
               }
        } catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public ArrayList<Club> getAllClubByCommune(String commune) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Club> returnValue = new ArrayList<Club>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT * FROM ps8_bdd.club WHERE nom_commune = ?";
	        ps = con.prepareStatement(query);
	        ps.setString(1, commune);
	       

	        rs = ps.executeQuery();

	        while (rs.next()) {
	        	int Idclub=rs.getInt("Idclub");
           	 String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String region=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
               }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return returnValue;
	}

	
	public ArrayList<Club> getListByRegion(String federation, String region, int limit, int offset) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Club> returnValue = new ArrayList<Club>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT * FROM ps8_bdd.club WHERE nom_federation = ? AND region = ? LIMIT ? OFFSET ?";
	        ps = con.prepareStatement(query);
	        ps.setString(1, federation);
	        ps.setString(2, region);
	        ps.setInt(3, limit);
	        ps.setInt(4, offset);

	        rs = ps.executeQuery();

	        while (rs.next()) {
	        	int Idclub=rs.getInt("Idclub");
           	 String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String regionn=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, regionn, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
                
	        }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return returnValue;
	}
	
	public ArrayList<Club> getAllClubByRegion(String region) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Club> returnValue = new ArrayList<Club>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT * FROM ps8_bdd.club WHERE region = ?";
	        ps = con.prepareStatement(query);
	        ps.setString(1, region);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	        	int Idclub=rs.getInt("Idclub");
           	 String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String regionn=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, regionn, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
                 
	        }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return returnValue;
	}
	
	public ArrayList<Club> getAllClubByRegion(String region, int limit, int offset) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Club> returnValue = new ArrayList<Club>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT * FROM ps8_bdd.club WHERE region = ? LIMIT ? OFFSET ?";
	        ps = con.prepareStatement(query);
	        ps.setString(1, region);
	        ps.setInt(2, limit);
	        ps.setInt(3, offset);

	        rs = ps.executeQuery();

	        while (rs.next()) {
	        	int Idclub=rs.getInt("Idclub");
           	 String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String regionn=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, regionn, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
               }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return returnValue;
	}
	
	public ArrayList<Club> getAllClubByCommune(String commune, int limit, int offset) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Club> returnValue = new ArrayList<Club>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT * FROM ps8_bdd.club WHERE nom_commune = ? LIMIT ? OFFSET ?";
	        ps = con.prepareStatement(query);
	        ps.setString(1, commune);
	        ps.setInt(2, limit);
	        ps.setInt(3, offset);

	        rs = ps.executeQuery();

	        while (rs.next()) {
	        	int Idclub=rs.getInt("Idclub");
           	 String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String region=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, region, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
                 
	        }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return returnValue;
	}
	
	public ArrayList<Club> getListByRegion(String federation, String region) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Club> returnValue = new ArrayList<Club>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT * FROM ps8_bdd.club WHERE nom_federation = ? AND region = ? ";
	        ps = con.prepareStatement(query);
	        ps.setString(1, federation);
	        ps.setString(2, region);
	        
	        rs = ps.executeQuery();

	        while (rs.next()) {
	        	int Idclub=rs.getInt("Idclub");
           	 String code_commune=rs.getString("code_commune");
				String nom_commune=rs.getString("nom_commune");
				String code_qpv=rs.getString("code_qpv");
				String nom_qpv=rs.getString("nom_qpv");
				String deprtement=rs.getString("deprtement");
				String regionn=rs.getString("region");
				String statut_geo=rs.getString("statut_geo");
				String code_fede=rs.getString("code_fede");
				String nom_federation=rs.getString("nom_federation");
				int nbr_clubs=rs.getInt("nbr_clubs");
				int nbr_epa=rs.getInt("nbr_epa");
				int total_epa_clubs=rs.getInt("total_epa_clubs");
				Club club = new Club(Idclub, code_commune, nom_commune, code_qpv, nom_qpv, deprtement, regionn, statut_geo, code_fede, nom_federation, nbr_clubs, nbr_epa, total_epa_clubs);
                returnValue.add(club);
               }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return returnValue;
	}
	
	public ArrayList<Coordonnee> getCoorsByRegion(String federation, String region) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Coordonnee> returnValue = new ArrayList<Coordonnee>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT * FROM ps8_bdd.coordonnee WHERE Insee_code IN (SELECT code_commune FROM ps8_bdd.club WHERE nom_federation = ? AND region = ? )";
	        ps = con.prepareStatement(query);
	        ps.setString(1, federation);
	        ps.setString(2, region);
	        
	        rs = ps.executeQuery();

	        while (rs.next()) {
	        	 String ic = rs.getString("Insee_code");
	                String zc = rs.getString("Zip_code");
	                float ltd = rs.getFloat("Latitude");
	                float lng = rs.getFloat("Longitude");
	                
	                Coordonnee coor = new Coordonnee(ic,zc,ltd,lng);
	                returnValue.add(coor);
	        }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return returnValue;
	}
	
	public ArrayList<Coordonnee> getAllCoorsByRegion(String region) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Coordonnee> returnValue = new ArrayList<Coordonnee>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT * FROM ps8_bdd.coordonnee WHERE Insee_code IN (SELECT code_commune FROM ps8_bdd.club WHERE  region = ? )";
	        ps = con.prepareStatement(query);
	        
	        ps.setString(1, region);
	        
	        rs = ps.executeQuery();

	        while (rs.next()) {
	        	 String ic = rs.getString("Insee_code");
	                String zc = rs.getString("Zip_code");
	                float ltd = rs.getFloat("Latitude");
	                float lng = rs.getFloat("Longitude");
	                
	                Coordonnee coor = new Coordonnee(ic,zc,ltd,lng);
	                returnValue.add(coor);
	        }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return returnValue;
	}
	
	public ArrayList<Coordonnee> getAllCoorsByCode(String code) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Coordonnee> returnValue = new ArrayList<Coordonnee>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT * FROM ps8_bdd.coordonnee WHERE Zip_code = ? ";
	        ps = con.prepareStatement(query);
	        
	        ps.setString(1, code);
	        
	        rs = ps.executeQuery();

	        while (rs.next()) {
	        	 String ic = rs.getString("Insee_code");
	                String zc = rs.getString("Zip_code");
	                float ltd = rs.getFloat("Latitude");
	                float lng = rs.getFloat("Longitude");
	                
	                Coordonnee coor = new Coordonnee(ic,zc,ltd,lng);
	                returnValue.add(coor);
	        }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return returnValue;
	}
	
	public ArrayList<Coordonnee> getAllCoorsByCommune(String commune) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Coordonnee> returnValue = new ArrayList<Coordonnee>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT * FROM ps8_bdd.coordonnee WHERE Insee_code IN (SELECT code_commune FROM ps8_bdd.club WHERE  nom_commune = ? )";
	        ps = con.prepareStatement(query);
	        
	        ps.setString(1, commune);
	        
	        rs = ps.executeQuery();

	        while (rs.next()) {
	        	 String ic = rs.getString("Insee_code");
	                String zc = rs.getString("Zip_code");
	                float ltd = rs.getFloat("Latitude");
	                float lng = rs.getFloat("Longitude");
	                
	                Coordonnee coor = new Coordonnee(ic,zc,ltd,lng);
	                returnValue.add(coor);
	        }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return returnValue;
	}
	
	public ArrayList<Coordonnee> getCoorsByCode(String federation, String codep) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Coordonnee> returnValue = new ArrayList<Coordonnee>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT * FROM ps8_bdd.coordonnee WHERE Insee_code IN (SELECT code_commune FROM ps8_bdd.club WHERE nom_federation = ? ) AND Zip_code = ? ";
	        ps = con.prepareStatement(query);
	        ps.setString(1, federation);
	        ps.setString(2, codep);
	        
	        rs = ps.executeQuery();

	        while (rs.next()) {
	        	 String ic = rs.getString("Insee_code");
	                String zc = rs.getString("Zip_code");
	                float ltd = rs.getFloat("Latitude");
	                float lng = rs.getFloat("Longitude");
	                
	                Coordonnee coor = new Coordonnee(ic,zc,ltd,lng);
	                returnValue.add(coor);
	        }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return returnValue;
	}
	
	public ArrayList<Coordonnee> getCoorsByCommune(String federation, String commu) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ArrayList<Coordonnee> returnValue = new ArrayList<Coordonnee>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT * FROM ps8_bdd.coordonnee WHERE Insee_code IN (SELECT code_commune FROM ps8_bdd.club WHERE nom_federation = ? AND nom_commune = ?)";
	        ps = con.prepareStatement(query);
	        ps.setString(1, federation);
	        ps.setString(2, commu);
	        
	        rs = ps.executeQuery();

	        while (rs.next()) {
	        	 String ic = rs.getString("Insee_code");
	                String zc = rs.getString("Zip_code");
	                float ltd = rs.getFloat("Latitude");
	                float lng = rs.getFloat("Longitude");
	                
	                Coordonnee coor = new Coordonnee(ic,zc,ltd,lng);
	                returnValue.add(coor);
	        }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return returnValue;
	}
	
	public int getCountAllClub() {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int count = 0;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT COUNT(*) FROM ps8_bdd.club";
	        ps = con.prepareStatement(query);

	        rs = ps.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return count;
	}

	
	public int getCountByRegion(String nom_federation, String region) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int count = 0;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT COUNT(*) FROM ps8_bdd.club WHERE nom_federation = ? AND region = ?";
	        ps = con.prepareStatement(query);
	        ps.setString(1, nom_federation);
	        ps.setString(2, region);

	        rs = ps.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return count;
	}
	public int getAllCountByRegion(String region) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int count = 0;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
	        con = DriverManager.getConnection(url, "root", "root");

	        String query = "SELECT COUNT(*) FROM ps8_bdd.club WHERE region = ?";
	        ps = con.prepareStatement(query);
	       
	        ps.setString(1, region);

	        rs = ps.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (Exception ee) {
	        ee.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (Exception ignore) {}
	    }
	    return count;
	}

	
	public ArrayList<Coordonnee> getListLatLngBr(String federation, String region) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Coordonnee> returnValue = new ArrayList<Coordonnee>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.coordonnee WHERE Insee_code  IN (SELECT code_commune FROM ps8_bdd.club WHERE nom_federation = ? AND region = ?);";
            ps = con.prepareStatement(query);
            ps.setString(1, federation);
            ps.setString(2, region);

            rs = ps.executeQuery();

            while (rs.next()) {
               
                String ic = rs.getString("Insee_code");
                String zc = rs.getString("Zip_code");
                float lat = rs.getFloat("Latitude");
                float lng = rs.getFloat("Longitude");
                
                Coordonnee coor = new Coordonnee(ic,zc,lat,lng);
                returnValue.add(coor);
            }
        } catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public ArrayList<Coordonnee> getAllCoor() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Coordonnee> returnValue = new ArrayList<Coordonnee>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.coordonnee;";
            ps = con.prepareStatement(query);
           

            rs = ps.executeQuery();

            while (rs.next()) {
               
                String ic = rs.getString("Insee_code");
                String zc = rs.getString("Zip_code");
                float lat = rs.getFloat("Latitude");
                float lng = rs.getFloat("Longitude");
                
                Coordonnee coor = new Coordonnee(ic,zc,lat,lng);
                returnValue.add(coor);
            }
        } catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	public ArrayList<Coordonnee> getCoorsDistance(double lat, double lon, int radius) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Coordonnee> returnValue = new ArrayList<Coordonnee>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            con = DriverManager.getConnection(url, "root", "root");

            String query = "SELECT * FROM ps8_bdd.coordonnee WHERE 6371.0 * 2 * ASIN(SQRT(POWER(SIN(RADIANS(? - latitude) / 2), 2) + COS(RADIANS(latitude)) * COS(RADIANS(?)) * POWER(SIN(RADIANS(? - longitude) / 2), 2))) < ?;";
            ps = con.prepareStatement(query);
            ps.setDouble(1, lat);
            ps.setDouble(2, lat);
            ps.setDouble(3,lon);
            ps.setInt(4,radius);

            rs = ps.executeQuery();

            while (rs.next()) {
               
                String ic = rs.getString("Insee_code");
                String zc = rs.getString("Zip_code");
                float ltd = rs.getFloat("Latitude");
                float lng = rs.getFloat("Longitude");
                
                Coordonnee coor = new Coordonnee(ic,zc,ltd,lng);
                returnValue.add(coor);
            }
        }
        catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
        return returnValue;
    }
	
	public static void main(String[] args) throws SQLException {
	    ClubDAO fede = new ClubDAO();
	    ArrayList<Coordonnee> listfed = fede.getCoorsByCode("FF de Rugby","76000");
	    listfed.forEach(fefe -> System.out.printf(" %s , %s", fefe.getInsee_code(), fefe.getLatitude()));
	    System.out.println(listfed.size());
	}

}
