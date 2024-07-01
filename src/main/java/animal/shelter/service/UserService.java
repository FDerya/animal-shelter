package animal.shelter.service;

import animal.shelter.model.User;
import animal.shelter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired

  public UserService(UserRepository userRepository){
      this.userRepository = userRepository;
  }
    public void saveUser(User user){
      userRepository.saveUser(user);

    }
    public Optional<User> findUserById(int idUser){
     return userRepository.findUserById(idUser);


    }

    public List<User>  findAllUser(){
     return userRepository.findAllUser();
    }
    public void updateUser(User user){
      userRepository.updateUser(user);
    }
    public void   deleteUser(User user){
      userRepository.deleteUser(user);
    }
}
