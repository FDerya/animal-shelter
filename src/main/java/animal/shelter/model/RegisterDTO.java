package animal.shelter.model;

public class RegisterDTO {

    private String email;
    private String password;

    private String role; // user of admin

    public RegisterDTO(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
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


}
