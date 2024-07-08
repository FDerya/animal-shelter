package animal.shelter.service;

import animal.shelter.model.LoginDTO;
import animal.shelter.model.User;
import animal.shelter.repository.AdoptionRequestRepository;
import animal.shelter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AdoptionRequestRepository adoptionRequestRepository;

    @Autowired
    public UserService(UserRepository userRepository, AdoptionRequestRepository adoptionRequestRepository) {
        this.userRepository = userRepository;
        this.adoptionRequestRepository = adoptionRequestRepository;
    }
    // LoginDTO --> getRole()
    public String register(LoginDTO loginDTO){
        if(userRepository.findByEmail(loginDTO.getEmail()).isPresent()){
            return "User already exists";
        }

        User newUser = new User();
        newUser.setEmail(loginDTO.getEmail());
        newUser.setPassword(loginDTO.getPassword());
        newUser.setRole(loginDTO.getRole());

        // Save the new user to the database
        userRepository.saveUser(newUser);

        return "Registration successful";
    }

    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    public Optional<User> findUserById(int idUser) {
        return userRepository.findUserById(idUser);
    }

    public List<User> findAllUser() {
        return userRepository.findAllUser();
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

//    public Optional<User> findUserByEmail(String email) {
//        return null;
//    }
}
