package dao;

import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO {
    public UserDAO(DBAccess dbAccess) {
        super(dbAccess);
    }

    public void storeUser(User user) {
        String sql = "INSERT INTO user (email, password, role) VALUES (?, ?, ?)";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            int key = executeInsertStatementWithKey();
            user.setIdUser(key);
        } catch (SQLException sqlException) {
            System.out.println("SQL error: " + sqlException.getMessage());
        }
    }

    // Read method
    public User getUserById(int userId) {
        String sql = "SELECT * FROM user WHERE id = ?";
        User result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                result = new User(userId, email, password, role);
            } else {
                System.out.println("User with this ID does not exist");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error: " + sqlException.getMessage());
        }
        return result;
    }
     // Get user met password
    public User getUserByPassword(String password) {
        String sql = "SELECT * FROM user WHERE password = ?";
        User result = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, password);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                int idUser = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                result = new User(idUser, email, password, role);

            } else {
                System.out.println("User with this password does not exist");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error: " + sqlException.getMessage());
        }
        return result;
    }


    // Update method
    public void updateUser(User user) {
        String sql = "UPDATE user SET email = ?, password = ?, role = ? WHERE id = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setInt(4, user.getIdUser());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println("SQL error: " + sqlException.getMessage());
        }
    }

    // Delete method
    public void deleteUser(int userId) {
        String sql = "DELETE FROM user WHERE id = ?";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, userId);
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println("SQL error: " + sqlException.getMessage());
        }
    }

    // Get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                int idUser = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                User user = new User(idUser, email, password, role);
                users.add(user);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error: " + sqlException.getMessage());
        }
        return users;
    }
}



