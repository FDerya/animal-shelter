package animal.shelter.controller;

import animal.shelter.model.User;
import animal.shelter.service.UserService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/user")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<String> blockUser(@PathVariable int id){
//        boolean result = userService.blockUser(id);
//        if(result){
//            return ResponseEntity.ok("User found successfully");
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//    }
//


    // deneme 1
    @GetMapping("/email/{idUser}")
    public ResponseEntity<String > getEmailByUserId (@PathVariable int idUser){
        Optional<String> email = userService.findEmailById(idUser);
        return email.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));

    }


    }


