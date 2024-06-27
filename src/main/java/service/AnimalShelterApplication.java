package service;

import dao.DBAccess;
import dao.UserDAO;
import model.User;

public class AnimalShelterApplication {

    public static  void main(String [] args){


        DBAccess dbAccess = new DBAccess("animalshelter", "root", "ftm7874tr");
        // open dbAccess
        dbAccess.openConnection();
        UserDAO userDAO = new UserDAO(dbAccess);

        // Test toevoeging User
        User newUser = new User("alice@example.com", "123", "admin");
        userDAO.storeUser(newUser);


        // Test om User met id op te halen
        User retrievedUser = userDAO.getUserById(newUser.getIdUser());
        System.out.println("Retrieved User: " + retrievedUser);

        // User update test
        retrievedUser.setEmail("alice_new@example.com");
        userDAO.updateUser(retrievedUser);
        System.out.println("Updated User: " + userDAO.getUserById(retrievedUser.getIdUser()));

        // Test om alle Users op te halen
        System.out.println("All Users:");
        for (User user : userDAO.getAllUsers()) {
            System.out.println(user.getEmail());
        }

        // Test delete User
        userDAO.deleteUser(retrievedUser.getIdUser());
        System.out.println("User deleted");

        dbAccess.closeConnection();
    }
    }


