package animal.shelter.repository;

import animal.shelter.dao.JdbcUserDAO;
import animal.shelter.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private JdbcUserDAO jdbcUserDAO;

    @Autowired
    public UserRepository(JdbcUserDAO jdbcUserDAO) {
        this.jdbcUserDAO = jdbcUserDAO;


    }


    public void saveUser(User user) {
        jdbcUserDAO.saveUser(user);

    }

    public Optional<User> findUserById(int idUser) {
        return jdbcUserDAO.findUserById(idUser);

    }

    public List<User> findAllUser() {
        return jdbcUserDAO.findAllUser();

    }

    public void updateUser(User user) {
        jdbcUserDAO.updateUser(user);

    }

    public void deleteUser(User user) {
        jdbcUserDAO.deleteUser(user);
    }

    // Login Service
    public Optional<User> findByEmail(String email) {
        return jdbcUserDAO.findByEmail(email);  


    }
}