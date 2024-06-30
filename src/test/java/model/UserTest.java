package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private  User user;

    @BeforeEach
    void setUp() {
        user = new User("fatma.tatar@com", "300", "admin");

    }

    @Test
    void getEmail() {
        assertEquals("fatma.tatar@com", user.getEmail());

    }

    @Test
    void setEmail() {
        user.setEmail("alice@example.com");
        assertEquals("alice@example.com", user.getEmail());
    }

    @Test
    void getPassword() {
    }

    @Test
    void getRole() {
    }
}