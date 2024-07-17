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

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Registers a new user if the email is not already in use.
    public String register(LoginDTO loginDTO) {
        if (userRepository.findByEmail(loginDTO.getEmail()).isPresent()) {
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

    // Saves a new user to the repository.
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    // Finds a user by their ID.
    public Optional<User> findUserById(int idUser) {
        return userRepository.findUserById(idUser);
    }

    // Retrieves all users from the repository.
    public List<User> findAllUser() {
        return userRepository.findAllUser();
    }

    // Updates an existing user in the repository.
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    // Deletes a user from the repository.
    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }
}
