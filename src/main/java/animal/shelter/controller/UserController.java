//package animal.shelter.controller;
//
//import animal.shelter.model.User;
//import animal.shelter.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.net.URI;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/user")
//public class UserController {
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    // Endpoint to create or update a user.
//    // If a user with the given ID exists, it updates the user.
//    // Otherwise, it creates a new user.
//    @PutMapping("/{idUser}")
//    public ResponseEntity<User> createOrUpdateUser(@RequestBody User user, @PathVariable("idUser") int idUser) {
//        Optional<User> optionalUser = userService.findUserById(idUser);
//        // If userID is present, update the user details
//        if (optionalUser.isPresent()) {
//            userService.updateUser(user);
//            return ResponseEntity.ok().body(user);
//        }
//        // If userID isn't present, create a new  user
//        else {
//            userService.saveUser(user);
//            URI uri = URI.create(String.format("http://localhost:8080/user/%d", user.getIdUser()));
//            return ResponseEntity.created(uri).body(user);
//        }
//    }
//
//    //  Endpoint to retrieve a user by their ID.
//    @GetMapping("/{idUser}")
//    public User getUserById(@PathVariable("idUser") int idUser) {
//        Optional<User> result = userService.findUserById(idUser);
//        // If the result comes back empty, show the error message "User not Found"
//        // Otherwise return the user details
//        if (result.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Geen User gevonden");
//        } else {
//            User user = result.get();
//            user.setIdUser(idUser);
//            return user;
//        }
//    }
//
//    //  Endpoint to retrieve all users.
//    @GetMapping
//    public List<User> getAll() {
//        return userService.findAllUser();
//    }
//
//    // Endpoint to delete a user by their ID.
//    @DeleteMapping("/delete/{code}")
//    public @ResponseBody void deleteUser(@PathVariable int code) {
//        User userDelete = getUserById(code);
//        userService.deleteUser(userDelete);
//    }
//}