package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static properties.DatabaseProperties.DATABASE_PROPERTIES;

public class Jdbc {

    private Connection connection;

    private Connection startConnection() {
        try {
            Class.forName(DATABASE_PROPERTIES.getJdbcDriver());
            connection = DriverManager.getConnection(DATABASE_PROPERTIES.getDbUrl(), DATABASE_PROPERTIES.getDbUser(), DATABASE_PROPERTIES.getDbPass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
        return connection;
    }

    public String getWord() {
        String word = null;
        PreparedStatement preparedStmt = null;
        connection = startConnection();
        try {
            String query = "SELECT word FROM words where id_word = ?";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, (int) (Math.random() * ((10 - 1) + 1)) + 1);
            ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()) {
                word = rs.getString("word");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return word;
    }

    public void insertWinner(String name, String word) {
        try {
            connection = startConnection();

            String query = "CALL insertWinner (?,?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, word);

            preparedStmt.execute();

            preparedStmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

