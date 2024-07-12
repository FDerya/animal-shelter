package animal.shelter.controller;

import animal.shelter.model.LoginDTO;
import animal.shelter.model.User;
import animal.shelter.service.LoginService;
import animal.shelter.utility.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class LoginController {

    private LoginService loginService;
    private JWTUtil jwtUtil;

    @Autowired
    public LoginController(LoginService loginService, JWTUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody LoginDTO loginDTO) {
        LoginDTO responseDTO = loginService.login(loginDTO);
        if (responseDTO != null) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        user.setRole("user");
        String result = loginService.register(user);
        if (result.equals("Registration successful")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }



}
