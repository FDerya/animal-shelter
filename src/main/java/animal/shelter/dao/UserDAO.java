package animal.shelter.dao;

import animal.shelter.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    void save(User user);

    Optional<User> findUserById(int idUser);

    List<User> findAllUser();

    void delete(User user);


}
