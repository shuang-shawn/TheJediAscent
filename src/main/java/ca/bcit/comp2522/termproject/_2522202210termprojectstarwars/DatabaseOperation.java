package ca.bcit.comp2522.termproject._2522202210termprojectstarwars;

import java.sql.*;
import java.util.Properties;

public final class DatabaseOperation {

    private DatabaseOperation() { }

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
        final Connection connection = DriverManager.getConnection(URL, connectionProperties);
        if (connection != null) {
            System.out.println("Successfully connected to MySQL database.");
        }

        // Create a statement to send on the connection...
        Statement stmt = connection.createStatement();

//        // Execute the statement and receive the result...
//        stmt.executeUpdate("INSERT INTO users VALUES (\"JIMMY\",\"QWERTY\")");
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");
//
//        // And then display the result!
        System.out.println("user_id\t\tpassword");
        while (rs.next()) {
            String userID = rs.getString("user_id");
            String password = rs.getString("password");
            System.out.println(userID + "\t\t" + password);
        }
    }

    public static void writeData(){
        System.out.println("Saved data.");
    }
}