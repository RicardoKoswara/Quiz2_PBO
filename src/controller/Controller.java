package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.User;
import javax.swing.JOptionPane;

public class Controller {

    DatabaseHandler conn = new DatabaseHandler();

    public Controller() {
    }
    
    public boolean insertNewUser(User user) {
        conn.connect();
        String query = "INSERT INTO `user`(`name`, `email`,`password`) VALUES ('?','?','?')";
        try {
            PreparedStatement stmt = conn.conn.prepareStatement(query);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();

            return (false);
        }
    }

    public boolean getUser(User user) {
        String query = " ";
        try {
            PreparedStatement stmt = conn.conn.prepareStatement(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
