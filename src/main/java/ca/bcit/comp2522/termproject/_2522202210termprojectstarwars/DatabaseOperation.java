package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import java.sql.*;
import java.util.Properties;

public final class DatabaseOperation {
    private static Connection connection;

    private DatabaseOperation() {
    }

    public static void readData() throws SQLException, ClassNotFoundException {
        // We register the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // We identify the driver, the rdbms, the host, the port, and the schema name
        final String URL = "jdbc:mysql://localhost:3306/comp2522";

        // We need to send a user and a password when we try to connect!
        final Properties connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "12345678");

        // We establish a connection...
        connection = DriverManager.getConnection(URL, connectionProperties);
        if (connection != null) {
            System.out.println("Successfully connected to MySQL database.");
        }

        // Create a statement to send on the connection...
        Statement stmt = connection.createStatement();

        // Execute the statement...
        try {
            String sql = "CREATE TABLE StarWarGameState" +
                    "(name VARCHAR(20) not NULL, " +
                    "hp INTEGER, " +
                    "PRIMARY KEY ( name ))";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Table exists. Loading data.");
        }

            ResultSet rs = stmt.executeQuery("SELECT * FROM StarWarGameState WHERE name = \"Chris\"");
            if(!rs.next()){
                System.out.println("Data not exist. Creating new data.");
                stmt.executeUpdate("INSERT INTO StarWarGameState VALUES ('Chris',100)");
            }
            else{
                System.out.println("Data read.");
            }
    }

    public static void writeData() {
        System.out.println("Saved data.");
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE StarWarGameState SET hp = 200 WHERE name = \"Chris\"");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error saving game data.");
        }
    }
}