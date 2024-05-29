package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class FederationDao extends ConnectionDAO{
	
	public ArrayList<String> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();

		// connexion a la base de donnees
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
 
            String url = "jdbc:mysql://localhost:3306/ps8_bdd?useSSL=false";
            Connection conn = DriverManager.getConnection(url, "root", "root");
            System.out.println("Connexion réussie");
 
            Statement statement = conn.createStatement();
            rs = statement.executeQuery("SELECT nom_federation FROM ps8_bdd.club");
            System.out.println("Requête exécutée");
 
            while (rs.next()) {
                String federation = rs.getString("nom_federation");
                
                boolean isPresent = false;
                for (String item : returnValue) {
                    if (item.equalsIgnoreCase(federation)) {
                        isPresent = true;
                        break;
                    }
                }

                if (isPresent) {
                    
                } else {
                    
                    returnValue.add(federation); 
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
		FederationDao fede = new FederationDao();
		ArrayList<String> listfed = fede.getList();
		listfed.forEach(fefe -> System.out.println(fefe));
	}

}
