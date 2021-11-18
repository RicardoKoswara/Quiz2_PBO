package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DatabaseHandler {

    public Connection conn;
    // private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://localhost/quiz2_PBO";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private Connection logOn() {
        try {
            // Load JDBC Driver
            Class.forName(DRIVER);

            // Make Object Connection
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ex) {

            // Handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getLocalizedMessage());
            JOptionPane.showMessageDialog(null, "Error Ocurred when login" + ex);
        }
        return conn;
    }

    private void logOff() {
        try {

            // Close connection
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error Ocurred when login" + ex);
        }
    }

    public void connect() {
        try {
            conn = logOn();
        } catch (Exception ex) {
            System.out.println("Error occured when connecting to database");
        }
    }

    public void disconnect() {
        try {
            logOff();
        } catch (Exception ex) {
            System.out.println("Error occured when connecting to database");
        }
    }
}

