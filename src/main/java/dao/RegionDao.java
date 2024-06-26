package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class RegionDao extends ConnectionDAO{
	
	public ArrayList<String> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver chargé avec succès");
 
            Connection conn = DriverManager.getConnection(url,username, password);
            System.out.println("Connexion réussie");
 
            Statement statement = conn.createStatement();
            rs = statement.executeQuery("SELECT region FROM ps8_bdd.club");
            System.out.println("Requête exécutée");
 
            while (rs.next()) {
                String region = rs.getString("region");
                
                boolean isPresent = false;
                for (String item : returnValue) {
                    if (item.equalsIgnoreCase(region)) {
                        isPresent = true;
                        break;
                    }
                }

                if (isPresent) {
                    
                } else {
                    
                    returnValue.add(region); 
                }
                               
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
	
	public static void main(String[] args) throws SQLException {
		RegionDao fede = new RegionDao();
		ArrayList<String> listfed = fede.getList();
		listfed.forEach(fefe -> System.out.println(fefe));
	}

}
