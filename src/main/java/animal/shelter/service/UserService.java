package animal.shelter.service;

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
}
