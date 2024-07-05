package animal.shelter.controller;

import animal.shelter.model.LoginDTO;
import animal.shelter.model.RegisterDTO;
import animal.shelter.service.LoginService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
        loginService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registratie geslaagd");
    }

    @PostMapping("/user-login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        String result = loginService.login(loginDTO);
        return getResponseEntity(loginDTO, result);
    }

    @PostMapping("/admin-login")
    public ResponseEntity<?> adminLogin(@RequestBody LoginDTO loginDTO) {
        String result = loginService.adminLogin(loginDTO);
        return getResponseEntity(loginDTO, result);
    }


    @NotNull
    private ResponseEntity<?> getResponseEntity(@RequestBody LoginDTO loginDTO, String result) {
        if (result != null) {
            String responseBody = "Welkom " + loginDTO.getEmail();
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Authorization", "JWT " + result)
                    .body(responseBody);
        } else {
            String message = "Login niet geslaagd";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
    }
}
