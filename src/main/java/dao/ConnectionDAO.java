package dao;
public class ConnectionDAO {
	/**
	 * Parametres de connexion a la base de donnees
	 
	 */

	final static String url   = "jdbc:mysql://localhost:3306/ps8_bdd?characterEncoding=UTF-8";
	final static String username = "root";
	final static String password  = "root";
	
	/**
	 * Constructor
	 * 
	 */
	public ConnectionDAO() {
		// chargement du pilote de bases de donnees
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
		}
	}
	
}