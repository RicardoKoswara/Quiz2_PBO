package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.*;

public class Controller {

    DatabaseHandler conn = new DatabaseHandler();

    public Controller() {
    }

    public boolean insertNewUser(User user) {
        conn.connect();
        String query = "INSERT INTO `users`(`name`, `email`, `password`, `id_category`) VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = conn.conn.prepareStatement(query);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getIdCategory());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();

            return (false);
        }
    }

    public User getUser(String email, String password) {
        conn.connect();
        String query = "SELECT `id_user`, `name`, `email`, `password`, `id_category` FROM `users` WHERE email='" + email
                + "' AND password='" + password + "'";
        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            User user = new User();

            while (result.next()) {
                user.setId(result.getInt("id_user"));
                user.setName(result.getString("name"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setIdCategory(result.getInt("id_category"));
            }

            if (user.getId() != 0) {
                return user;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<CategoryUser> selectCategoryUser() {
        conn.connect();
        String query = "SELECT `id_category`, `name` FROM `categoryuser` WHERE 1";
        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            ArrayList<CategoryUser> categories = new ArrayList<>();

            while (result.next()) {
                CategoryUser categoryUser = new CategoryUser();
                categoryUser.setIdCategory(result.getInt("id_category"));
                categoryUser.setCategoryName(result.getString("name"));
                categories.add(categoryUser);
            }
            return categories;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> selectUserByCategory(String category) {
        conn.connect();

        CategoryUser categoryUser = selectCategoryUserByName(category);
        String query = "SELECT `id_user`, `name`, `email`, `password`, `id_category` FROM `users` WHERE id_category='"
                + categoryUser.getIdCategory() + "'";
        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            ArrayList<User> users = new ArrayList<>();

            while (result.next()) {
                User user = new User();
                user.setId(result.getInt("id_user"));
                user.setName(result.getString("name"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setIdCategory(result.getInt("id_category"));
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

  

    public CategoryUser selectCategoryUserByName(String name) {
        conn.connect();
        String query = "SELECT `id_category`, `name` FROM `categoryuser` WHERE name='" + name + "'";
        try {
            Statement stmt = conn.conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            CategoryUser categoryUser = new CategoryUser();

            while (result.next()) {
                categoryUser.setIdCategory(result.getInt("id_category"));
                categoryUser.setCategoryName(result.getString("name"));
            }

            return categoryUser;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateUser(int idUser, User user) {
        conn.connect();
        String query = "UPDATE `users` SET `name`=?,`email`=?,`password`=?,`id_category`=? WHERE `id_user`=?";
        try {
            PreparedStatement stmt = conn.conn.prepareStatement(query);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getIdCategory());
            stmt.setInt(5, idUser);
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public boolean deleteUser(int idUser) {
        conn.connect();
        String query = "DELETE FROM `users` WHERE `id_user`=?";
        try {
            PreparedStatement stmt = conn.conn.prepareStatement(query);
            stmt.setInt(1, idUser);
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
}
