package model;

public class User {

    // Attributes
    private int idUser;
    private String email;
    private String password;
    private String role;


    // Constructors
    public User (int idUser, String email, String password,String role){
        this.idUser = idUser;
        this.email = email;
        this.password = password;
        this.role = role;

    }

    public User (String email, String password, String role){
        this(0,email,password,role);
    }
     // Getters & Setters

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

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
}

