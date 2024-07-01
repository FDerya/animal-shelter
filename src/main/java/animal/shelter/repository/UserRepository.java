package animal.shelter.repository;

import animal.shelter.dao.JdbcUserDAO;
import animal.shelter.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class UserRepository {

    private JdbcUserDAO jdbcUserDAO;
    private UserRepository(JdbcUserDAO jdbcUserDAO){
        this.jdbcUserDAO = jdbcUserDAO;


    }

    public void saveUser(User user){
        jdbcUserDAO.save(user);

    }

    public Optional<User> findUserById(int idUser){
        return jdbcUserDAO.findUserById(idUser);

    }
    public List<User> findAllUser(){
        return jdbcUserDAO.findAllUser();

    }
    public void updateUser(User user){
        jdbcUserDAO.update(user);

    }
    public void deleteUser(User user){
        jdbcUserDAO.delete(user);
    }

            }
