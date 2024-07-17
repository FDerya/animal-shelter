package animal.shelter.model;

public class LoginDTO {

    private String email;
    private String password;
    private String role; // user or admin
    private String token;

    /**
     * Constructs a new LoginDTO with the specified details.
     *
     * @param email    the email of the user
     * @param password the password of the user
     * @param role     the role of the user (user or admin)
     * @param token    the token for authentication
     */
    public LoginDTO(String email, String password, String role, String token) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.token = token;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
