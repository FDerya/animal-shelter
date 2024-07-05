package animal.shelter.service;

import animal.shelter.model.LoginDTO;
import animal.shelter.model.RegisterDTO;
import animal.shelter.model.User;
import animal.shelter.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(LoginDTO loginDTO) {
        Optional<User> userOptional = userRepository.findByEmail(loginDTO.getEmail());
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(loginDTO.getPassword())) {
            return "Success!";
        }
        return null;
    }

    public String adminLogin(LoginDTO loginDTO) {
        Optional<User> userOptional = userRepository.findByEmail(loginDTO.getEmail());
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(loginDTO.getPassword()) && "admin".equals(userOptional.get().getRole())) {
            return "Success!";
        }
        return null;
    }

    public void register(RegisterDTO registerDTO) {
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setRole(registerDTO.getRole());
        userRepository.saveUser(user);
    }
}
