package animal.shelter.model;

public class LoginDTO {

    private String email;
    private String password;
    private String role; // user or admin

    private String token;


    public LoginDTO(String email, String password, String role, String token) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
