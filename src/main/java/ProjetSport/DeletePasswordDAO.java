package ProjetSport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePasswordDAO {
    private String url = "jdbc:mysql://localhost:3306/projet_sport";
    private String username = "root";
    private String password = "root";

    public void deletePasswords() {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(url, username, password);

            String query = "UPDATE projet_sport.user SET password = NULL";
            statement = connection.prepareStatement(query);

            int rowsAffected = statement.executeUpdate();
            System.out.println("Nombre de mots de passe supprim¨¦s : " + rowsAffected);
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
}
