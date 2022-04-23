package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import java.sql.*;
import java.util.Properties;

public final class DatabaseOperation {
    private static Connection connection;

    private DatabaseOperation() {
    }

    public static void connectDB() throws SQLException, ClassNotFoundException {
        // We register the Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // We identify the driver, the rdbms, the host, the port, and the schema name
        final String URL = "jdbc:mysql://localhost:3306/comp2522";

        // We need to send a user and a password when we try to connect!
        final Properties connectionProperties = new Properties();
        connectionProperties.put("user", "comp2522");
        connectionProperties.put("password", "I was born in 1973");

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
            System.out.println("Table already exists.");
        }
    }

    public static String readData(String userName) {
        String sqlQuery = String.join("", "SELECT * FROM StarWarGameState WHERE name = \"", userName, "\"");
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (rs.next()) {
                return rs.getString("hp");
            } else {
                return "User not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error reading database.";
        }
    }

    public static void overwriteData(String userName) {
        String sqlQuery = String.join("", "UPDATE StarWarGameState SET hp = 100 WHERE name = \"", userName, "\"");
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newData(String userName) {
        String sqlQuery = String.join("", "SELECT * FROM StarWarGameState WHERE name = \"", userName, "\"");
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if (!rs.next()) {
                newUserData(userName);
            } else {
                overwriteData(userName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newUserData(String userName) {
        String sqlQuery = String.join("", "INSERT INTO StarWarGameState VALUES (\"", userName, "\",100)");
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateData(String userName, Integer hp) {
        String sqlQuery = String.join("", "UPDATE StarWarGameState SET hp = ", hp.toString(), " WHERE name = \"", userName, "\"");
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}