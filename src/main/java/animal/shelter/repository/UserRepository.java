package animal.shelter.repository;

import animal.shelter.dao.JdbcUserDAO;
import animal.shelter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcUserDAO jdbcUserDAO;

    @Autowired
    public UserRepository(JdbcUserDAO jdbcUserDAO) {
        this.jdbcUserDAO = jdbcUserDAO;
    }

    // Saves a new user to the database.
    public void saveUser(User user) {
        jdbcUserDAO.saveUser(user);
    }

    // Finds a user by their ID.
    public Optional<User> findUserById(int idUser) {
        return jdbcUserDAO.findUserById(idUser);
    }

    // Retrieves all users from the database.
    public List<User> findAllUser() {
        return jdbcUserDAO.findAllUser();
    }

    // Updates an existing user in the database.
    public void updateUser(User user) {
        jdbcUserDAO.updateUser(user);
    }

    // Deletes a user from the database.
    public void deleteUser(User user) {
        jdbcUserDAO.deleteUser(user);
    }

    // Finds a user by their email address.
    public Optional<User> findByEmail(String email) {
        return jdbcUserDAO.findByEmail(email);
    }
}