package animal.shelter.controller;

import animal.shelter.model.User;
import animal.shelter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create en Update
    @PutMapping("/{idUser}")
    ResponseEntity<User> createUser(@RequestBody User user, @PathVariable("idUser") int idUser) {
        Optional<User> optionalUser = userService.findUserById(idUser);
        if (optionalUser.isPresent()) {
            userService.updateUser(user);
            return ResponseEntity.ok().body(user);
        } else {
            userService.saveUser(user);
            URI uri = URI.create(String.format("http://localhost:8080/user/%d", user.getIdUser()));
            return ResponseEntity.created(uri).body(user);
        }


    }

    //Read
    @GetMapping("/{idUser}")
    @ResponseBody
    User getUserById(@PathVariable("idUser") int idUser) {
        Optional<User> result = userService.findUserById(idUser);
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Geen User gevonden");
        } else {
            User user = result.get();
            user.setIdUser(idUser);
            return user;
        }
    }

    @GetMapping
    public List<User> getAll() {
        return userService.findAllUser();
    }


    @DeleteMapping("/delete/{code}")
    public @ResponseBody void deleteUser(@PathVariable int code) {
        User userDelete = getUserById(code);
        userService.deleteUser(userDelete);
    }


}













